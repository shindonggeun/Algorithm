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
            smallerList = new ArrayList<>();
            tallerList = new ArrayList<>();
            
            for(int i=0; i<=N; i++) {
            	tallerList.add(new ArrayList<>());
            	smallerList.add(new ArrayList<>());
            }
            
            for(int i=0; i<M; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	// from -> to
            	int fromVertex = Integer.parseInt(st.nextToken());	// 키가 작은 학생 번호
            	int toVertex = Integer.parseInt(st.nextToken());	// 키가 큰 학생 번호
            	
            	
            	tallerList.get(fromVertex).add(toVertex);
            	smallerList.get(toVertex).add(fromVertex);
            }
            
            int studentCount = 0;
            for(int i=1; i<=N; i++) {
            	if(bfs(i)) {
            		studentCount++;
            	}
            }

            sb.append("#").append(tc).append(" ").append(studentCount).append("\n");
        }

        System.out.print(sb);
    }

    public static boolean bfs(int startStudentNum) {
       Queue<Integer> queue = new LinkedList<>();
       boolean[] visited = new boolean[N+1];
       
       // 큰 학생을 찾기 위한 과정
       queue.add(startStudentNum);
       visited[startStudentNum] = true;
       
       while(!queue.isEmpty()) {
    	   int currentStudentNum = queue.poll();
    	   
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
    	   int currentStudentNum = queue.poll();
    	   
    	   for(int nextStudentNum: smallerList.get(currentStudentNum)) {
    		   if(!visited[nextStudentNum]) {
    			   queue.add(nextStudentNum);
    			   visited[nextStudentNum] = true;
    		   }
    	   }
       }
       
       // 자신보다 큰 학생과 작은 학생을 모두 확인하였으면 순서를 알 수 있음
       for(int i=1; i<=N; i++) {
    	   if(!visited[i]) {
    		   return false;
    	   }
       }
       
       return true;
    }
}
