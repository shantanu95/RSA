package rsa;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import prime.Division;
import prime.Primes;
	
@SuppressWarnings("serial")
public class RSA extends JFrame implements ActionListener {
	
		static String p1, p2, n, phi, d, m, e;
		static Thread t1;
		static boolean set=false, tAlive=false;
		String s, f="";
		JTextArea ta1;
		static JTextArea ta2;
		JButton b1, b2;
		static JButton b3;
		static Thread en, de, pr;
		
		RSA(String s){
			super(s);
			Container c=this.getContentPane();
			ta1=new JTextArea();
			ta2=new JTextArea();
			Font f=new Font("Dialog.plain", Font.PLAIN, 24);
			ta1.setFont(f);
			ta2.setFont(f);
			JScrollPane s1=new JScrollPane(ta1);
			JScrollPane s2=new JScrollPane(ta2);
			Buttons but=new Buttons();
			b1.addActionListener(this);
			b2.addActionListener(this);
			b3.addActionListener(this);
			this.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
			c.add(s1);
			c.add(but);
			c.add(s2);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		class Buttons extends JPanel{
			Buttons(){
				super();
				b1=new JButton("Encrypt");
				b2=new JButton("Decrypt");
				b3=new JButton("Show Progress");
				this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
				add(b3);
				add(Box.createHorizontalGlue());
				add(b1);
				add(b2);
				this.setBackground(Color.LIGHT_GRAY);
			}
		}
	
	/*
	static void setVal(){
		
		prime.Primes.genPrime(0, 50);
		String f1=Primes.v.elementAt(0).toString();
		for(int i=0; i<Primes.v.size()-1; i++){
			f1=prime.Multiply.myMulti(f1, Primes.v.elementAt(i+1).toString());
		}
		String t1=prime.Multiply.myMulti(f1, ""+2);
		t1=prime.Multiply.myMulti(t1, ""+3);
		String p1=Addition.myAdd(t1, ""+1);
		
		Primes.genPrime(0, 60);
		String f2=Primes.v.elementAt(0).toString();
		for(int i=0; i<Primes.v.size()-1; i++){
			f2=prime.Multiply.myMulti(f2, Primes.v.elementAt(i+1).toString());
		}
		String t2=prime.Multiply.myMulti(f2, ""+2);
		t2=prime.Multiply.myMulti(t2, ""+3);
		String p2=Addition.myAdd(t2, ""+1);
		
		n=prime.Multiply.myMulti(p1, p2);
		phi=prime.Multiply.myMulti(t1, t2);
		
		prime.Primes.genPrime(60, 80);
		e=""+Primes.v.elementAt(0).longValue();
		
		d=modulo.Mod.modInv(phi, e);
		
		System.out.println("n: "+n);
		System.out.println("phi: "+phi);
		System.out.println("e: "+e);
		System.out.println("d: "+d);
		System.out.println("Values set");
	}*/

	/**
	 * @param args
	 */
	
	static String pad(String s, int k){
			int i=s.length();
			for( ; i<=k-1; i++){
				s="0"+s;
			}
			return s;
	}	
		
	static Vector<String> divideStr(String s, int i){
		int k;
		Vector<String> v=new Vector<String>();
		v.removeAllElements();
		while(true){
			k=s.length();
			if(k<=i){
				v.addElement(s);
				break;
			}else{
				String t=s.substring(0, i);
				v.addElement(t);
				s=s.substring(i, s.length());
			}
		}
		return v;
	}
		
	static String makeStr(String s){
		String f="";
		for(int i=0; i<s.length(); i++){
			String t1=""+(int)s.charAt(i);
			String t2=pad(t1, 3);
			f=f+t2;
		}	
		return f;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RSA r=new RSA("RSA Implementation");
		Progress.p=new Progress(r);
		r.setSize(640, 480);
		r.setVisible(true);
		T1 a=new T1();
		Thread t=new Thread(a);
		t.start();
		pr=new Thread(Progress.p);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub 
		m=ta1.getText();
		while(set==false)
			;
		if(ae.getSource()==b1){
			Encrypt e=new Encrypt();
			en=new Thread(e);
			en.start();
		}else if(ae.getSource()==b2){
			Decrypt d=new Decrypt();
			de=new Thread(d);
			de.start();
		}else{
			pr=new Thread(Progress.p);
			pr.start();
			}	
		}
	}

class Encrypt implements Runnable{
	
