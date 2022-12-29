class Solution {
    public int solution(String s) {
        int answer = 0;
        int x_count = 0;
        int notX_count = 0;
        char x = s.charAt(0);
        String str = "";
        
        for(int i=0; i<s.length(); i++) {
            if(x == s.charAt(i)) {
                x_count++;
            }
            else {
                notX_count++;
            }
            str+=s.charAt(i);
            
            if(x_count == notX_count) {
                if(i != s.length()-1) {
                    x = s.charAt(i+1);
                }
                //System.out.println(str);
                x_count = 0;
                notX_count = 0;
                answer++;
                str = "";
            }
            else {
                if(i == s.length()-1) {
                    answer++;
                }
            }
            
        }
        
        return answer;
    }
}