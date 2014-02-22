package modulo;

import java.util.Vector;

import prime.Division;
import prime.Multiply;
import rsa.Progress;
import rsa.RSA;

/**
 *
 * @author Student
 */
@SuppressWarnings("unused")
public class Mod {
	
	public static String m2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* Calculations of large modulos.
    	long num=2009;
        long pow=2008;
        long mod=3233;
        long p1=pow, p2;
        long ans=1;
        Vector<Long> v=new Vector<Long>();
        while(p1>=1){
            if(pow%2==0){
                p1=pow/2;
                p2=0;
            }
            else{
                p1=(pow-1)/2;
                p2=1;
            }
            long l=(p2*num)%mod;
            Long l1=new Long(l);
            if(l1!=0)
                v.addElement(l1);
            pow=p1;
            num=(num*num)%mod;
    }
        for(int i=0; i<v.size(); i++){
            ans=(ans*v.elementAt(i).longValue())%mod;
        }
        if(v.size()==0)
            ans=0;
        System.out.println(ans);
        */
    	
    	System.out.println(modInv("12716872682", "4"));
    }
    
    public static String powMod(String num, String pow, String mod){
    	String p1=pow, p2;
    	String ans="1";
    	Vector<String> v=new Vector<String>();
    	v.removeAllElements();
    	while(p1.equals("0")==false){
    		int x1=Integer.parseInt(""+p1.charAt(p1.length()-1));
    		if(x1%2==0){
    			p1=prime.Division.myDiv(pow, "2");
    			p2="0";
    		}else{
    			String temp=pow.substring(0, pow.length()-1)+""+(x1-1);
    			p1=prime.Division.myDiv(temp, "2");
    			p2="1";
    		}
    		String t1=prime.Multiply.myMulti(p2, num);
    		if(t1.equals("0")==false){
    			String t2=Division.myDiv(t1, mod);
    			v.addElement(Division.rem);
    		}	
    		pow=p1;
    		rsa.Progress.ta.append(pow+"\n");
    		String t3=Division.myDiv(num, mod);
    		String t4=Multiply.myMulti(Division.rem, Division.rem);
    		Division.myDiv(t4, mod);
    		num=Division.rem;
    	}
    	for(int i=0; i<v.size(); i++){
    		String t1=Multiply.myMulti(ans, v.elementAt(i));
    		Division.myDiv(t1, mod);
    		ans=Division.rem;
    	}
    	return ans;
    }
    
    public static String modInv(String n, String e){
    	String k="1";
		String i="0";
		String ans;
    	for( ; ; ){
    		i=prime.Addition.myAdd(i, k);
    		String m1=prime.Multiply.myMulti(n, i);
    		m2=prime.Addition.myAdd(m1, ""+1);
    		ans=prime.Division.myDiv(m2, e);
    		if(Integer.parseInt(prime.Division.rem)==0)
    			break;
    	}
    	return ans;
    }
}