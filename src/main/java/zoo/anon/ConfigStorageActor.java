package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ConfigStorageActor extends AbstractActor {
    final Http http = Http.get(context().system());
    private ZooWatcher zooWatcher;
    private ZooKeeper zoo;
    private ArrayList<String> servers;

        private Route handleRequest(Request r){
        return r.count==0 ? completeOKWithFuture(fetch(r.url).thenApply()) :
    }

    CompletionStage<HttpResponse> fetch(String url) {
        final Http http = Http.get(context().system());
        return http.singleRequest(HttpRequest.create(url));
    }

    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .match(Request.class,r->{})
                .build();
    }
}
