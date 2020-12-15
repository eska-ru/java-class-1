package Lesson_5;

public class Person {
    private final String fio;
    private final String position;
    private final String email;
    private final String phone;
    private final int salary;

    public int getAge() {
        return age;
    }

    private final int age;

    Person(String fio, String position, String email, String phone, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printPersonInfo() {
        System.out.printf("FIO: %s, Position: %s, email: %s, Phone: %s, Salary: %d, Age: %d%n",
                fio, position, email, phone, salary, age);
    }
}
