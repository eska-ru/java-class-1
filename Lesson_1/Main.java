package Lesson_1;

public class Main {
    public static void main(String[] args) {
        byte b = 10;
        short s = 1000;
        int i = 1000000;
        long l = 1000000000000L;

        double d = 0.1;
        float f = 0.00000001f;

        String str = "GeekBrians";

        // Tests

        System.out.println("Сумма 1 и 2 в диапазоне от 10 до 20: " + inRange(1, 2));
        System.out.println("Сумма 10 и 2 в диапазоне от 10 до 20: " + inRange(10, 2));
        System.out.println("Сумма 10 и 20 в диапазоне от 10 до 20: " + inRange(10, 20));

        System.out.print("Число 1: "); printSign(1);
        System.out.print("Число 0: "); printSign(0);
        System.out.print("Число -1: "); printSign(-1);

        System.out.println("Число 1 отрицательное: " + isNegative(1));
        System.out.println("Число 0 отрицательное: " + isNegative(0));
        System.out.println("Число -1 отрицательное: " + isNegative(-1));

        printGreetins("Sasha");

        System.out.print("2020 - ");
        isLeapYear(2020);
        System.out.print("2021 - ");
        isLeapYear(2021);
        System.out.print("2000 - ");
        isLeapYear(2000);
        System.out.print("0 - ");
        isLeapYear(0);
        System.out.print("1900 - ");
        isLeapYear(1900);
    }

    /* Пункт 3 */
    public static int makeCalculations(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    /* Пункт 4 */
    public static boolean inRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    /* Пункт 5 */
    public static void printSign(int a) {
        if (a < 0) {
            System.out.println("Отрицательное число");
        } else {
            System.out.println("Положительное число");
        }
    }

    /* Пункт 6 */
    public static boolean isNegative(int a) {
        return a < 0;
    }

    /* Пункт 7 */
    public static void printGreetins(String name) {
        System.out.println("Привет, " + name + "!");
    }

    /* Пункт 8 */
    public static boolean isLeapYear(int year) {
        boolean leapYear = false;

        if ((year % 4) == 0 && ((year % 100) != 0 || (year % 400) == 0)) {
            System.out.println("Это високосный год");
            leapYear = true;
        } else {
            System.out.println("Это не високосный год");
        }

        return leapYear;
    }
}
