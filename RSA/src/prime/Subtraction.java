package prime;

public class Subtraction {
	
	static String s1, s2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="0";
		String b="2";
		System.out.println(mySub(a, b));

	}
	
	public static String mySub(String st1, String st2){
		int b=0;
		s1=st1;
		s2=st2;
		String ans="";
		sameLength();
		for(int i=0; i<s1.length(); i++){
			int x1=Integer.parseInt(""+s1.charAt(s1.length()-i-1));
			int x2=Integer.parseInt(""+s2.charAt(s2.length()-i-1));
			int x=x1-x2-b;
			if(x>=0){
				ans=x+""+ans;
				b=0;
			}else{
				ans=(10+x)+""+ans;
				b=1;
			}
		}
		if(b==1)
			ans="NP";
		while(ans.charAt(0)=='0' && ans.length()>1)
			ans=ans.substring(1, ans.length());
		return ans;
	}
	
	static void sameLength(){
		if(s1.length()>=s2.length()){
			int i=s1.length()-s2.length();
			for(int i1=0; i1<i; i1++)
				s2=""+0+s2;
		}else{
			int i=s2.length()-s1.length();
			for(int i1=0; i1<i; i1++)
				s1=""+0+s1;
		}
	}

}
