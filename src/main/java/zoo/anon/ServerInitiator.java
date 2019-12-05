package zoo.anon;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import scala.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class ServerInitiator {
    private ZooKeeper zoo;
    ActorRef storage;
    ServerInitiator(ZooKeeper zoo,ActorRef storage){
        this.zoo = zoo;
        this.storage = storage;
    }
    public void createServer(String host, String port) throws KeeperException,InterruptedException{
        String path = zoo.create("/servers/"+host,
                port.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println(path+" Connected");
    }
    public Route createRoute() {
        return
                route(
                        pathSingleSlash(() ->
                                parameter("url", (url) ->
                                        parameter("count", (count) ->{
                                                    handleRequest();
                                        }
                                        )
                                )
                        )
                );
    }

    private Route handleRequest(){
        return complete("s");
    }
}
