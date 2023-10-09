import java.util.*;
import java.io.*;

public class Main {
	
	static class Country {
		int x;
		int y;
		
		public Country(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int L;
	static int R;
	static int[][] map;
	static boolean[][] visited;
	// 4가지 방향 탐색 (상, 하, 좌, 우) => 배열에서는 하, 상, 좌, 우
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];	// [0][0] ~ [N-1][N-1]
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dayCount = 0;
		
		while(true) {
			visited = new boolean[N][N];
			boolean moved = false;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && openBorder(i, j)) {
						List<Country> unionList = new ArrayList<>();
						explore(i, j, unionList);
						if(unionList.size() > 1) {
							updatePopulation(unionList);
							moved = true;
						}
					}
				}
			}
			
			if(!moved) {
				break;
			}
			
			dayCount++;
		}
		
		System.out.println(dayCount);
	}
	
	public static boolean openBorder(int x, int y) {
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                int diff = Math.abs(map[x][y] - map[nextX][nextY]);
                if (diff >= L && diff <= R) {
                    return true;
                }
            }
        }

        return false;
	}
	
	
	public static void explore(int x, int y, List<Country> unionList) {
		visited[x][y] = true;
		unionList.add(new Country(x, y));
		
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) {
				continue;
			}
			
			if(visited[nextX][nextY]) {
				continue;
			}
			
			int populationDiff = Math.abs(map[x][y] - map[nextX][nextY]);
			if(populationDiff >= L && populationDiff <= R) {
				explore(nextX, nextY, unionList);
			}
		}
	}
	
	public static void updatePopulation(List<Country> unionList) {
		int totalPopulation = 0;
		
		for(Country country : unionList) {
			totalPopulation += map[country.x][country.y];
		}
		
		int newPopulation = totalPopulation / unionList.size();
		
		for(Country country : unionList) {
			map[country.x][country.y] = newPopulation;
		}
	}

}
