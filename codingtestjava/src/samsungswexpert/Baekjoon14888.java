package samsungswexpert;

/*
 1. 유형
 - 완탐 + 백트래킹
 
 2. 디버깅 부분
 1) 삽입 부분과 종료 부분의 위치 처음 위치 -> 수정 위치 -> 처음 위치 : 백트래킹은 항상 똑같다!!
 2) 나머지값 찾기 위치를 숫자 다음 연산 인덱스 널 때마다 한게 아니라 삽입 직전에 한 번만 : remain이 1밖에 없는 경우 삽입이 안된다 막상
 3) 값 연산에서 -> operation에서 가져오는게 아닌 combination에서 값 가져오기 : 의사코드 없어서 그런 듯, 이름도 직관적이지 않고
 4) 이상한데서 시간 허비 -> c++14 스타일 나누기 무시하자
 */
/*
     N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 
     또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 
     연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

     우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
     이때, 주어진 수의 순서를 바꾸면 안 된다.
     식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 
     또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 
     음수를 양수로 나눌 때는 C++14의 기준을 따른다. 
     즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

     완탐
     */

     /*
      <메인함수>
     for(int i = 0; i < 4; i++){
        1. 처음 넣어줄 연산자 인덱스 찾기
        int firstOperationIndex = i;

        2. 연산자 인덱스 남은 갯수 찾기
        int remain = operations[first]

        3. 연산자 갯수 < 1 continue;

        5. findMaxMin(연산자 인덱스)
     }

     6.최대, 최소 순서대로 출력
      */

          /*

     void findMaxMin()
     1. 컴비네이션사이즈가 N-1에 도달하면 수식의 합을 구하고 탐색 중지
       1) 최댓값 구하기
       2) 최솟값 구하기
     
     2. combinations에 연산자 인덱스 넣어주기

     3. 연산자인덱스에 해당하는 연산자 갯수 줄이기

     4. 연산자 기호 선택하기 
     for(operations 배열의 index 0 ~ 4)
       1) OperationsIndex 선택
       2) operations[Index] <= 0이라면 continue;
       3) operation[Index]--;
       4) operationIndex = choosedOperationsIndex;
       5) findMaxMin 실행

     5. combinations 배열의 마지막 요소 삭제
     */

import java.util.*;
import java.io.*;

public class Baekjoon14888 {
    static int[] numbers;

    static long max;
    static long min;

    static int[] operations;
    static List<Integer> combination;
    static int N;
    static int combinationSize;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] numbersInput = br.readLine().split(" ");
        numbers = new int[N];
        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(numbersInput[i]);
        }

        String[] operationCounts = br.readLine().split(" ");
        int plusCount = Integer.parseInt(operationCounts[0]);
        int minusCount = Integer.parseInt(operationCounts[1]);
        int timesCount = Integer.parseInt(operationCounts[2]);
        int divideCount = Integer.parseInt(operationCounts[3]);

        operations = new int[4];
        operations[0] = plusCount;
        operations[1] = minusCount;
        operations[2] = timesCount;
        operations[3] = divideCount;

        max = -1000000000;
        min = 1000000000;

        combination = new ArrayList<>();
        combinationSize = N -1;

        for(int firstOperationIndex = 0; firstOperationIndex < 4; firstOperationIndex++){
            // 1. findMaxMin(연산자 인덱스)
            findMaxMin(firstOperationIndex);
        }

        // 6.최대, 최소 순서대로 출력
        System.out.println(max);
        System.out.println(min);

    }
    
    public static void findMaxMin(int operationIndex){
         // 1. 컴비네이션사이즈가 N-1에 도달하면 수식의 합을 구하고 탐색 중지
        if(combination.size() == combinationSize){
            //1) 값 구하기
            long value = findValue();
            // 1)최댓값 구하기
            max = Math.max(max, value);
            // 2)최솟값 구하기
            min = Math.min(min, value);

            return;
        }

        // 2. 연산자 인덱스 남은 갯수 찾기
        int remain = operations[operationIndex];

        // 3. 해당 인덱스의 남은 연산자가 없다면 종료
        if(remain < 1){
            return;
        }

        // 4. combinations에 연산자 인덱스 넣어주기
        combination.add(operationIndex);

        // 5. 연산자인덱스에 해당하는 연산자 갯수 줄이기
        operations[operationIndex]--;
       
        // 6. 다음 연산자 기호 선택하기 
        for(int nextIndex = 0; nextIndex < 4; nextIndex++){
            // 1) findMaxMin 실행
            findMaxMin(nextIndex);
        }

        // 7. 마지막 방문위치 복구
        int lastIndex = combination.size() - 1;
        combination.remove(lastIndex);

        //8. 사용했던 연산 기호 수 되돌리기
        operations[operationIndex]++;
    }

    public static long findValue(){
        //1. sum에 숫자배열 첫 숫자 넣어줌
        long sum = (long) numbers[0];

        //2. 2번째 숫자부터 마지막숫자까지 계속해서 연산
        for(int numberIndex = 1; numberIndex < N; numberIndex++){
            //3. 다음 숫자 찾기
            long nextNumber = (long) numbers[numberIndex];

            //4. 연산기호 조합 인덱스 찾기 : 연산기호 조합의 인덱스는 항상 다음 숫자 인덱스보다 하나 작다
            int calCombinationIndex = numberIndex - 1;

            //5. 연산기호 찾기
            int calOperation = combination.get(calCombinationIndex);

            if(calOperation == 0){
                sum = sum + nextNumber;
            } else if(calOperation == 1){
                sum = sum - nextNumber;
            } else if(calOperation == 2){
                sum = sum * nextNumber;
            } else if(calOperation == 3){
                sum = sum / nextNumber;
            }
        }

        return sum;
    }
}
