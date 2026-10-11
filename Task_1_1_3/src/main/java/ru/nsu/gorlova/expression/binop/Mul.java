package ru.nsu.gorlova.expression.binop;

import ru.nsu.gorlova.Expression;
import ru.nsu.gorlova.expression.BinOperation;

/**
 * Операция умножения.
 */
public class Mul extends BinOperation {

    /**
     * Создание операции сложения, как у родителя.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Функция знака операции.
     *
     * @return символ знака операции
     */
    @Override
    protected char getOperator() {
        return '*';
    }

    /**
     * Функция вычисляет производную выражения по заданной переменной ((u⋅v)′=u'⋅v+u⋅v′).
     *
     * @param variable переменная для взятия производной
     * @return выражение со взятой производной
     */
    @Override
    public Expression derivative(String variable) {
        Expression leftDerive = left.derivative(variable);
        Expression rightDerive = right.derivative(variable);

        Expression firstTerm = new Mul(leftDerive, right);
        Expression secondTerm = new Mul(left, rightDerive);

        return new Add(firstTerm, secondTerm);
    }

    /**
     *  Функция вычисляет значение выражения при известных переменных.
     *
     * @param term строка значений переменных
     * @return результат выражения
     */
    @Override
    public int eval(String term) {
        return left.eval(term) * right.eval(term);
    }
}