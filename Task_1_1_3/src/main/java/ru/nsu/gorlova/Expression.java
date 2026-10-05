package ru.nsu.gorlova;

/**
 * Выражение.
 */
public abstract class Expression {

    /**
     * Функция выводит выражение в стандартный поток вывода.
     */
    public abstract void print();

    /**
     * Функция вычисляет производную выражения по заданной переменной.
     *
     * @param variable переменная для взятия производной
     * @return выражение со взятой производной
     */
    public abstract Expression derivative(String variable);

    /**
     * Функция вычисляет значение выражения при известных переменных.
     *
     * @param term строка значений переменных
     * @return результат выражения
     */
    public abstract int eval(String term);
}