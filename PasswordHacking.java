import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PasswordHacking {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfNums = fio.nextInt();
        Double[] probabilities = new Double[numOfNums];
        for (int i = 0; i < numOfNums; i++) {
            fio.next();
            probabilities[i] = fio.nextDouble();
        }
        Arrays.sort(probabilities, (x, y) -> y.compareTo(x));
        double totalAttempts = 0;
        for (int j = 0; j < numOfNums; j++) {
            totalAttempts += (j + 1) * probabilities[j];
        }
        fio.println(totalAttempts);

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
