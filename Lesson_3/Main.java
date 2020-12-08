package Lesson_3;

import java.util.Random;
import java.util.Scanner;

class WordGame {
    private static final String[] words;

    static {
        words = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado",
                "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin",
                "potato"};
    }

    private Scanner scanner;

    WordGame(Scanner scanner) {
        this.scanner = scanner;
    }

    void play() {
        System.out.println("Игра \"Угадай слово\"");
        System.out.println();

        Random random = new Random();
        int wordNumber = random.nextInt(words.length);
        StringBuffer resultWord = new StringBuffer("###############");

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

class NumberGame {
    private Scanner scanner;

    NumberGame (Scanner scanner) {
        this.scanner = scanner;
    }

    public void play() {
        do {
            playRound();
        } while (askToPlay());
    }

    private void playRound() {
        Random random = new Random();
        int resultNumber = random.nextInt(10);
        System.out.println("Число загадано - угадайте его!");

        int userNumber = -1;
        for (int i = 1; i <=3 && userNumber != resultNumber ; i++) {
            userNumber = scanner.nextInt();
            if (userNumber < resultNumber) {
                System.out.println("Загаданное число больше");
            } else if (userNumber > resultNumber) {
                System.out.println("Загаданное число меньше");
            }
        }
        if (userNumber == resultNumber) {
            System.out.println("Поздравляем! Вы угадали!");
        } else {
            System.out.println("Увы, у вас не получилось.");
        }
    }

    private boolean askToPlay() {
        System.out.println();
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет\n(1 – повторить, 0 – нет)");

        return (scanner.nextInt() == 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Задание 1
        NumberGame numberGame = new NumberGame(scanner);
        numberGame.play();

        // Задание 2
        WordGame wordGame = new WordGame(scanner);
        wordGame.play();

        // Задание 3
        System.out.println(makeSentence("Игра окончена Приходите ещё Будем вам очень рады"));
    }

    public static String makeSentence(String str) {
        return str.replaceAll(" ([А-Я])", ". $1") + ".";
    }
}
