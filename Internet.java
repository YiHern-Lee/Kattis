import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Internet {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int houses = fio.nextInt();
        int lines = fio.nextInt();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < houses; i++) adjList.add(new ArrayList<>());
        for (int i = 0; i < lines; i++) {
            int u = fio.nextInt() - 1;
            int v = fio.nextInt() - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[houses];
        q.offer(0);
        visited[0] = true;
        while(!q.isEmpty()) {
            int curr = q.poll();
            ArrayList<Integer> neighbours = adjList.get(curr);
            for (int neighbour : neighbours) {
                if (!visited[neighbour]) {
                    q.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
        
        int unvisitedHouses = 0;
        for (int i = 0; i < houses; i++) {
            if (!visited[i]) {
                fio.println(i + 1);
                unvisitedHouses++;
            }
        }
        if (unvisitedHouses == 0) fio.println("Connected");

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