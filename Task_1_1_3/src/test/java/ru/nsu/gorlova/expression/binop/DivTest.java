package ru.nsu.gorlova.expression.binop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.Expression;
import ru.nsu.gorlova.expression.Number;
import ru.nsu.gorlova.expression.Variable;

/**
 * Тесты для класса Div.
 */
class DivTest {

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
     * Проверяет вывод частного числа и переменной.
     */
    @Test
    void testPrintNumberAndVariable() {
        Expression e = new Div(new Number(10), new Variable("x"));
        e.print();
        assertEquals("(10/x)", out.toString());
    }

    /**
     * Проверяет вывод частного двух чисел.
     */
    @Test
    void testPrintTwoNumbers() {
        Expression e = new Div(new Number(10), new Number(2));
        e.print();
        assertEquals("(10/2)", out.toString());
    }

    /**
     * Проверяет вывод частного двух переменных.
     */
    @Test
    void testPrintTwoVariables() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        e.print();
        assertEquals("(x/y)", out.toString());
    }

    /**
     * Проверяет вывод вложенного выражения.
     */
    @Test
    void testPrintNested() {
        Expression e = new Div(new Number(3),
                new Mul(new Number(2), new Variable("x")));
        e.print();
        assertEquals("(3/(2*x))", out.toString());
    }

    /**
     * Проверяет вычисление частного числа и переменной.
     */
    @Test
    void testEvalNumberAndVariable() {
        Expression e = new Div(new Number(10), new Variable("x"));
        assertEquals(5, e.eval("x = 2"));
    }

    /**
     * Проверяет вычисление частного двух чисел.
     */
    @Test
    void testEvalTwoNumbers() {
        Expression e = new Div(new Number(10), new Number(2));
        assertEquals(5, e.eval(""));
    }

    /**
     * Проверяет вычисление частного двух переменных.
     */
    @Test
    void testEvalTwoVariables() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        assertEquals(2, e.eval("x = 10; y = 5"));
    }

    /**
     * Проверяет деление на ноль.
     */
    @Test
    void testEvalDivisionByZero() {
        Expression e = new Div(new Number(10), new Number(0));
        assertThrows(ArithmeticException.class, () -> e.eval(""));
    }

    /**
     * Проверяет производную частного числа и переменной.
     */
    @Test
    void testDerivativeNumberAndVariable() {
        Expression e = new Div(new Number(10), new Variable("x"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(((0*x)-(10*1))/(x*x))", out.toString());
    }

    /**
     * Проверяет производную частного переменной и числа.
     */
    @Test
    void testDerivativeVariableAndNumber() {
        Expression e = new Div(new Variable("x"), new Number(5));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(((1*5)-(x*0))/(5*5))", out.toString());
    }

    /**
     * Проверяет производную частного двух переменных.
     */
    @Test
    void testDerivativeTwoVariables() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(((1*y)-(x*0))/(y*y))", out.toString());
    }

    /**
     * Проверяет производную частного двух чисел.
     */
    @Test
    void testDerivativeTwoNumbers() {
        Expression e = new Div(new Number(10), new Number(2));
        Expression d = e.derivative("x");
        d.print();
        assertEquals("(((0*2)-(10*0))/(2*2))", out.toString());
    }

    /**
     * Проверяет, что исходное выражение не меняется после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Div(new Number(10), new Variable("x"));
        e.derivative("x");
        e.print();
        assertEquals("(10/x)", out.toString());
    }
}
