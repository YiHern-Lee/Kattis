import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Hyacinth {
    static int[][] frequencies;
    static int numGenerator = 1;
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numNodes = fio.nextInt();
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(numNodes);
        frequencies = new int[numNodes][2];
        for (int i = 0; i < numNodes; i++) adjacencyList.add(new ArrayList<>());
        int deg[] = new int[numNodes];
        for (int i = 0; i < numNodes - 1; i++) {
            int u = fio.nextInt() - 1;
            int v = fio.nextInt() - 1;
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
            deg[u]++;
            deg[v]++;
        }
        int root = 0;
        for (int i = 0; i < numNodes; i++) {
            if (deg[i] == 1) {
                root = i;
                break;
            }
        }
        dfs(adjacencyList, root, -1, root);
        for (int[] frequency : frequencies) {
            fio.println(frequency[0] + " " + frequency[1]);
        }

        fio.close(); 
    }

    static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, int curr, int prev, int root) {
        ArrayList<Integer> neighbours = adjacencyList.get(curr);
        int numChildren = 0;
        for (Integer u : neighbours) {
            if (u != prev) numChildren++;
        }
        if (curr == root) {
            frequencies[curr][0] = assignNum();
            frequencies[curr][1] = assignNum();
        } else {
            if (numChildren == 0) {
                frequencies[curr][0] = frequencies[prev][0];
                frequencies[curr][1] = frequencies[prev][1];
            } else {
                frequencies[curr][0] = frequencies[prev][1];
                frequencies[curr][1] = assignNum();
            }
        }
        for (Integer neighbour : neighbours) {
            if (neighbour != prev)
                dfs(adjacencyList, neighbour, curr, root);
        }
    }

    static int assignNum() {
        return numGenerator++;
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
