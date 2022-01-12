import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.IOException;

public class SortOfSorting {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while (true) {
            int numOfNames = fio.nextInt();
            if (numOfNames <= 0) {
                break;
            }
            String[] names = new String[numOfNames];
            for (int i = 0; i < numOfNames; i++) {
                names[i] = fio.next();
            }
            Arrays.sort(names, new NameComparator());
            for (int i = 0; i < numOfNames; i++) {
                fio.println(names[i]);
            }
            fio.println("");
        }

        fio.close();
        
    }

    static class NameComparator implements Comparator<String> {

        @Override
        public int compare(String name1, String name2) {
            int firstCharComp = Character.compare(name1.charAt(0), name2.charAt(0));
            if (firstCharComp == 0) {
                return Character.compare(name1.charAt(1), name2.charAt(1));
            } else {
                return firstCharComp;
            }
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