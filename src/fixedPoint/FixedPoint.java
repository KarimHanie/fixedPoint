package fixedPoint;

import java.util.Scanner;
public class FixedPoint {

	public static void main(String[] args) {		
		Scanner sc=new Scanner(System.in);
		Fx fx=new Fx();
		//  take equation ,a,b
		System.out.print("Enter equation: ");
		String fxEquation=sc.nextLine();
		//concatenate equation(String) and +x to create new String
		String gxEquation=fxEquation.concat("+x");
		System.out.print("Enter value of Xo: ");
		fx.setX0(sc.nextDouble());
		System.out.print("Enter Number of iteration: ");
		fx.setnOfI(sc.nextInt());
		System.out.print("Enter tolerance value :");
		fx.setT(sc.nextDouble());
	 //invoke fixedpoint method  send fx(equation) and gx(equation)
		fx.fixedPoint(fxEquation,gxEquation);
				
	
		
	}
}
