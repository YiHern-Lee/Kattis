import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class HumanCannonBall {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        double[] start = new double[3];
        double[] end = new double[3];
        for (int i = 0; i < 2; i++) {
            start[i] = fio.nextDouble();
        }
        for (int i = 0; i < 2; i++) {
            end[i] = fio.nextDouble();
        }
        int nCannons = fio.nextInt();
        int totalNodes = nCannons + 2;
        // x, y, isCannon = 1
        double[][] nodes = new double[totalNodes][3];
        nodes[0] = start;
        nodes[totalNodes - 1] = end;
        for (int i = 1; i < totalNodes - 1; i++) {
            double x = fio.nextDouble();
            double y = fio.nextDouble();
            nodes[i] = new double[] {x, y, 1};
        }
        double[][] edges = new double[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            double[] source = nodes[i];
            for (int j = 0; j < totalNodes; j++) {
                if (j == i)
                    continue;
                double[] dest = nodes[j];
                double runTime = Math.sqrt(Math.pow(source[0] - dest[0], 2)
                        + Math.pow(source[1] - dest[1], 2)) / 5;
                double cannonAndRunTime = Integer.MAX_VALUE;
                if (source[2] > 0) {
                    cannonAndRunTime = (Math.abs(Math.sqrt(Math.pow(source[0] - dest[0], 2)
                            + Math.pow(source[1] - dest[1], 2)) - 50) / 5) + 2;
                }
                edges[i][j] = Math.min(runTime, cannonAndRunTime);
            }
        }
        for (double[] edge: edges) {
            System.out.println(Arrays.toString(edge));
        }

        TreeSet<Pair> pq = new TreeSet<>((x,y) -> x.dist != y.dist ? Double.compare(x.dist, y.dist) : 
                x.idx - y.idx);
        Pair[] pqPairs = new Pair[totalNodes];
        Pair firstPair = new Pair(0, 0.0);
        pq.add(firstPair);
        pqPairs[0] = firstPair;
        for (int i = 1; i < totalNodes; i++) {
            Pair nextPair = new Pair(i, Double.MAX_VALUE);
            pq.add(nextPair);
            pqPairs[i] = nextPair;
        }
        int solved[] = new int[totalNodes];
        while (!pq.isEmpty()) {
            Pair curr = pq.pollFirst();
            System.out.println(curr.dist);
            if (curr.idx == totalNodes - 1) 
                break;
            int idx = curr.idx;
            solved[idx] = 1;
            for (int i = 0; i < totalNodes; i++) {
                if (i == idx || solved[i] == 1)
                    continue;
                
                if (pqPairs[i].dist > pqPairs[idx].dist + edges[idx][i]) {
                    pq.remove(pqPairs[i]);
                    Pair newPair = new Pair(pqPairs[i].idx, pqPairs[idx].dist + edges[idx][i]);
                    pq.add(newPair);
                    pqPairs[i] = newPair;
                }
            }
        }

        fio.println(pqPairs[totalNodes - 1].dist);

        fio.close();
    }

    static class Pair {
        int idx;
        double dist;
        Pair(int idx, double dist) {
            this.idx = idx;
            this.dist = dist;
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