package com.christianoette.ssedemo.controller;

import java.time.Duration;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SSEController {
  @GetMapping("/stock/transaction")
  public Flux<String> stockTransactionEvents() {
    return randomMessage();
  }

  Flux<String> randomMessage() {
    Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
    Flux<String> stockTransactionFlux = Flux.fromStream(Stream.of(String.valueOf(new Random().nextInt())));
    return Flux.zip(interval, stockTransactionFlux).map(Tuple2::getT2);
  }

  String getRandomUser() {
    String users[] = "TamNT,TamCT,HieuPV,HieuTM,ThanhD".split(",");
    return users[RandomGenerator.getDefault().nextInt()];
  }
}
