package prime;

import java.util.*;

public class Primes {
    	public static Vector<Long> v=new Vector<Long>();

    public static void main(String arg[]){
        genPrime(0, 50);
        String f1=v.elementAt(0).toString();
        for(int i=0; i<Primes.v.size()-1; i++){
			f1=prime.Multiply.myMulti(f1, Primes.v.elementAt(i+1).toString());
		}
        for(int i=0; i<v.size(); i++)
            System.out.println(v.elementAt(i));
        String t1=prime.Multiply.myMulti(f1, "2");
        String t2=prime.Multiply.myMulti(t1, "3");
        System.out.println(Addition.myAdd(t2, "1"));
    }
	

	
    public static void genPrime(long start, long end) {
        // TODO code application logic here
    	v.removeAllElements();
        long i=(start/6)+1;
        for( ; 6*i+1<end; i++){
        	long l1=6*i-1;
        	if(checkPrime(l1)==true)
        		v.addElement(new Long(l1));
        	long l2=6*i+1;
        	if(checkPrime(l2)==true)
        		v.addElement(new Long(l2));

        }
    }

    static boolean checkPrime(long l){
        if(l%2!=0 && l%3!=0){
            long sq=(long) Math.sqrt(l);
            for(long i=1; 6*i-1<=sq; i++){
                long l1=6*i+1;
                long l2=6*i-1;
                if(l%l1==0 || l%l2==0){
                    return false;
                }else
                    continue;
            }
        }else
            return false;
        return true;
    }
}
