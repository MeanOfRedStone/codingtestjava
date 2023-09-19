package greedy;
import java.util.PriorityQueue;
/*
 <문제33. 카드 정렬하기>
 정렬된 두 묶음의 숫자 카드가 있다. 각 묶음의 카드의 개수가 A,B일 때 보통 두묶음을 합쳐 1개로 만들려면 A+B번 비교해야 한다.
 예를 들어 20장의 숫자 카드 묶음과 30장의 숫자 카드 묶음을 합치려면 50번의 비교가 필요하다.

 매우 많은 숫자 카드 묶음이 책상 위에 놓여있다고 가정해 보자. 이들을 두 묶음씩 골라 서로 합쳐 나가면 고르는 순서에 따라 비교 횟수가 달라진다.
 예를 들어 10장, 20장, 40장의 묶음이 있다면 10장과 20장을 합친 후 30장 묶음과 40장을 합치면 (10 + 20) + (30 + 40) = 100번의 비교가 필요하다.
 그러나 10장과 40장을 합친 후 50장 묶음과 20장을 합치면 (10+40) + (50+20) = 120번의 비교가 필요하므로 덜 효율적이다.

 N개의 숫자 카드 묶음의 각각의 크기가 주어질 때 최소한 몇 번의 비교가 필요한지를 구하는 프로그램을 작성하시오.
 */
/*
 <출체 포인트>
 (1) 그리디 알고리즘의 정의에 대한 이해
 (2) 정렬구현할 수 있는가? -> 정렬이 아니라 우선순위 큐를 활용하면 더 간단히 사용할 수 있다.
 (3) 자바에서 우선순위 큐 라이브러리 구현 능력

 <생각하지 못한 점>
 (1) 그리디 : 어떻게 가장 적은 합이 나오는 짝을 만들 것인가
 <더해서 작은 수>
 (2) 자료구조 :우선 순위 큐

 */
import java.util.Scanner;
import java.util.PriorityQueue;

public class Problem33 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        int n;
        n = sc.nextInt();
        
        /* 배열에 데이터 저장하는 것이 아니라 우선순위 큐에 저장
        int[] cards = new int[n];
         

        for(int i = 0; i < n; i++){
            cards[i] = sc.nextInt();
        }
        

        //카드 뭉치를 크기순으로 정렬
        int min = 9999;
        for(int i = 0; i < n-1; i++){
            for(int j = i; j < n-1; j++){
                if(cards[j] < min){
                    min = cards[j];
                }
            }
            cards[i] = min;
            min = 9999;
        }
        */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n ; i++){
            int data = sc.nextInt();
            pq.add(data);
        }
        
        
        /*
        -우선순위 큐 크기가 1이될 때까지 큐에서 최소 카드 뭉치 두개를 빼고 다시 우선순우 큐에 넣어준다.

        -삽입 연산 add

        -뽑는 연산 remove

        -크기 연산 size
        */
        int data1 = 0, data2 = 0, count = 0;

        while(pq.size() != 1){
            //제일 작은 카드뭉치
            data1 = pq.remove();
            // 그다음으로 작은 카드 뭉치
            data2 = pq.remove();
            count += data1 + data2;
            pq.add(data1+data2);
        }
        System.out.println(count);
    }
    
}
