import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 입력 받을 걸그룹의 수
		int M = Integer.parseInt(st.nextToken());	// 맞혀야 할 문제의 수
		Map<String, String> girlGroupMap = new HashMap<>();	// key: 걸그룹 멤버 이름, value: 걸그룹 명
		
		// 걸그룹 수에 따른 걸그룹 명과 걸그룹 멤버 이름들 입력 받는 부분
		for(int i=0; i<N; i++) {
			String girlGroup = br.readLine();	// 걸그룹 이름 입력 받아오기
			int count = Integer.parseInt(br.readLine());	// 걸그룹 수 입력받기
			
			for(int j=0; j<count; j++) {
				String name = br.readLine();	// 걸그룹 멤버 이름 입력 받아오기
				girlGroupMap.put(name, girlGroup);	// 맵에 저장해주기
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 맵의 keySet() 메서드를 이용해서 걸그룹 멤버 이름을 전부다 저장한 리스트
		List<String> memberList = new ArrayList<>(girlGroupMap.keySet());	
		Collections.sort(memberList);	// 걸그룹 멤버 이름 오름차순 정렬
		// 문제 맞추는 부분 입력받기
		for(int i=0; i<M; i++) {
			String question = br.readLine();	// 걸그룹 멤버 이름인지 걸그룹 명인지 입력받기 
			int quizType = Integer.parseInt(br.readLine());	// 퀴즈 종류 입력받기
			
			// 퀴즈 종류가 0일 경우 해당 팀의 속한 멤버 이름 뽑기
			if(quizType == 0) {
				for(String memberName: memberList) {
					// map에서 해당 멤버 이름(key 값)에 따른 걸그룹명(value 값)이 동일한 경우
					if(girlGroupMap.get(memberName).equals(question)) {
						sb.append(memberName).append("\n");
					}
				}
			}
			// 퀴즈 종류가 1인 경우 해당 멤버가 속한 팀의 이름 출력
			else if(quizType == 1) {
				sb.append(girlGroupMap.get(question)).append("\n");
			}
		}
		
		System.out.print(sb);

	}

}
