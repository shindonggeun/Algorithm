import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 걸그룹 수
		int M = Integer.parseInt(st.nextToken());	// 맞춰야할 문제 수
		
		Map<String, String> map = new HashMap<>();	// key: 멤버 이름, value: 걸그룹 이름
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String girlGroup = st.nextToken();
			st = new StringTokenizer(br.readLine());
			int memberNum = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<memberNum; j++) {
				st = new StringTokenizer(br.readLine());
				String memeberName = st.nextToken();
				map.put(memeberName, girlGroup);
			}
		}
		//System.out.println(map);
		
		// 문제 낸 순서에 따른 출력값 나와야하므로 LinkedHashMap 이용!!
		Map<String, Integer> quiz = new LinkedHashMap<>();	// key: 걸그룹 이름 or 그룹명, value: 퀴즈 종류(0 or 1)
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String quiz1 = st.nextToken();
			st = new StringTokenizer(br.readLine());
			int quiz2 = Integer.parseInt(st.nextToken());
			quiz.put(quiz1, quiz2);
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<String> memberList = new ArrayList<>(map.keySet());	// 걸그룹 멤버들 이름 담은 리스트들(모든 걸그룹)
		Collections.sort(memberList);	// 이름 오름차순 정렬
		
		// 문제 풀기
		for(String key: quiz.keySet()) {
			// 퀴즈 종류가 0인 경우 (그룹명 나오면 그 걸그룹에 따른 멤버들 이름 전부 출력)
			if(quiz.get(key) == 0) {
				for(String memberName: memberList) {
					if(map.get(memberName).equals(key)) {
						sb.append(memberName).append("\n");
					}
				}
			}
			// 퀴즈 종류가 1인 경우 (걸그룹 멤버 이름 나오면 그 걸그룹명 출력)
			else {
				for(String memberName: map.keySet()) {
					if(key.equals(memberName)) {
						sb.append(map.get(memberName)).append("\n");
					}
				}
			}
		}
		
		System.out.println(sb);
		
	}

}