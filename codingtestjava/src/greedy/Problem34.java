package greedy;

/*
 <문제 34>
 길이가 N인 수열이 주어질 때 수열의 합을 구하려고 한다. 그런데 수열의 합을 구하기 전에 먼저 수열 안에 잇는 임의의 두 수를 묶으려고 한다.
 위치에 상관없이 두 수를 묶을 수 있다. 단, 같은 위치에 있는 수(자기 자신)를 묶을 수는 없다. 묶인 두 수는 수열의 합을 구할 때 서로 곱한 후 계산한다.
 수열의 모든 수는 각각 한 번씩만 묶을 수 있다. 예를 들어 어떤 수열이 {0,1, 2, 4, 3, 5}일 때 그냥 이 수열의 합을 구하면 0 + 1 + 2 + 4 + 3 + 5 = 15이다.
 하지만 2와 2을 묶고, 4와 5를 묶으면 0 + 1 + (2 * 3) + (4 * 5) = 27이 돼 최댓값이 나온다.
 
 주어진 수열의 각 수를 적절히 묶어 그 합을 최대로 만드는 프로그램을 작성하시오.
 */

/*
 <출제 포인트>
 (1) 그리디 알고리즘에 대한 이해 : -1 큰 수부터 뽑아 곱해야지 값이 커진다. -2 숫자의 범위를 어떻게 나누어야 할지(1이 곱하면 사라지는데 더할 때는 남는다.)
 (2) 우선순위 큐에 대한 이해(큰 수부터 뽑을 때 사용법) 및 구현
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.PriorityQueue;

public class Problem34 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        //받은 수를 큰 수부터 뽑는다.
        PriorityQueue<Integer> pluspq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minuspq = new PriorityQueue<>();
        int zero_count = 0;
        int one_count = 0;
        for(int i = 0; i < N; i++){
            int data = sc.nextInt();
            if(data > 1) {
                pluspq.add(data);
            } else if(data < 0) {
                minuspq.add(data);
            } else if(data == 0) {
                zero_count += 1;
            } else {
                one_count += 1;
            }
    
        }

        int sum = 0;
        while(pluspq.size() > 1){
            int data1 = pluspq.remove();
            int data2 = pluspq.remove();
            sum += data1 * data2;
        }
        if(pluspq.size() == 1){
            sum += pluspq.remove();
        }
        while(minuspq.size() > 1){
            int data1 = minuspq.remove();
            int data2 = minuspq.remove();
            sum += data1 * data2;
        }
        if(zero_count != 0 && minuspq.size() == 1){
            sum += 0 * minuspq.remove();
        } else if(zero_count == 0 && minuspq.size() == 1){
            sum += minuspq.remove();
        }
        System.out.println(sum + one_count);

    }

}
