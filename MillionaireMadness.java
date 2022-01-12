import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class MillionaireMadness {
    static int[][] MOVES = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int length = fio.nextInt();
        int breadth = fio.nextInt();
        int[][] grid = new int[length][breadth];
        int[][] distance = new int[length][breadth];
        Integer[][][] treeVertices = new Integer[length][breadth][3];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                grid[i][j] = fio.nextInt();
                distance[i][j] = Integer.MAX_VALUE;
                treeVertices[i][j] = new Integer[] {Integer.MAX_VALUE, i, j};
            }
        }
        distance[0][0] = 0;
        treeVertices[0][0] = new Integer[] {0, 0, 0};
        TreeSet<Integer[]> treeSet = new TreeSet<>(new VertexComparator());
        // 0: cost, 1: row, 2: col
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                treeSet.add(treeVertices[i][j]);
            }
        }
        while (!treeSet.isEmpty()) {
            Integer[] currVertex = treeSet.first();
            treeSet.remove(currVertex);
            int currHeight = grid[currVertex[1]][currVertex[2]];
            int currCost = currVertex[0];
            for (int[] move : MOVES) {
                int row = currVertex[1] + move[0];
                int col = currVertex[2] + move[1];
                if (inbounds(row, length) && inbounds(col, breadth)) {
                    Integer[] neighbourVertex = treeVertices[row][col];
                    int neighborHeight = grid[row][col];
                    int newCost = Math.max(neighborHeight - currHeight, currCost);
                    int originalCost = distance[row][col];
                    if (newCost < originalCost) {
                        treeSet.remove(neighbourVertex);
                        distance[row][col] = newCost;
                        Integer[] newVertex = new Integer[] {newCost, row, col};
                        treeVertices[row][col] = newVertex;
                        treeSet.add(newVertex);
                    }
                }
            }
        }
        for (int[] row : distance) {
            fio.println(Arrays.toString(row));
        }
        fio.println(distance[length-1][breadth-1]);
        fio.close();
    }

    static boolean inbounds(int coord, int upperbound) {
        return coord >= 0 && coord < upperbound;
    }

    static class VertexComparator implements Comparator<Integer[]> {
        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            if (o1[0].compareTo(o2[0]) == 0) {
                if (o1[1].compareTo(o2[1]) == 0) {
                    if (o1[2].compareTo(o2[2]) == 0) {
                        return 0;
                    } else return o1[1] + o1[2] - o2[1] - o2[2];
                } else return o1[1] - o2[1];
            } else return o1[0] - o2[0];
        }
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
