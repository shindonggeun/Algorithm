import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> map = new HashMap<>();
        HashMap<String, Integer> idxMap = new HashMap<>();
        
        // id 리스트가 적힌 배열의 원소 수만큼 반복문 돌리기
        for(int i=0; i<id_list.length; i++) {
            String name = id_list[i];   // id 받아오기
            map.put(name, new HashSet<>()); // key: id(신고당한 사람), value: hashset -> 중복허용 X (신고한 사람)
            idxMap.put(name, i);    // key: id(신고당한 사람), value: 인덱스(0번부터)
        }
        //System.out.println(idxMap);
        
        // "신고자 신고받은사람" 이런 형식으로 저장된 report 배열의 원소 수만큼 반복문 돌리기
        for(String re: report) {
            String[] str = re.split(" ");   // 문자열 공백을 기준으로 자르기
            String from = str[0];   // 신고한 사람
            String to = str[1];     // 신고당한 사람
            map.get(to).add(from);  // 신고당한사람의 key값을 기준으로 value값을 뽑은다음의 hashset()에 넣기(신고한 사람)
        }
        //System.out.println(map);
        for(int i=0; i<id_list.length; i++) {
            HashSet<String> send = map.get(id_list[i]); // 신고한사람은 누구인지 찾기
            //System.out.println(send);   
            // 신고한사람이 2명이상인 경우
            if(send.size() >= k) {
                // 결과 메일 받을 수 있도록 반복문 돌려서 저장하기
                for (String name : send) {
                    answer[idxMap.get(name)]++; // 결과메일 카운트 증가
                }
            }
        }
        
        
        
        return answer;
    }
}