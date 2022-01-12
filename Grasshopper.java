import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Grasshopper {
    static int[][] MOVES = new int[][] { {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2} };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FastIO fio = new FastIO();
        while (sc.hasNextInt()) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            int Gr = sc.nextInt() - 1;
            int Gc = sc.nextInt() - 1;
            int Lr = sc.nextInt() - 1;
            int Lc = sc.nextInt() - 1;

            int[][] visited = new int[R][C];
            
            Queue<Integer[]> q = new LinkedList<>();
            visited[Gr][Gc] = 1;
            
            int minJumps = -1;
            q.offer(new Integer[] { Gr, Gc, 0 });
            while (!q.isEmpty()) {
                Integer[] currPos = q.poll();
                int row = currPos[0];
                int col = currPos[1];
                int jumps = currPos[2];
                if (row == Lr && col == Lc) {
                    minJumps = jumps;
                }
                for (int[] move : MOVES) {
                    int nextMoveR = row + move[0];
                    int nextMoveC = col + move[1];
                    if (inbounds(nextMoveR, nextMoveC, R, C) && visited[nextMoveR][nextMoveC] == 0) {
                        visited[nextMoveR][nextMoveC] = 1;
                        q.offer(new Integer[] { nextMoveR, nextMoveC, jumps + 1 });
                    }
                }
            }
            if (minJumps == -1) {
                fio.println("impossible");
            } else {
                fio.println(minJumps);
            }
        }
        sc.close();
        fio.close();
    }

    static boolean inbounds(int row, int col, int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    static class FastIO extends PrintWriter {
        BufferedReader br;
        StringTokenizer st;
    
        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        int nextInt() {
            return Integer.parseInt(next());
        }
    
        long nextLong() {
            return Long.parseLong(next());
        }
    
        double nextDouble() {
            return Double.parseDouble(next());
        }
    
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}