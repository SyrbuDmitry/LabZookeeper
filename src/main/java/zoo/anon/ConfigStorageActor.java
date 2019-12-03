package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class ConfigStorageActor extends AbstractActor {
    private ZooWatcher zooWatcher;
    private ZooKeeper zoo;
    ConfigStorageActor() throws IOException,KeeperException,InterruptedException{
        System.out.println("CONFIG");
        zooWatcher = new ZooWatcher();
        System.out.println("Watcher");
        String zkConnString = "<zknode1>:2181,<zknode2>:2181,<zknode3>:2181";
        System.out.println("NODES");
        zoo = new ZooKeeper(zkConnString,3000,zooWatcher);
        System.out.println("ZOOKEEPER");
        List<String> zkNodes = zoo.getChildren("/", true);
        System.out.println("GETCHILDREN");
        for(String node : zkNodes) {
            System.out.println(node);
        }

    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
