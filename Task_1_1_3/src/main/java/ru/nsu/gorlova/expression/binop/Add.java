package ru.nsu.gorlova.expression.binop;

import ru.nsu.gorlova.Expression;
import ru.nsu.gorlova.expression.BinOperation;

/**
 * Операция сложения.
 */
public class Add extends BinOperation {

    /**
     * Создание операции сложения, как у родителя.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Функция знака операции.
     *
     * @return символ знака операции
     */
    @Override
    protected char getOperator() {
        return '+';
    }

    /**
     * Функция вычисляет производную выражения по заданной переменной.
     *
     * @param variable переменная для взятия производной
     * @return выражение со взятой производной
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    /**
     *  Функция вычисляет значение выражения при известных переменных.
     *
     * @param term строка значений переменных
     * @return результат выражения
     */
    @Override
    public int eval(String term) {
        return left.eval(term) + right.eval(term);
    }
}