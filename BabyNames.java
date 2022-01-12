import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BabyNames {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        Trie trieTree = new Trie();
        int instr = 0;
        do {
            instr = fio.nextInt();
            if (instr == 1) {
                String name = fio.next();
                int genderSuitability = fio.nextInt();
                trieTree.insert(name, genderSuitability);
            } else if (instr == 2) {
                String name = fio.next();
                trieTree.delete(name);
            } else if (instr == 3) {
                String start = fio.next();
                String end = fio.next();
                int genderSuitability = fio.nextInt();
                if (genderSuitability == 0) 
                    fio.println(trieTree.query(start, end));
                else 
                    fio.println(trieTree.query(start, end, genderSuitability));
            }
        } while (instr != 0);
        fio.close();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String key, int type) {
            TrieNode currNode = root;
            for (int i = 0; i < key.length(); i++) {
                currNode = currNode.getNextOrInsert(key.charAt(i) - 'A', type);
            }
            currNode.markEnd(type);
        }

        void delete(String key) {
            root.delete(key, 0);
        }

        int query(String start, String end) {
            return root.query(start, 0) - root.query(end, 0);
        }

        int query(String start, String end, int type) {
            return root.query(start, 0, type) - root.query(end, 0, type);
        } 
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isName = false;
        int type;
        int numWords = 0;
        int[] numWordsByType = new int[] {0, 0};

        TrieNode getNextOrInsert(int id, int type) {
            numWords += 1;
            numWordsByType[type - 1] += 1;
            return children[id] == null ? insert(id) : children[id];
        }

        TrieNode getNext(int id) {
            return children[id] == null ? null : children[id];
        }

        TrieNode insert(int id) {
            return children[id] = new TrieNode();
        }

        void markEnd(int type) {
            this.isName = true;
            this.type = type;
            numWords += 1;
            numWordsByType[type - 1] += 1;
        }

        int delete(String key, int currIdx) {
            if (currIdx == key.length()) {
                this.isName = false;
                numWords -= 1;
                numWordsByType[this.type - 1] -= 1;
                return this.type;
            }
            int next = key.charAt(currIdx) - 'A';
            TrieNode nextNode = children[next];
            if (nextNode != null) {
                int type = nextNode.delete(key, currIdx + 1);
                numWords -= 1;
                numWordsByType[type - 1] -= 1;
                return type;
            } else {
                return 0;
            }
        }
        
        int query(String query, int index) {
            if (index < query.length() - 1) {
                int next = query.charAt(index) - 'A';
                int sum = 0;
                for (int i = next + 1; i < 26; i++) {
                    if (children[i] != null)
                        sum += children[i].numWords;
                }
                if (children[next] != null) {
                    sum += children[next].query(query, index + 1);
                }
                return sum;
            } else {
                int next = query.charAt(index) - 'A';
                int sum = 0;
                for (int i = next; i < 26; i++) {
                    if (children[i] != null)
                        sum += children[i].numWords;
                }
                return sum;
            }
        }

        int query(String query, int index, int type) {
            if (index < query.length() - 1) {
                int next = query.charAt(index) - 'A';
                int sum = 0;
                for (int i = next + 1; i < 26; i++) {
                    if (children[i] != null)
                        sum += children[i].numWordsByType[type - 1];
                }
                if (children[next] != null) {
                    sum += children[next].query(query, index + 1, type);
                }
                return sum;
            } else {
                int next = query.charAt(index) - 'A';
                int sum = 0;
                for (int i = next; i < 26; i++) {
                    if (children[i] != null)
                        sum += children[i].numWordsByType[type - 1];
                }
                return sum;
            }
        }
    }

    static class FastIO extends PrintWriter {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}