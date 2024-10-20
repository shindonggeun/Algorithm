import java.util.*;
import java.io.*;

public class Main {

	// 문서의 정보를 담은 내부 클래스
	static class Document {
		int importance; // 문서의 중요도
		int idx; // 문서의 원래 인덱스
		
		public Document(int importance, int idx) {
			this.importance = importance;
			this.idx = idx;
		}
	}
	
	static int N; // 문서의 개수
	static int M; // 우리가 관심 있는 문서의 위치
	static Queue<Document> queue; // 문서들을 저장할 큐 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			queue = new LinkedList<>(); // 큐 생성
			List<Integer> importanceList = new ArrayList<>(); // 문서의 중요도들을 저장할 리스트
			
			st = new StringTokenizer(br.readLine());
			// 문서의 중요도를 입력하는 과정
			for (int i=0; i<N; i++) {
				int importance = Integer.parseInt(st.nextToken());
				
				queue.add(new Document(importance, i)); // 큐에 문서의 중요도와 인덱스 정보 저장
				importanceList.add(importance); // 해당 문서의 중요도 리스트에 저장
			}
			
			int count = 0; // 문서가 몇 번째로 인쇄되는지를 카운트하는 변수
			while (!queue.isEmpty()) {
				Document now = queue.poll(); // 큐에 현재 저장된 문서 정보 뽑아냄
				
				// 현재 문서의 중요도가 현재 남은 문서들중에 최대 중요도보다 작은 경우
				if (now.importance < Collections.max(importanceList)) {
					queue.add(now); // 인쇄하지 않고 현재 문서를 큐에 다시 저장 (뒤로 보내기)
				}
				// 현재 문서의 중요도가 남은 문서들 중 최대 중요도보다 크거나 같은 경우
				else {
					count++; // 문서 인쇄된 카운트 증가
					// 문서의 중요도를 담은 리스트에서 현재 인쇄한 문서의 중요도 제거
					importanceList.remove(Integer.valueOf(now.importance));
					
					// 현재 문서의 인덱스가 우리가 관심 있는 문서인 경우
					if (now.idx == M) {
						// 그 문서의 인쇄 순서를 결과에 기록한 뒤 반복문 빠져나옴
						sb.append(count).append("\n");
						break;
					}
				}
			}
		}
		
		System.out.print(sb);

	}

}