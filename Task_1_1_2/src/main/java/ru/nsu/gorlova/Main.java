package ru.nsu.gorlova;

import java.util.Scanner;

/**
 * Точка входа в приложение «Консольный блэкджек».
 */
public class Main {

    /**
     * Запускает игру.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new Game(scanner).start();
    }
}