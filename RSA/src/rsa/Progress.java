package rsa;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Progress extends JDialog implements Runnable, ItemListener,
	ActionListener
	{
	static Progress p;
	public static JTextArea ta;
	static JCheckBox cb;
	static JButton but;
	static boolean sel=true;
	Progress(Frame f){
		super(f);
		this.setTitle("Progress Window");
		Container c=this.getContentPane();
		ta=new JTextArea();
		cb=new JCheckBox("Autoscroll");
		cb.setSelected(true);
		cb.addItemListener(this);
		but=new JButton("Clear");
		but.addActionListener(this);
		Horz h=new Horz();
		JScrollPane scr=new JScrollPane(ta);
		scr.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(sel)
					e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
			}});  
		this.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.add(h);
		c.add(scr);
		this.addWindowListener(new WindowAdapter(){
			@SuppressWarnings("deprecation")
			public void windowClosing(WindowEvent we){
				RSA.pr.suspend();
				RSA.b3.setEnabled(true);
				setVisible(false);
			}
		});
		this.setSize(390, 320);
		this.setVisible(false);
	}
	
	class Horz extends JPanel{
		Horz(){
			super();
			this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			add(cb);
			add(Box.createRigidArea(new Dimension(10, 0)));
			add(but);
			add(Box.createHorizontalGlue());
			this.setBorder(BorderFactory.createEmptyBorder(8, 2, 8, 0));
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		p.setVisible(true);
		RSA.b3.setEnabled(false);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getStateChange()==ItemEvent.SELECTED)
			sel=true;
		else
			sel=false;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ta.setText("");
	}
}
