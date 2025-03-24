import java.util.*;
import java.io.*;

public class Main {
	
	static int K; // 해당 과목이 수강 가능 인원 수
	static int L; // 학생들의 수강신청 기록 대기목록 수
	static Set<String> classRegisterSet; // 학생들의 수강신청 대기목록을 담은 셋 (중복 허용 X)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		classRegisterSet = new LinkedHashSet<>(); // 학생들의 수강신청 대기목록을 순차적으로 담은 해시셋 (중복 허용 X)
		
		// 1. 학생들 수강신청 하는 과정
		for (int i=0; i<L; i++) {
			String classNum = br.readLine();
			// 해당 학번이 수강신청 대기목록에 있는 경우
			if (classRegisterSet.contains(classNum)) {
				classRegisterSet.remove(classNum); // 해당 수강신청 대기목록에 삭제 한 뒤
			}
			classRegisterSet.add(classNum); // 다시 수강신청 기록에 저장 (대기목록 맨 뒤로 보내기) 
		}
		
		// 2. 수강신청에 성공한 인원들의 학번을 출력하는 과정
		int count = 0; // 수강신청 대기목록에서 맨 앞자리부터 셀 카운트 변수
		StringBuilder sb = new StringBuilder();
		// 수강신청 대기목록 탐색
		for (String classNum: classRegisterSet) {
			// 수강 가능 인원수가 다 찬 경우
			if (count == K) {
				break; // 더이상 탐색할 필요없이 반복문 빠져나옴
			}
			
			sb.append(classNum).append("\n"); // 해당 학번 뽑아냄
			count++; // 카운트 변수 증가
		}
		
		System.out.print(sb);
	}

}