package prefixSum;

/*
 * 부분합
 1) 부분합은 원래 index를 살리는게 좋다 -> 여기서는 코드 수정 안함
 */


import java.io.*;
import java.util.*;

public class Baekjoon11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sumInformation = br.readLine().split(" ");

        int N = Integer.parseInt(sumInformation[0]);
        int M = Integer.parseInt(sumInformation[1]);

        List<Integer> number = new ArrayList<>();
        String[] numberInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            number.add(Integer.parseInt(numberInput[i]));
        }

        //1. 자신의 index까지 합을 구해 sum List에 넣어준다
        List<Integer> sum = new ArrayList<>();
        int prefixSum = 0;
        for(int num : number){
            prefixSum += num;
            sum.add(prefixSum);
        }

        //2. 구간합을 구할 구간을 range List에 넣어준다.
        List<int[]> range = new ArrayList<>();
        for(int i = 0; i < M; i++){
            String[] rangeNumber = br.readLine().split(" ");
            int firstNum = Integer.parseInt(rangeNumber[0]) - 1;
            int secondNum = Integer.parseInt(rangeNumber[1]) - 1;

            range.add(new int[] {firstNum, secondNum});
        }

        //3. range List에 맞는 구간합을 출력한다.
        for(int i = 0; i < M; i++){
            int firstNum = range.get(i)[0];
            int secondNumb = range.get(i)[1];
            int firstPrefixSum = 0;
            int secondPrefixSum = sum.get(secondNumb);

            //1) 첫번째 범위가 0인아닌 경우 부분합 계산에 포함
            if(firstNum != 0){
                firstPrefixSum = sum.get(firstNum - 1);
            }
            
            int answer = secondPrefixSum - firstPrefixSum;
            System.out.println(answer);
        }
    }
}
