package com.alejandro.projectreactor.batching;

import com.alejandro.projectreactor.Utils;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CodeChallenge {

    //science fiction, fantasy, thriller

    public static void main(String[] args) {

        Set<String> allowCategories = Set.of(
                "Science fiction",
                "fantasy",
                "Suspense/Thriller"
        );

        bookStream()
                .filter(book -> allowCategories.contains(book.getCategory()))
                .buffer(Duration.ofSeconds(5))
                .map(CodeChallenge::calculateRevenue)
                .subscribe(Utils.subscriber());

        Utils.sleepInSeconds(60L);

    }

    private static RevenueReport calculateRevenue(List<Book> books) {
        Map<String, DoubleSummaryStatistics> revenueAmount = books
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Book::getCategory,
                                Collectors.summarizingDouble(
                                        Book::getPrice)
                        ));

        return new RevenueReport(revenueAmount);
    }

    private static  Flux<Book> bookStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new Book());
    }

}

@Getter
@ToString
class Book {
    private String title;
    private Double price;
    private String category;

    Book () {
        var randomBook = Utils.getFaker().book();
        this.title = randomBook.title();
        this.category = randomBook.genre();
        this.price = Double.parseDouble(Utils.getFaker().commerce().price());
    }
}

@Data
class RevenueReport {

    private LocalDateTime date;
    private Map<String, Double> revenue;

    public RevenueReport(Map<String, DoubleSummaryStatistics> revenues) {
        this.revenue = new HashMap<>();
        revenues.forEach((k, v) -> {
            this.revenue.put(k, v.getSum());
        });
        this.date = LocalDateTime.now();
    }
}
