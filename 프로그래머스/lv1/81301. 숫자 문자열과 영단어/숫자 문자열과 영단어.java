class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] num_word = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i=0; i<num_word.length; i++) {
            s = s.replace(num_word[i], Integer.toString(i));
            //System.out.println(s);
        }
        answer = Integer.parseInt(s);
        
        return answer;
    }
}