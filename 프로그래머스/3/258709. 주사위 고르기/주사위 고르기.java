import java.util.*;

class Solution {
    
    static int N;
    static int[] combination;
    static int[] bestCombination;
    static int maxWinCount;
    
    public int[] solution(int[][] dice) {
        int[] answer = {};
        
        N = dice.length;
        combination = new int[N/2];
        bestCombination = new int[N/2];
        maxWinCount = Integer.MIN_VALUE;
        
        generateCombination(dice, 0, 0);
        
        answer = new int[N/2];
        
        System.out.println(Arrays.toString(bestCombination));
        
        for (int i=0; i<N/2; i++) {
            answer[i] = bestCombination[i] + 1;
        }
        return answer;
    }
    
    public void generateCombination(int[][] dice, int depth, int idx) {
        if (depth == N/2) {
            // System.out.println(Arrays.toString(combination));
            int winCount = calculateWin(dice, combination);
            if (winCount > maxWinCount) {
                maxWinCount = winCount;
                bestCombination = combination.clone();
            }
            return;
        }
        
        for (int i=idx; i<N; i++) {
            combination[depth] = i;
            generateCombination(dice, depth+1, i+1);
        }
    }
    
    public int calculateWin(int[][] dice, int[] aCombination) {
        List<Integer> aDiceList = new ArrayList<>();
        List<Integer> bDiceList = new ArrayList<>();
        
        boolean[] isSelected = new boolean[N];
        
        for (int i: aCombination) {
            aDiceList.add(i);
            isSelected[i] = true;
        }
        
        for (int i=0; i<N; i++) {
            if (!isSelected[i]) {
                bDiceList.add(i);
            }
        }
        
        List<Integer> aSumList = new ArrayList<>();
        List<Integer> bSumList = new ArrayList<>();
        
        calculateSum(dice, aDiceList, 0, 0, aSumList);
        calculateSum(dice, bDiceList, 0, 0, bSumList);
        
        Collections.sort(bSumList);
        
        int winCount = 0;
        for (int aSum: aSumList) {
            winCount += calculateWinCount(bSumList, aSum);
        }
        
        return winCount;
    }
    
    public void calculateSum(int[][] dice, List<Integer> diceList, int idx, int currentSum, List<Integer> sumList) {
        if (idx == diceList.size()) {
            sumList.add(currentSum);
            return;
        }
        
        int diceIdx = diceList.get(idx);
        for (int i=0; i<6; i++) {
            calculateSum(dice, diceList, idx+1, currentSum + dice[diceIdx][i], sumList);
        }
    }
    
    public int calculateWinCount(List<Integer> bSumList, int aSum) {
        int start = 0;
        int end = bSumList.size() - 1;
        int count = 0;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (bSumList.get(mid) < aSum) {
                count = mid + 1;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        
        return count;
    }
}