package org.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;

public class SettingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnNewRadioButton;
	private boolean radioSelected=true,checkboxSelected=true;

	public SettingDialog() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setShape(new RoundRectangle2D.Double(
						0.0D, 0.0D, getWidth(), getHeight(), 50.0D,
						50.0D));
			}
		});
		setUndecorated(true);
		setOpacity(0.9f);
		setBounds(830, 400, 350, 200);
		setTitle("设置");
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			rdbtnNewRadioButton = new JRadioButton("\u73A9\u5BB6\u4E3A\u9ED1\u5B50,\u7535\u8111\u4E3A\u767D\u5B50");
			rdbtnNewRadioButton.setFont(new Font("幼圆", Font.BOLD, 18));
			rdbtnNewRadioButton.setSelected(radioSelected);
		}
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u73A9\u5BB6\u5148\u624B");
		chckbxNewCheckBox.setFont(new Font("幼圆", Font.BOLD, 18));
		chckbxNewCheckBox.setSelected(checkboxSelected);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(chckbxNewCheckBox)
							.addGap(115))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(rdbtnNewRadioButton)
							.addGap(54))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(43, Short.MAX_VALUE)
					.addComponent(rdbtnNewRadioButton)
					.addGap(18)
					.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("保存");
				okButton.setActionCommand("保存");
				buttonPane.add(okButton);
				okButton.setBackground(new Color(211, 211, 211));
				okButton.setPreferredSize(new Dimension(60, 30));
				okButton.setFont(new Font("华文新魏", Font.PLAIN, 13));
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {					
					public void actionPerformed(ActionEvent e) {
//						radioSelected=false;
						checkboxSelected=false;
//						dispose();
						setVisible(false);
					}
				});
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
