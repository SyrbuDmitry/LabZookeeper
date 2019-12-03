package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ConfigStorageActor extends AbstractActor {
    private ZooWatcher zooWatcher;
    private ZooKeeper zoo;
    ConfigStorageActor() throws IOException{
        zooWatcher = new ZooWatcher();
        String zkConnString = "<zknode1>:2181,<zknode2>:2181,<zknode3>:2181";
        zoo = new ZooKeeper(zkConnString,3000,zooWatcher);
    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
