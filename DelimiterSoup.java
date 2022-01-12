import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class DelimiterSoup {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numChars = fio.nextInt();
        char[] chars = fio.nextLine().toCharArray();
        Deque<Character> charStack = new ArrayDeque<>(numChars);
        boolean ok = true;
        for (int i = 0; i < numChars; i++) {
            char currChar = chars[i];
            if (currChar == ')' || currChar == '}' || currChar == ']') {
                if (charStack.size() > 0) {
                    char prevChar = charStack.pop();
                    if (currChar == ')' && prevChar != '(') {
                        fio.println(String.format("%c %d", currChar, i));
                        ok = false;
                        break;
                    } else if (currChar == ']' && prevChar != '[') {
                        fio.println(String.format("%c %d", currChar, i));
                        ok = false;
                        break;
                    } else if (currChar == '}' && prevChar != '{') {
                        fio.println(String.format("%c %d", currChar, i));
                        ok = false;
                        break;
                    }
                } else {
                    fio.println(String.format("%c %d", currChar, i));
                    ok = false;
                    break;
                }
            } else if (currChar == '(' || currChar == '{' || currChar == '[') {
                charStack.push(currChar);
            }
        }

        
        if (ok) 
            fio.println("ok so far");
        

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