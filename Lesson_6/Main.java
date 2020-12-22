package Lesson_6;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dog");
        Dog dog = new Dog();
        dog.run(100);
        dog.run(700);
        dog.jump(1);
        dog.jump(0.2);
        dog.swim(5);
        dog.swim(15);

        System.out.println("Cat");
        Cat cat = new Cat();
        cat.run(100);
        cat.run(700);
        cat.jump(1);
        cat.jump(0.2);
        cat.swim(5);
        cat.swim(15);

        System.out.println("Dog run 1000 m");
        Dog dogLongRun = new Dog(1000);
        dogLongRun.run(700);
        dogLongRun.run(2000);
    }
}
