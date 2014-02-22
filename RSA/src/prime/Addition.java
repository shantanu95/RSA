package prime;

public class Addition {
	
	static String a, b;
	static String s1="", s2="";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a="28682368127893471293891";
		b="3424234347";
		System.out.println("Number of Digits: "+a.length());
		System.out.println(myAdd(a, b));
	}
	
	public static String myAdd(String st1, String st2){
		s1=st1;
		s2=st2;
		sameLength();
		String f=""+0;
		int co=0;
		if(s1.length()!=0){
			f="";
			for(int i=0; i<s1.length(); i++){
				int x1=Integer.parseInt(""+s1.charAt(s1.length()-i-1));
				int x2=Integer.parseInt(""+s2.charAt(s2.length()-i-1));
				int t=co+x1+x2;
				int ans;
				if(i!=s1.length()-1)
					ans=t%10;
				else
					ans=t;
				f=ans+""+f;
				if(t>=10)
					co=1;
				else
					co=0;
			}
		}
		while(f.charAt(0)=='0' && f.length()>1)
			f=f.substring(1, f.length());
		return f;
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
