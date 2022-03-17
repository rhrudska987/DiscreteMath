package ����;

import java.util.Scanner;
import java.util.Stack;
public class Problem1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int propositionVariable = scanner.nextInt();//���� ����
		int logicalOperation = scanner.nextInt();//�� ������
		int totalNum = scanner.nextInt();//������ ��
		scanner.nextLine();
		String[] operation_arr = scanner.nextLine().split(" "); //����
		if(propositionVariable==0) System.exit(0);
		Stack<String> propositionCases = new Stack<>();//���� �������� ����� ���� ���� ����
		
		for(int i=0; i<Math.pow(2, propositionVariable); i++) { //����� �� ���
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
		
		while(propositionCases.size()!=0) { //����� �� ���θ� ��� 111, 110 ...
			boolean[] TF = new boolean[propositionVariable];
			char[] c_arr = propositionCases.pop().toCharArray();
			
			for(int i=0; i<c_arr.length; i++) {
				if(c_arr[i]=='0') //0�̸� false
					TF[i] = false;
				else //1�̸� true
					TF[i] = true;
			}
			Stack<Boolean> pro = new Stack<>(); //���� ������ T or F�� ���� ����
			Stack<String> log = new Stack<>(); //�� �����ڸ� ���� ����
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
			if(pro.size()!=1) { //pro size�� 1�� ���� n P�ۿ� ����
				while(log.size()!=0) {
					switch(log.pop()) {
						case "a":
							first = pro.pop(); //���ÿ��� ���� ù ��° ��
							last = pro.pop(); //���ÿ��� ���� �� ��° ��
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
