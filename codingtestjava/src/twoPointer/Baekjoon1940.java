package twoPointer;

/*
 <투포인터>
 항상 
 (1) 구하려는 값 == goal
 (2) 구하려는 값 < goal
 (3) 구하려는 값 > goal
  -> 3가지로 나눠서 고민할 수 있또록

  아래 풀이는 돌아는 가는 풀인데

  배열을 제대로 이용하지 않아서 반쪽차리 풀이이다.

  더 정확한 풀이는 책 참조

 */

import java.io.*;

//돌아는 가는 풀이
public class Baekjoon1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //재료의 개수 (<= 15,000)
        int N = Integer.parseInt(br.readLine());
        //갑옷이 완성되는 번호의 합(<= 10,000,000)
        int M = Integer.parseInt(br.readLine());

        int[] material = new int[100_001];
        // 고유한 번호 <= 100,000보다 작음
        String[] materialInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            material[Integer.parseInt(materialInput[i])]++;
        }

        int startNum = 0;
        int endNum = M;
        int count = 0;

        if(M > 199_999){
            System.out.println(count);
            return;
        }
        
        int visit = 0;
        //시간 제한 2초  200,000,000
        while(startNum < endNum) {
            System.out.println(visit++);
            if(material[startNum] == 0 || material[endNum] == 0) {
                System.out.println(visit++);
                startNum++;
                endNum--;
                continue;
            }

            count++;
            startNum++;
            endNum--;
        }

        System.out.println(count);
    }
}
