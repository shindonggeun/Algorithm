class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        // p의 길이가 18이므로 18자리의 수는 int형으로 표현하기에는 한계가 있음
        // 그래서 Long타입으로 변환해서 비교해야한다!!!(안그러면 런타임에러 발생)
        for(int i=0; i<t.length(); i++) {
            if(i + p.length() - 1 == t.length()) {
                break;
            }
            String str = t.substring(i, i + p.length());
            //System.out.println(str);
            if(Long.parseLong(str) <= Long.parseLong(p)) {
                answer++;
            }
            
         }
        return answer;
    }
}