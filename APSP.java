import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class APSP {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while (true) {
            int vertices = fio.nextInt();
            int edges = fio.nextInt();
            int queries = fio.nextInt();
            if (vertices == 0 && edges == 0 && queries == 0) break;
            int[][] distance = new int[vertices][vertices];
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (i != j) distance[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < edges; i++) {
                int u = fio.nextInt();
                int v = fio.nextInt();
                int w = fio.nextInt();
                if (w < distance[u][v]) {
                    distance[u][v] = w;
                }
            }

            for (int k = 0; k < vertices; k++) {
                for (int i = 0; i < vertices; i++) {
                    for (int j = 0; j < vertices; j++) {
                        if (distance[i][k] < Integer.MAX_VALUE && distance[k][j] < Integer.MAX_VALUE)
                            distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
                

            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    for(int k = 0; distance[i][j] != Integer.MIN_VALUE && k < vertices; k++) {
                        if( distance[i][k] != Integer.MAX_VALUE &&
                            distance[k][j] != Integer.MAX_VALUE &&
                            distance[k][k] < 0)   

                            distance[i][j] = Integer.MIN_VALUE;  
                    }
                }
            }    

            for (int i = 0; i < queries; i++) {
                int u = fio.nextInt();
                int v = fio.nextInt();
                long dist = distance[u][v];
                if (dist <= Integer.MIN_VALUE) {
                    fio.println("-Infinity");
                } else if (dist >= Integer.MAX_VALUE) {
                    fio.println("Impossible");
                } else {
                    fio.println(dist);
                }
            }
            fio.println();
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