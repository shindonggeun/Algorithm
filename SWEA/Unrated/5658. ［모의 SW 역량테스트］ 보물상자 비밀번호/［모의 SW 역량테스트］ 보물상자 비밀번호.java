import java.util.*;
import java.io.*;

public class Solution {

	static int N;	// N개의 숫자
	static int K;	// 찾아야할 K번째로 큰 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			String input = br.readLine();	// 숫자들(패스워드들)
			int sideLength = N / 4;	// 보물상자는 4개의 변으로 이루어짐 (즉, 한 변에 해당하는 숫자의 개수)
			// 중복을 허용하지 않고 모든 가능한 숫자를 저장할 Set
			Set<Long> set = new HashSet<>();	// 숫자들(패스워드들)을 10진수로 변환한 것들을 저장할 Set (중복 허용 X)
			
			 // 각 변에 해당하는 숫자들을 모두 추출하고 회전시키는 과정을 반복
			for(int i=0; i<sideLength; i++) {
				// 한 변의 길이만큼 숫자를 추출하는 과정
				for(int j=0; j<N; j+=sideLength) {
					String password = input.substring(j, j + sideLength);	// 한 변의 숫자 추출
					Long pwd = Long.parseLong(password, 16);	// 추출된 16진수를 10진수로 변환
					set.add(pwd);
				}
				// 문제에서 요구한 회전 시키는 부분 (맨 끝 글자 맨 앞으로 오고, 원래 문자열에서 끝 글자 전까지 생성한 뒤 붙이기)
				// 즉, 문자열의 맨 끝 문자를 맨 앞으로 옮김
				input = input.charAt(input.length() - 1) + input.substring(0, input.length() - 1);
			}
			
			List<Long> pwdList = new ArrayList<>(set);	// Set에 저장된 숫자들을 리스트로 변환
			Collections.sort(pwdList, Collections.reverseOrder());	// 내림차순 정렬
			// K번째로 큰 숫자를 추출하여 StringBuilder에 추가
			sb.append("#").append(tc).append(" ").append(pwdList.get(K-1)).append("\n");
		}
		System.out.print(sb);

	}

}
