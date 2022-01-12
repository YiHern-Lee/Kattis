import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.lang.Math;

public class Teque {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numInstr = fio.nextInt();
        TequeArray teque = new TequeArray(numInstr);
        for (int i = 0; i < numInstr; i++) {
            String instr = fio.next();
            int number = fio.nextInt();
            if (instr.equals("push_back"))
                teque.pushBack(number);
            else if (instr.equals("push_front"))
                teque.pushFront(number);
            else if (instr.equals("push_middle"))
                teque.pushMiddle(number);
            else 
                fio.println(teque.get(number));
        }
        fio.println(Arrays.toString(teque.firstHalf.arr));
        fio.println(Arrays.toString(teque.secondHalf.arr));
        fio.close();
        
    }

    static class TequeArray {
        DequeArray firstHalf;
        DequeArray secondHalf;
        int firstSize = 0;
        int secondSize = 0;

        TequeArray(int num) {
            firstHalf = new DequeArray(num);
            secondHalf = new DequeArray(num);
        }

        void pushFront(int num) {
            firstHalf.addFirst(num);
            firstSize += 1;

            if (firstSize - secondSize > 1) {
                secondHalf.addFirst(firstHalf.removeLast());
                firstSize -= 1;
                secondSize += 1;
            }
        }

        void pushBack(int num) {
            secondHalf.addLast(num);
            secondSize += 1;

            if (secondSize - firstSize >= 1) {
                firstHalf.addLast(secondHalf.removeFirst());
                secondSize -= 1;
                firstSize += 1;
            }
        }

        void pushMiddle(int num) {
            if (firstSize > secondSize) {
                secondHalf.addFirst(num);
                secondSize += 1;
            } else {
                firstHalf.addLast(num);
                firstSize += 1;
            }
        }

        int get(int idx) {
            if (idx >= firstSize) {
                return secondHalf.get(idx - firstSize);
            } else {
                return firstHalf.get(idx);
            }
        }
        
    }

    static class DequeArray {
        int[] arr;
        int front, back;
        int size;

        DequeArray(int size) {
            arr = new int[size];
            this.size = size;

        }

        void addFirst(int num) {
            if (empty()) {
                arr[front] = num;
                back = (back + 1) % size;
            } else {
                front = Math.floorMod(front - 1, size);
                arr[front] = num;
            }
        }

        void addLast(int num) {
            arr[back] = num;
            back = (back + 1) % size;
        }

        int removeFirst() {
            int num = arr[front];
            front = (front + 1) % size;
            return num;
        }

        int removeLast() {
            back = Math.floorMod(back - 1, size);
            int num = arr[back];
            return num;
        }

        boolean empty() {
            return front == back;
        }

        int get(int idx) {
            return arr[(front + idx) % size];
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
