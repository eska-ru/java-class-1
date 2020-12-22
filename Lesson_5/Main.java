package Lesson_5;

public class Main {
    public static void main(String[] args) {
        // Задание 1 и 2
        Person person = new Person("Ivan Ivanov", "Worker", "Ivanov@mail.ru",
                "+7987654321", 100, 20);

        // Задание 3
        System.out.println("Task 3. Print person");
        person.printPersonInfo();

        // Задание 4
        Person[] personArray = new Person[5];
        personArray[0] = person;
        personArray[1] = new Person("Petr Petrov", "Engineer", "petrov@mail.ru",
                "+7123456789", 200, 50);
        personArray[2] = new Person("Vasya Vasiliev", "Prorab", "vasya@mail.ru",
                "+7123456987", 180, 45);
        personArray[3] = new Person("Sidor Sidorov", "Director", "sidorov@mail.ru",
                "+7123456879", 250, 39);
        personArray[4] = new Person("Anna Sidorova", "Buhgalter", "sidorova@mail.ru",
                "+7123456999", 150, 18);

        // Задание 5
        System.out.println();
        System.out.println("Task 5. 40+ age only");
        for (Person p : personArray) {
            if (p.getAge() > 40) {
                p.printPersonInfo();
            }
        }
    }
}
