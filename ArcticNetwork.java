import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ArcticNetwork {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int testCases = fio.nextInt();
        for (int k = 0; k < testCases; k++) {
            int numSatellites = fio.nextInt();
            int numOutposts = fio.nextInt();
            int[][] outposts = new int[numOutposts][2];
            for (int i = 0; i < numOutposts; i++) {
                outposts[i][0] = fio.nextInt();
                outposts[i][1] = fio.nextInt();
            }
            LinkedList<Edge> edgeList = new LinkedList<>();
            for (int i = 0; i < numOutposts - 1; i++) {
                for (int j = i + 1; j < numOutposts; j++) {
                    double weight = Math.sqrt(Math.pow(outposts[i][0] - outposts[j][0], 2)
                            + Math.pow(outposts[i][1] - outposts[j][1], 2));
                    edgeList.add(new Edge(i, j, weight));
                }
            }
            edgeList.sort((x, y) -> Double.compare(x.weight, y.weight));
            LinkedList<Edge> mst = new LinkedList<>();
            UnionFind mstVertices = new UnionFind(numOutposts);
            Iterator<Edge> iter = edgeList.iterator();
            while (iter.hasNext() && mst.size() < numOutposts - 1) {
                Edge next = iter.next();
                if (!mstVertices.isSameSet(next.u, next.v)) {
                    mstVertices.unionSet(next.u, next.v);
                    mst.add(next);
                }
            }
            for (int i = 1; i < numSatellites; i++) {
                mst.removeLast();
            }

            DecimalFormat f = new DecimalFormat("##.00");
            fio.println(f.format(Math.round(mst.peekLast().weight * 100.0) / 100.0));
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

        @Override
        public String toString() {
            return "" + weight;
        }
    }

    static class UnionFind {
        public int[] p;
        public int[] rank;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                p[i] = i;
                rank[i] = 0;
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
                if (rank[x] > rank[y])
                    p[y] = x;
                else {
                    p[x] = y;
                    if (rank[x] == rank[y])
                        rank[y] = rank[y] + 1;
                }
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