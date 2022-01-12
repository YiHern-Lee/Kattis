import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class FerryLoadingIV {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numCases = fio.nextInt();
        for (int i = 0; i < numCases; i++) {
            int ferryLength = fio.nextInt() * 100;
            int numFerries = fio.nextInt();
            Deque<Integer> leftFerries = new ArrayDeque<>(numFerries);
            Deque<Integer> rightFerries = new ArrayDeque<>(numFerries); 
            for (int j = 0; j < numFerries; j++) {
                int currFerryLength = fio.nextInt();
                String ferrySide = fio.next();
                if (ferrySide.equals("left"))
                    leftFerries.addLast(currFerryLength);
                else
                    rightFerries.addLast(currFerryLength);
            }
            boolean isFerryLeft = true;
            int crosses = 0;
            int currLoad = 0;
            while (leftFerries.size() > 0 || rightFerries.size() > 0) {
                if (isFerryLeft) {
                    while (leftFerries.size() > 0) {
                        int queueHead = leftFerries.peekFirst();
                        if (currLoad + queueHead > ferryLength) {
                            break;
                        } else {
                            leftFerries.removeFirst();
                            currLoad += queueHead;
                        }
                    }
                } else {
                    while (rightFerries.size() > 0) {
                        int queueHead = rightFerries.peekFirst();
                        if (currLoad + queueHead > ferryLength) {
                            break;
                        } else {
                            rightFerries.removeFirst();
                            currLoad += queueHead;
                        }
                    }
                }
                isFerryLeft = !isFerryLeft;
                crosses += 1;
                currLoad = 0;
            }
            fio.println(crosses);
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
