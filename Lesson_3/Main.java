package Lesson_3;

import java.util.Random;
import java.util.Scanner;

class Game {
    private static final String[] words;

    static {
        words = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin",
                "potato"};
    }

    void play() {
        System.out.println("Игра \"Угадай слово\"");
        System.out.println();

        Random random = new Random();
        int wordNumber = random.nextInt(words.length);
        StringBuffer resultWord = new StringBuffer("###############");

        try (Scanner scanner = new Scanner(System.in)) {
            while (resultWord.indexOf(words[wordNumber]) < 0) {
                System.out.print("Укажите ваш вариант: ");
                String userWord = scanner.next();
                fillResultWord(userWord, resultWord, words[wordNumber]);
                System.out.println("Результат: " + resultWord);
                System.out.println();
            }
            System.out.println("УРА! У вас получилось!");
            System.out.println("Верное слово - " + words[wordNumber]);
        }
    }

    void fillResultWord(String userWord, StringBuffer resultWord, String patternWord) {
        if (userWord.length() > patternWord.length()) {
            userWord = userWord.substring(0, patternWord.length());
        }

        for (int i = 0; i < userWord.length(); i++) {
            if (userWord.charAt(i) == patternWord.charAt(i)) {
                resultWord.setCharAt(i, userWord.charAt(i));
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();

        System.out.println(makeSentence("Игра окончена Приходите ещё Будем вам очень рады"));
    }

    public static String makeSentence(String str) {
        return str.replaceAll(" ([А-Я])", ". $1") + ".";
    }
}
