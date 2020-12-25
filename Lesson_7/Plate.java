package Lesson_7;

import java.beans.PropertyEditorSupport;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public boolean decreaseFood(int n) {
        if (n > food) {
            return false;
        }

        food -= n;
        return true;
    }

    public int getFood() {
        return food;
    }
}