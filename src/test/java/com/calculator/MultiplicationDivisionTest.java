package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiplicationDivisionTest {

    CalculatorLogic calc = new CalculatorLogic();

    // Multiplication Tests
    @Test
    void testMultiplicationPositiveNumbers() {
        assertEquals(15.0, calc.multiply(3, 5), 0.0001, "3 * 5 should equal 15");
    }

    @Test
    void testMultiplicationWithZero() {
        assertEquals(0.0, calc.multiply(7, 0), 0.0001, "7 * 0 should equal 0");
    }

    @Test
    void testMultiplicationWithNegativeNumbers() {
        assertEquals(-20.0, calc.multiply(4, -5), 0.0001, "4 * -5 should equal -20");
    }

    // Division Tests
    @Test
    void testDivisionPositiveNumbers() {
        assertEquals(2.0, calc.divide(10, 5), 0.0001, "10 / 5 should equal 2");
    }

    @Test
    void testDivisionWithNegativeNumbers() {
        assertEquals(-2.0, calc.divide(10, -5), 0.0001, "10 / -5 should equal -2");
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0), "Division by zero should throw ArithmeticException");
    }
}
