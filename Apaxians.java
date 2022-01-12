import java.util.Arrays;
import java.util.Scanner;

public class Apaxians {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        char[] nameLetters = name.toCharArray();
        scanName(nameLetters);
        sc.close();
    }

    private static void scanName(char[] name) {
        int end = name.length;
        int curr = 0;
        int next = 1;
        while (next < end) {
            char currLetter = name[curr];
            char nextLetter = name[next];
            if (currLetter == nextLetter) {
                name[next] = '*';
                next += 1;
            } else {
                next += 1;
                curr = next - 1;
            }
        }
        reduceName(name);
    }

    private static void reduceName(char[] name) {
        int end = name.length;
        int curr = 0;
        int next = 1;
        while (next < end) {
            if (name[curr] != '*' && name[next] == '*') {
                curr = next;
                next += 1;
            } else if (name[curr] == '*' && name[next] != '*') {
                name[curr] = name[next];
                name[next] = '*';
                next += 1;
                curr += 1;
            } else {
                next += 1;
            }
        }
        curr = curr == 0 ? end : curr;
        char[] reducedName = Arrays.copyOf(name, curr);
        System.out.println(reducedName);
    }
}