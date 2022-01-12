import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SumKindOfProblem {
    
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfDataSets = fio.nextInt();
        for (int i = 0; i < numOfDataSets; i++) {
            int dataSetNum = fio.nextInt();
            int dataSet = fio.nextInt();
            long sum1 = 0;
            long sum2 = 0;
            long sum3 = 0;
            for (int j = 1; j <= dataSet; j++) {
                sum1 += j;
                sum2 += j * 2 - 1;
                sum3 += j * 2;
            }
            fio.print(String.format("%d %d %d %d", dataSetNum, sum1, sum2, sum3));
            fio.println();
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
