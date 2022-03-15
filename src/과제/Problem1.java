package °úÁ¦;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Problem1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int propositionVariable = sc.nextInt();
		int logicalOperation = sc.nextInt();
		int totalNum = sc.nextInt();
		Scanner ssc = new Scanner(System.in);
		String[] operation_arr = ssc.nextLine().split(" ");
		
		Stack<String> propositionCases = new Stack<>();
		
		for(int i=0; i<Math.pow(2, propositionVariable); i++) {
			String TFCase = Integer.toBinaryString(i);
			if(TFCase.length()<propositionVariable) {
				for(int j = TFCase.length(); j<propositionVariable; j++) {
					TFCase = "0" + TFCase;
				}
			}
			propositionCases.push(TFCase);
		}
		
		System.out.println("P Q R RESULT");
		
		while(propositionCases.size()!=0) {
			boolean[] TF = new boolean[propositionVariable];
			char[] c_arr = propositionCases.pop().toCharArray();
			
			for(int i=0; i<c_arr.length; i++) {
				if(c_arr[i]=='0')
					TF[i] = false;
				else
					TF[i] = true;
			}
			Stack<Boolean> pro = new Stack<>();
			Stack<String> log = new Stack<>();
			for(int i=operation_arr.length-1; i>=0; i--) {
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
			Stack<Boolean> pro2 = new Stack<>();
			while(pro.size()!=0)
				pro2.push(pro.pop());
			
			boolean result;
			while(log.size()!=0) {
				switch(log.pop()) {
					case "a":
						result = pro2.pop() && pro2.pop();
						pro2.push(result);
						break;
					case "o":
						result = pro2.pop() || pro2.pop();
						pro2.push(result);
						break;
					case "i":
						if(pro2.pop() == false && pro2.pop() == true)
							pro2.push(false);
						else
							pro2.push(true);
						break;
				}
			}
			result = pro2.pop();
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
			pro.clear();
			log.clear();
		}
		sc.close();
		ssc.close();
	}

}
