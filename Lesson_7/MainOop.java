package Lesson_7;

public class MainOop {
    public static void main(String[] args) {
        System.out.println("Поставили миску с едой.");
        Plate plate = new Plate(50);
        System.out.println("В миске " + plate.getFood() + " грамм еды");

        Cat[] cats = {
                new Cat("Stepa", 10),
                new Cat("Simka", 15),
                new Cat("Markiza", 17),
                new Cat("Murzik", 25),
                new Cat("Mashka", 7)
        };
        System.out.println("Запустили в комнату " + cats.length + " котов и кошек.");
        System.out.println("Они по очереди подходят к миске.");

        for (Cat cat : cats) {
            catEat(cat, plate);
        }
        System.out.println("В миске осталось " + plate.getFood() + " грамм еды.");

        plate.addFood(5);
        System.out.println("В миску добавили ещё еды. Теперь в миске " + plate.getFood() + " грамм еды.");

        System.out.println("К миске подходят только голодные коты и кошки.");
        int notFullCats = 0;
        for (Cat cat : cats) {
            if (cat.isFool()) {
                continue;
            }
            catEat(cat, plate);
            if (!cat.isFool()) {
                notFullCats++;
            }
        }

        if (notFullCats == 0) {
            System.out.println("Все кошки и коты накормлены и ушли спать.");
        } else {
            System.out.println("Остались голодными: " + notFullCats);
        }
    }

    private static void catEat(Cat cat, Plate plate) {
        System.out.print("Подходит " + cat.getName() + ". ");
        if (cat.eat(plate)) {
            System.out.println("Скушал и довольный уходит.");
        } else {
            System.out.println("Еды слишком мало. Уходит грустный.");
        }
    }
}
