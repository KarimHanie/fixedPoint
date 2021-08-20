package fixedPoint;

import net.objecthunter.exp4j.ExpressionBuilder;
									/*Karim Hanie Medhat*/
public class Fx {
	private  double tol;
	private int nOfI;//number of iteration 
	private int n=1;//counter for iterations
	// x0 initial value of x 
	private double x0,gx,fx;
	private double result;
	private boolean cond1; //| Fx <= tol |
	private boolean cond2; // check N
	
	//getters and setters
	public double getT()
	{
		return tol;
	}
	public void setT(double tolerance)
	{
		tol=tolerance;
	}
	public int getnOfI()
	{
		return nOfI;
	}
	public void setnOfI(int i)
	{
		nOfI=i;
	}

	public int getN()
	{
		return n;
	}
	public void setN(int newN)
	{
		if(n<=newN)
		n++;
	}
	public double getFx() {
		return fx;
	}
	public void setFx(double fx) {
		this.fx = fx;
	}
	public double getX0() {
		return x0;
	}
	public void setX0(double x0) {
		this.x0 = x0;
	}
	public double getGx() {
		return gx;
	}
	public void setGx(double gx) {
		this.gx = gx;
	}
		// method take string double num(Xo=initial value of x )
	public double fOfx(String equation ,double num)
	{
		// library exp4j 
		/*
		 * build equation >> change from String equation to become like a predefined formula   
		 * setVariable    >> replace each x in equation by  num( any decimal value(double,int,.......)  )  
		 * evaluate 	  >> calculate equation after assigning a value of the number to it, return a double value 
		 * 
		 * */
		
		net.objecthunter.exp4j.Expression exp = new ExpressionBuilder(equation).variables("x").build().setVariable("x", num);
		result = exp.evaluate();
		return result;
	}
	public double gOfx(String gxEquation ,double num)
	{
		net.objecthunter.exp4j.Expression exp = new ExpressionBuilder(gxEquation).variables("x").build().setVariable("x", num);
		result = exp.evaluate();
		return result;
	}
	
	// did i found tolerance or not.... return false or true 
	public boolean checkCond1(double fx)
	{
		if(Math.abs(fx)>tol)
			cond1=false;
		 if(Math.abs(fx)<=tol)
			 cond1=true;
		 
		 return cond1;	
	}
	// check did i reach to max number of iteration , return false or true 
	public boolean checkCond2(int n)
	{
		if(this.n<n)
			return cond2;
		else
			return cond2=true;
	}
	public void fixedPoint(String fxEq,String gxEq)
	{	
		
		/*			calc  fx , gx
		 *  pass value of Xo ,fxEq, to fOfx Method to evaluate equation 
		 * return double value, pass it to setFx
		 * 
		 * pass value of Xo ,gxEq, to fOfx Method to evaluate equation 
		 * return double value, pass it to setGx
		 */
		setFx(fOfx(fxEq,getX0()));
		setGx(gOfx(gxEq,getX0()));
		//print  table header 
		System.out.printf(" %-3s%-12s%-12s%-12s%-12s%-12s\n","i","Xi","F(x)","G(x)","cond1","cond2");	
			for(int i=0;i<getnOfI() ;i++)
			{				
				/*
				 * print value of i,Xi,F(x),G(x),cond1,cond2
				 * send number of iteration to  checkCond2 return boolean 
				 * send double value of F(x) to checkCond1  return boolean
				 */
				System.out.printf(" %-3d%-12.4f%-12.4f%-12.4f%-12b%-12b\n",getN(),getX0(),getFx(),getGx(),checkCond1(getFx()),checkCond2(getnOfI()));	
				
				// placed after print cause last iteration row will not be printed 
				//check if we found the root ..check cond1 ..True then break 
				if(checkCond1(getFx()))
				{
					System.out.printf(" Root =%-12.4f",getGx());
					break;
				}
				//update xi.. xi=Gx will used to calc new Fx ,gx
				setX0(getGx());
				//update value of fx , gx based on new Xi above
				setFx(fOfx(fxEq,getX0()));
				setGx(gOfx(gxEq,getX0()));
				// update number of iteration by passing number of iteration that i took from user 
				setN(getnOfI());
				
			}		
	}
}
