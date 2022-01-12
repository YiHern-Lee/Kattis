import java.util.Hashtable;
import java.util.Scanner;

/**
 * AMazing
 */
public class AMazing {
    static String[] MOVES = new String[] { "up", "down", "left", "right" };
    /* static LinkedList<String> moveHistory = new LinkedList<>(); */
    static Hashtable<String, String> reverseMove = new Hashtable<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        reverseMove.put("up", "down");
        reverseMove.put("down", "up");
        reverseMove.put("left", "right");
        reverseMove.put("right", "left");
        boolean foundWayOut = false;
        for (String move : MOVES) {
            System.out.println(move);
            System.out.flush();
            String res = sc.next();
            if (res.equals("ok")) {
                foundWayOut = foundWayOut || makeMove(move);
                if (foundWayOut) break;
            } else if (res.equals("solved")) {
                foundWayOut = true;
                break;
            }
        }
        if (!foundWayOut) {
            System.out.println("no way out");
            System.out.flush();
        }

        sc.close();
    }

    static boolean makeMove(String prevMove) {
        String revMove = reverseMove.get(prevMove);
        boolean foundWayOut = false;
        for (String move : MOVES) {
            if (!move.equals(revMove)) {
                System.out.println(move);
                System.out.flush();
                String res = sc.next();
                if (res.equals("ok")) {
                    foundWayOut = foundWayOut || makeMove(move);
                    if (foundWayOut) return foundWayOut;
                } else if (res.equals("solved")) {
                    return true;
                }
            }
        }
        if (!foundWayOut) {
            System.out.println(revMove);
            System.out.flush();
            sc.next();
        } 
        return foundWayOut;
    }
}