package twoPointer;

/*
 <투포인터>
 1. 기본 원리
 -> 먼저 수열 입력한 부분 정렬
 1)sum == find
 start_idx ++ , end_index--;
 count++

 2) sum < find
  start++

 3) sum > find
  end--;

 2. 디버깅 포인트
 1) sum의 위치
  -> index 이동에 따라 변경될 수 있게 끔 위치 변경

 2) endIndex
  (1) 처음 endIndex = goodNumberIndex
  (2) 수정 endIndex = N - 1
  -> 음수가 수열에 존재하기 때문에 뒤에서부터 시작해줘도 값을 생성할 수 있음
  -> 예제는 음수 없이 딱 떨어지게끔 줬기 때문에 무조건 더 적은 인덱스부터 시작해야된다고 생각

 3) goodNumber를 만들어주는 쌍을 찾는게 아니다. goodNumber는 한쌍이라도 존재하면 goodNumber이기 때문에 이에 맞추어 수정

 4) 뒤에서부터 따라 내려오기때문에 startIndex 도는 endIndex 중에서 goodNumberIndex와 같으면 넘어가게끔 변경해줌
 
 */

import java.io.*;
import java.util.Arrays;

public class Baekjoon1253 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Long[] number = new Long[N];

        String[] numberInput = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            number[i] = Long.parseLong(numberInput[i]);
        }

        Arrays.sort(number);

        int goodNumberIndex = 0;
        int goodNumberCount = 0;
        
        //O(N^2)
        while(goodNumberIndex < N) {
            int startIndex = 0;
            int endIndex = N - 1;
            long goodNumber = number[goodNumberIndex];
            
            //중복되는 수 + 0 = goodNumber로 받아줘야 한다.
            while(startIndex < endIndex) {
                long sum = number[startIndex] + number[endIndex];

                if(sum == goodNumber) {
                    if(startIndex == goodNumberIndex) {
                        startIndex++;
                        continue;
                    }
                    if(endIndex == goodNumberIndex) {
                        endIndex--;
                        continue;
                    }

                    goodNumberCount++;
                    break;
                }

                if(sum < goodNumber) {
                    startIndex++;
                    continue;
                }

                if(sum > goodNumber) {
                    endIndex--;
                    continue;
                }
            }
            goodNumberIndex++;
        }
        System.out.println(goodNumberCount);
    }
}
