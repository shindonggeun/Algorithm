class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            int operation = arr1[i] | arr2[i];  // arr1의 원소와 arr2의 원소를 OR 연산해서 값 저장
            String binary = Integer.toBinaryString(operation);  // 위의 OR 연산한 값을 이진수로 나타내기 (Stirng)
            binary = "0".repeat(n - binary.length()) + binary;  // 이진수로 나타낸 값을 지도 길이 n만큼 될 수 있도록 앞에 0 붙여주기
            String convert = binary.replaceAll("1", "#").replaceAll("0", " ");  // 1은 #으로 대체, 0은 공백으로 대체
            answer[i] = convert;   
        }
        
        return answer;
    }
}