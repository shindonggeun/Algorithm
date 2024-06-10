import java.util.*;
import java.io.*;

public class Main {
	
	static class Student {
		int num;
		int food;
		
		public Student(int num, int food) {
			this.num = num;
			this.food = food;
		}
	}
	
	static int N;
	static Queue<Student> queue;
	static List<Student> AList;
	static List<Student> BList;
	static List<Student> CList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		AList = new ArrayList<>();
		BList = new ArrayList<>();
		CList = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			
			if (type == 1) {
				int num = Integer.parseInt(st.nextToken());
				int food = Integer.parseInt(st.nextToken());
				
				queue.add(new Student(num, food));
			}
			else {
				int food = Integer.parseInt(st.nextToken());
				
				Student now = queue.poll();
				
				if (food == now.food) {
					AList.add(now);
				}
				else {
					BList.add(now);
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Student student = queue.poll();
			CList.add(student);
		}
		
		Collections.sort(AList, (a, b) -> a.num - b.num);
		Collections.sort(BList, (a, b) -> a.num - b.num);
		Collections.sort(CList, (a, b) -> a.num - b.num);
		
		StringBuilder sb = new StringBuilder();
		
		if (AList.size() == 0) {
			sb.append("None").append("\n");
		}
		else {
			for (Student student: AList) {
				sb.append(student.num).append(" ");
			}
			sb.append("\n");
		}
		
		if (BList.size() == 0) {
			sb.append("None").append("\n");
		}
		else {
			for (Student student: BList) {
				sb.append(student.num).append(" ");
			}
			sb.append("\n");
		}
		
		if (CList.size() == 0) {
			sb.append("None").append("\n");
		}
		else {
			for (Student student: CList) {
				sb.append(student.num).append(" ");
			}
		}
		
		System.out.print(sb);
	}

}