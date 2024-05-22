package org.containercraft.messenger;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SpringBootApplication

public class MicroReactiveMessengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroReactiveMessengerApplication.class, args);
    }

}


@Slf4j
class Test {
    public static void main(String[] args) {
        Subscriber<String> subscriber = new Subscriber<String>() {
            volatile Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(String string) {
                subscription.request(1);
                System.out.println(string);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };


        Flux<String> stringFlux = Flux.just("Hi","Hi","Hi","Hi");
        stringFlux.subscribe(subscriber);
        Flux<String> stringFlux1 = Flux.just();

        Mono<String> mono = stringFlux1.single();




        stringFlux1.single().subscribe(e -> {},e -> {System.out.println("It is ok");});

        Flux.just(3, 5, 7, 9, 11, 15, 17)
                .any(e -> e % 2 == 0)
                .subscribe(hasEvens -> log.info("Has evens: {}", hasEvens));

    }
}
