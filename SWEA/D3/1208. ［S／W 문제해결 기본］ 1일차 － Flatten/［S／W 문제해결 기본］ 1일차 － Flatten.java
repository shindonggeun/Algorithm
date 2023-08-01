import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {
			int dumpCount = Integer.parseInt(br.readLine());
			
			int[] heightArr = new int[100];	// 높이를 담은 배열(가로 길이는 항상 100이므로 0 ~ 99번 방까지)
			
			String input = br.readLine();	// 한줄 입력 받기
			String[] inputSplit = input.split(" ");	// 입력받은거 공백단위로 끊기
			// 공백단위로 끊은 값들 높이를 담은 배열에 저장하기
			for(int i=0; i<inputSplit.length; i++) {
				heightArr[i] = Integer.parseInt(inputSplit[i]);
			}
			
			Arrays.sort(heightArr);	//높이를 담은 배열 오름차순 정렬
			
			// 덤프 횟수만큼 반복문 돌리기
			for(int i=0; i<dumpCount; i++) {
				heightArr[99]--;	// 덤프 높이가 제일 높은거에서 빼주고
				heightArr[0]++;		// 덤프 높이가 제일 낮은거에서 더해주고
				Arrays.sort(heightArr);	// 다시 덤프 높이를 담은 배열 오름차순 정렬
			}
			
			sb.append("#").append(tc).append(" ").append(heightArr[99] - heightArr[0]).append("\n");
		}
		System.out.print(sb);

	}

}
