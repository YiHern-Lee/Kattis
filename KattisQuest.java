import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class KattisQuest {
    public static void main(String[] args) {
        TreeMap<Integer, PriorityQueue<Integer>> quests = new TreeMap<>();
        FastIO fio = new FastIO();  
        int n = fio.nextInt();
        for (int i = 0; i < n; i++) {
            String op = fio.next();
            if (op.equals("add")) {
                int energy = fio.nextInt();
                int gold = fio.nextInt();
                PriorityQueue<Integer> pq = quests.get(energy);
                if (pq == null) {
                    PriorityQueue<Integer> newPq = new PriorityQueue<>((a, b) -> b - a);
                    newPq.add(gold);
                    quests.put(energy, newPq);
                } else {
                    pq.add(gold);
                }
            } else {
                int query = fio.nextInt();
                long totalGold = 0;
                while (query > 0) {
                    Map.Entry<Integer, PriorityQueue<Integer>> entry = quests.floorEntry(query);
                    if (entry == null) {
                        break;
                    } else {
                        Integer key = entry.getKey();
                        PriorityQueue<Integer> pq = entry.getValue();
                        totalGold += pq.poll();
                        query -= key;
                        if (pq.isEmpty()) quests.remove(key);
                    } 
                }
                fio.println(totalGold);
            }
        }
        fio.close();
    }

    static class FastIO extends PrintWriter { 
        BufferedReader br; 
        StringTokenizer st;

        public FastIO() 
        { 
            super(new BufferedOutputStream(System.out)); 
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        } 

        String next() 
        { 
            while (st == null || !st.hasMoreElements()) 
            { 
                try
                { 
                    st = new StringTokenizer(br.readLine()); 
                } 
                catch (IOException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
            return st.nextToken(); 
        } 

        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        } 

        long nextLong() 
        { 
            return Long.parseLong(next()); 
        } 

        double nextDouble() 
        { 
            return Double.parseDouble(next()); 
        } 

        String nextLine() 
        { 
            String str = ""; 
            try
            { 
                str = br.readLine(); 
            } 
            catch (IOException e) 
            { 
                e.printStackTrace(); 
            } 
            return str; 
        } 
    }
}