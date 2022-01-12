import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class IslandHopping {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int testCases = fio.nextInt();
        for (int k = 0; k < testCases; k++) {
            int numIslands = fio.nextInt();
            double[][] islands = new double[numIslands][2];
            for (int i = 0; i < numIslands; i++) {
                double start = fio.nextDouble();
                double end = fio.nextDouble();
                islands[i] = new double[] { start, end };
            }
            LinkedList<Edge> edgeList = new LinkedList<>();

            for (int i = 0; i < numIslands - 1; i++) {
                for (int j = i + 1; j < numIslands; j++) {
                    edgeList.add(new Edge(i, j, Math.sqrt(Math.pow(islands[i][0] - islands[j][0], 2) + Math.pow(islands[i][1] - islands[j][1], 2))));
                }
            }
            edgeList.sort((x, y) -> Double.compare(x.weight, y.weight));
            UnionFind mstSet = new UnionFind(numIslands);
            int edgesAdded = 0;
            Iterator<Edge> iter = edgeList.iterator();
            double mstCost = 0.0d;
            
            while(iter.hasNext() && edgesAdded < numIslands - 1) {
                Edge next = iter.next();
                if (!mstSet.isSameSet(next.u, next.v)) {
                    mstSet.unionSet(next.u, next.v);
                    edgesAdded += 1;
                    mstCost += next.weight;
                }
            }
            fio.println(mstCost);
        }

        fio.close();
    }

    static class Edge {
        int u;
        int v;
        double weight;

        Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
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

    static class UnionFind {
        public int[] p;
        public int[] rank;
        public int[] size;
        public int[] capacity;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            size = new int[N];
            capacity = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = i;
                rank[i] = 0;
                capacity[i] = 1;
            }
        }

        public int findSet(int i) {
            if (p[i] == i)
                return i;
            else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        public Boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int x = findSet(i), y = findSet(j);
                // rank is used to keep the tree short
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    capacity[x] += capacity[y];
                    size[x] += size[y];
                } else {
                    p[x] = y;
                    if (rank[x] == rank[y])
                        rank[y] = rank[y] + 1;
                    capacity[y] += capacity[x];
                    size[y] += size[x];
                }
            }
        }

        public boolean add(int i) {
            int x = findSet(i);
            if (capacity[x] > size[x]) {
                size[x] += 1;
                return true;
            } else 
                return false;
        }
    }
}