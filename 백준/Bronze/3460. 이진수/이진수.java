import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			int num = Integer.parseInt(br.readLine());
			String binary = Integer.toBinaryString(num);
			// 이진수 맨 왼쪽부터 시작 -> 맨 왼쪽부터가 0번째임
			// 그러므로 이진수값을 뒤집어서 탐색을 하기 위해 StringBuilder 이용
			StringBuilder binary_sb = new StringBuilder(binary);
			String binaryReverse = binary_sb.reverse().toString();	// 이진수값 뒤집음
			
			for(int i=0; i<binaryReverse.length(); i++) {
				// 이진수로 나타내서 1인 값을 찾으면 해당 값의 위치 추가
				if(binaryReverse.charAt(i) == '1') {
					sb.append(i + " ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);

	}

}
