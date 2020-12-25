package Lesson_6;

public abstract class Animal {
    protected double maxRunLength;
    protected double maxSwimLength = 10;
    protected double maxJumpHeight = 0.5;

    void run(double length) {
        boolean result = false;
        if (length <= maxRunLength && length >= 0) {
            result = true;
        }
        System.out.println("результат: run: " + result);
    }

    abstract void swim(double length);

    void jump(double height) {
        boolean result = false;
        if (height <= maxJumpHeight && height >= 0) {
            result = true;
        }
        System.out.println("результат: jump: " + result);
    }
}
