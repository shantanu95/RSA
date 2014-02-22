package prime;

public class Division {
	
	public static String rem="";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String a="0";
		String b="5324356786867745375";
		System.out.println("Quotient: "+myDiv(a, b));
		System.out.println("Remainder: "+rem);*/
		String s="1";
		System.out.println(s.substring(0, s.length()-1));
	}
	
	public static String myDiv(String s1, String i1){
		String q="", tq="";
		rem="";
		for(int i=0; i<s1.length(); i++){
			String k=rem+""+s1.charAt(i);
			for(int z=1; z<=10; z++){
				String prod=Multiply.myMulti(i1, ""+z);
				String dif=Subtraction.mySub(k, prod);
				if(dif.equals("NP")){
					q=q+""+(z-1);
					tq=""+(z-1);
					break;
				}	  
				else
					continue;
			}
			//k1=k/i1;
			String prod=Multiply.myMulti(tq, i1);
			rem=""+Subtraction.mySub(k, prod);
			//rem=""+(k-(k1*i1));
		}
		
		while(q.charAt(0)=='0' && q.length()>1)
			q=q.substring(1, q.length());
		return q;
	}

}
