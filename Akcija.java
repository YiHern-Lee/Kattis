import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Akcija {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfBooks = fio.nextInt();
        Integer[] bookPrices = new Integer[numOfBooks];
        for (int i = 0; i < numOfBooks; i++) {
            bookPrices[i] = fio.nextInt();
        }
        Arrays.sort(bookPrices, (x, y) -> y - x);

        long total = 0;
        int currBooksCounted = 0;
        for (int i = 0; i < numOfBooks; i++) {
            if (currBooksCounted < 2) {
                total += bookPrices[i];
                currBooksCounted++;
            } else {
                currBooksCounted = 0;
            }
        }
        fio.println(total);

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