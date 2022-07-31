package com.alejandro.projectreactor.hot_publishers;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class HotPublisher {

    public static void main(String[] args) {
            var coldMovieStream = Flux.fromStream(HotAndColdPublisher::emmitMovie)
                    .delayElements(Duration.ofSeconds(2))
                            .publish().refCount(1);  //To share publish().refCount(int)
            coldMovieStream.subscribe(Utils.subscriber());
            Utils.sleepInSeconds(15L);
            coldMovieStream.subscribe(Utils.subscriber("Carl"));

            Utils.sleepInSeconds(15L);
    }

    public static Stream<String> emmitMovie() {
        System.out.println("Start streaming");
        return Stream.of(
                "-",
                "--",
                "---",
                "----",
                "----",
                "----->"
        );
    }
}
