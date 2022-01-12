import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class BestRelayTeam {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int num_runners = fio.nextInt();
        Runner[] runners = new Runner[num_runners];
        for (int i = 0; i < num_runners; i++) {
            String name = fio.next();
            double firstLegTime = fio.nextDouble();
            double nextLegTime = fio.nextDouble();
            runners[i] = new Runner(name, firstLegTime, nextLegTime);
        }
        RunnerComparator rc = new RunnerComparator();
        Arrays.sort(runners, rc);
        double bestRuntime = -1;
        Runner[] bestRunners = new Runner[4];
        for (int i = 0; i < num_runners; i++) {
            Runner[] currRunTeam = new Runner[4];
            currRunTeam[0] = runners[i];
            double currRuntime = runners[i].firstLegtime;
            int nextRunTeamIdx = 1;
            int runnersNeeded = 3;
            int currRunnerIdx = 0;
            while (runnersNeeded > 0) {
                if (currRunnerIdx != i) {
                    currRuntime += runners[currRunnerIdx].nextLegtime;
                    currRunTeam[nextRunTeamIdx] = runners[currRunnerIdx];
                    runnersNeeded -= 1;
                    currRunnerIdx += 1;
                    nextRunTeamIdx += 1;
                } else {
                    currRunnerIdx += 1;
                }
            }
            if (bestRuntime < 0 || currRuntime < bestRuntime) {
                bestRunners = currRunTeam;
                bestRuntime = currRuntime;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(bestRuntime + "\n");
        for (Runner runner : bestRunners) {
            sb.append(runner.name + "\n");
        }
        fio.println(sb.toString());
        fio.close();
    }

    private static class Runner {
        public String name;
        public Double firstLegtime;
        public Double nextLegtime;
        public Runner(String name, double firstLegTime, double nextLegTime) {
            this.name = name;
            this.firstLegtime = firstLegTime;
            this.nextLegtime = nextLegTime;
        }
    }

    private static class RunnerComparator implements Comparator<Runner> {

        @Override
        public int compare(Runner r1, Runner r2) {
            return r1.nextLegtime.compareTo(r2.nextLegtime);
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
