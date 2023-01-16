public class Test {
    long sum(int[] a) {
        long sum = 0; 
        // 배열에 저장된 원소의 합 구하기(정수 n개의 합, n개가 저장되어 있는 배열)
        for(int i=0; i<a.length; i++) {
        	sum+=a[i];
        }
        return sum;
    }
}
