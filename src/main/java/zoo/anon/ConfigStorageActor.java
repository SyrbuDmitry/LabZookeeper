package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public static class ConfigStorageActor implements Watcher {
    @Override
//    public Receive createReceive(){
//        return ReceiveBuilder.create()
//                .build();
    public void process(WatchedEvent event){

    }
}
