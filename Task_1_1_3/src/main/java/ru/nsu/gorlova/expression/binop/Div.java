package ru.nsu.gorlova.expression.binop;

import ru.nsu.gorlova.Expression;
import ru.nsu.gorlova.expression.BinOperation;

public class Div extends BinOperation {

    /**
     * Создание операции сложения, как у родителя.
     *
     * @param left левое выражение
     * @param right правое выражение
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Функция знака операции.
     *
     * @return символ знака операции
     */
    @Override
    protected char getOperator() {
        return '/';
    }

    /**
     * Функция вычисляет производную выражения по заданной переменной ((u/v)'=(u'·v-u·v')/v²).
     *
     * @param variable переменная для взятия производной
     * @return выражение со взятой производной
     */
    @Override
    public Expression derivative(String variable) {
        Expression leftDerive = left.derivative(variable);
        Expression rightDerive = right.derivative(variable);

        Expression numerator1 = new Mul(leftDerive, right);
        Expression numerator2 = new Mul(left, rightDerive);
        Expression numerator = new Sub(numerator1, numerator2);

        Expression denominator = new Mul(right, right);

        return new Div(numerator, denominator);
    }

    /**
     *  Функция вычисляет значение выражения при известных переменных.
     *
     * @param term строка значений переменных
     * @return результат выражения
     */
    @Override
    public int eval(String term) {
        int rightValue = right.eval(term);
        if (rightValue == 0) {
            System.out.println("Ошибка деление на 0. Неправильное деление заменяется на 0.");
            return 0;
        }
        return left.eval(term) / rightValue;
    }
}