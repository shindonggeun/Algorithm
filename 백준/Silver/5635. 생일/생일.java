import java.util.*;
import java.io.*;

public class Main {
	
	static class Student {
		String name = "";
		int dd = 0;
		int mm = 0;
		int year = 0;
		
		public Student(String name, int dd, int mm, int year) {
			this.name = name;
			this.dd = dd;
			this.mm = mm;
			this.year = year;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Student> studentList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int dd = Integer.parseInt(st.nextToken());
			int mm = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			
			studentList.add(new Student(name, dd, mm, year));
		}
		
		Collections.sort(studentList, new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {
				// 년도가 작으면서 월도 작고 일도 작은게 더 빨리 태어난거임
				// 태어난 년도 같으면
				if(s1.year == s2.year) {
					// 태어난 월 같으면
					if(s1.mm == s2.mm) {
						// 일수 내림차순 
						return s2.dd - s1.dd;		
					}
					else {
						// 월 내림차순
						return s2.mm - s1.mm;
					}
				}
				else {
					// 년도 내림차순으로 (즉 나이가 어린순부터 -> (년도 높을수록 나이 적은거))
					return s2.year - s1.year;	
				}
				
			}
		});
		
		System.out.println(studentList.get(0).name);	// 나이 제일 어린사람 이름 출력
		System.out.println(studentList.get(studentList.size()-1).name);		// 나이 제일 많은사람 이름 출력
	}

}
