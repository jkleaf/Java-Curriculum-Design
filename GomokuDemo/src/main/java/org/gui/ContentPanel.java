package org.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class ContentPanel extends JPanel {
	
	public ContentPanel() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0,0));
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		JPanel panel1_1=new JPanel();
		panel1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel1_1, BorderLayout.CENTER);
		
		JButton button1 = new JButton("\u5F00\u59CB\u6E38\u620F");
		button1.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button1.setPreferredSize(new Dimension(200, 50));
		button1.setBackground(new Color(211, 211, 211));
		addButtonEnteredListener(button1, new Color(0 ,191 ,255), new Color(211, 211, 211));
		panel1_1.add(button1);
		JButton button2 = new JButton("\u6094\u68CB");
		button2.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button2.setPreferredSize(new Dimension(150, 50));
		button2.setBackground(new Color(211, 211, 211));
		addButtonEnteredListener(button2, new Color(255 ,99 ,71), new Color(211, 211, 211));
		panel1_1.add(button2);
		JPanel panel1_2=new JPanel();
		panel1_2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(panel1_2, BorderLayout.EAST);
		JButton button3 = new JButton("\u8BBE\u7F6E");
		button3.setFont(new Font("华文新魏", Font.PLAIN, 30));
		button3.setForeground(new Color(0, 0, 0));
		button3.setPreferredSize(new Dimension(150, 50));
		button3.setBackground(new Color(211, 211, 211));
		addButtonEnteredListener(button3, new Color(238,230,133), new Color(211, 211, 211));
		panel1_2.add(button3);
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
		this.add(panel2, BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
		this.add(panel3, BorderLayout.EAST);
		
		JPanel panel3_1 = new JPanel();
		panel3.add(panel3_1);
		panel3_1.setLayout(new BoxLayout(panel3_1, BoxLayout.Y_AXIS));
		panel3_1.add(Box.createVerticalStrut(100));
		JPanel panel3_1_1=new JPanel();
		panel3_1_1.setPreferredSize(new Dimension(150, 200));
		panel3_1_1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		panel3_1.add(panel3_1_1);
		panel3_1_1.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBox = new JComboBox();
		
		panel3_1_1.add(comboBox, BorderLayout.NORTH);
		panel3_1.add(Box.createVerticalStrut(100));
		JPanel panel3_1_2=new JPanel();
		panel3_1_2.setPreferredSize(new Dimension(150, 200));
		panel3_1_2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		panel3_1_1.setBackground(new Color(211, 211, 211));
		panel3_1.add(panel3_1_2);
		panel3_1_2.setLayout(new BorderLayout(0, 0));
		panel3_1_2.setBackground(new Color(211, 211, 211));
		JComboBox comboBox_1 = new JComboBox();
		panel3_1_2.add(comboBox_1, BorderLayout.NORTH);
	}
	
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
}
