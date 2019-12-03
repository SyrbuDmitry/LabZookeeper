package zoo.anon;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;
import akka.stream.ActorMaterializer;

public class ConfifStorageActor extends AbstractActor {
    private ActorSystem system;
    private ActorMaterializer materializer;
    ConfifStorageActor(ActorSystem system, ActorMaterializer materializer){
        this.system = system;
        this.materializer = materializer;
    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .build();
    }
}
