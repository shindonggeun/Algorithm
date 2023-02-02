import java.util.*;
import java.io.*;

public class Main {
	
	static class Human {
		int age = 0;
		String name = "";
		
		public Human(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Human> humanList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			humanList.add(new Human(age, name));
		}
		
		// 나이순으로 정렬(오름차순)
		// 나이 같은경우 저장된 순으로
		Collections.sort(humanList, (o1, o2) -> o1.age - o2.age);	
		
		StringBuilder sb = new StringBuilder();
		for(Human human: humanList) {
			sb.append(human.age + " " + human.name).append("\n");
		}
		System.out.print(sb);
		
	}

}