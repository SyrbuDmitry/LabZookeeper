package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class ConfigStorageActor extends AbstractActor {
    private ZooWatcher zooWatcher;
    private ZooKeeper zoo;


    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
