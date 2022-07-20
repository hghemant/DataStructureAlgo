import static java.lang.Math.max;

public class Knapsack {
    private int numOfItems;
    private int capacityOfKnapsack;
    private int[][] knapsackTable;
    private int totalBenefit;
    private int[] weights;
    private int[] values;

    public Knapsack (int numOfItems, int capacityOfKnapsack, int[] weights, int[] values) {
        this.numOfItems = numOfItems;
        this.capacityOfKnapsack = capacityOfKnapsack;
        this.weights = weights;
        this.values = values;
        this.knapsackTable = new int[numOfItems + 1][capacityOfKnapsack + 1];
    }
    public void solve() {
        for (int i = 1; i <= numOfItems; i++) {
            for (int j = 1; j <= capacityOfKnapsack; j++) {
                if(i==0 ||j==0){
                    knapsackTable[i][j]=0;
                }
                if(weights[i-1]<=j){
                    knapsackTable[i][j]=max(values[i-1]+knapsackTable[i-1][j-weights[i-1]],knapsackTable[i-1][j]);
                }else {
                    knapsackTable[i][j]=knapsackTable[i-1][j];
                }
            }
        }
        totalBenefit = knapsackTable[numOfItems][capacityOfKnapsack];
    }

    public void showResult() {
        System.out.println("Total benefit: " + totalBenefit);
        for (int n = numOfItems, w = capacityOfKnapsack; n > 0;) {
            if (knapsackTable[n][w] != 0 && knapsackTable[n - 1][w] != knapsackTable[n][w]) {
                System.out.println("We take item #: " + n + "m");
                w = w - n;
            } else {
                n--;
            }
        }
    }

    public static void main(String[] args) {
        int numOfItems = 3;
        int capacityOfKnapsack = 5;

        int[] weights = {4, 2, 3};
        int[] values = {10, 4, 7};
  /*      int[] weights = new int[numOfItems + 1];
        weights[1] = 4;
        weights[2] = 2;
        weights[3] = 3;

        int[] values = new int[numOfItems + 1];
        values[1] = 10;
        values[2] = 4;
        values[3] = 7;*/

        Knapsack knapsack = new Knapsack(numOfItems, capacityOfKnapsack, weights, values);
        knapsack.solve();
        knapsack.showResult();
    }


}
