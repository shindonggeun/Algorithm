import java.util.*;

public class Solution {

    static class Music{
        String genre;   // 장르
        int play;       // 장르당 재생된 횟수
        int idx;        // 고유 번호(인덱스)

        public Music(String genre, int play, int idx) {
            this.genre = genre;
            this.play = play;
            this.idx = idx;
        }
    }
    
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, Integer> map = new HashMap<>();     // key: 장르, value: 장르당 재생된 총 횟수
        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }

        // 1. 장르 선정
        ArrayList<String> genres_ordered = new ArrayList<>();   // 장르당 재생된 총 횟수가 많은(내림차순 별) 장르 이름을 담은 리스트
        
        while(map.size()!=0){
            int max = -1;
            String max_key = "";
            for(String key : map.keySet()){
                int tmp_cnt = map.get(key);
                if(tmp_cnt>max){
                    max = tmp_cnt;
                    max_key = key;
                }
            }
            genres_ordered.add(max_key);
            map.remove(max_key);
        }
        //System.out.println(genres_ordered);
		
        // 2. 장르 내 노래 선정
        ArrayList<Music> result = new ArrayList<>();
        for(String gern : genres_ordered){
            ArrayList<Music> list = new ArrayList<>();
            for(int i=0; i<genres.length; i++){
                if(genres[i].equals(gern)){
                    list.add(new Music(gern, plays[i], i));
                }
            }
            Collections.sort(list, (o1, o2) -> o2.play - o1.play); // 내림차순 소팅
            //System.out.println(list.get(0).play);
            //System.out.println(list.get(1).play);
            result.add(list.get(0)); 	// 1개는 무조건 수록
            if(list.size()!=1){ 	// 더 수록할 곡이 있으면(장르 내의 노래가 1개보다 많으면) 수록
                result.add(list.get(1));
            }
        }
        
        answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i).idx;
        }
        return answer;
    }
}