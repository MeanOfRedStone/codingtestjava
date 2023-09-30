package dynamic;



import java.util.*;
public class Baekjoon24416 {
    static int fibCount;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        fibCount = 0;

        //피보나치 재귀호출
        fib(n);

        int fibonacci[] = new int[41];
        int fibonacciCount = 0;
        fibonacci[1] = fibonacci[2] = 1;
        //피보나치 동적 프로그래밍
        for(int i = 3; i <= n; i++){
            fibonacciCount += 1;
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        System.out.println(fibCount + " " + fibonacciCount);
    }
    static int fib(int i){
        if(i == 1 || i == 2){
            fibCount += 1;
            return 1;
        } else{      
            return (fib(i -1) + fib(i-2));
        }
    }
}
