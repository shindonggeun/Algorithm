import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] ingredient;
	static int[] minValue; // 각 영양 성분의 최소값들을 저장하는 배열 (단백질, 지방, 탄수화물, 비타민)
	static int minCost;
	static List<Integer> combination;
	static List<Integer> resultCombination;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		minValue = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			minValue[i] = Integer.parseInt(st.nextToken());
		}
		
		ingredient = new int[N][5];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<5; j++) {
				ingredient[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minCost = Integer.MAX_VALUE;
		combination = new ArrayList<>();
		resultCombination = new ArrayList<>();
		
		generateCombination(new ArrayList<>(), 0);
		
		if (minCost == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(minCost).append("\n");
		
		for (int idx: resultCombination) {
			sb.append(idx+1).append(" ");
		}
		
		System.out.println(sb);
	}
	
	public static void generateCombination(List<Integer> combination, int idx) {
		calculateMinCost(combination);
		
		for (int i=idx; i<N; i++) {
			combination.add(i);
			generateCombination(combination, i + 1);
			combination.remove(combination.size() - 1);
		}
	}
	
	public static void calculateMinCost(List<Integer> combination) {
		int sumP = 0; // 단백질의 영양성분 합
		int sumF = 0; // 지방의 영양성분 합
		int sumS = 0; // 탄수화물의 영양성분 합
		int sumV = 0; // 비타민들의 영양성분 합
		int sumCost = 0; // 식재료들의 비용 합
		
		for (int idx: combination) {
			sumP += ingredient[idx][0];
			sumF += ingredient[idx][1];
			sumS += ingredient[idx][2];
			sumV += ingredient[idx][3];
			sumCost += ingredient[idx][4];
		}
		
		if (sumP >= minValue[0] && sumF >= minValue[1] && 
				sumS >= minValue[2] && sumV >= minValue[3]) {
			if (sumCost < minCost) {
				minCost = sumCost;
				resultCombination = new ArrayList<>(combination);
			}
		}
	}

}