package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;

public class ConfigStorageActor extends AbstractActor {
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
