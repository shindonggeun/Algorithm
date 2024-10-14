import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 출입 기록 개수
	static Set<String> enterLogNameSet; // 회사에 출근한 사람들의 이름들을 저장하는 해시셋

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		enterLogNameSet = new HashSet<>(); // 회사에 출근한 사람들의 이름들을 저장하는 해시셋 초기화
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken(); // 이름 입력
			String enterLog = st.nextToken(); // 출입 기록 입력
			
			// 출입 기록이 "enter"인 경우 (출근인 경우)
			if (enterLog.equals("enter")) {
				enterLogNameSet.add(name); // 해당 사람의 이름 출근 기록을 저장하는 해시셋에 저장
			}
			// 출입 기록이 "leave"인 경우 (퇴근인 경우)
			else {
				// 해당 사람의 이름이 출근 기록을 저장한 해시셋에 존재하는 경우
				if (enterLogNameSet.contains(name)) {
					enterLogNameSet.remove(name); // 해당 사람의 이름 해시셋에 제거
				}
			}
		}
		
		List<String> nameList = new ArrayList<>(enterLogNameSet); // 해시셋을 리스트화
		Collections.sort(nameList, Collections.reverseOrder()); // 사람들의 이름 사전 순의 역순으로 정렬
		
		StringBuilder sb = new StringBuilder();
		// 사람들의 이름이 사전 순의 역순으로 저장된 리스트 순회
		for (String name: nameList) {
			sb.append(name).append("\n"); // 해당 이름 StringBuilder에 저장 및 줄바꿈까지 같이 저장
		}
		
		System.out.print(sb); // 최종 결과 출력
	}

}