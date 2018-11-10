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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.game.GameFrame;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SettingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AboutDialog aDialog=new AboutDialog();
	private boolean checkboxSelected=true;
	private String [] ITEMS=new String[]{"±³¾°Í¼1","±³¾°Í¼2","±³¾°Í¼3","±³¾°Í¼4","±³¾°Í¼5","´¿É«"};
	public static int bgSelectIndex;
	public static boolean clickAboutButtonFirst;
	
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
		setBounds(790, 400, 350, 200);
		setTitle("ÉèÖÃ");
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("\u73A9\u5BB6\u5148\u624B");
		chckbxNewCheckBox.setFont(new Font("Ó×Ô²", Font.BOLD, 18));
		chckbxNewCheckBox.setSelected(checkboxSelected);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()){
					GameFrame.playerFirst=true;
				}else{
					GameFrame.playerFirst=false;
				}
			}
		});
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Ó×Ô²", Font.BOLD, 18));
		comboBox.setBackground(new Color(211, 211, 211));
		for(int i=0;i<ITEMS.length;i++){
			comboBox.addItem(ITEMS[i]);
		}
		comboBox.addItemListener(new ItemListener() {			
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					bgSelectIndex=comboBox.getSelectedIndex();
				}
			}
		});
		
		JButton btnNewButton = new JButton("\u5173\u4E8E");
		btnNewButton.setFont(new Font("Ó×Ô²", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(!clickAboutButtonFirst){
					aDialog.setVisible(true);
					clickAboutButtonFirst=true;
				}	
				else {
					aDialog.setVisible(false);
					clickAboutButtonFirst=false;
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("\u5207\u6362\u80CC\u666F\u56FE\u7247");
		lblNewLabel.setFont(new Font("Ó×Ô²", Font.BOLD, 18));
		lblNewLabel.setBackground(Color.WHITE);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(45)
					.addComponent(btnNewButton)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(chckbxNewCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("±£´æ");
				okButton.setActionCommand("±£´æ");
				buttonPane.add(okButton);
				okButton.setBackground(new Color(211, 211, 211));
				okButton.setPreferredSize(new Dimension(60, 30));
				okButton.setFont(new Font("»ªÎÄÐÂÎº", Font.PLAIN, 13));
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {					
					public void actionPerformed(ActionEvent e) {
//						radioSelected=false;
						checkboxSelected=false;
//						dispose();
						ContentPanel.button1.setEnabled(true);
						ContentPanel.button2.setEnabled(true);
						GameFrame.getFrame().selectBackGround();
						setVisible(false);
					}
				});
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
}
