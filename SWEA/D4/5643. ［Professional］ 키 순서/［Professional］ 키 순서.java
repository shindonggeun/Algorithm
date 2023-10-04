import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> tallerList;	// 자신보다 큰 학생의 목록을 저장할 리스트
    static ArrayList<ArrayList<Integer>> smallerList;	// 자신보다 작은 학생의 목록을 저장할 리스트
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            // 자신보다 크거나 작은 학생들의 목록을 저장할 리스트들 초기화
            smallerList = new ArrayList<>();
            tallerList = new ArrayList<>();
            
            // N번의 학생까지 정점 생성해주기
            for(int i=0; i<=N; i++) {
            	tallerList.add(new ArrayList<>());
            	smallerList.add(new ArrayList<>());
            }
            
            for(int i=0; i<M; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	// from -> to
            	int fromVertex = Integer.parseInt(st.nextToken());	// 키가 작은 학생 번호
            	int toVertex = Integer.parseInt(st.nextToken());	// 키가 큰 학생 번호
            	
            	// 단방향 간선
            	tallerList.get(fromVertex).add(toVertex);	// 자신(키가 작음)보다 큰 학생 목록 저장해주기
            	smallerList.get(toVertex).add(fromVertex);	// 자신(키가 큼)보다 작은 학생 목록 저장해주기
            }
            
            int studentCount = 0;	// 자신의 키가 몇 번째인지 알 수 있는 학생의 수
            for(int i=1; i<=N; i++) {
            	// 너비우선탐색 실시해서 키 몇번째인지 확인 가능하면
            	if(bfs(i)) {
            		studentCount++;	// 자신의 키가 몇 번째인지 알 수 있는 학생의 수 증가
            	}
            }

            sb.append("#").append(tc).append(" ").append(studentCount).append("\n");
        }

        System.out.print(sb);
    }

    // 너비우선탐색 메서드
    public static boolean bfs(int startStudentNum) {
       Queue<Integer> queue = new LinkedList<>();
       boolean[] visited = new boolean[N+1];
       
       // 큰 학생을 찾기 위한 과정
       queue.add(startStudentNum);
       visited[startStudentNum] = true;
       
       while(!queue.isEmpty()) {
    	   int currentStudentNum = queue.poll();	// 현재 학생 번호 뽑기 (키가 큰 학생)
    	   
    	   // 자신보다 키가 큰 학생 목록 순회
    	   for(int nextStudentNum: tallerList.get(currentStudentNum)) {
    		   if(!visited[nextStudentNum]) {
    			   queue.add(nextStudentNum);
    			   visited[nextStudentNum] = true;
    		   }
    	   }
       }
       
       // 작은 학생을 찾기 위한 과정
       queue.add(startStudentNum);
       
       while(!queue.isEmpty()) {
    	   int currentStudentNum = queue.poll();	// 현재 학생 번호 뽑기 (키가 작은 학생)
    	   
    	   // 자신보다 키가 작은 학생 목록 순회
    	   for(int nextStudentNum: smallerList.get(currentStudentNum)) {
    		   if(!visited[nextStudentNum]) {
    			   queue.add(nextStudentNum);
    			   visited[nextStudentNum] = true;
    		   }
    	   }
       }
       
       // 자신보다 큰 학생과 작은 학생을 모두 확인하였으면 순서를 알 수 있음
       for(int i=1; i<=N; i++) {
    	   // 만약 방문 안한 학생번호인 경우
    	   if(!visited[i]) {
    		   return false;	// 순서 알 수 없음
    	   }
       }
       
       return true;	// 순서 알 수 있음
    }
}
