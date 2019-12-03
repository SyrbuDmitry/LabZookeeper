package zoo.anon;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class AnonApp {
    public static void main(String[] args ) throws InterruptedException,IOException,KeeperException{
        ActorSystem system = ActorSystem.create("lab6");
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        ZooWatcher zooWatcher = new ZooWatcher();
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 3000,zooWatcher);
        String path = zoo.create("/s",
                "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(zoo.getChildren("/s", zooWatcher));

    }
}
