import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Conformity {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numFrosh = fio.nextInt();
        HashMap<String, Integer> modulesMap = new HashMap<>(501);
        for (int i = 0; i < numFrosh; i++) {
            Integer[] currModules = new Integer[5];
            for (int j = 0; j < 5; j++) {
                int moduleCode = fio.nextInt();
                currModules[j] = moduleCode;
            }
            Arrays.sort(currModules);
            String moduleKey = Arrays.toString(currModules);
            int occur = modulesMap.getOrDefault(moduleKey, 0);
            modulesMap.put(moduleKey, occur + 1);
        }
        
        int maxOccur = 0;
        int count = 0;
        for (Integer occur : modulesMap.values()) {
            if (occur > maxOccur) {
                maxOccur = occur;
                count = occur;
            }
            else if (occur == maxOccur)
                count += occur;
        }

        fio.println(count);
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
