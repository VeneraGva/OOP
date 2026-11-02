package ru.nsu.gorlova.expression;

import ru.nsu.gorlova.Expression;

/**
 * Число.
 */
public class Number extends Expression {

    /**
     * Поле значения числа.
     */
    private final int value;

    /**
     * Создание числа.
     *
     * @param value значение числа
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * Функция вывода числа в выражении.
     */
    @Override
    public void print() {
        System.out.print(value);
    }

    /**
     * Функция дифференцирующая число.
     *
     * @param variable переменная для взятия производной
     * @return ноль
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    /**
     * Функция считающая значения числа.
     *
     * @param term строка значений переменных
     * @return значения числа
     */
    @Override
    public int eval(String term) {
        return value;
    }
}