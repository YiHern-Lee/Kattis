import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Islands {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int length = fio.nextInt();
        int breadth = fio.nextInt();
        char[][] grid = new char[length][breadth];
        int[][] coords = new int[length * breadth][2];
        for (int i = 0; i < length; i++) {
            String line = fio.next();
            for (int j = 0; j < breadth; j++) {
                grid[i][j] = line.charAt(j);
                coords[i*breadth + j][0] = i;
                coords[i*breadth + j][1] = j;
            }      
        }

        int numIslands = 0;
        for (int i = 0; i < length * breadth; i++) {
            if (grid[coords[i][0]][coords[i][1]] == 'L') {
                numIslands += 1;
                dfs(coords[i][0], coords[i][1], grid);
            }
            
        }
        fio.println(numIslands);

        fio.close();
    }

    public static void dfs(int row, int col, char[][] grid) {
        grid[row][col] = 'X';
        int[][] possibleNeighbours = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
        for (int[] neighbour : possibleNeighbours) {
            if (inbounds(neighbour[0], grid.length) && inbounds(neighbour[1], grid[0].length) && 
                    (grid[neighbour[0]][neighbour[1]] == 'L' || grid[neighbour[0]][neighbour[1]] == 'C')) {
                dfs(neighbour[0], neighbour[1], grid);
            }
        }

    }

    /* public static int[][] getNeighbours(int row, int col, char[][] grid) {
        int[][] possibleNeighbours = {{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
        for (int[] neighbours : possibleNeighbours) {
            if (inbounds(neighbours[0], grid.length) && inbounds(neighbours[1], grid[0].length) && 
                    (grid[neighbours[0]][neighbours[1]] == 'L' || grid[neighbours[0]][neighbours[1]] == 'C')) {
                
            }
        }
    } */

    public static boolean inbounds(int coord, int upperbound) {
        return coord >= 0 && coord < upperbound;
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