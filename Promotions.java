import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Promotions {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int A = fio.nextInt();
        int B = fio.nextInt();
        int numEmployees = fio.nextInt();
        int numLines = fio.nextInt();
        ArrayList<ArrayList<Integer>> adjParent = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adjChildren = new ArrayList<>();
        int[] indeg = new int[numEmployees];
        for (int i = 0; i < numEmployees; i++) adjParent.add(new ArrayList<>());
        for (int i = 0; i < numEmployees; i++) adjChildren.add(new ArrayList<>());
        for (int i = 0; i < numLines; i++) {
            int x = fio.nextInt();
            int y = fio.nextInt();
            adjChildren.get(x).add(y);
            adjParent.get(y).add(x);
            indeg[y] += 1;
        }
        int[] aboveOrEqual = new int[numEmployees];
        int[] above = new int[numEmployees];
        LinkedList<Integer> degzero = new LinkedList<>();
        for (int i = 0; i < numEmployees; i++) {
            if (indeg[i] == 0) degzero.add(i);
        }

        for (int i = 0; i < numEmployees; i++) {
            int[] indeg2 = indeg.clone();
            LinkedList<Integer> q = new LinkedList<>();
            q.addAll(degzero);
            while (!q.isEmpty()) {
                Integer curr = q.poll();
                if (curr == i) continue;
                aboveOrEqual[i] += 1;
                ArrayList<Integer> childs = adjChildren.get(curr);
                for (Integer child : childs) {
                    indeg2[child] -= 1;
                    if (indeg2[child] == 0) {
                        q.offer(child);
                    }
                }
            }
        }

        for (int i = 0; i < numEmployees; i++) {
            Queue<Integer> q = new LinkedList<>();
            int[] visited = new int[numEmployees];
            visited[i] = 1;
            q.offer(i);
            while (!q.isEmpty()) {
                int curr = q.poll();
                ArrayList<Integer> parents = adjParent.get(curr);
                for (Integer parent : parents) {
                    if (visited[parent] == 0) {
                        above[i] += 1;
                        visited[parent] = 1;
                        q.offer(parent);
                    }
                }
            }
        }

        int promotionsA = 0;
        int promotionsB = 0;
        int notPromoting = 0;

        for (int i = 0; i < numEmployees; i++) {
            if (aboveOrEqual[i] < A) promotionsA += 1;
        }
        for (int i = 0; i < numEmployees; i++) {
            if (aboveOrEqual[i] < B) promotionsB += 1;
        }
        for (int i = 0; i < numEmployees; i++) {
            if (above[i] >= B) notPromoting += 1;
        }

        for (ArrayList<Integer> parent : adjParent) fio.println(parent);
        fio.println("\n");
        for (ArrayList<Integer> child : adjChildren) fio.println(child);
        
        fio.println(Arrays.toString(indeg));
        fio.println(Arrays.toString(aboveOrEqual));
        fio.println(Arrays.toString(above));

        fio.println(promotionsA);
        fio.println(promotionsB);
        fio.println(notPromoting);
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