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
 * Тесты для класса Mul.
 */
class MulTest {

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
     * Проверяет вывод произведения числа и переменной.
     */
    @Test
    void testPrintNumberAndVariable() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        e.print();
        assertEquals("(2*x)", out.toString());
    }

    /**
     * Проверяет вывод произведения двух чисел.
     */
    @Test
    void testPrintTwoNumbers() {
        Expression e = new Mul(new Number(3), new Number(4));
        e.print();
        assertEquals("(3*4)", out.toString());
    }

    /**
     * Проверяет вывод произведения двух переменных.
     */
    @Test
    void testPrintTwoVariables() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        e.print();
        assertEquals("(x*y)", out.toString());
    }

    /**
     * Проверяет вывод вложенного произведения.
     */
    @Test
    void testPrintNested() {
        Expression e = new Mul(new Number(2),
                new Add(new Number(3), new Variable("x")));
        e.print();
        assertEquals("(2*(3+x))", out.toString());
    }

    /**
     * Проверяет вычисление произведения числа и переменной.
     */
    @Test
    void testEvalNumberAndVariable() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        assertEquals(20, e.eval("x = 10"));
    }

    /**
     * Проверяет вычисление произведения двух чисел.
     */
    @Test
    void testEvalTwoNumbers() {
        Expression e = new Mul(new Number(3), new Number(4));
        assertEquals(12, e.eval(""));
    }

    /**
     * Проверяет вычисление произведения двух переменных.
     */
    @Test
    void testEvalTwoVariables() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(130, e.eval("x = 10; y = 13"));
    }

    /**
     * Проверяет вычисление произведения с нулём.
     */
    @Test
    void testEvalWithZero() {
        Expression e = new Mul(new Number(0), new Variable("x"));
        assertEquals(0, e.eval("x = 10"));
    }

    /**
     * Проверяет производную произведения числа и переменной.
     */
    @Test
    void testDerivativeNumberAndVariable() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("((0*x)+(2*1))", out.toString());
    }

    /**
     * Проверяет производную произведения переменной и числа.
     */
    @Test
    void testDerivativeVariableAndNumber() {
        Expression e = new Mul(new Variable("x"), new Number(5));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("((1*5)+(x*0))", out.toString());
    }

    /**
     * Проверяет производную произведения двух переменных.
     */
    @Test
    void testDerivativeTwoVariables() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("((1*y)+(x*0))", out.toString());
    }

    /**
     * Проверяет производную произведения двух чисел.
     */
    @Test
    void testDerivativeTwoNumbers() {
        Expression e = new Mul(new Number(3), new Number(4));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("((0*4)+(3*0))", out.toString());
    }

    /**
     * Проверяет, что исходное выражение не меняется после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Mul(new Number(2), new Variable("x"));
        e.derivative("x");
        e.print();
        assertEquals("(2*x)", out.toString());
    }
}