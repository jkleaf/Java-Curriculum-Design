package org.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.game.GameFrame;
import javax.swing.JTextArea;

public class ContentPanel extends JPanel {
	
	private static SettingDialog settingDialog=new SettingDialog();
	public static final int PANEL_HGAP=80;
	public static final int PANEL_VGAP=120;	
	public static boolean isStarted;//isPainted;
	public static JTextArea textArea;
	public static JButton button1;
	public static JButton button2;
	public static JButton button3;
//	private static final String[] items=new String[]{"玩家","电脑"};
	
	public ContentPanel() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0,0));
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		JPanel panel1_1=new JPanel();
		panel1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel1_1, BorderLayout.CENTER);
		
		button1 = new JButton("\u5F00\u59CB\u6E38\u620F");
		button1.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button1.setPreferredSize(new Dimension(200, 50));
		button1.setBackground(new Color(211, 211, 211));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("开始游戏")){
					isStarted=true;
					button1.setText("结束游戏");
					textArea.append("游戏开始！\n");
					button3.setEnabled(false);
				}else{
					button1.setText("开始游戏");
					button2.setEnabled(true);
					GameFrame.getFrame().initChessBoard();
					isStarted=false;
					GameFrame.chessList.clear();
					textArea.setText("");
					button3.setEnabled(true);
				}
			}
		});
		addButtonEnteredListener(button1, new Color(0 ,191 ,255), new Color(211, 211, 211));
		panel1_1.add(button1);
		button2 = new JButton("\u6094\u68CB");
		button2.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button2.setPreferredSize(new Dimension(150, 50));
		button2.setBackground(new Color(211, 211, 211));
		button2.setToolTipText("每局只能悔棋一次!");
		button2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(isStarted&&!GameFrame.chessList.isEmpty()){
					String s="每局只能悔棋一次!\n是否继续？";
					int res=JOptionPane.showConfirmDialog(null, s,"提示",JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION){
						button2.setEnabled(false);
						GameFrame.getFrame().goBack();
					}
				}
			}
		});
		addButtonEnteredListener(button2, new Color(255 ,99 ,71), new Color(211, 211, 211));
		panel1_1.add(button2);
		JPanel panel1_2=new JPanel();
		panel1_2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(panel1_2, BorderLayout.EAST);
		button3 = new JButton("\u8BBE\u7F6E");
		button3.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button3.setForeground(new Color(0, 0, 0));
		button3.setPreferredSize(new Dimension(150, 50));
		button3.setBackground(new Color(211, 211, 211));
		addButtonEnteredListener(button3, new Color(238,230,133), new Color(211, 211, 211));
		button3.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				settingDialog.setVisible(true);
				button1.setEnabled(false);
				button2.setEnabled(false);
			}
		});
		panel1_2.add(button3);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
		this.add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
		panel3.setPreferredSize(new Dimension(164, 200));
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setFont(new Font("华文新魏", Font.PLAIN, 15));
		JScrollPane scrollPane=new JScrollPane(textArea);
		panel3.add(scrollPane,BorderLayout.CENTER);
		this.add(panel3, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("\u6E05 \u5C4F");
		btnNewButton.setBackground(new Color(200, 200, 200));
		btnNewButton.setFont(new Font("华文新魏", Font.PLAIN, 25));
		panel3.add(btnNewButton, BorderLayout.SOUTH);
		btnNewButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		setVisible(true);
//		JPanel panel3_1 = new JPanel();
//		panel3.add(panel3_1);
//		panel3_1.setLayout(new BoxLayout(panel3_1, BoxLayout.Y_AXIS));
//		panel3_1.add(Box.createVerticalStrut(100));
//		JPanel panel3_1_1=new JPanel();
//		panel3_1_1.setPreferredSize(new Dimension(150, 200));
//		panel3_1_1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
//		panel3_1.add(panel3_1_1);
//		panel3_1_1.setLayout(new BorderLayout(0, 0));
//		
////		JComboBox<String> comboBox = new JComboBox();
////		comboBox.addItem(items[0]);
////		comboBox.addItem(items[1]);
////		panel3_1_1.add(comboBox, BorderLayout.NORTH);
//		panel3_1.add(Box.createVerticalStrut(100));
//		JPanel panel3_1_2=new JPanel();
//		panel3_1_2.setPreferredSize(new Dimension(150, 200));
//		panel3_1_2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
//		panel3_1_1.setBackground(new Color(211, 211, 211));
//		panel3_1.add(panel3_1_2);
//		panel3_1_2.setLayout(new BorderLayout(0, 0));
//		panel3_1_2.setBackground(new Color(211, 211, 211));
//		JComboBox<String> comboBox_1 = new JComboBox();
//		comboBox_1.addItem(items[1]);
//		comboBox_1.addItem(items[0]);
//		panel3_1_2.add(comboBox_1, BorderLayout.NORTH);
	}
	
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		System.err.println("paint");
//		g.drawImage(bgImg,8,68,714,690,null);
//		g.setColor(Color.BLACK);
//		Graphics2D g2=(Graphics2D)g;
//		g2.setStroke(new BasicStroke(2.0f));
//		for(int i=PANEL_VGAP;i<=PANEL_VGAP+40*14;i+=40)
//			g2.drawLine(PANEL_HGAP, i, PANEL_HGAP+40*14, i);
//		for(int i=PANEL_HGAP;i<=PANEL_HGAP+40*14;i+=40)
//			g2.drawLine(i, PANEL_VGAP, i, PANEL_VGAP+40*14);
//		g.fillOval(PANEL_HGAP+40*3-6, PANEL_VGAP+40*3-6, 12, 12);
//		g.fillOval(PANEL_HGAP+40*11-6, PANEL_VGAP+40*3-6, 12, 12);
//		g.fillOval(PANEL_HGAP+40*3-6, PANEL_VGAP+40*11-6, 12, 12);
//		g.fillOval(PANEL_HGAP+40*11-6, PANEL_VGAP+40*11-6, 12, 12);
//		g.fillOval(PANEL_HGAP+40*7-6, PANEL_VGAP+40*7-6, 12, 12);
//	}
	
	public void addButtonEnteredListener(final JButton button,final Color c1,final Color c2){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(c1);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(c2);
			}
		});
	}
	
	public static void setTextArea(String player,int x,int y){
		textArea.append(player+"("+x+","+y+")\n");
	}
	
	public static void setTextArea(String txt){
		textArea.append(txt);
	}
}
