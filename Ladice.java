import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Ladice {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt();
        int numClosets = fio.nextInt();
        UnionFind set = new UnionFind(numClosets);
        for (int i = 0; i < n; i++) {
            int a = fio.nextInt() - 1;
            int b = fio.nextInt() - 1;
            set.unionSet(a, b);         
            if (set.add(a)) {
                fio.println("LADICA");
            } else
                fio.println("SMECE");
        }
        fio.close();
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