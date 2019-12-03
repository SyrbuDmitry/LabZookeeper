package zoo.anon;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class AnonApp {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        ActorSystem system = ActorSystem.create("lab6");
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        ZooWatcher zooWatcher = new ZooWatcher();
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 3000, zooWatcher);
        ServerInitiator init = new ServerInitiator(zoo);
        init.createServer(args[0],args[1]);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute(RouteActor).flow(system, materializer);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8085),
                materializer
        );
        System.out.println("Server online at http://localhost:8085/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

}
}