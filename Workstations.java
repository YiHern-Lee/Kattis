import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Workstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt();
        long m = fio.nextLong();
        PriorityQueue<Researcher> arrivals = new PriorityQueue<>(n > 0 ? n : 11, (x, y) -> Long.compare(x.arrivalTime, y.arrivalTime));
        for (int i = 0; i < n; i++) {
            long arrivalTime = fio.nextLong();
            long workTime = fio.nextLong();
            Researcher currResearcher = new Researcher(arrivalTime, workTime);
            arrivals.add(currResearcher);
        }
        PriorityQueue<Researcher> workstations = new PriorityQueue<>(n > 0 ? n : 11, (x, y) -> Long.compare(x.finishTime, y.finishTime));
        int unlocksSaved = 0;
        while (!arrivals.isEmpty()) {
            Researcher currResearcher = arrivals.poll();
            while (!workstations.isEmpty() && workstations.peek().finishTime + m < currResearcher.arrivalTime) {
                workstations.poll();
            }
            if (!workstations.isEmpty() && workstations.peek().finishTime <= currResearcher.arrivalTime
                    && workstations.peek().finishTime + m >= currResearcher.arrivalTime) {
                workstations.poll();
                workstations.add(currResearcher);
                unlocksSaved++;
            } else {
                workstations.add(currResearcher);
            }
        }
        fio.println(unlocksSaved);
        fio.close();
    }

    static class Researcher {
        long arrivalTime;
        long finishTime;

        Researcher(long arrivalTime, long workTime) {
            this.arrivalTime = arrivalTime;
            this.finishTime = arrivalTime + workTime;
        }

        @Override
        public String toString() {
            return "[" + this.arrivalTime + "," + this.finishTime + "]";
        }
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