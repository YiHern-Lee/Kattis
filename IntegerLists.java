import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


public class IntegerLists {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numCases = fio.nextInt();
        for (int i = 0; i < numCases; i++) {
            String instructions = fio.nextLine();
            int size = fio.nextInt();
            String[] listStr = fio.nextLine().split("[\\]\\[,]");
            IntegerArray intList = new IntegerArray(size + 1);
            for (int j = 0; j < listStr.length; j++) {
                if (!listStr[j].equals("")) 
                    intList.addLast(Integer.parseInt(listStr[j]));
            }
            for (int j = 0; j < instructions.length(); j++) {
                if (instructions.charAt(j) == 'D')
                    intList.drop();
                else 
                    intList.reversed = !intList.reversed;
            }
            fio.println(intList);
        }

        fio.close();
    }

    static class IntegerArray {
        int[] arr;
        int front, back;
        int size;
        int currSize = 0;
        boolean error = false;
        boolean reversed = false;

        IntegerArray(int size) {
            arr = new int[size];
            this.size = size;
        }

        void drop() {
            if (reversed)
                removeLast();
            else
                removeFirst();
        }

        void addFirst(int num) {
            if (empty()) {
                arr[front] = num;
                back = (back + 1) % size;
            } else {
                front = Math.floorMod(front - 1, size);
                arr[front] = num;
            }
            currSize++;
        }

        void addLast(int num) {
            arr[back] = num;
            back = (back + 1) % size;
            currSize++;
        }

        int removeFirst() {
            if (empty()) {
                error = true;
                return 0;
            } else {
                int num = arr[front];
                front = (front + 1) % size;
                currSize--;
                return num;
            }
        }

        int removeLast() {
            if (empty()) {
                error = true;
                return 0;
            } else {
                back = Math.floorMod(back - 1, size);
                int num = arr[back];
                currSize--;
                return num;
            }
        }

        boolean empty() {
            return front == back;
        }

        int get(int idx) {
            return arr[(front + idx) % size];
        }

        @Override
        public String toString() {
            if (error) {
                return "error";
            } else {
                if (reversed) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    for (int i = 0; i < currSize; i++) {
                        sb.append(arr[Math.floorMod(back - (i + 1), size)]);
                        if (i != currSize - 1)
                            sb.append(',');
                    }
                    sb.append(']');
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    for (int i = 0; i < currSize; i++) {
                        sb.append(arr[Math.floorMod(front + i, size)]);
                        if (i != currSize - 1)
                            sb.append(',');
                    }
                    sb.append(']');
                    return sb.toString();
                }
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
