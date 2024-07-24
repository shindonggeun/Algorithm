import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] abilityArr;
	static boolean[] visited;
	static int maxAbility;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			
			abilityArr = new int[11][11];
			visited = new boolean[11];
			
			for (int i=0; i<11; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<11; j++) {
					abilityArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxAbility = Integer.MIN_VALUE;
			findPosition(0, 0);
			
			sb.append(maxAbility).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void findPosition(int player, int sum) {
		if (player == 11) {
			maxAbility = Math.max(maxAbility, sum);
			return;
		}
		
		for (int position=0; position<11; position++) {
			if (!visited[position] && abilityArr[player][position] > 0) {
				visited[position] = true;
				findPosition(player + 1, sum + abilityArr[player][position]);
				visited[position] = false;
			}
		}
	}

}
