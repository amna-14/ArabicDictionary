package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdditionSubtractionTest {

    CalculatorLogic calc = new CalculatorLogic();

    // Addition Tests
    @Test
    void testAdditionWithPositiveNumbers() {
        assertEquals(9.0, calc.add(4, 5), 0.0001, "4 + 5 should equal 9");
    }

    @Test
    void testAdditionWithNegativeNumbers() {
        assertEquals(-3.0, calc.add(-1, -2), 0.0001, "-1 + -2 should equal -3");
    }

    @Test
    void testAdditionWithMixedNumbers() {
        assertEquals(2.0, calc.add(5, -3), 0.0001, "5 + -3 should equal 2");
    }

    @Test
    void testAdditionBoundaryMaxValue() {
        assertEquals(Double.MAX_VALUE, calc.add(Double.MAX_VALUE, 0), 0.0001, "MAX_VALUE + 0 should equal MAX_VALUE");
    }

    @Test
    void testSubtractionWithPositiveNumbers() {
        assertEquals(2.0, calc.subtract(5, 3), 0.0001, "5 - 3 should equal 2");
    }

    @Test
    void testSubtractionWithNegativeResult() {
        assertEquals(-1.0, calc.subtract(2, 3), 0.0001, "2 - 3 should equal -1");
    }

    @Test
    void testSubtractionBoundaryMaxValue() {
        assertEquals(-Double.MAX_VALUE, calc.subtract(0, Double.MAX_VALUE), 0.0001, "0 - MAX_VALUE should equal -MAX_VALUE");
    }
}
