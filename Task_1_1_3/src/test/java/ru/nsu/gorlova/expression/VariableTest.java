package ru.nsu.gorlova.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.gorlova.Expression;

/**
 * Тесты для класса Variable.
 */
class VariableTest {

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
     * Проверяет вывод однобуквенной переменной.
     */
    @Test
    void testPrintSingleLetter() {
        new Variable("x").print();
        assertEquals("x", out.toString());
    }

    /**
     * Проверяет вывод многобуквенной переменной.
     */
    @Test
    void testPrintLongName() {
        new Variable("alpha").print();
        assertEquals("alpha", out.toString());
    }

    /**
     * Проверяет вывод переменной с цифрами.
     */
    @Test
    void testPrintWithDigits() {
        new Variable("x1").print();
        assertEquals("x1", out.toString());
    }

    /**
     * Проверяет производную совпадающей переменной.
     */
    @Test
    void testDerivativeSameName() {
        Expression d = new Variable("x").derivative("x");
        d.print();
        assertEquals("1", out.toString());
    }

    /**
     * Проверяет производную другой переменной.
     */
    @Test
    void testDerivativeDifferentName() {
        Expression d = new Variable("y").derivative("x");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет производную многобуквенной переменной.
     */
    @Test
    void testDerivativeLongName() {
        Expression d = new Variable("alpha").derivative("alpha");
        d.print();
        assertEquals("1", out.toString());
    }

    /**
     * Проверяет производную по несовпадающей многобуквенной переменной.
     */
    @Test
    void testDerivativeLongNameDifferent() {
        Expression d = new Variable("alpha").derivative("beta");
        d.print();
        assertEquals("0", out.toString());
    }

    /**
     * Проверяет вычисление означенной переменной.
     */
    @Test
    void testEvalAssigned() {
        assertEquals(10, new Variable("x").eval("x = 10"));
    }

    /**
     * Проверяет вычисление среди нескольких переменных.
     */
    @Test
    void testEvalAmongOthers() {
        assertEquals(13, new Variable("y").eval("x = 10; y = 13"));
    }

    /**
     * Проверяет вычисление многобуквенной переменной.
     */
    @Test
    void testEvalLongName() {
        assertEquals(42, new Variable("alpha").eval("alpha = 42"));
    }

    /**
     * Проверяет вычисление неозначенной переменной.
     */
    @Test
    void testEvalNotAssigned() {
        int result = new Variable("z").eval("x = 10; y = 13");
        assertEquals(0, result);
        assertEquals("Переменную z не обозначили, поэтому она равняется нулю.\n",
                out.toString().replace("\r\n", "\n"));
    }

    /**
     * Проверяет вычисление при пустой строке.
     */
    @Test
    void testEvalEmptyString() {
        int result = new Variable("x").eval("");
        assertEquals(0, result);
        assertEquals("Переменную x не обозначили, поэтому она равняется нулю.\n",
                out.toString().replace("\r\n", "\n"));
    }

    /**
     * Проверяет вычисление при null.
     */
    @Test
    void testEvalNull() {
        int result = new Variable("x").eval(null);
        assertEquals(0, result);
        assertEquals("Переменную x не обозначили, поэтому она равняется нулю.\n",
                out.toString().replace("\r\n", "\n"));
    }

    /**
     * Проверяет вычисление с отрицательным значением.
     */
    @Test
    void testEvalNegative() {
        assertEquals(-5, new Variable("x").eval("x = -5"));
    }

    /**
     * Проверяет вычисление с нулевым значением.
     */
    @Test
    void testEvalZero() {
        assertEquals(0, new Variable("x").eval("x = 0"));
    }

    /**
     * Проверяет, что переменная не меняется после derivative.
     */
    @Test
    void testOriginalUnchangedAfterDerivative() {
        Expression e = new Variable("x");
        e.derivative("x");
        e.print();
        assertEquals("x", out.toString());
    }
}