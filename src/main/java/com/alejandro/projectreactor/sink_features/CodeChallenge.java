package com.alejandro.projectreactor.sink_features;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;

public class CodeChallenge {

    public static void main(String[] args) {
        SlackRoom slackRoom = new SlackRoom("My-room");

        SlackMember max = new SlackMember("Max");
        SlackMember jake = new SlackMember("Jake");
        SlackMember mike = new SlackMember("Mike");

        slackRoom.joinsRoom(max);
        slackRoom.joinsRoom(jake);
        slackRoom.joinsRoom(mike);

        max.says("Hi there");
        Utils.sleepInSeconds(4L);

        jake.says(":)");
        max.says("Hello guys");

        Utils.sleepInSeconds(4L);

        mike.says("I joined");
    }
}

@Data
class SlackMessage {

    private String sender;
    private String receiver;
    private String message;

    @Override
    public String toString() {
        return String.format(FORMAT, this.sender, this.receiver, this.message);
    }

    private static final String FORMAT = "[%s -> %s]: %s";

}

@Data
class SlackMember {
    private String name;
    private Consumer<String> consumer;

    public SlackMember(String name) {
        this.name = name;
    }

    public void receives(String message) {
        System.out.println(message);
    }

    public void says(String message) {
        this.consumer.accept(message);
    }

    public void setMessage(Consumer<String> consumer) {
        this.consumer = consumer;
    }
}

class SlackRoom {

    private String roomName;
    private Sinks.Many<SlackMessage> messageEmitter;
    private Flux<SlackMessage> roomMessages;

    public SlackRoom(String roomName) {
        this.roomName = roomName;
        this.messageEmitter = Sinks.many().replay().all();
        this.roomMessages = this.messageEmitter.asFlux();
    }

    public void  joinsRoom(SlackMember member) {
        System.out.println(member.getName() + " joined " + this.roomName);
        this.subscribe(member);
        member.setConsumer(message -> this.post(message, member));
    }

    private  void post(String message, SlackMember member) {
        SlackMessage messageModel = new SlackMessage();
        messageModel.setMessage(message);
        messageModel.setSender(member.getName());
        this.messageEmitter.tryEmitNext(messageModel);
    }

    private void subscribe(SlackMember member) {
        this.roomMessages
                .filter(slackMessage -> !slackMessage.getSender().equals(member.getName()))
                .doOnNext(slackMessage -> slackMessage.setReceiver(member.getName()))
                .map(SlackMessage::toString)
                .subscribe(member::receives);
    }

}
