import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] sensorArr = new int[N];	// 센서들의 좌표를 담은 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sensorArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sensorArr);	// 센서 좌표들 오름차순 정렬
		
		int[] distanceDiffArr = new int[N-1];	// 센서들의 좌표(거리) 차이를 저장한 배열
		for(int i=0; i<N-1; i++) {
			distanceDiffArr[i] = sensorArr[i+1] - sensorArr[i];
		}
		
		
		Arrays.sort(distanceDiffArr);	// 센서들끼리의 거리 차이를 담은 배열 오름차순 정렬
		
		int minDistance = 0;	// 수신 가능영역의 길이의 합 최소
		
		// 가장 긴 거리를 K-1번까지 빼면서 합산
		// K-1개의 가장 긴 거리를 제외하고 나머지 거리를 더하여 수신 가능 영역의 길이를 최소화
        for(int i=0; i<N-K; i++) {
        	minDistance += distanceDiffArr[i];
        }
		System.out.println(minDistance);

	}

}
