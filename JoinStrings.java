import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.IOException;

public class JoinStrings {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfStrings = fio.nextInt();
        DoubleEndedLinkedList[] strings = new DoubleEndedLinkedList[numOfStrings];
        for (int i = 0; i < numOfStrings; i++) {
            strings[i] = new DoubleEndedLinkedList(fio.next());
        }

        int lastInstructionNode = 0;
        for (int i = 0; i < numOfStrings - 1; i++) {
            int firstNode = fio.nextInt() - 1;
            int secondNode = fio.nextInt() - 1;
            strings[firstNode].tail.next = strings[secondNode].head;
            strings[firstNode].tail = strings[secondNode].tail;
            if (i == numOfStrings - 2) {
                lastInstructionNode = firstNode;
            }
        }
        int idx = 0;
        StringNode currNode = strings[lastInstructionNode].head;
        while (idx < numOfStrings) {
            fio.print(currNode);
            currNode = currNode.next;
            idx += 1;
        }

        fio.close();
    }

    static class DoubleEndedLinkedList {
        public StringNode head;
        public StringNode tail;

        public DoubleEndedLinkedList(String str) {
            this.head = new StringNode(str, null);
            this.tail = this.head;
        }

    }

    static class StringNode {
        public String str;
        public StringNode next;

        public StringNode(String str, StringNode next) {
            this.str = str;
            this.next = next;
        }

        @Override
        public String toString() {
            return str;
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
