import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());	// 최대 수강 인워
		int L = Integer.parseInt(st.nextToken());	// 수강 대기목록 길이
		
		Set<String> set = new LinkedHashSet<>();	// 순서 보장된 HashSet (수강신청 대기 인원들을 담은 자료구조)
		
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			String classNumber = st.nextToken();
			// 이미 수강신청한 경우 수강신청 대기명단에서 삭제한뒤 가장 뒤로 밀어남
			if(set.contains(classNumber)) {
				set.remove(classNumber);
			}
			set.add(classNumber);
		}
		
		StringBuilder sb = new StringBuilder();
		int count = 0;	// 수강인원 카운트해주는 변수
		for(String s: set) {
			// 최대 수강신청 인원까지 카운트 됐으면 반복문 빠져나오게끔
			if(count == K) {
				break;
			}
			sb.append(s).append("\n");
			count++;
		}
		System.out.print(sb);

	}

}