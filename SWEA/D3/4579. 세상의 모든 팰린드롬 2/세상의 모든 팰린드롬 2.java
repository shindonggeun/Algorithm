import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			String input = br.readLine();
			char[] chArr = input.toCharArray();
			boolean exist = isExist(chArr);
			String result = "";
			
			if(exist) {
				result = "Exist";
			}
			else {
				result = "Not exist";
			}
			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.print(sb);

	}
	
	public static boolean isExist(char[] chArr) {
		int left = 0;
		int right = chArr.length-1;
		
		// 문자열의 길이의 반만 검사하면 된다
		// 와일드카드가 있으면 펠린드롬 의미가 없음 (exist)
		while(left<=right) {
			if(chArr[left] == '*' || chArr[right] == '*') {
				return true;
			}
			
			// 와일드카드가 없는데 투 포인터들이 가리키는 값들이 같지 않은 경우
			if(chArr[left] != chArr[right]) {
				return false;
			}
			
			left++;
			right--;
		}
		
		return true;
	}

}
