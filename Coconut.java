import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
/* import java.util.Arrays;
import java.util.Comparator; */
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Deque;

public class Coconut {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int syllabus = fio.nextInt();
        int playersNum = fio.nextInt();
        Deque<Player> players = new LinkedList<>();
        for (int i = 0; i < playersNum; i++) {
            players.addLast(new Player(i + 1, PlayerStates.FOLDED));
        }

        int currRoundSyllabus = 0;
        Player currPlayer = players.peekFirst();

        while (players.size() > 0) {
            while (currRoundSyllabus < syllabus) {
                currPlayer = players.removeFirst();
                currRoundSyllabus += 1;
                players.addLast(currPlayer);                
            }
            players.removeLast();
            if (currPlayer.state == PlayerStates.FOLDED) {
                currPlayer.state = PlayerStates.CRACKED;
                players.addFirst(currPlayer);
                players.addFirst(new Player(currPlayer.id, PlayerStates.CRACKED));
            } else if (currPlayer.state == PlayerStates.CRACKED) {
                currPlayer.state = PlayerStates.SPILLED;
                players.addLast(currPlayer);
            }

            currRoundSyllabus = 0;
        }
        fio.println(currPlayer);

        fio.close();
        
    }

    static class Player {
        public int id = 0;
        public PlayerStates state;

        public Player(int id, PlayerStates state) {
            this.id = id;
            this.state = state;
        }
        
        public String toString() {
            return String.format("%d", id);
        }
    }

    static enum PlayerStates {
        FOLDED, CRACKED, SPILLED
    }

    static class FastIO extends PrintWriter { 
        BufferedReader br; 
        StringTokenizer st;

        public FastIO() 
        { 
            super(new BufferedOutputStream(System.out)); 
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        } 

        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 

        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 

        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 

        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 

        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}