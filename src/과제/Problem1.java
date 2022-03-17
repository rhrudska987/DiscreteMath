package 과제;

import java.util.Scanner;
import java.util.Stack;
public class Problem1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int propositionVariable = scanner.nextInt();//명제 변수
		int logicalOperation = scanner.nextInt();//논리 연산자
		int totalNum = scanner.nextInt();//개수의 합
		scanner.nextLine();
		String[] operation_arr = scanner.nextLine().split(" "); //논리식
		if(propositionVariable==0) System.exit(0);
		Stack<String> propositionCases = new Stack<>();//명제 변수들의 경우의 수를 담을 스택
		
		for(int i=0; i<Math.pow(2, propositionVariable); i++) { //경우의 수 계산
			String TFCase = Integer.toBinaryString(i);
			if(TFCase.length()<propositionVariable) {
				for(int j = TFCase.length(); j<propositionVariable; j++) {
					TFCase = "0" + TFCase;
				}
			}
			propositionCases.push(TFCase);
		}
		
		char c = 'P';
		for(int i=0; i<propositionVariable; i++)
			System.out.print(c++ + " ");
		System.out.println("RESULT");
		System.out.println();
		
		while(propositionCases.size()!=0) { //경우의 수 전부를 계산 111, 110 ...
			boolean[] TF = new boolean[propositionVariable];
			char[] c_arr = propositionCases.pop().toCharArray();
			
			for(int i=0; i<c_arr.length; i++) {
				if(c_arr[i]=='0') //0이면 false
					TF[i] = false;
				else //1이면 true
					TF[i] = true;
			}
			Stack<Boolean> pro = new Stack<>(); //명제 변수에 T or F를 담을 스택
			Stack<String> log = new Stack<>(); //논리 연산자를 담을 스택
			for(int i=totalNum-1; i>=0; i--) {
				switch(operation_arr[i]) {
					case "P":
						pro.push(TF[0]);
						break;
					case "Q":
						pro.push(TF[1]);
						break;
					case "R":
						pro.push(TF[2]);
						break;
					case "n":
						boolean not = pro.pop();
						pro.push(!not);
						break;
					default:
						log.push(operation_arr[i]);
				}
			}
			
			boolean result, first, last;
			if(pro.size()!=1) { //pro size가 1인 경우는 n P밖에 없음
				while(log.size()!=0) {
					switch(log.pop()) {
						case "a":
							first = pro.pop(); //스택에서 꺼낸 첫 번째 값
							last = pro.pop(); //스택에서 꺼낸 두 번째 값
							result = first && last;
							pro.push(result);
							break;
						case "o":
							first = pro.pop();
							last = pro.pop();
							result = first || last;
							pro.push(result);
							break;
						case "i":
							last = pro.pop();
							first = pro.pop();
							if(last == true && first == false) 
								pro.push(false);
							else 
								pro.push(true);
							break;
					}
				}
			}
			result = pro.pop();
			String ln = "";
			for(int i=0; i<TF.length; i++) {
				if(TF[i])
					ln += "T ";
				else
					ln += "F ";
			}
			if(result)
				System.out.println(ln + "T");
			else
				System.out.println(ln + "F");
			System.out.println();
			pro.clear();;
			log.clear();
		}
		scanner.close();
	}
}
