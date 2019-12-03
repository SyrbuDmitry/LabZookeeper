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
    ConfigStorageActor() throws IOException,KeeperException,InterruptedException {
        System.out.println("CONFIG");
        zooWatcher = new ZooWatcher();
        System.out.println("Watcher");
        String zkConnString = "<zknode1>:2181,<zknode2>:2181,<zknode3>:2181";
        System.out.println("NODES");
        zoo = new ZooKeeper(zkConnString, 3000, zooWatcher);
        zoo.create("/servers/s",
                "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        List<String> servers = zoo.getChildren("/servers", zooWatcher);
        for (String s : servers) {
            byte[] data = zoo.getData("/servers/" + s, false, null);
            System.out.println("server " + s + " data=" + new String(data));
        }
    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
