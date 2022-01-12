import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Fire {
    static int[][] MOVES = new int[][] { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int height = fio.nextInt();
        int width = fio.nextInt();
        int[][] joeMat = new int[height][width];
        int[][] fireMat = new int[height][width];
        ArrayDeque<Integer[]> joeQ = new ArrayDeque<>();
        ArrayDeque<Integer[]> fireQ = new ArrayDeque<>();
        ArrayDeque<Integer[]> escapeQ = new ArrayDeque<>();
        for (int i = 0; i < height; i++) {
            String line = fio.next();
            for (int j = 0; j < width; j++) {
                char next = line.charAt(j);
                if (next == '#') {
                    joeMat[i][j] = Integer.MIN_VALUE;
                    fireMat[i][j] = Integer.MIN_VALUE;
                } else if (next == 'J') {
                    joeMat[i][j] = 0;
                    fireMat[i][j] = Integer.MAX_VALUE;
                    joeQ.offer(new Integer[] { i, j, 0 });
                    escapeQ.offer(new Integer[] { i, j, 0 });
                } else if (next == 'F') {
                    joeMat[i][j] = Integer.MAX_VALUE;
                    fireMat[i][j] = 0;
                    fireQ.offer(new Integer[] { i, j, 0 });
                } else {
                    joeMat[i][j] = Integer.MAX_VALUE;
                    fireMat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!joeQ.isEmpty()) {
            Integer[] currJoe = joeQ.poll();
            int row = currJoe[0];
            int col = currJoe[1];
            if (currJoe[2] > joeMat[row][col]) continue;
            for (int[] move : MOVES) {
                int nextMoveR = row + move[0];
                int nextMoveC = col + move[1];
                int nextTime = currJoe[2] + 1;
                if (inbounds(nextMoveR, nextMoveC, height, width) && nextTime < joeMat[nextMoveR][nextMoveC]) {
                    joeMat[nextMoveR][nextMoveC] = nextTime;
                    joeQ.offer(new Integer[] { nextMoveR, nextMoveC, nextTime });
                }
            }
        }

        while (!fireQ.isEmpty()) {
            Integer[] currFire = fireQ.poll();
            int row = currFire[0];
            int col = currFire[1];
            if (currFire[2] > fireMat[row][col]) continue;
            for (int[] move : MOVES) {
                int nextMoveR = row + move[0];
                int nextMoveC = col + move[1];
                int nextTime = currFire[2] + 1;
                if (inbounds(nextMoveR, nextMoveC, height, width) && nextTime < fireMat[nextMoveR][nextMoveC]) {
                    fireMat[nextMoveR][nextMoveC] = nextTime;
                    fireQ.offer(new Integer[] { nextMoveR, nextMoveC, nextTime });
                }
            }
        }

        int[][] escapeMat = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (joeMat[i][j] == Integer.MIN_VALUE) escapeMat[i][j] = Integer.MIN_VALUE;
                else {
                    if (fireMat[i][j] > joeMat[i][j]) escapeMat[i][j] = Integer.MAX_VALUE;
                    else escapeMat[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        int minEscapeTime = Integer.MAX_VALUE;
        Integer[] joePos = escapeQ.peek();
        escapeMat[joePos[0]][joePos[1]] = 0;

        while (!escapeQ.isEmpty()) {
            Integer[] currPos = escapeQ.poll();
            int row = currPos[0];
            int col = currPos[1];
            if (currPos[2] > escapeMat[row][col]) continue;
            for (int[] move : MOVES) {
                int nextMoveR = row + move[0];
                int nextMoveC = col + move[1];
                int nextTime = currPos[2] + 1;
                if (!inbounds(nextMoveR, nextMoveC, height, width) && minEscapeTime > nextTime) minEscapeTime = nextTime;
                else if (inbounds(nextMoveR, nextMoveC, height, width) && nextTime < escapeMat[nextMoveR][nextMoveC]) {
                    escapeMat[nextMoveR][nextMoveC] = nextTime;
                    escapeQ.offer(new Integer[] { nextMoveR, nextMoveC, nextTime });
                }
            }
        }

        if (minEscapeTime == Integer.MAX_VALUE) fio.println("IMPOSSIBLE");
        else fio.println(minEscapeTime);     

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
