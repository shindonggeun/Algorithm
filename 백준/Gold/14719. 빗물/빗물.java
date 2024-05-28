import java.util.*;
import java.io.*;

public class Main {
	
	static int H;	// 세로 길이
	static int W;	// 가로 길이
	static int[] heights;	// 블록의 높이를 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		heights = new int[W];	// [0] ~ [W-1]
		
		st = new StringTokenizer(br.readLine());
		// 가로 길이만큼 반복
		for (int i=0; i<W; i++) {
			heights[i] = Integer.parseInt(st.nextToken());	// 각 위치의 블록 높이를 배열에 저장
		}
		
		int left = 0;	// 왼쪽 포인터 초기화
		int right = W - 1;	// 오른쪽 포인터 초기화
		
		int leftMaxHeight = heights[left];	// 왼쪽 포인터가 가리키는 최대 높이 초기화
		int rightMaxHeight = heights[right];	// 오른쪽 포인터가 가리키는 최대 높이 초기화
		
		int totalWater = 0;	// 고이는 빗물의 총량
		
		// 투포인터 알고리즘 이용
		while (left < right) {
			// 왼쪽 블록 높이가 오른쪽 블록 높이보다 작은 경우
			if (heights[left] < heights[right]) {
				// 현재 왼쪽 블록 높이가 왼쪽 최대 높이보다 크거나 같은 경우
				if (heights[left] >= leftMaxHeight) {
					leftMaxHeight = heights[left];	// 왼쪽 최대 높이 갱신
				}
				// 현재 왼쪽 블록 높이가 왼쪽 최대 높이보다 작은 경우
				else {
					// 해당 경우에 빗물이 고일 수 있으므로 고일 수 있는 물의 양 더해줌
					totalWater += leftMaxHeight - heights[left];
				}
				left++;	// 왼쪽 포인터를 오른쪽으로 이동해줌
			}
			// 왼쪽 블록 높이가 오른쪽 블록 높이보다 크거나 같은 경우
			else {
				// 현재 오른쪽 블록 높이가 오른쪽 최대 블록 높이보다 크거나 같은 경우
				if (heights[right] >= rightMaxHeight) {
					rightMaxHeight = heights[right];	// 오른쪽 최대 높이 갱신
				}
				else {
					// 해당 경우에 빗물이 고일 수 있으므로 고일 수 있는 물의 양 더해줌
					totalWater += rightMaxHeight - heights[right];
				}
				right--;	// 오른쪽 포인터를 왼쪽으로 이동해줌
			}
		}
		
		System.out.println(totalWater);
		
	}

}