package zoo.anon;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ServerInitiator {
    private ZooKeeper zoo;
    public void createServer(String host, String port) throws KeeperException,InterruptedException{
        zoo.create("/servers/"+host+":"+port,
                "data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

    }

}
