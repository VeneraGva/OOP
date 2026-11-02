package ru.nsu.gorlova.expression;

import ru.nsu.gorlova.Expression;

/**
 * Бинарная операция.
 */
public abstract class BinOperation extends Expression {

    /**
     * Поле для выражения, которое находится слева.
     */
    protected Expression left;

    /**
     * Поле для выражения, которое находится справа.
     */
    protected Expression right;

    /**
     * Создание бинарной операции.
     *
     * @param left выражение, которое находится слева
     * @param right выражение, которое находится справа
     */
    public BinOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Функция вывода бинарной операции в скобках.
     */
    @Override
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print(getOperator());
        right.print();
        System.out.print(")");
    }

    /**
     * Функция знака операции.
     *
     * @return символ знака операции
     */
    protected abstract char getOperator();
}