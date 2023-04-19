import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String sk_tr: skill_trees) {
            sk_tr = sk_tr.replaceAll("[^"+ skill +"]", "");
            
            if(skill.indexOf(sk_tr) == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}