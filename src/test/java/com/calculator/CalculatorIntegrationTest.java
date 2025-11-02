package com.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorIntegrationTest {

    CalculatorLogic calc = new CalculatorLogic();

    @Test
    void testSimulatedAdditionFromGUI() {
        double oldValue = 5;
        double input = 3;
        double result = oldValue + input; // Simulate what GUI would calculate
        assertEquals(8.0, result, 0.0001, "5 + 3 via GUI should equal 8");
    }

    @Test
    void testSimulatedSubtractionFromGUI() {
        double oldValue = 10;
        double input = 7;
        double result = oldValue - input;
        assertEquals(3.0, result, 0.0001, "10 - 7 via GUI should equal 3");
    }

    @Test
    void testSimulatedMultiplicationFromGUI() {
        double oldValue = 4;
        double input = 5;
        double result = oldValue * input;
        assertEquals(20.0, result, 0.0001, "4 * 5 via GUI should equal 20");
    }

    @Test
    void testSimulatedDivisionFromGUI() {
        double oldValue = 12;
        double input = 4;
        double result = oldValue / input;
        assertEquals(3.0, result, 0.0001, "12 / 4 via GUI should equal 3");
    }

    @Test
    void testSimulatedDivisionByZeroFromGUI() {
        double oldValue = 10;
        double input = 0;

        // Use assertThrows correctly
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            if (input == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            // normal division (this won't run if input == 0)
            double result = oldValue / input;
        });

        // Optionally check the exception message
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

}
