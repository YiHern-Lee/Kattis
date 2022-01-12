import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CardTrading {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int cardNumber = fio.nextInt();
        int cardTypes = fio.nextInt();
        int numCombosNeeded = fio.nextInt();
        // 0 -> Buy Price, 1 -> Sell Price, 2 -> |Sell - Buy|, 3 -> Cards in hand, 4 -> Cards Bought
        int[][] cardsByTypes = new int[cardTypes][5];
        long profits = 0;
        for (int i = 0; i < cardNumber; i++) {
            int cardType = fio.nextInt();
            cardsByTypes[cardType - 1][3] += 1;
        }
        for (int i = 0; i < cardTypes; i++){
            int buyPrice = fio.nextInt();
            int sellPrice = fio.nextInt();
            int cardsInHand = cardsByTypes[i][3];
            int cardValue = sellPrice * cardsInHand + (2 - cardsInHand) * buyPrice;
            cardsByTypes[i][0] = buyPrice;
            cardsByTypes[i][1] = sellPrice;
            cardsByTypes[i][2] = cardValue;
        }
        
        Arrays.sort(cardsByTypes, (a, b) -> a[2] - b[2]);
        /* fio.println("Sorted by value");
        for (int i = 0; i < cardsByTypes.length; i++) {
            fio.println(Arrays.toString(cardsByTypes[i]));
        }
        fio.println("\n"); */

        for (int i = 0; i < numCombosNeeded; i++) {
            int cardsInHand = cardsByTypes[i][3];
            if (cardsInHand < 2) {
                int cardsBought = 2 - cardsInHand;
                profits -= cardsBought * cardsByTypes[i][0];
                /* fio.println("CARDS BOUGHT FOR: " + cardsBought * cardsByTypes[i][0]); */
                cardsByTypes[i][4] = cardsBought;
            }
        }

        for (int i = numCombosNeeded; i < cardTypes; i++) {
            int cardsInHand = cardsByTypes[i][3];
            if (cardsInHand > 0) {
                /* fio.println("CARDS SOLD FOR: " + cardsInHand * cardsByTypes[i][1]); */
                profits += cardsInHand * cardsByTypes[i][1];
            }
        }

        fio.println(profits);

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