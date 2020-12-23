package Lesson_7;

public class Cat {
    private String name;
    private int appetite;
    private boolean fool;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public boolean eat(Plate p) {
        fool = p.decreaseFood(appetite);
        return fool;
    }

    public boolean isFool() {
        return fool;
    }

    String getName(){
        return name;
    }
}