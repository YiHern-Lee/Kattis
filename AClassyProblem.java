import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AClassyProblem {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int numOfCases = fio.nextInt();
        for (int i = 0; i < numOfCases; i++) {
            int numOfPersons = fio.nextInt();
            Person[] persons = new Person[numOfPersons];
            for (int j = 0; j < numOfPersons; j++) {
                String descriptor = fio.nextLine();
                String[] s = descriptor.split("-|: | |class");
                persons[j] = new Person(s);
            }
            Arrays.sort(persons);
            for (int k = 0; k < numOfPersons; k++) {
                fio.println(persons[k]);
            }
            fio.println("==============================");
        }

        fio.close();
    }

    static class Person implements Comparable<Person> {
        int[] classes;
        String name;

        public Person(String[] descriptor) {
            this.classes = new int[10];
            int classesId = 9;
            for (int i = descriptor.length - 1; i >= 1; i--) {
                this.classes[classesId] = classToNum(descriptor[i]);
                classesId--;
            }
            this.name = descriptor[0];
        }

        static int classToNum(String classStatus) {
            if (classStatus.equals("upper")) {
                return 1;
            } else if (classStatus.equals("lower")) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public int compareTo(Person anotherPerson) {
            for (int i = 9; i >= 0; i--) {
                if (this.classes[i] > anotherPerson.classes[i]) 
                    return -1;
                
                else if (this.classes[i] < anotherPerson.classes[i]) 
                    return 1;
                
            }
            return this.name.compareTo(anotherPerson.name);
        }

        @Override
        public String toString() {
            return name;
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
