import java.util.*;
import java.io.*;

public class Main {

	static class Information {
		String name = "";
		int language = 0;
		int english = 0;
		int math = 0;
		
		public Information(String name, int language, int english, int math) {
			this.name = name;
			this.language = language;
			this.english = english;
			this.math = math;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Information> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int score1 = Integer.parseInt(st.nextToken());	// 국어
			int score2 = Integer.parseInt(st.nextToken());	// 영어
			int score3 = Integer.parseInt(st.nextToken());	// 수학
			
			list.add(new Information(name, score1, score2, score3));
		}
		
		Collections.sort(list, new Comparator<Information>() {
			
			@Override
			public int compare(Information i1, Information i2) {
				
				// 국어 점수가 같으면
				if(i1.language == i2.language) {
					// 영어 점수까지 같으면 (국어, 영어)
					if(i1.english == i2.english) {
						// 수학 점수까지 같으면	(모든 점수 같으면) -> 4번 조건
						if(i1.math == i2.math) {
							// 모든 점수가 같으면 (두 학생이 국어, 영어, 수학까지 다 같은 경우) 이름 사전 순으로
							return i1.name.compareTo(i2.name);	// 이름 사전순을 증가 (오름차순 정렬)
						}
						// 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
						return i2.math - i1.math;	// 수학점수 내림차순 정렬
					}
					// 국어 점수가 같으면 영어 점수가 증가하는 순으로 -> 2번 조건
					return i1.english - i2.english;	// 영어 점수 오름차순 정렬	
				}
				// 위의 경우 만족하지 않으면 1번 조건 
				return i2.language - i1.language;	// 국어 점수가 감소하는 순으로(내림차순 정렬) 
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(Information information: list) {
			sb.append(information.name).append("\n");
		}
		System.out.print(sb);
		

	}

}