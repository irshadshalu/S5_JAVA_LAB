/*
<applet code="Calculator.class" width=240 height=200>
</applet>
*/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
public class Calculator extends Applet  implements ActionListener {
	Button numbers[],functions[];
	TextField txt;
	boolean to_clear=false;
	public void init(){
	 	Panel input = new Panel();
        Panel numberkeys = new Panel();
        Panel functionkeys = new Panel();
        setLayout (new BorderLayout ());
		numbers=new Button[10];
		functions=new Button[6];
		numberkeys.setLayout(new GridLayout(4,3));
		functionkeys.setLayout(new GridLayout(3,2,3,3));
		for(int i=1;i<10;i++){
			numbers[i]=new Button(""+i);
			numberkeys.add(numbers[i]);
			numbers[i].addActionListener(this);
		}
		Dimension keypadsize = new Dimension(150,150);
		numberkeys.add(new Button(""));
		numberkeys.setPreferredSize(keypadsize);
		numbers[0]=new Button("0");
		numbers[0].addActionListener(this);
		numberkeys.add(numbers[0]);
		numberkeys.add(new Button(""));
		txt = new TextField(20);
		input.add(txt);
		String[] fns = {" + "," - "," / "," * "," % "," = "};
		for (int i=0; i<6;i++) {
			functions[i] = new Button(fns[i]);
			functionkeys.add(functions[i]);
			functions[i].addActionListener(this);
		}
		add(input,BorderLayout.PAGE_START);
		add(numberkeys,BorderLayout.LINE_START);
		add(functionkeys,BorderLayout.LINE_END);

	}
	public void actionPerformed(ActionEvent evt) {
		Button b = (Button)evt.getSource();

		if(b==functions[5])
		{
			String expr = txt.getText();
			txt.setText(this.evaluate(expr));
		}
		else{
			if(to_clear){
				txt.setText("");
				to_clear=false;
			}
			txt.setText(txt.getText()+b.getLabel());
		}
	}
	private String evaluate(String expr){
		ScriptEngineManager mgr = new ScriptEngineManager();
    	ScriptEngine engine = mgr.getEngineByName("JavaScript");
    	String res="";
    	try{
    		res=(engine.eval(expr)).toString();
    	}
    	catch(Exception e){
    		res="Invalid Expression";
    	}
    	to_clear=true;
    	return res;
	}
}