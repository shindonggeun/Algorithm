import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] romanNum = {1, 5, 10, 50};
	static Set<Integer> resultSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		resultSet = new HashSet<>();
		
		generateCombination(0, 0, 0);
		
		System.out.println(resultSet.size());

	}
	
	public static void generateCombination(int depth, int idx, int num) {
		if (depth == N) {
			resultSet.add(num);
			return;
		}
		
		for (int i=idx; i<4; i++) {
			generateCombination(depth+1, i, num + romanNum[i]);
		}
	}

}