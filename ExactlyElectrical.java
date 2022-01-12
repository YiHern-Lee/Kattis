import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.lang.Math;

public class ExactlyElectrical {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int startX = fio.nextInt();
        int startY = fio.nextInt();
        int endX = fio.nextInt();
        int endY = fio.nextInt();
        int charge = fio.nextInt();

        int chargeRequired = Math.abs(startX - endX) + Math.abs(startY - endY);
        if ((charge >= chargeRequired) && (charge - chargeRequired) % 2 == 0) {
            fio.println("Y");
        } else {
            fio.println("N");
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