package learnJava.rz20200307;

public class FinallyCode {
	
	public static void main(String[] args) {
		
		System.out.println(getResult());
	}
	
	public static int getResult(){
		int x;
		try {
			x=1;
			return x;
		}
		catch (Exception ex){
			x=2;
			return x;
		}
		finally {
			
			x=3;
			System.out.println("finally");
		}
	}
}
