import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		List<String> numberList = new ArrayList<>();
		numberList.add("ZRO"); numberList.add("ONE"); numberList.add("TWO"); 
		numberList.add("THR");	numberList.add("FOR");	numberList.add("FIV");
		numberList.add("SIX");	numberList.add("SVN");	numberList.add("EGT");
		numberList.add("NIN");
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			String test = st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			
			List<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				String number = st.nextToken();	// 해당 문자열 입력받아서
				// 입력받은 문자열과 매치되는 숫자값(인덱스)을 numberList에 찾아서 그 숫자값(인덱스)를 list에 저장
				list.add(numberList.indexOf(number));	
			}
			
			Collections.sort(list);	// 숫자값(인덱스)들이 저장된 리스트 오름차순 정렬
			
			// 출력부분
			sb.append(test).append("\n");
			
			// 숫자값(인덱스)들이 저장된 리스트에서 숫자값(인덱스)를 이용하여 매치되는 문자열을 뽑아낸다
			for(int num: list) {
				sb.append(numberList.get(num) + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
