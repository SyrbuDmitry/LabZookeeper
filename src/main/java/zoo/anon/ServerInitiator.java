package zoo.anon;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import scala.concurrent.Future;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static akka.http.javadsl.server.Directives.*;

public class ServerInitiator {

    private ZooKeeper zoo;
    private Http http;
    private ActorRef storage;
    ServerInitiator(ZooKeeper zoo,ActorRef storage, Http http){
        this.zoo = zoo;
        this.storage = storage;
        this.http = http;
    }
    public void createServer(String host, String port) throws KeeperException,InterruptedException{
        String path = zoo.create("/servers/"+host+":"+port,
                port.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        storage.tell(new AddServer(host+":"+port),ActorRef.noSender());
        System.out.println(path+" Connected");
    }

    public Route createRoute() {
        return
                route(
                        pathSingleSlash(() ->
                                parameter("url", (url) ->
                                        parameter("count", (count) ->{
                                            System.out.println(url+" "+count);
                                            return  handleRequest(new Request(url,count));
                                        })
                                )
                        )
                );
    }

    private Route handleRequest(Request r){
        System.out.println(r.url+" "+r.count);
        return r.count==0? completeWithFuture(fetch(r.url)) :
                completeWithFuture(sendRequestToRandomServer(new Request(r.url,r.count-1)));
    }


    //отправка url
    private CompletionStage<HttpResponse> fetch(String url) {
        System.out.println("FETCH "+url);
        return http.singleRequest(HttpRequest.create("http://localhost:8085"));
    }

    //отправка рандому
    private CompletionStage<HttpResponse> sendRequestToRandomServer(Request r){
        return Patterns.ask(storage,new GetServer(), Duration.ofSeconds(5))
                    .thenApply(o->(String)o)
                    .thenCompose(re->fetch(serverRequestBuilder(re,r)));
    }

    //запрос серверу
    private String serverRequestBuilder(String servUrl,Request r){
        return "http://"+servUrl+"/?url="+r.url+"&count="+r.count;
    }
}
