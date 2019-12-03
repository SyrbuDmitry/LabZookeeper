package zoo.anon;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;

public class AnonApp {
    public static void main(String[] args ){
        ActorSystem system = ActorSystem.create("lab5");
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        ActorRef configActor = system.actorOf(Props.create(ConfigStorageActor.class));
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181",3000,this);
    }
}
