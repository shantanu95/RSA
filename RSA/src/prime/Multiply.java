package prime;

import java.util.Vector;

public class Multiply {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(myMulti("1828", "0"));
		
	}
	
	public static String myMulti(String s1, String s2){
		Vector<String> v=new Vector<String>();
		v.removeAllElements();
		for(int i=0; i<s2.length(); i++){
			int x1=Integer.parseInt(""+s2.charAt(s2.length()-i-1));
			String f=sMulti(s1, x1);
			for(int i1=0; i1<i; i1++)
				f=f+""+0;
			v.addElement(f);
		}
		String f="";
		if(v.size()!=0){
			f=v.elementAt(0);
		}
		for(int i=0; i<v.size()-1; i++){
			f=Addition.myAdd(f, v.elementAt(i+1));
		}
		while(f.charAt(0)=='0' && f.length()>1)
			f=f.substring(1, f.length());
		return f;
	}

	static String sMulti(String s1, int i){
		int co=0;
		String f="";
		for(int k=0; k<s1.length(); k++){
			int x1=Integer.parseInt(""+s1.charAt(s1.length()-k-1));
			int x2=co+(x1*i);
			int ans;
			if(k!=s1.length()-1)
				ans=x2%10;
			else
				ans=x2;
			f=ans+""+f;
			if(x2>=10)
				co=x2/10;
			else
				co=0;
			
		}
		return f;
	}
}
