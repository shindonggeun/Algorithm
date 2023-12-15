import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 나무의 수
	static int M;	// 가져가야 할 나무의 길이
	static int[] treeArr;	// 나무들의 높이들을 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		treeArr = new int[N];	// [0] ~ [N]
		int maxHeight = 0;	// 나무들 중 가장 높은 나무의 높이를 저장할 변수
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			treeArr[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(maxHeight, treeArr[i]);	// 가장 높은 나무의 높이 갱신
		}
		
		long maxCutter = binarySearch(0, maxHeight);	// 이분탐색 알고리즘을 이용하여 최적의 절단기 높이 저장
		System.out.println(maxCutter);	
		
	}
	
	// 최적의 절단기 높이를 찾는 메서드 (이분탐색 알고리즘 이용)
	public static long binarySearch(int low, int high) {
		while(low<=high) {
			int mid = (low + high) / 2;	// 나무 높이의 중간값을 절단 높이로 설정
			long sum = 0;	// 절단기로 나무들 다 잘라서 남은 높이들 합한 값 (즉, 잘라진 나무의 총 길이)
			
			// 해당 나무들 탐색
			for(int tree: treeArr) {
				// 나무 높이가 절단 높이보다 큰 경우
				if(tree > mid) {
					sum += (tree - mid);	// 절단된 나무 길이 합산
				}
			}
			
			
			// 절단된 나무 길이가 필요한 길이보다 작은 경우
			if(sum < M) {
				high = mid - 1;	// 절단 높이 낯춤 (상한을 중간값 - 1로 조정하여 탐색 범위 줄임)
			}
			// 절단된 나무 길이가 필요한 길이보다 크거나 같은 경우
			else {
				low = mid + 1;	// 절단 높이 높임 (하한을 중간값 + 1로 조정하여 탐색 범위 줄임) 
			}
		}
		
		return high;	// 최적의 절단 높이 반환 (절단기로 설정할 수 있는 높이의 최대값)
	}

}
