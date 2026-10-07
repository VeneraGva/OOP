package ru.nsu.gorlova;

import ru.nsu.gorlova.expression.Number;
import ru.nsu.gorlova.expression.Variable;
import ru.nsu.gorlova.expression.binop.Add;
import ru.nsu.gorlova.expression.binop.Mul;

/**
 * Точка входа в программу.
 */
public class Main {

    /**
     * Создаёт экземпляр Main.
     */
    public Main() {
    }

    /**
     * Запускает демонстрацию работы с выражениями.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x"))); // (3+(2*x))

        e.print();
        System.out.println();

        Expression de = e.derivative("x");
        de.print();
        System.out.println();

        Expression ve = new Add(new Number(3), new Mul(new Number(2), new Variable("x"))); // (3+(2*x))
        int result = ve.eval("x = 10; y = 13");
        System.out.println(result);

    }
}