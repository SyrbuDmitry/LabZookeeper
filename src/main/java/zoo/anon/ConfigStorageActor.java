package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfigStorageActor extends AbstractActor {
    final Http http = Http.get(context().system());
    private ZooWatcher zooWatcher;
    private ZooKeeper zoo;
    private ArrayList<String> servers;
    private Random random = new Random();


    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .match(Request.class,r->{})
                .match(GetServer.class,r->{
                    sender().tell(getRandomServer(),ActorRef.noSender());
                })
                .build();
    }
    private String getRandomServer(){
        return servers.get(random.nextInt(servers.size()));

    }
}
