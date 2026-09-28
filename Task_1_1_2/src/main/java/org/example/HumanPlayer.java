package org.example;

import java.util.Scanner;

/**
 * Игрок-человек, делающий ход через консоль.
 */
public class HumanPlayer extends Player {
    private final Scanner scanner;

    /**
     * Создаёт игрока со сканером стандартного ввода.
     */
    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Спрашивает пользователя, хочет ли он взять карту.
     */
    @Override
    public boolean wantsToHit() {
        System.out.print("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться . ");
        return scanner.nextInt() == 1;
    }
}