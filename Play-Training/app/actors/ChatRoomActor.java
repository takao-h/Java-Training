package actors;

import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;


public class ChatRoomActor extends UntypedActor {


    @Override
    public void onReceive(Object message)throws Throwable {
        if (message instanceof JsonNode) {
            JsonNode jsonMsseage = (JsonNode) message;
            String type = jsonMsseage.get("type").textValue();


            switch (type) {

                case "json":
                String joinedUser     = jsonMsseage.get("username").asText();
                JsonNode joinToClient = Json.newObject()
                        .put("type", "joined")
                        .put("username", joinedUser);
                    Publisher.broadcast(joinToClient);
                    break;

                case "talk":
                    String talkedUser  = joinMessage.get("username").asText();
                    String chatMessage = joinMessage.get("chatMessage").asText();
                    JsonNode talkClient =
                            Json.newObject()
                            .put("type", "talked")
                            .put("username", talkedUser)
                            .put("chatMessage", chatMessage);
                    publisher.broascast(talkClient);
                    break;

                default:
                    System.out.println("Json Error: type is not allowed");
                    break;
            }
        }else {
            System.out.println("chatRoomActor received not Json");
        }
    }

}