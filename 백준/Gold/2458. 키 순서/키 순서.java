import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> tallerList;
	static ArrayList<ArrayList<Integer>> shorterList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tallerList = new ArrayList<>();
		shorterList = new ArrayList<>();
		
		for (int i=0; i<=N; i++) {
			tallerList.add(new ArrayList<>());
			shorterList.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int shortStudent = Integer.parseInt(st.nextToken()); // 키가 작은 학생 번호
			int tallStudent = Integer.parseInt(st.nextToken()); // 키가 큰 학생 번호
			
			tallerList.get(shortStudent).add(tallStudent);
			shorterList.get(tallStudent).add(shortStudent);
		}
		
		int heightKnowCount = 0;
		
		for (int i=1; i<=N; i++) {
			if (bfs(i)) {
				heightKnowCount++;
			}
		}
		
		System.out.println(heightKnowCount);

	}
	
	public static boolean bfs(int student) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		queue.add(student);
		visited[student] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next: tallerList.get(now)) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		
		queue.add(student);
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next: shorterList.get(now)) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		
		return true;
	}

}