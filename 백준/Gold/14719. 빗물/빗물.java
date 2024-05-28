import java.util.*;
import java.io.*;

public class Main {
	
	static int H;	// 세로 길이
	static int W;	// 가로 길이
	static int[] heights;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		heights = new int[W];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = W - 1;
		
		int leftMaxHeight = heights[left];
		int rightMaxHeight = heights[right];
		
		int totalWater = 0;	// 고이는 빗물의 양
		
		// 투포인터 알고리즘 이용
		while (left < right) {
			if (heights[left] < heights[right]) {
				if (heights[left] >= leftMaxHeight) {
					leftMaxHeight = heights[left];
				}
				else {
					totalWater += leftMaxHeight - heights[left];
				}
				left++;
			}
			else {
				if (heights[right] >= rightMaxHeight) {
					rightMaxHeight = heights[right];
				}
				else {
					totalWater += rightMaxHeight - heights[right];
				}
				right--;
			}
		}
		
		System.out.println(totalWater);
		
	}

}