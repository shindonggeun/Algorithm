import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        // 스킬트리 전부 탐색
        for(String sk_tr: skill_trees) {
            // 정규식을 이용하여 선행스킬이 아닌 스킬들은 전부 빈 문자열 처리
            sk_tr = sk_tr.replaceAll("[^"+ skill +"]", ""); 
            
            // 스킬트리에서 선행스킬이 처음 나온경우 가능한 스킬트리임
            if(skill.indexOf(sk_tr) == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}