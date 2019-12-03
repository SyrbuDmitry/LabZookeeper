package zoo.anon;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;

public class AnonApp {
    public static void main(String[] args ){
        ActorSystem system = ActorSystem.create("lab5");
        final ActorMaterializer materializer = ActorMaterializer.create(system);

    }
}
