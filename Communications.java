import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Communications
 */
public class Communications {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numDishes = fio.nextInt();
        int[][] dishes = new int[numDishes][3];
        for (int i = 0; i < numDishes; i++) {
            int x = fio.nextInt();
            int y = fio.nextInt();
            int radius = fio.nextInt();
            dishes[i] = new int[] { x, y, radius };
        }

        LinkedList<Edge> edgeList = new LinkedList<>();
        for (int i = 0; i < numDishes - 1; i++) {
            for (int j = i + 1; j < numDishes; j++) {
                edgeList.add(new Edge( i, j, pythagoras(dishes[i][0], dishes[i][1], dishes[j][0], dishes[j][1]) - 
                        dishes[i][2] - dishes[j][2]));
            }
        }
        edgeList.sort((x, y) -> Double.compare(x.weight, y.weight));

        UnionFind mstSet = new UnionFind(numDishes);
        double mstCost = 0.0d;
        int edgesAdded = 0;
        Iterator<Edge> iter = edgeList.iterator();

        while(iter.hasNext() && edgesAdded < numDishes - 1) {
            Edge next = iter.next();
            if (!mstSet.isSameSet(next.u, next.v)) {
                mstSet.unionSet(next.u, next.v);
                edgesAdded += 1;
                mstCost += next.weight;
            }
        }
        fio.println(mstCost);

        fio.close();
    }

    static double pythagoras(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
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