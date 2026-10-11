package ru.nsu.gorlova.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.Expression;

/**
 * Тесты для класса Number.
 */
class NumberTest {

    /**
     * Поток для перехвата вывода.
     */
    private ByteArrayOutputStream out;

    /**
     * Исходный поток вывода.
     */
    private PrintStream originalOut;

    /**
     * Подменяет System.out перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        originalOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    /**
     * Возвращает System.out после каждого теста.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Проверяет вывод нуля.
     */
    @Test
    void testPrintZero() {
        new Number(0).print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет вывод положительного числа.
     */
    @Test
    void testPrintPositive() {
        new Number(42).print();
        assertEquals("42", out.toString());
    }

    /**
     * Проверяет вывод отрицательного числа.
     */
    @Test
    void testPrintNegative() {
        new Number(-7).print();
        assertEquals("-7", out.toString());
    }

    /**
     * Проверяет вывод большого числа.
     */
    @Test
    void testPrintLarge() {
        new Number(123456789).print();
        assertEquals("123456789", out.toString());
    }

    /**
     * Проверяет производную нуля.
     */
    @Test
    void testDerivativeZero() {
        Expression d = new Number(0).derivative("x");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет производную положительного числа.
     */
    @Test
    void testDerivativePositive() {
        Expression d = new Number(42).derivative("x");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет производную отрицательного числа.
     */
    @Test
    void testDerivativeNegative() {
        Expression d = new Number(-7).derivative("x");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет, что производная не зависит от имени переменной.
     */
    @Test
    void testDerivativeAnyVariable() {
        Expression d = new Number(5).derivative("abc");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет вычисление нуля.
     */
    @Test
    void testEvalZero() {
        assertEquals(0, new Number(0).eval(""));
    }

    /**
     * Проверяет вычисление положительного числа.
     */
    @Test
    void testEvalPositive() {
        assertEquals(42, new Number(42).eval(""));
    }

    /**
     * Проверяет вычисление отрицательного числа.
     */
    @Test
    void testEvalNegative() {
        assertEquals(-7, new Number(-7).eval(""));
    }

    /**
     * Проверяет, что вычисление не зависит от строки присваиваний.
     */
    @Test
    void testEvalIgnoresAssignments() {
        assertEquals(5, new Number(5).eval("x = 10; y = 13"));
    }

    /**
     * Проверяет неизменность числа после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Number(42);
        e.derivative("x");
        e.print();
        assertEquals("42", out.toString());
    }
}