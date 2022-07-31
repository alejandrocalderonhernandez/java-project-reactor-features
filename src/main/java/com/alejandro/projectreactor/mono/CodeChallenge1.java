package com.alejandro.projectreactor.mono;

import com.alejandro.projectreactor.Utils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CodeChallenge1 {

    static IFileService fileService = new FileService("src/main/resources/section01.txt");

    public static void main(String[] args) {
       fileService.getText()
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );

      fileService.writeText(" New line")
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );

      fileService.deleteText()
              .subscribe(
                      Utils.onNext(),
                      Utils.onError(),
                      Utils.onComplete()
              );


    }
}

class FileService implements IFileService {

    private final String path;

    public FileService(String path) {
        this.path = path;
    }

    @Override
    public Mono<String> getText() {
        Path filePath = Paths.get(path);
        return Mono.fromSupplier( () -> {
            try {
                return Files.readString(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error to read text");
            }
        });
    }

    @Override
    public Mono<String> writeText(String text) {
        Path filePath = Paths.get(path);
        return Mono.fromSupplier( () -> {
            try {
                Files.writeString(filePath, text, StandardOpenOption.APPEND);
                return Files.readString(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error to read text");
            }
        });
    }

    @Override
    public Mono<Void> deleteText() {
        Path filePath = Paths.get(path);
        return Mono.fromRunnable( () -> {
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error to read text");
            }
        });
    }
}

interface IFileService {

    Mono<String> getText();
    Mono<String> writeText(String text);
    Mono<Void>  deleteText();
}