import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GCPC {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int n = fio.nextInt();
        int events = fio.nextInt();
        Team[] teams = new Team[n];
        for (int i = 0; i < n; i++) {
            teams[i] = new Team(i + 1);
        }
        Team firstTeam = teams[0];
        AVL<Team> tree = new AVL<>(new BSTVertex<Team>(firstTeam));
        for (int i = 1; i < n; i++) {
            tree.insert(teams[i]);
        }

        for (int i = 0; i < events; i++) {
            int currTeamIdx = fio.nextInt() - 1;
            int currPenalty = fio.nextInt();
            Team currTeam = teams[currTeamIdx];
            tree.delete(currTeam);
            Team newTeam = new Team(currTeam, currPenalty);
            teams[currTeamIdx] = newTeam;
            tree.insert(newTeam);
            fio.println(tree.rank(teams[0]));
        }
        
        fio.close();
    }

    static class BSTVertex<V> {
        public BSTVertex<V> parent, left, right;
        public int height, size;
        public V key;

        BSTVertex(V v) {
            key = v;
            parent = left = right = null;
            height = 0;
            size = 1;
        }
    }

    static class AVL<V extends Comparable<V>> {
        public BSTVertex<V> root;

        public AVL(BSTVertex<V> root) {
            this.root = root;
        }

        public void insert(V v) {
            root = insert(root, v); // -> must return the actual root at the end
        }

        public BSTVertex<V> insert(BSTVertex<V> T, V v) {
            if (T == null) return new BSTVertex<>(v);

            if (T.key.compareTo(v) < 0) {
                T.right = insert(T.right, v);
                T.right.parent = T;
            } else {
                T.left = insert(T.left, v);
                T.left.parent = T;
            }

            // rebalance w.r.t T
            updateHeight(T);
            updateSize(T);
            T = rebalance(T);
            return T;
        }

        public BSTVertex<V> rebalance(BSTVertex<V> T) {
            int balance = getBalance(T);
            int balanceLeft = getBalance(T.left);
            int balanceRight = getBalance(T.right);

            if (balance > 1 && (balanceLeft >= 0 && balanceLeft <= 1))
                return rightRotate(T);
            if (balance < -1 && (balanceRight >= -1 && balanceRight <= 0))
                return leftRotate(T);
            if (balance > 1 && balanceLeft == -1) {
                T.left = leftRotate(T.left);
                return rightRotate(T);
            }
            if (balance < -1 && balanceRight == 1) {
                T.right = rightRotate(T.right);
                return leftRotate(T);
            }
            return T;
        }

        public BSTVertex<V> rightRotate(BSTVertex<V> T) {
            BSTVertex<V> TLeft = T.left;
            BSTVertex<V> TLRight = TLeft.right;
            TLeft.right = T;
            T.left = TLRight;

            updateHeight(T);
            updateSize(T);
            updateHeight(TLeft);
            updateSize(TLeft);
            return TLeft;
        }

        public BSTVertex<V> leftRotate(BSTVertex<V> T) {
            BSTVertex<V> TRight = T.right;
            BSTVertex<V> TRLeft = TRight.left;
            TRight.left = T;
            T.right = TRLeft;

            updateHeight(T);
            updateSize(T);
            updateHeight(TRight);
            updateSize(TRight);
            return TRight;
        }

        public void updateHeight(BSTVertex<V> n) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
        }

        public void updateSize(BSTVertex<V> n) {
            n.size = size(n.left) + size(n.right) + 1;
        }

        public int height(BSTVertex<V> n) {
            return n == null ? -1 : n.height;
        }

        public int size(BSTVertex<V> n) {
            return n == null ? 0 : n.size;
        }

        public int getBalance(BSTVertex<V> n) {
            return n == null ? 0 : height(n.left) - height(n.right);
        }

        public V findMin(BSTVertex<V> n) {
            if (n.left == null) return n.key;
            return findMin(n.left);
        }

        public V search(V key) {
            BSTVertex<V> res = search(root, key);
            return res == null ? null : res.key;
        }

        public BSTVertex<V> search(BSTVertex<V> T, V key) {
            if (T == null) return null;
            else if (T.key.compareTo(key) == 0) return T;
            else if (T.key.compareTo(key) < 0) return search(T.right, key);
            else return search(T.left, key);
        }

        public V successor(V key) {
            BSTVertex<V> find = search(root, key);
            return find == null ? null : successor(find);
        }

        public V successor(BSTVertex<V> T) {
            if (T.right != null)
                return findMin(T.right);
            else {
                BSTVertex<V> parent = T.parent;
                BSTVertex<V> current = T;
                while (parent != null && current == parent.right) {
                    current = parent;
                    parent = current.parent;
                }
                return parent == null ? null : parent.key;
            }
        }

        public void delete(V key) {
            root = delete(root, key);
        }

        public BSTVertex<V> delete(BSTVertex<V> T, V key) {
            if (T == null) return T;

            if (T.key.compareTo(key) < 0)
                T.right = delete(T.right, key);
            else if (T.key.compareTo(key) > 0)
                T.left = delete(T.left, key);
            else {
                if (T.left == null && T.right == null)
                    return null;
                else if (T.left == null && T.right != null) {
                    T.right.parent = T.parent;
                    T = T.right;
                } else if (T.left != null && T.right == null) {
                    T.left.parent = T.parent;
                    T = T.left;
                } else {
                    V successor = successor(key);
                    T.key = successor;
                    T.right = delete(T.right, successor);
                }
            }
            updateHeight(T);
            updateSize(T);
            T = rebalance(T);
            return T;
        }

        public void inorder() { 
            inorder(root);
            System.out.println();
        }
        
            // helper method to perform inorder traversal
        public void inorder(BSTVertex<V> T) {
            if (T == null) return;
            inorder(T.left);                               // recursively go to the left
            System.out.println(T.key);                      // visit this BST node
            inorder(T.right);                             // recursively go to the right
        }

        public int rank(BSTVertex<V> T, V key) {
            if (T.key.compareTo(key) == 0) {
                return size(T.right) + 1;
            } else if (T.key.compareTo(key) > 0)
                return size(T.right) + rank(T.left, key) + 1;
            else return rank(T.right, key);
        }

        public int rank(V key) {
            return rank(root, key);
        }
    }

    static class Team implements Comparable<Team> {
        int score;
        int penalty;
        int id;

        Team(int id) {
            score = penalty = 0;
            this.id = id;
        }

        Team(Team oldTeam, int penalty) {
            this.score = oldTeam.score + 1;
            this.penalty = oldTeam.penalty + penalty;
            this.id = oldTeam.id;
        }

        @Override
        public int compareTo(Team T) {
            if (this.score > T.score) 
                return 1;
            else if (this.score < T.score)
                return -1;
            else {
                if (this.penalty < T.penalty)
                    return 1;
                else if (this.penalty > T.penalty)
                    return -1;
                else {
                    if (this.id < T.id)
                        return 1;
                    else if (this.id > T.id)
                        return -1; 
                    else return 0;
                }
            }
        }

        @Override
        public String toString() {
            return "Team: " + id + "; ";
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