	String c;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Date d1=new Date();
		RSA.ta2.setText("");
		String t1=RSA.makeStr(RSA.m);
		Vector<String> v=RSA.divideStr(t1, 39);
		for(int i=0; i<v.size(); i++){
			c=modulo.Mod.powMod(v.elementAt(i), RSA.e, RSA.n);
			String t2=RSA.pad(c, 42);
			Progress.ta.append("Plain text: "+v.elementAt(i)+"\n");
			Progress.ta.append("Encrypted text: "+t2+"\n");
			Progress.ta.append("Length of encrypted text: "+t2.length()+"\n");
			c=makeStr(t2);
			RSA.ta2.append(c);
		}
		Date d2=new Date();
		Progress.ta.append("Time taken: "+(d2.getTime()-d1.getTime())+"ms\n");
	}
	
	String makeStr(String s){
		String f="";
		Vector<String> v=RSA.divideStr(s, 3);
		for(int i=0; i<v.size(); i++){
			int x=Integer.parseInt(v.elementAt(i));
			f=f+(char)x;
		}
		return f;
	}
	
}

class Decrypt implements Runnable{

	String c;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Date d1=new Date();
		RSA.ta2.setText("");
		String t1=RSA.makeStr(RSA.m);
		Vector<String> v=RSA.divideStr(t1, 42);
		for(int i=0; i<v.size(); i++){
			String t2=remZero(v.elementAt(i));
			Progress.ta.append("Decrypt text: "+v.elementAt(i)+"\n");
			c=modulo.Mod.powMod(t2, RSA.d, RSA.n);
			int x=(3-(c.length()%3))%3;
			for(int x1=0; x1<x; x1++)
				c="0"+c;
			Progress.ta.append("Decrypted text: "+c+"\n");
			c=makeStr(c);
			RSA.ta2.append(c);
		}
		Date d2=new Date();
		Progress.ta.append("Time taken: "+(d2.getTime()-d1.getTime())+"ms\n");
	}
	
	String remZero(String q){
		while(q.charAt(0)=='0' && q.length()>1)
			q=q.substring(1, q.length());
		return q;
	}
	
	String makeStr(String s){
		String f="";
		Vector<String> v=RSA.divideStr(s, 3);
		for(int i=0; i<v.size(); i++){
			int x=(Integer.parseInt(v.elementAt(i))+256)%256;
			f=f+(char)x;
		}
		return f;
	}
	
}

class T1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		RSA.p1="48112959837082048697";
		RSA.p2="54673257461630679457";
		RSA.n=prime.Multiply.myMulti(RSA.p1, RSA.p2);
		Progress.ta.append("n: "+RSA.n+" "+"\n");
		
		int i=Integer.parseInt(""+RSA.p1.charAt(RSA.p1.length()-1));
		String t1=RSA.p1.substring(0, RSA.p1.length()-1)+(i-1);
		i=Integer.parseInt(""+RSA.p2.charAt(RSA.p2.length()-1));
		String t2=RSA.p2.substring(0, RSA.p2.length()-1)+(i-1);
		RSA.phi=prime.Multiply.myMulti(t1, t2);
		Progress.ta.append("phi: "+RSA.phi+" "+"\n");
		
		boolean found=false;
		long l=100;
		while(found==false){
			prime.Primes.genPrime(l, l+10);
			for(int i1=0; i1<Primes.v.size(); i1++){
				RSA.e=Primes.v.elementAt(i1).toString();
				Division.myDiv(RSA.phi, Primes.v.elementAt(i1).toString());
				if(Division.rem.equals("0")==false){
					found=true;
					break;
				}
			}
			l=l+10;
		}
		Progress.ta.append("e: "+RSA.e+"\n");
		
		RSA.d=modulo.Mod.modInv(RSA.phi, RSA.e);
		Progress.ta.append("d: "+RSA.d+"\n");		
		RSA.set=true;
	}
	
}
