import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int minMove = calculateMinMove(x, y);
			sb.append(minMove).append("\n");
		}
		
		System.out.print(sb);

	}
	
	public static int calculateMinMove(int x, int y) {
		int distance = y - x;
		int max = (int) Math.sqrt(distance);
		
		if (distance == max * max) {
			return 2 * max - 1;
		}
		else if (distance <= max * max + max) {
			return 2 * max;
		}
		else {
			return 2 * max + 1;
		}
	}

}