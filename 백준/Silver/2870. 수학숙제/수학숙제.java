import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String[] input = new String[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = st.nextToken();
		}
		
		StringBuilder sb = new StringBuilder();
		// BigInteger로 하는 경우는 문제에서 각 줄에 최대 글자수는 100글자라고 했으므로
		// 숫자가 만약 100글자가 넘는 경우는 long타입으로도 표현이 안된다!
		List<BigInteger> list = new ArrayList<>();		// 숫자들 정렬하기 위해 동적 배열 ArrayList 이용
		for(int i=0; i<N; i++) {
			String[] result = input[i].split("[a-z]");	// 소문자 기준으로 문자열 자름 
			for(String re: result) {
				// 문자열 빈 경우가 아닌 경우 -> re.equals("") 를 써도 된다
				if(!re.isEmpty()) {	
					BigInteger num = new BigInteger(re);	
					list.add(num);		// list에 추가
				}
			}
		}
		
		Collections.sort(list);		// 오름차순 정렬
		for(BigInteger i: list) {
			sb.append(i).append("\n");
		}
		System.out.print(sb);

	}

}