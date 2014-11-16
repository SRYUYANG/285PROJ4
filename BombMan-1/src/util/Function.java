package util;

public class Function {
	public static int getYfromFun1(int x, int maxY, int totalTime){
		// totalTime = -b/2a * 2 = -b/a;
		// maxY = -b*b/4a ;
		double b = (double)maxY / (totalTime >> 2);
		double a = -b / totalTime;
		return (int)(a*x*x + b*x);
	}
	
	public static int getYfromFun2(int x, int maxY, int totalTime){
		double a = (double)maxY / (totalTime*totalTime);
		return (int)(a*x*x);
	}
}
