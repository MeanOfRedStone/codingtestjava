package dynamic;

/*
 <문제85 퇴사 준비하기>
 상담원으로 일하고 있는 백준이는 퇴사하려고 한다.
 오늘부터 N + 1일째 되는 날 퇴사하기 위해 남은 N일 동한 최대한 많은 상담을 하려고 한다.
 백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁했고, 비서는 하루에 1개씩 서로 다른 사람의 상담을 잡아 놓았다.
 각각의 상담은 상담을 완료하는 데 걸리는 기간 Ti와 상담했을 때 받을 수 있는 금액 Pㅑ로 이루어져 있다.
 N = 7일 때 다음과 같은 상담 일정표를 살펴보자.

 1일에 잡혀 잇는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다.
 5일에 잡혀 있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.
 상담하는 데 필요한 기간은 1일보다 클 수 있기 때문에 모든 상담을 할 수는 없다.
 예를 들어 1일에 상담하면 2일 ,2일에 있는 상담은 할 수 없게 된다.
 2일에 있는 상담을 하면 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.
 또한, N + 1일째에는 회사에 없기 때문에 6,7일에 있는 상담은 할 수 없다.
 퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10 + 20 + 15 = 45이다.
 상담을 적절히 했을 때 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.

 */
/*
 <출제 포인트>
 (1) 동적 계획범
 -> 점화식은 일정하지 않다. 자기만의 방식을 세우자
 -> 최대 수입을 점화식 배열에 저장. 뭘 저장할지를 잘 고민해보자.

 (2) 해설 점화식
 D[i] = D[i + 1]
 D[i] = MAX(D[i + 1], D[[i] + T[i]]+P[i])

 -> 나는 MAX 아이디어까지는 동일하게 냈음
    하지만 상담 하고 안하고를 어떻게 표현할지를 몰랐음
 */

import java.util.*;

public class Question85 {
    // static ArrayList<Sangdam>[] A;
    public static void main(String[] ars){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //점화식 배열 : i일부터 퇴사일까지 벌 수 잇는 최대 수입을 저장 -> D[7]까지 상담이 이루어질 수 있으므로 마지막 점화식 배열은 D[8]에서 끝난다.
        int[] D = new int[N + 2];
        //상담에 필요한 일 수 저장 배열
        int[] T = new int[N + 1];
        //상담 완료했을 때 받는 수입 저장 배열
        int[] P = new int[N + 1];

        for(int i = 1; i <= N; i++){
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }
        for(int i = N; i > 0; i--){ //탑 - 다운
            if(i + T[i] > N + 1){ //오늘 시작되는 상담을 했을 때 퇴사일까지 끝나지 않는 경우
                D[i] = D[i+1];
            }else {
                D[i] = Math.max(D[i + 1], P[i] + D[i + T[i]]); //오늘 시작되는 상담을 했을 때 퇴사일 안에 끝나는 경우. 인덱스로 크기 비교를 진행. 전자 : 오늘 상담을 안하는 경우 / 후자 오늘 상담을 햇을 때 + 이후 최댓값을 더함.
            }
        }
        System.out.println(D[1]);


        /* 
        A = new ArrayList[1];

        A[0] = new ArrayList<>();

        for(int i = 1; i < N+1; i++){
            int t = sc.nextInt();
            int p = sc.nextInt();
            Sangdam schedule = new Sangdam(t, p);
            A[0].add(schedule);
        }
        int days = 7;
        boolean[] working = new boolean[N+1];
        for(int i = 1; i < N+1; i++){
            working[i] = true;
        }
        int[] money = new int[N+1];
        money[0] = 0;
        for(int i = 1; i <= N; i++){
            //오늘 날짜를 기록
            int today = i;
            //오늘 예약을 잡으면 생기는 일정을 기록
            int temp = A[0].get(i).Ti;
            
            money[i] = money[i-1]; 

            //오늘 예약을 잡을경우
            if(today +(temp-1) < days && working[i] ){
                //오늘 예약을 잡은 경우 돈을 등록한다.
                money[i] = money[i-1] + A[0].get(i).Pi;
                //오늘 약속을 잡으면 예약 불가능한 날짜를 설졍
                for(int j = today; j <= today + temp -1; j++){
                    working[j] = true;
                }
            }

        }
        */
    }
}
/*
1.가능한 방식
(1) P[7] -1 : P[5](2) + P[4](1) + P[3](1) = 45
            -2 : P[5](2) + P[4](1) + P[1](3) = 45
(2) P[7] = P[2](5) = 20

2. 결정 인자(Ti)
T[1] = 3 -> T[4] = 1 -> T[5] = 2       
         -> T[5] = 2

T[2] = 5 

T[3] = 1 -> T[4] = 1 -> T[5] = 2
         -> T[5] = 2

T[4] = 1 -> T[5] = 2

T[5] = 2

  */

  /*
   * 나는 구조체(클래스) 만들어서 썼음 해설에서는 간단히 배열로 해결함
  
class Sangdam {
    int Ti;
    int Pi;

    public Sangdam(int Ti, int Pi){
        this.Ti = Ti;
        this.Pi = Pi;
    }
}
 */