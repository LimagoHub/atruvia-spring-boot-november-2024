package de.atruvia.consoledemo.math;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("logger")
@RequiredArgsConstructor
public class CalculatorLogger implements Calculator {

    @Qualifier("impl")
    private final Calculator calculator;

    //public CalculatorLogger(@Qualifier("impl") final Calculator calculator) {
    //    this.calculator = calculator;
    //}

    @Override
    public double add(final double a, final double b) {
        System.out.println("Add wurde gerufen");
        return calculator.add(a, b);
    }
    @Override
    public double sub(final double a, final double b) {
        return calculator.sub(a, b);
    }
}
