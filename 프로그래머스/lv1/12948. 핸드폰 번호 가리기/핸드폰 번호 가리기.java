class Solution {
    public String solution(String phone_number) {
        String answer = "";
        //System.out.println(phone_number.substring(0, phone_number.length() - 4));
        //String star = phone_number.substring(0, phone_number.length() - 4);
        
        for(int i=0; i<phone_number.length() - 4; i++) {
            answer+="*";
        }
        answer+=phone_number.substring(phone_number.length() - 4, phone_number.length());
        
        return answer;
    }
}