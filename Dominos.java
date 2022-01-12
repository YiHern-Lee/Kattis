import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Dominos {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numTest = fio.nextInt();
        for (int x = 0; x < numTest; x++) {
            int dominos = fio.nextInt();
            int lines = fio.nextInt();
            int[] fallen = new int[dominos];
            ArrayList<LinkedList<Integer>> graph = new ArrayList<>(dominos);
            // Init adjacency list for dominos
            for (int i = 0; i < dominos; i++) {
                graph.add(new LinkedList<>());
            }
            for (int i = 0; i < lines; i++) {
                int first = fio.nextInt() - 1;
                int second = fio.nextInt() - 1;
                LinkedList<Integer> nodeList = graph.get(first);
                nodeList.addLast(second);
            }
            
            // Topo-sort 
            int[] visited = new int[dominos];
            int[] predecessor = new int[dominos];
            for (int i = 0; i < dominos; i++) {
                predecessor[i] = -1;
            }
            LinkedList<Integer> toposorted = new LinkedList<>();
            for (int i = 0; i < dominos; i++) {
                if (visited[i] == 0) {
                    toposort(i, graph, visited, predecessor, toposorted);
                }
            }
            Iterator<Integer> iter = toposorted.descendingIterator();
            int pushes = 0;
            while (iter.hasNext()) {
                int next = iter.next();
                if (fallen[next] == 0) {
                    pushes += 1;
                    dfs(next, graph, fallen);
                }
            }
            fio.println(pushes);
        }
        fio.close();
    }

    public static void toposort(int curr, ArrayList<LinkedList<Integer>> graph, int[] visited, int[] predecessor, LinkedList<Integer> toposorted) {
        visited[curr] = 1;
        LinkedList<Integer> nodeList = graph.get(curr);
        Iterator<Integer> iter = nodeList.iterator();
        while (iter.hasNext()) {
            int next = iter.next();
            if (visited[next] == 0) {
                predecessor[next] = curr;
                toposort(next, graph, visited, predecessor, toposorted);
            }
        }
        toposorted.addLast(curr);
    }

    public static void dfs(int curr, ArrayList<LinkedList<Integer>> graph, int[] visited) {
        visited[curr] = 1;
        LinkedList<Integer> nodeList = graph.get(curr);
        Iterator<Integer> iter = nodeList.iterator();
        while (iter.hasNext()) {
            int next = iter.next();
            if (visited[next] == 0) {
                dfs(next, graph, visited);
            }
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