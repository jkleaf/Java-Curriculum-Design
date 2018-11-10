package org.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.tools.ImgUtil;

public class AboutDialog extends JDialog{
	private static final long serialVersionUID = -2654789551983416697L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea=new JTextArea();
	private Image blankImg=ImgUtil.getImage("blank.png");
	
	public AboutDialog() {
		setTitle("关于...");
		setIconImage(blankImg);
		setBounds(920, 200, 150, 200);
		setBackground(new Color(211, 211, 211));
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("幼圆", Font.BOLD, 18));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setEditable(false);
		String s="该人机五子棋算法较为简陋,若出现卡顿,请耐心等待递归(-_-!)";
		textArea.append(s);
		contentPanel.add(textArea);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SettingDialog.clickAboutButtonFirst=false;
			}
		});
	}
}
