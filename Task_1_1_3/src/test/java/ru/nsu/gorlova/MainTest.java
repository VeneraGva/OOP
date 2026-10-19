package ru.nsu.gorlova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.expression.Number;
import ru.nsu.gorlova.expression.Variable;
import ru.nsu.gorlova.expression.binop.Add;
import ru.nsu.gorlova.expression.binop.Div;
import ru.nsu.gorlova.expression.binop.Mul;
import ru.nsu.gorlova.expression.binop.Sub;

/**
 * Интеграционные тесты всей программы.
 */
class MainTest {

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
     * Проверяет пример из задания: (3+(2*x)).
     */
    @Test
    void testExampleFromTask() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        e.print();
        assertEquals("(3+(2*x))", out.toString());
    }

    /**
     * Проверяет производную примера из задания.
     */
    @Test
    void testDerivativeExampleFromTask() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        Expression de = e.derivative("x");
        de.print();
        assertEquals("(0+((0*x)+(2*1)))", out.toString());
    }

    /**
     * Проверяет вычисление примера из задания.
     */
    @Test
    void testEvalExampleFromTask() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        int result = e.eval("x = 10; y = 13");
        assertEquals(23, result);
    }

    /**
     * Проверяет полный цикл: print, derivative, eval.
     */
    @Test
    void testFullCycle() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        e.print();
        assertEquals("(3+(2*x))", out.toString());

        out.reset();

        Expression de = e.derivative("x");
        de.print();
        assertEquals("(0+((0*x)+(2*1)))", out.toString());

        out.reset();

        int result = e.eval("x = 10; y = 13");
        assertEquals(23, result);
    }

    /**
     * Проверяет, что исходное выражение не меняется после всех операций.
     */
    @Test
    void testOriginalUnchangedAfterAllOperations() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        e.derivative("x");
        e.eval("x = 10");
        e.print();

        assertEquals("(3+(2*x))", out.toString());
    }

    /**
     * Проверяет сложное выражение со всеми операциями.
     */
    @Test
    void testComplexExpression() {
        Expression e = new Add(
                new Sub(
                        new Mul(new Number(2), new Variable("x")),
                        new Div(new Number(10), new Number(2))),
                new Number(5));

        e.print();
        assertEquals("(((2*x)-(10/2))+5)", out.toString());
    }

    /**
     * Проверяет вычисление сложного выражения.
     */
    @Test
    void testComplexExpressionEval() {
        Expression e = new Add(
                new Sub(
                        new Mul(new Number(2), new Variable("x")),
                        new Div(new Number(10), new Number(2))),
                new Number(5));

        assertEquals(20, e.eval("x = 10"));
    }

    /**
     * Проверяет производную сложного выражения.
     */
    @Test
    void testComplexExpressionDerivative() {
        Expression e = new Add(
                new Sub(
                        new Mul(new Number(2), new Variable("x")),
                        new Div(new Number(10), new Number(2))),
                new Number(5));

        Expression de = e.derivative("x");
        de.print();
        assertEquals("((((0*x)+(2*1))-(((0*2)-(10*0))/(2*2)))+0)", out.toString());
    }

    /**
     * Проверяет выражение с многобуквенными переменными.
     */
    @Test
    void testLongVariableNames() {
        Expression e = new Mul(
                new Variable("alpha"),
                new Variable("beta"));

        e.print();
        assertEquals("(alpha*beta)", out.toString());

        out.reset();

        assertEquals(130, e.eval("alpha = 10; beta = 13"));
    }

    /**
     * Проверяет производную по многобуквенной переменной.
     */
    @Test
    void testLongVariableNamesDerivative() {
        Expression e = new Mul(
                new Variable("alpha"),
                new Variable("beta"));

        Expression de = e.derivative("alpha");
        de.print();
        assertEquals("((1*beta)+(alpha*0))", out.toString());
    }

    /**
     * Проверяет, что дифференцирование по другой переменной даёт 0.
     */
    @Test
    void testDerivativeByUnknownVariable() {
        Expression e = new Add(
                new Number(3),
                new Mul(new Number(2), new Variable("x")));

        Expression de = e.derivative("z");
        de.print();
        assertEquals("(0+((0*x)+(2*0)))", out.toString());
    }

    /**
     * Проверяет, что вычисление с неозначенной переменной даёт 0.
     */
    @Test
    void testEvalWithMissingVariable() {
        Expression e = new Mul(
                new Number(2),
                new Variable("x"));

        int result = e.eval("y = 10");
        assertEquals(0, result);
    }
}