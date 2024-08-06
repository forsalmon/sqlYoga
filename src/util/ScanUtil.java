package util;

import java.util.Scanner;

public class ScanUtil   {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	// static => 객체를 안 만들어도 가져올 수 있다 !
	static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		return nextInt("메뉴 선택 >> ");
	}
	
	
	// 반드시 출력부분을 함께 넣도록 만들어 놓음
	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	// 안내 문구가 없으면 사용을 못하도록 막아 놓은 것
	private static String nextLine() {
		return sc.nextLine();
	}
	
	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}
	
	private static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
