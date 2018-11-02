package com.calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.tool.ImgUtil;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class CalculatorTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private DetailedResults dResults=new DetailedResults();
	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel_1;
	private String name[]={"n!","MOD","","C","7","8","9","÷","4",
		"5","6","×","1","2","3","-","0",".","=","+"},finalResults=null;
	static Point p=new Point();
	private JLabel lblNewLabel;
	private JButton button_1;
	private ArrayList<JButton> buttons=new ArrayList<>();
	private Image closeImg=ImgUtil.getImage("images/close.png");
	private Image backspaceImg=ImgUtil.getImage("images/backspace.png");
	private boolean pointButtonClickable=true;
	private boolean factorialExists;
	private boolean isFacted,isCalculated,hasFact;
	private int has,prestatus;
	private BigDecimal num=BigDecimal.ZERO,preNum=BigDecimal.ZERO,
			secondNum=BigDecimal.ZERO,preSecondNum=BigDecimal.ZERO;
	private boolean execute,firstCalculate=true,onlyEquals;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					CalculatorTest frame = new CalculatorTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initLisenter(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				p.x=e.getX();
				p.y=e.getY();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					if(e.getX()>=410&&e.getY()<=30){
						System.exit(0);
					}
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point=getLocation();
				setLocation(point.x+e.getX()-p.x,point.y+e.getY()-p.y);
			}
		});
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dResults.setVisible(true);
			}
		});
	}
	
	public CalculatorTest() {
		setUndecorated(true);
		setOpacity(0.9f);
		setTitle("\u8BA1\u7B97\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 300, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(50, 150));
		panel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setToolTipText("\u53EF\u8BA1\u7B97\u5927\u6570");
		textField.setForeground(Color.DARK_GRAY);
		textField.setText("0");
		textField.setFont(new Font("Tahoma", Font.BOLD, 70));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		panel.add(textField);
		
		lblNewLabel = new JLabel("\u8BA1  \u7B97  \u5668");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("华文琥珀", Font.PLAIN, 28));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		button_1 = new JButton("D e t a i l e d        R e s u l t s");
		button_1.setFont(new Font("One Stroke Script LET", Font.PLAIN, 18));
		button_1.setBackground(SystemColor.menu);
		button_1.setToolTipText("显示详细结果");
		panel.add(button_1, BorderLayout.SOUTH);
		panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(5, 4, 0, 0));
		backspaceImg=backspaceImg.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
		
        initLisenter();
        
		for(int i=0;i<name.length;i++){
			JButton button=new JButton(name[i]);
			buttons.add(button);
			if(!name[i].equals("MOD"))
				button.setFont(new Font("", Font.BOLD, 40));
			else
				button.setFont(new Font("", Font.BOLD, 31));
			if(!name[i].equals("")&&!Character.isDigit(name[i].charAt(0))){
				button.setBackground(new Color(211, 211, 211));
				if(name[i].equals(".")){
					addAppendPointListener(button);
				}else if(name[i].equals("C")){
					addClearButtonListener(button);
				}else if(name[i].equals("=")){
					outputResults(button);
				}else{
					addCalcuButtonListener(button);
				}
			}else if(name[i].equals("")){
				button.setBackground(new Color(211, 211, 211));
				button.setIcon(new ImageIcon(backspaceImg));
				addBackSpaceListener(button);
			}else{
				button.setFont(new Font("One Stroke Script LET", Font.BOLD, 40));
				button.setBackground(Color.WHITE);
				addNumButtonListener(button);
			}
			button.setForeground(Color.DARK_GRAY);
			panel_1.add(button);
		}
		
		contentPane.add(panel_1, BorderLayout.CENTER);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(closeImg,410,3,30,30,null);
	}
	
	private void setCalcuEnable(boolean enable){
		for (JButton jButton : buttons) {
			String txt=jButton.getText();
			if(!txt.equals("C")){
				jButton.setEnabled(enable);
			}
		}
	}
	
	private void addBackSpaceListener(JButton button) {
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isCalculated){
					String txt=textField.getText();
					int len=txt.length();
					if(len>0){
						txt=txt.substring(0,len-1);
						textField.setText(txt);
						if(txt.length()==0){
							textField.setText("0");
						}
					hasFact=false;//
					factorialExists=false;//
					pointButtonClickable=true;
					}
				}	
			}
		});
	}
	
	private void addNumButtonListener(JButton button){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!factorialExists){
					boolean setblank=false;
					if(textField.getText().equals("0"))
						textField.setText("");
					if(execute){
						if(!setblank){
							textField.setText("");
							setblank=true;
						}
						execute=false;
					}
					if(isCalculated){
						if(!setblank)
							textField.setText("");
						if(has==0)
							initPreNum();
						firstCalculate=true;
						isCalculated=false;
					}
					textField.setText(textField.getText()+e.getActionCommand());
				}
				if(isFacted){
					textField.setText(e.getActionCommand());
					isFacted=false;
					factorialExists=false;
					pointButtonClickable=true;
					hasFact=false;//
				}
			}
		});
	}
	
	private void addAppendPointListener(JButton button){
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pointButtonClickable){
						textField.setText(textField.getText()+".");
					pointButtonClickable=false;
				}
			}
		});
	}
	
	private void clear(){
		textField.setText("0");
		pointButtonClickable=true;
		factorialExists=false;
		isFacted=false;
		hasFact=false;
		isCalculated=false;
		has=0;//
		firstCalculate=true;
		preNum=BigDecimal.ZERO;
		onlyEquals=false;
		setCalcuEnable(true);
	}
	
	private void addClearButtonListener(JButton button){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
	}
	
	private void addCalcuButtonListener(JButton button){
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!factorialExists){
					switch(e.getActionCommand()){
					case "n!":
						if(pointButtonClickable&&!textField.getText().contains(".")){
							textField.setText(textField.getText()+"!");
							hasFact=true;
							factorialExists=true;
							pointButtonClickable=false;
						}
						break;
					case "MOD":
						selectButton();
						has=5;
						prestatus=has;
						onlyEquals=false;
						break;
					case "÷":
						selectButton();
						has=4;
						prestatus=has;
						onlyEquals=false;
						break;
					case "×":
						selectButton();
						has=3;
						prestatus=has;
						onlyEquals=false;
						break;
					case "-":
						selectButton();
						has=2;
						prestatus=has;
						onlyEquals=false;
						break;
					case "+":
						selectButton();
						has=1;
						prestatus=has;
						onlyEquals=false;
						break;
				}
				}
			}
		});
	}

	private void initPreNum(){
		preNum=BigDecimal.ZERO;
		pointButtonClickable=true;
	}
	
	private void checkFirstCalculate(){
		if(firstCalculate){
			preNum=new BigDecimal(textField.getText());
			firstCalculate=false;
		}
	}
	
	private void selectButton(){
		checkFirstCalculate();
		execute=true;
		pointButtonClickable=true;
	}
	
	private void checkResLength(String res){
		if(res.length()>11)
			dResults.setVisible(true);
	}
	
	private String getResults(BigDecimal number){
		String res=number.toPlainString();
		textField.setText(res);
		checkResLength(res);
		return res;
	}
	
	private void outputResults(JButton button){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finalResults=textField.getText();
				if(hasFact){
					preNum=operateFactorial();
					if(preNum==null){
						finalResults="阶乘运算数据过大，输入应不大于10000";
						setCalcuEnable(false);
						dResults.setVisible(true);
					}else if(preNum.toString().equals("-1")){
						finalResults="阶乘运算不能有负数";
						setCalcuEnable(false);
						dResults.setVisible(true);		
					}else{
						finalResults=getResults(preNum);
					}
					isFacted=true;
					factorialExists=false;//
					pointButtonClickable=true;
				}else{
					if(has==0&&prestatus!=0){
						has=prestatus;
						onlyEquals=true;
					}
					if(has==5){
						preNum=operateMod();
						preSecondNum=secondNum;
						finalResults=getResults(preNum);
						has=0;
					}
					else if(has==4){
						preNum=operateDivide();
						if(preNum!=null){
							preSecondNum=secondNum;
							finalResults=getResults(preNum);
							has=0;
						}else{
							finalResults="算术错误，除数应不为0";
							setCalcuEnable(false);
							dResults.setVisible(true);
						}
					}else if(has==3){
						preNum=operateMutiply();
						preSecondNum=secondNum;
						finalResults=getResults(preNum);
						has=0;
					}else if(has==2){
						preNum=operateSubtract();
						preSecondNum=secondNum;
						finalResults=getResults(preNum);
						has=0;
					}else if(has==1){
						preNum=operateAdd();
						preSecondNum=secondNum;
						finalResults=getResults(preNum);
						has=0;
					}
					isCalculated=true;
				}
				dResults.setTextAreaContent(finalResults);
			}
		});
	}

	private BigDecimal operateFactorial(){
		BigDecimal res = null;
		String txt=textField.getText();
		if(txt.contains("!")){
			num=new BigDecimal(txt.substring(0, textField.getText().length()-1));
			if(num.toString().indexOf("-")==0)
				return BigDecimal.valueOf(-1);
		}
		else{
			secondNum=onlyEquals?preSecondNum:new BigDecimal(txt);
			return secondNum;
		}
		if(num.toString().equals("0"))
			return BigDecimal.ONE;
		if(num.compareTo(BigDecimal.valueOf(10000))==1)
			return null;
		long numTmp=num.longValue();
		res=num;
		for(long i=1;i<numTmp;i++){
			res=res.multiply(BigDecimal.valueOf(i));
		}
		return res;
	}
	
	private BigDecimal operateMod(){
		secondNum=onlyEquals?preSecondNum:new BigDecimal(textField.getText());
		return preNum.remainder(secondNum).stripTrailingZeros();
	}
	
	private BigDecimal operateDivide(){
		String res=textField.getText();
		if(res.equals("0")) return null;
		secondNum=onlyEquals?preSecondNum:new BigDecimal(res);
		return preNum.divide(secondNum,50,BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros();
	}
	
	private BigDecimal operateMutiply(){
		secondNum=onlyEquals?preSecondNum:new BigDecimal(textField.getText());
		return preNum.multiply(secondNum).stripTrailingZeros();
	}
	
	private BigDecimal operateSubtract(){
		secondNum=onlyEquals?preSecondNum:new BigDecimal(textField.getText());
		return preNum.subtract(secondNum).stripTrailingZeros();
	}
	
	private BigDecimal operateAdd(){
		secondNum=onlyEquals?preSecondNum:new BigDecimal(textField.getText());
		return preNum.add(secondNum).stripTrailingZeros();
	}
}
