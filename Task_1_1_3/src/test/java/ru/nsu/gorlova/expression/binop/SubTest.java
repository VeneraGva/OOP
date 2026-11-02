package ru.nsu.gorlova.expression.binop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.Expression;
import ru.nsu.gorlova.expression.Number;
import ru.nsu.gorlova.expression.Variable;

/**
 * Тесты для класса Sub.
 */
class SubTest {

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
     * Проверяет вывод разности числа и переменной.
     */
    @Test
    void testPrintNumberAndVariable() {
        Expression e = new Sub(new Number(10), new Variable("x"));
        e.print();
        assertEquals("(10-x)", out.toString());
    }

    /**
     * Проверяет вывод разности двух чисел.
     */
    @Test
    void testPrintTwoNumbers() {
        Expression e = new Sub(new Number(10), new Number(3));
        e.print();
        assertEquals("(10-3)", out.toString());
    }

    /**
     * Проверяет вывод разности двух переменных.
     */
    @Test
    void testPrintTwoVariables() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        e.print();
        assertEquals("(x-y)", out.toString());
    }

    /**
     * Проверяет вывод вложенной разности.
     */
    @Test
    void testPrintNested() {
        Expression e = new Sub(new Number(10),
                new Mul(new Number(2), new Variable("x")));
        e.print();
        assertEquals("(10-(2*x))", out.toString());
    }

    /**
     * Проверяет вычисление разности числа и переменной.
     */
    @Test
    void testEvalNumberAndVariable() {
        Expression e = new Sub(new Number(10), new Variable("x"));
        assertEquals(7, e.eval("x = 3"));
    }

    /**
     * Проверяет вычисление разности двух чисел.
     */
    @Test
    void testEvalTwoNumbers() {
        Expression e = new Sub(new Number(10), new Number(3));
        assertEquals(7, e.eval(""));
    }

    /**
     * Проверяет вычисление разности двух переменных.
     */
    @Test
    void testEvalTwoVariables() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        assertEquals(-3, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет вычисление с отрицательным результатом.
     */
    @Test
    void testEvalNegativeResult() {
        Expression e = new Sub(new Number(3), new Number(10));
        assertEquals(-7, e.eval(""));
    }

    /**
     * Проверяет производную разности числа и переменной.
     */
    @Test
    void testDerivativeNumberAndVariable() {
        Expression e = new Sub(new Number(10), new Variable("x"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(0-1)", out.toString());
    }

    /**
     * Проверяет производную разности переменной и числа.
     */
    @Test
    void testDerivativeVariableAndNumber() {
        Expression e = new Sub(new Variable("x"), new Number(5));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(1-0)", out.toString());
    }

    /**
     * Проверяет производную разности двух переменных.
     */
    @Test
    void testDerivativeTwoVariables() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(1-0)", out.toString());
    }

    /**
     * Проверяет производную разности двух чисел.
     */
    @Test
    void testDerivativeTwoNumbers() {
        Expression e = new Sub(new Number(10), new Number(3));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(0-0)", out.toString());
    }

    /**
     * Проверяет, что исходное выражение не меняется после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Sub(new Number(10), new Variable("x"));
        e.derivative("x");
        e.print();
        assertEquals("(10-x)", out.toString());
    }
}