package Lesson_6;

public class Cat extends Animal{
    Cat() {
        super();
        maxRunLength = 200;
        maxJumpHeight = 2;
    }

    @Override
    void swim(double length) {
        System.out.println("результат: swim: false");
    }
}
