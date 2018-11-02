package com.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import com.tool.ImgUtil;

import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class DetailedResults extends JDialog {

	private static final long serialVersionUID = 1L;
	public static JTextArea textArea;
	private Image blankIcon=ImgUtil.getImage("images/blank.png");
	
	public DetailedResults() {
		setBounds(1100, 400,600, 450);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setForeground(SystemColor.textHighlight);
		textArea.setSize(580, 300);
		textArea.setBackground(new Color(240, 240, 240));
		textArea.setFont(new Font(null, Font.BOLD, 15));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		getContentPane().add(textArea, BorderLayout.CENTER);
		JScrollPane scrollPane=new JScrollPane(textArea);
		getContentPane().add(scrollPane);
		
		setTitle("Detailed Results");
		setIconImage(blankIcon);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
	}
	
	public void setTextAreaContent(String results){
		textArea.setText(results);
	}

}
