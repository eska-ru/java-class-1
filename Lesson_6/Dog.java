package Lesson_6;

public class Dog extends Animal{
    Dog() {
        super();
        maxRunLength = 500;
        maxSwimLength = 10;
        maxJumpHeight = 0.5;
    }

    Dog(int max_run_length) {
        this();
        maxRunLength = max_run_length;
    }

    @Override
    void swim(double length) {
        boolean result = false;
        if (length <= maxSwimLength && length >= 0) {
            result = true;
        }
        System.out.println("результат: swim: " + result);
    }
}
