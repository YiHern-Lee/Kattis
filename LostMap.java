import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LostMap {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numVillages = fio.nextInt();
        int[][] villages = new int[numVillages][numVillages];
        int[] visited = new int[numVillages];
        int villagesVisited = 0;
        for (int i = 0; i < numVillages; i++) {
            for (int j = 0; j < numVillages; j++) {
                villages[i][j] = fio.nextInt();
            }
        }
        int[][] shortestEdges = new int[numVillages][2];
        for (int i = 0; i < numVillages; i++) {
            shortestEdges[i][0] = Integer.MAX_VALUE;
            shortestEdges[i][1] = -1;
        }
        shortestEdges[0][0] = 0;
        int source = 0;
        LinkedList<Integer[]> mst = new LinkedList<>();
        while (villagesVisited < numVillages - 1) {
            visited[source] = 1;
            villagesVisited += 1;
            for (int i = 0; i < numVillages; i++) {
                if (i != source) {
                    if (villages[source][i] < shortestEdges[i][0]) {
                        shortestEdges[i][0] = villages[source][i];
                        shortestEdges[i][1] = source;
                    }     
                }
            }
            int min = Integer.MAX_VALUE;
            int minDest = -1;
            int minSource = -1;
            for (int i = 0; i < numVillages; i++) {
                if (visited[i] == 0 && shortestEdges[i][0] < min) {
                    min = shortestEdges[i][0];
                    minDest = i;
                    minSource = shortestEdges[i][1];
                }
            }
            mst.addLast(new Integer[] {minSource + 1, minDest + 1});
            source = minDest;
        }
        Iterator<Integer[]> iter = mst.iterator();
        while (iter.hasNext()) {
            Integer[] next = iter.next();
            fio.println(next[0] + " " + next[1]);
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