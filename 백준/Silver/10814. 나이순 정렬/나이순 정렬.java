import java.util.*;
import java.io.*;

public class Main {
	
	static class Information {
		int age;		// 나이
		String name;	// 이름
		int idx;		// 가입 순서 
		
		public Information(int age, String name, int idx) {
			this.age = age;
			this.name = name;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		List<Information> list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());	// 나이 입력받음
			String name = st.nextToken();	// 이름 입력받음
			
			// 리스트에 온라인 저지 회원 정보를 저장해줌
			list.add(new Information(age, name, i));
		}
		
		// 정렬 커스텀화 (1순위는 나이순으로 정렬, 나이 같으면 2순위로 가입한 순으로 정렬하게끔)
		Collections.sort(list, new Comparator<Information>() {

			@Override
			public int compare(Information i1, Information i2) {
				// 나이가 같으면
				if(i1.age == i2.age) {
					return i1.idx - i2.idx;	// 가입한 순으로 정렬(오름차순 정렬)
				}
				// 그 이외의 경우는 나이 낮은 순으로 정렬
				return i1.age - i2.age;	// 나이 낮은 순으로 정렬(오름차순 정렬)
				
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(Information info: list) {
			sb.append(info.age).append(" ").append(info.name).append("\n");
		}
		System.out.print(sb);
	}

}
