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
 * Тесты для класса Add.
 */
class AddTest {

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
     * Проверяет вывод суммы числа и переменной.
     */
    @Test
    void testPrintNumberAndVariable() {
        Expression e = new Add(new Number(3), new Variable("x"));
        e.print();
        assertEquals("(3+x)", out.toString());
    }

    /**
     * Проверяет вывод суммы двух чисел.
     */
    @Test
    void testPrintTwoNumbers() {
        Expression e = new Add(new Number(1), new Number(2));
        e.print();
        assertEquals("(1+2)", out.toString());
    }

    /**
     * Проверяет вывод суммы двух переменных.
     */
    @Test
    void testPrintTwoVariables() {
        Expression e = new Add(new Variable("x"), new Variable("y"));
        e.print();
        assertEquals("(x+y)", out.toString());
    }

    /**
     * Проверяет вывод вложенной суммы.
     */
    @Test
    void testPrintNested() {
        Expression e = new Add(new Number(3),
                new Mul(new Number(2), new Variable("x")));
        e.print();
        assertEquals("(3+(2*x))", out.toString());
    }

    /**
     * Проверяет вычисление суммы числа и переменной.
     */
    @Test
    void testEvalNumberAndVariable() {
        Expression e = new Add(new Number(3), new Variable("x"));
        assertEquals(13, e.eval("x = 10"));
    }

    /**
     * Проверяет вычисление суммы двух чисел.
     */
    @Test
    void testEvalTwoNumbers() {
        Expression e = new Add(new Number(3), new Number(4));
        assertEquals(7, e.eval(""));
    }

    /**
     * Проверяет вычисление суммы двух переменных.
     */
    @Test
    void testEvalTwoVariables() {
        Expression e = new Add(new Variable("x"), new Variable("y"));
        assertEquals(23, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет производную суммы числа и переменной.
     */
    @Test
    void testDerivativeNumberAndVariable() {
        Expression e = new Add(new Number(3), new Variable("x"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(0+1)", out.toString());
    }

    /**
     * Проверяет производную суммы двух переменных.
     */
    @Test
    void testDerivativeTwoVariables() {
        Expression e = new Add(new Variable("x"), new Variable("y"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(1+0)", out.toString());
    }

    /**
     * Проверяет производную суммы двух чисел.
     */
    @Test
    void testDerivativeTwoNumbers() {
        Expression e = new Add(new Number(3), new Number(4));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(0+0)", out.toString());
    }

    /**
     * Проверяет, что исходное выражение не меняется после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Add(new Number(3), new Variable("x"));
        e.derivative("x");
        e.print();
        assertEquals("(3+x)", out.toString());
    }
}