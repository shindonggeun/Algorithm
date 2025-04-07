class Solution {
    public int[] solution(String str) {
        int[] result = new int[str.length()];
        
        for(int i=0;i<str.length();i++){
            String subStr = str.substring(0,i);
            // 자신의 앞에 같은 글자 없는 경우
            if(subStr.indexOf(str.charAt(i)) == -1) {
                result[i] = -1;
            }
            // 자신의 앞에 같은 글자 있는 경우
            else {
                result[i] = i-subStr.lastIndexOf(str.charAt(i));
            }
        }
        return result;
    }
}