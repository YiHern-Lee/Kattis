import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BlockCrusher {
    static int[][] MOVES = new int[][] { {0, 1}, {1, 0}, {0, -1}, {-1, 0}, 
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1 } };
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while(true) {
            int h = fio.nextInt();
            int w = fio.nextInt();
            if (h == 0 && w == 0) break;
            int[][] blocks = new int[h][w];
            char[][] printableBlock = new char[h][w];
            for (int i = 0; i < h; i++) {
                String line = fio.next();
                for (int j = 0; j < w; j++) {
                    char numC = line.charAt(j);
                    blocks[i][j] = numC - '0';
                    printableBlock[i][j] = numC;
                }
            }
            int[][][] predecessor = new int[h][w][2];
            int[][] weights = new int[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    weights[i][j] = Integer.MAX_VALUE;
                }
            }

            // pq entry: { row, col, weight }
            PriorityQueue<Integer[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
            
            for (int i = 0; i < w; i++) {
                pq.offer(new Integer[] { 0, i, blocks[0][i] });
                weights[0][i] = blocks[0][i];
                predecessor[0][i][0] = -1;
                predecessor[0][i][1] = -1; 
            }
            Integer[] currBlock = null;

            while(!pq.isEmpty()) {
                currBlock = pq.poll();
                if (currBlock[0] == h - 1) break;
                if (currBlock[2] > weights[currBlock[0]][currBlock[1]]) continue;
                int row = currBlock[0];
                int col = currBlock[1];
                for (int[] move : MOVES) {
                    int nextMoveR = row + move[0];
                    int nextMoveC = col + move[1];
                    if (inbounds(nextMoveR, nextMoveC, h, w)) {
                        int nextWeight = currBlock[2] + blocks[nextMoveR][nextMoveC];
                        if (nextWeight < weights[nextMoveR][nextMoveC]) {
                            weights[nextMoveR][nextMoveC] = nextWeight;
                            predecessor[nextMoveR][nextMoveC][0] = row;
                            predecessor[nextMoveR][nextMoveC][1] = col;
                            pq.offer(new Integer[] { nextMoveR, nextMoveC, nextWeight} );
                        }
                    }
                }
            }
            if (currBlock != null) {
                int currRow = currBlock[0];
                int currCol = currBlock[1];
                while (inbounds(currRow, currCol, h, w)) {
                    printableBlock[currRow][currCol] = ' ';
                    int nextRow = predecessor[currRow][currCol][0];
                    int nextCol = predecessor[currRow][currCol][1];
                    currRow = nextRow;
                    currCol = nextCol;
                }
            }
            for (char[] line : printableBlock) {
                fio.println(String.valueOf(line));
            }
            fio.println();

        }

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
