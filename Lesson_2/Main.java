package Lesson_2;

public class Main {
    public static void main(String[] args) {
        // Задание 1
        int[] mass = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray(mass);

        for (int i = 0; i < mass.length; i++) {
            mass[i] = mass[i] == 1 ? 0 : 1;
        }
        printArray(mass);

        // Задание 2
        int massLength = 8;
        int[] destMass = new int[massLength];
        int[] sourceMass = {0, 3, 6, 9, 12, 15, 18, 21};

        for (int i = 0; i < massLength; i++) {
            destMass[i] = sourceMass[i];
        }

        // Вот так эффективнее:
        // destMass = Arrays.copyOf(sourceMass, sourceMass.length);

        printArray(destMass);

        // Задание 3
        int[] mass3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < mass3.length; i++) {
            if (mass3[i] < 6) {
                mass3[i] *=2;
            }
        }
        printArray(mass3);

        // Задание 4
        int[][] mass2D = new int[10][10];
        for (int i = 0; i < mass2D.length; i++) {
            mass2D[i][i] = 1;
            mass2D[i][mass2D.length-i-1] = 1;
        }
        print2DArray(mass2D);

        // Задание 5
        int min = mass3[0], max = mass3[0];
        for (int i : mass3) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        System.out.println("Min: " + min + ", Max: " + max);

        // Задание 6
        int[] checkMass1 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(checkMass1));

        int[] checkMass2 = {1, 1, 1, 2, 1};
        System.out.println(checkBalance(checkMass2));

        int[] checkMass3 = {2, 1, 2, 2};
        System.out.println(checkBalance(checkMass3));

        // Задание 7
        // Два варианта смещений. Описаны в комментариях к методам.
        printArray(checkMass1);
        arrayMove(checkMass1, 2);
        printArray(checkMass1);
        arrayRound(checkMass1, 2);
        printArray(checkMass1);

        checkMass2 = new int[] {1,2,3,4,5};
        printArray(checkMass2);
        arrayMove(checkMass2, -3);
        printArray(checkMass2);
        arrayRound(checkMass2, 3);
        printArray(checkMass2);

        printArray(checkMass3);
        arrayMove(checkMass3, 1);
        printArray(checkMass3);
        arrayRound(checkMass3, 1);
        printArray(checkMass3);

        int[] checkMass4 = {1,2,3,4,5,6,7,8,9};
        printArray(checkMass4);
        arrayMove(checkMass4, 3);
        printArray(checkMass4);

        checkMass4 = new int[] {1,2,3,4,5,6,7,8,9};
        printArray(checkMass4);
        arrayRound(checkMass4, -2);
        printArray(checkMass4);

    }

    static void printArray(int[] mass) {
        for (int m : mass) System.out.print(m + " ");
        System.out.println();
    }

    static void print2DArray(int[][] mass) {
        for (int[] m : mass) {
            for (int n : m) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    static boolean checkBalance(int[] mass) {
        int begin = 0;
        int end = mass.length-1;
        int diff = 0;
        while ((end-begin) >= 0) {
            if (diff >= 0) {
                diff -= mass[end];
                end--;
            }
            if (diff <= 0 && (end-begin) >= 0) {
                diff += mass[begin];
                begin++;
            }
        }

        return diff == 0;
    }

    /*
        Смещение элементов массива на count позиций. Первые позиции затираются нулями.
        Смещение производится из точки начала в точку назначения без дополнительных итераций.
     */
    static void arrayMove(int[] mass, int count) {
        if (count == 0) {
            return;
        }

        int tmp=0;
        for (int n = 1; n <= Math.abs(count); n++) {
            for (int i = 1; i <= mass.length/Math.abs(count); i++) {
                int source = (count > 0) ? (n*i - 1) : (mass.length - n*i);
                int dest = (count > 0) ? (count*i+n - 1) : (mass.length + count*i - n);
                if (i == 1) {
                    tmp = mass[source];
                    mass[source] = 0;
                }
                if (dest < mass.length && dest >= 0) {
                    int t = mass[dest];
                    mass[dest] = tmp;
                    tmp = t;
                }
            }
        }
    }

    /*
        Смещение элементов массива по кругу на count позиций.
        На первые позиции помещаются "выталкиваемые" позиции.
        Смещение производится последовательно (смещение на одну позицию за итерацию), из-за чего при
        больших значениях count данный метод будет неэффективен.
     */
    static void arrayRound(int[] mass, int count) {
        if (count == 0) {
            return;
        }

        int tmp=0;
        for (int n = 1; n <= Math.abs(count); n++) {
            for (int i = 1; i <= mass.length; i++) {
                int source = (count > 0) ? (i - 1) : (mass.length - i);
                int dest = (count > 0) ? i : (mass.length - i - 1);
                if (i == 1) {
                    tmp = mass[source];
                }
                if (dest < mass.length && dest >= 0) {
                    int t = mass[dest];
                    mass[dest] = tmp;
                    tmp = t;
                } else if (dest <= mass.length && dest >= -1) {
                    dest = (count > 0) ? (dest - mass.length) : (dest + mass.length);
                    mass[dest] = tmp;
                }
            }
        }
    }
}
