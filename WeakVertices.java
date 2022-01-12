import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WeakVertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while (true) {
            int vertices = fio.nextInt();
            if (vertices == -1)
                break;
            int[][] graph = new int[vertices][vertices];
            for (int i = 0; i < vertices; i++) 
                for (int j = 0; j < vertices; j++) {
                    int next = fio.nextInt();
                    graph[i][j] = next;
                }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vertices; i++) {
                int[] currRow = graph[i];
                int numTriNeighbors = 0;
                for (int j = 0; j < vertices; j++) {
                    if (currRow[j] == 1) {
                        for (int k = 0; k < vertices; k++) {
                            if (graph[j][k] == 1 && currRow[k] == 1)
                                numTriNeighbors += 1;
                        }
                    }
                    if (numTriNeighbors > 1)
                        break; 
                }
                if (numTriNeighbors < 2)
                    sb.append(i + " ");
            }
            fio.println(sb.toString());
        }
        fio.close();
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