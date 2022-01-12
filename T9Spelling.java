import java.io.*;
import java.util.*;
import java.lang.StringBuilder;

public class T9Spelling {

    private static String[] letterMappings = {"2", "22", "222", "3", "33", "333", "4", "44", 
        "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", 
        "9", "99", "999", "9999", " ", " ", " ", " ", " ", " ", "0"};
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfCases = fio.nextInt();
        for (int i = 0; i < numOfCases; i++) {
            String word = fio.nextLine();
            StringBuilder sb = new StringBuilder();
            String last = " ";
            for (int j = 0; j < word.length(); j++) {
                int index = (int) word.charAt(j) % 97;
                String digits = letterMappings[index];
                if (last.charAt(0) == digits.charAt(0)) {
                    sb.append(" ");
                }
                sb.append(digits);
                last = digits;
            }
            fio.println(String.format("Case #%d: %s", i + 1, sb.toString()));
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
                catch (IOException  e) 
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
