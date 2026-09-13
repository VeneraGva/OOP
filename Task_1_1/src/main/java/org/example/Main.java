package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс с алгоритмом пирамидальной сортировки и демонстрацией его работы.
 *
 * <p>Реализует классический алгоритм heap sort, который работает
 * за время {@code O(n log n)} в лучшем, среднем и худшем случаях.
 * Сортировка выполняется на месте, дополнительная память — {@code O(1)}.</p>
 *
 * @author Горлова В.
 * @version 1.0
 */
public class Main {

    /**
     * Точка входа приложения.
     *
     * <p>Читает строку вида
     * {@code heapsort(new int[] {5, 4, 3, 2, 1});},
     * извлекает из неё числа, сортирует их и выводит результат.</p>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вход");
        String line = scanner.nextLine();

        int[] input = parseArray(line);

        System.out.println("Выход");
        System.out.println(Arrays.toString(heapsort(input)));

        scanner.close();
    }

    /**
     * Извлекает все целые числа из строки и возвращает их массивом.
     *
     * <p>Понимает отрицательные числа. Игнорирует все символы, кроме цифр
     * и знака минус.</p>
     *
     * @param line входная строка
     * @return массив чисел, найденных в строке
     */
    private static int[] parseArray(String line) {
        Matcher m = Pattern.compile("-?\\d+").matcher(line);
        java.util.List<Integer> nums = new java.util.ArrayList<>();
        while (m.find()) {
            nums.add(Integer.parseInt(m.group()));
        }
        int[] result = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            result[i] = nums.get(i);
        }
        return result;
    }

    /**
     * Сортирует массив по возрастанию пирамидальной сортировкой.
     *
     * <p>Создаёт копию входного массива, сортирует её и возвращает.
     * Исходный массив не изменяется.</p>
     *
     * @param arr входной массив
     * @return новый отсортированный массив
     */
    public static int[] heapsort(int[] arr) {
        int[] result = arr.clone();
        sortInPlace(result);
        return result;
    }

    /**
     * Сортирует массив на месте (изменяет переданный массив).
     *
     * <p>Строит max-heap из элементов массива, затем поочерёдно
     * извлекает максимум и ставит его в конец.</p>
     *
     * @param arr массив для сортировки
     */
    public static void sortInPlace(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    /**
     * Просеивает узел вниз по куче, восстанавливая свойство max-heap.
     *
     * @param arr массив, представляющий кучу
     * @param size размер кучи
     * @param root индекс узла, от которого начинается просеивание
     */
    private static void siftDown(int[] arr, int size, int root) {
        while (true) {
            int left = 2 * root + 1;
            int right = 2 * root + 2;
            int largest = root;

            if (left < size && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < size && arr[right] > arr[largest]) {
                largest = right;
            }

            if (largest == root) {
                return;
            }

            swap(arr, root, largest);
            root = largest;
        }
    }

    /**
     * Меняет местами два элемента массива.
     *
     * @param arr массив
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}