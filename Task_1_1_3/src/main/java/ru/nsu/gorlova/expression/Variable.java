package ru.nsu.gorlova.expression;

import java.util.Map;
import ru.nsu.gorlova.Expression;

/**
 * Переменная.
 */
public class Variable extends Expression {

    /**
     * Поле названия переменной.
     */
    private final String name;

    /**
     * Создание переменной.
     *
     * @param name название переменной
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Функция вывода переменной в строке.
     */
    @Override
    public void print() {
        System.out.print(name);
    }

    /**
     * Функция дифференцирующая переменную.
     *
     * @param variable переменная для взятия производной
     * @return дифференциал от переменной
     */
    @Override
    public Expression derivative(String variable) {
        if (name.equals(variable)) {
            return new Number(1);
        }
        return new Number(0);
    }

    /**
     * Функция считающая значение переменной.
     *
     * @param term строка значений переменных
     * @return значение переменной
     */
    @Override
    public int eval(String term) {
        Map<String, Integer> values = StringParser.parse(term);
        if (values.containsKey(name)) {
            return values.get(name);
        }
        System.out.println("Переменную " + name + " не обозначили, поэтому она равняется нулю.");
        return 0;
    }
}