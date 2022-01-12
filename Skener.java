import java.util.Scanner;

public class Skener {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int verMult = sc.nextInt();
        int horMult = sc.nextInt();
        for (int i = 0; i < row; i++) {
            String line = sc.next();
            if (horMult > 1) {
                line = horizontalMultiply(line, col, horMult);
            }
            for (int j = 0; j < verMult; j++) {
                System.out.println(line);
            }
        }
        sc.close();
    }

    public static String horizontalMultiply(String str, int length, int mult) {
        char[] newString = new char[length * mult];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < mult; j++) {
                newString[i * mult + j] = str.charAt(i);
            }
        }
        return new String(newString);
    }
}
