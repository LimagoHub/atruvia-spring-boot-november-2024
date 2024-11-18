package de.atruvia.consoledemo.runner;

import de.atruvia.consoledemo.math.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.inject.Named;


@Component
@Order(100)
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    @Qualifier("secure")
    private final Calculator calculator;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(calculator.add(3,4));
    }
}
