package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.ZooKeeper;

public class ConfigStorageActor extends AbstractActor {
    private void createZoo() throws Exception{
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181",3000,this);
    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
