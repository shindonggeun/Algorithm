import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 포켓몬의 개수
	static int M; // 문제의 개수
	// key: 포켓몬 이름, value: 포켓몬 번호
	static Map<String, Integer> nameMap;
	// key: 포켓몬 번호, value: 포켓몬 이름
	static Map<Integer, String> numberMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nameMap = new HashMap<>();
		numberMap = new HashMap<>();
		
		for (int i=1; i<=N; i++) {
			String name = br.readLine();
			nameMap.put(name, i); // key에 해당 포켓몬 이름을 value에는 해당 포켓몬 번호를 저장
			numberMap.put(i, name); // key에 해당 포켓몬 번호를 value에는 해당 포켓몬 이름을 저장
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 문제 맞추는 과정
		for (int i=0; i<M; i++) {
			String nameOrNum = br.readLine(); // 이름 또는 번호 입력
			char ch = nameOrNum.charAt(0); // 해당 입력값의 첫 번째 글자 추출
			
			// 해당 글자가 숫자인 경우
			if (Character.isDigit(ch)) {
				int num = Integer.parseInt(nameOrNum);
				String name = numberMap.get(num); // 해당 포켓몬의 번호에 따른 이름 해시맵에서 추출
				sb.append(name);
			}
			// 해당 글자가 숫자가 아닌 경우
			else {
				int num = nameMap.get(nameOrNum); // 해당 포켓몬의 이름에 따른 번호 해시맵에서 추출
				sb.append(num);
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);

	}

}