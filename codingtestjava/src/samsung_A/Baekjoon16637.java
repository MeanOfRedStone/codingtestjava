package samsung_A;
/*
 모두 괄호를 친 것을 탐색하는 경우를 생각하지 못했다.
 
 -> 이런 경우 모든 경우의 수를 백트래킹 + 탐색하며 확인하는 것을 기억하자.
 
 -> 괄호와 기호 계산 참고
 */
import java.io.*;
import java.util.*;

public class Baekjoon16637 {
    static int N;
    static int max;
    static List<Integer> numbers;
    static List<Character> operations;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            max = Integer.MIN_VALUE;
            numbers = new ArrayList<>();
            operations = new ArrayList<>();

            String input = br.readLine();
            for(int i = 0; i < N; i++){
                if(i % 2 == 0){
                    numbers.add(input.charAt(i) - '0');
                    
                } else{
                    operations.add(input.charAt(i));
                }
            }

            dfs(0, numbers.get(0));

            System.out.println(max);
    }

    public static void dfs(int index, int sum){
        if(index >= N / 2){ // 숫자랑 ,기호로 나누었기 때문에 길이는 N / 2로 생각한다.
            max = Math.max(max, sum);
            return;
        }

        //그냥 진행
        dfs(index + 1, calculate(index, sum, numbers.get(index+1)));
        
        //괄호 진행
        if(index + 1 < N / 2){ // operation 의 인덱스 제약 조건
            //뒤에 괄호 친 경우
            int num = calculate(index + 1, numbers.get(index + 1), numbers.get(index + 2));

            dfs(index + 2, calculate(index, sum, num));
        }
    }

    public static int calculate(int operationsIndex, int sum, int nextNumber){
        char operation = operations.get(operationsIndex);
        
        if(operation == '+'){
            sum = sum + nextNumber;
        } else if(operation == '-'){
            sum = sum - nextNumber;
        } else if(operation == '*'){
            sum = sum * nextNumber;
        }

        return sum;
    }
}
