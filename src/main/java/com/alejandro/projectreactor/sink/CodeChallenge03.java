package com.alejandro.projectreactor.sink;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CodeChallenge03 {

    public static void main(String[] args) {
        FileReaderService frs = new FileReaderService();
        frs.read(Paths.get("srs/main/resources/code_challenge03.txt")).subscribe();
    }
}


class FileReaderService {

    private Callable<BufferedReader> openReader (Path path) {
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
        return  (br, synk) -> {
            try {
                Optional<String> line = Optional.of(br.readLine());
                if (line.isPresent()) {
                    synk.next(line.get());
                } else {
                    synk.complete();
                }
            } catch (IOException e) {
               synk.error(e);
            }
            return  br ;
        };
    }

    private Consumer<BufferedReader> closeReader() {
        return  br -> {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
     public Flux<String> read (Path path) {
         return  Flux.generate(
                 openReader(path),
                 read(),
                 closeReader()
         );
     }
}