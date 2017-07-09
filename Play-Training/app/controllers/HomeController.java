package controllers;

import actors.ChatRoomActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.javadsl.Flow;
import com.fasterxml.jackson.databind.JsonNode;
import org.reactivestreams.Publisher;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        ActorSystem actorSystem = ActorSystem.create();
        ActorRef chatRoomActor = actorSystem.actorOf(Props.create(ChatRoomActor.class));
        final Publisher<JsonNode> publisher = new Publisher<>();



        return ok(index.render("Your new application is ready."));
    }


    public WebSocket ws(){
        return WebSocket.Json.accept((Http.RequestHeader requestHeader) -> {
            akka.stream.javadsl.Source<JsonNode, ?> source = publisher.register();
            akka.stream.javadsl.Sink<JsonNode, NotUsed> sink = Sink.actorRef(chatRoomeActor, "Sucsess");
            Flow<JsonNode, JsonNode, NotUsed> flow = Flow.fromSinkAndSource(sink, source);
           return flow;
        });
    }
}
