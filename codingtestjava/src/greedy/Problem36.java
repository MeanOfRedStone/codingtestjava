package greedy;

/*
 <문제 36>
 세준이는 양수와 +, - 그리고 괄호를 이용해 어떤 수식을 만들었다. 
 그리고 괄호를 모두 지우고, 다시 괄호를 적절히 넣어 이 수식의 값을 최소로 만들려고한다.
 이렇게 수식의 괄호를 다시 적절하게 배치해 수식의 값을 최소로 만드는 프로그램을 작성하시오.
 */



import java.util.Scanner;

/*
 <출제 포인트>
 (1) 그리디 알고리즘에 대한 이해
 - 부터 다음 -전까지를 괄호로 묶어주는 것이 가장 좋은 방법
 (2) 문자열 인덱싱 - charAt()
 (3) 문자열 분리 - split
 (참고) + -> \\+ 형태로 해야함
 (4) 문자 -> int : Integer.valueOf() 
 */

public class Problem36 {
    public static void main(String[] args){
        //입력
        Scanner sc = new Scanner(System.in);
        String formula = sc.next();
        //- 를 기준으로 식을 분리
        String[] split = formula.split("-");
        
        //분리된 중 수식 중 첫 묶음 더하기
        int sum = 0;
        String[] split2 = split[0].split("\\+");
        for(int i = 0; i < split2.length; i++){
            sum += Integer.valueOf(split2[i]);
        }
        
        //두번 째 묶음부터는 빼준다
        for(int i = 1; i < split.length; i++){
            int sum2 = 0;
            String[] split3 = split[i].split("\\+");
            for(int j = 0; j < split3.length; j++){
                sum2 += Integer.valueOf(split3[j]);
            }

            sum -= sum2;
        }
        System.out.println(sum);
    }    
}
