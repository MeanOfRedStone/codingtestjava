package greedy;

/*
 * 

 max = 0
 rope[0]
 max = rope[0]

 rope[1]
 if(rope[0] < rope[1]){
    
    count * rope[0] < rope[1]
 } else{

 }
 rope[1]
 10 30 50 40 -> 50 40 30 10
 (1)
 max = ropes[0]
 (2)
 count += 1;
 if(max < ropes[1] * count){
    max = ropes[1] * count
 } else{
    break;
 }
 */

/*
 <출제 포인트>
 -1 왜 그리디 알고리즘 사용했지?
 현재 상황에서 최적해를 구하는 방법을 택했기 때문이다
 -> 그리디 알고리즘 분류인거 몰랐어도 풀 수 있었을까?
 -> 가장 단순하게 최적해를 생각할때 그리디를 생각해보자
 
 -2 조심할 것
 이전것은 빼졌더라도 더 작은 밧줄이 더 큰 수로 곱해지면 더 많이 들 수도 있다.
 break 주의
 */

import java.util.*;
public class Baekjoon2217 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Integer ropes[] = new Integer[N];
        for(int i = 0; i < N; i++){
            ropes[i] = sc.nextInt();
        }

        Arrays.sort(ropes, Collections.reverseOrder());

        int max = 0;
        int count = 0;
        for(int i = 0; i < N; i++){
            count += 1;
            if(max < ropes[i] * count){
                max = ropes[i] * count;
            } else{
                continue;
            }
        }
        System.out.println(max);
    }
}
