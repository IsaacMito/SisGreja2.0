package com.isaac.sisgreja.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.isaac.sisgreja.util.Constantes;

public class UiSobre  {

	private JDialog form;
	
	/**
	 * Create the dialog.
	 */
	public UiSobre(JFrame uiPai) {
		initialize(uiPai);
	}

	private void initialize(JFrame uiPai) {
		
		form = new JDialog(uiPai);
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.setTitle(Constantes.NOME_SISTEMA + " | Sobre");
		form.setBounds(100, 100, 450, 300);
		form.getContentPane().setLayout(null);
		
		JTextArea txtrFeitoPorIsaac = new JTextArea();
		txtrFeitoPorIsaac.setEditable(false);
		txtrFeitoPorIsaac.setText("Feito por: Isaac\r\n\r\nVer\u00E7\u00E3o 1.0");
		txtrFeitoPorIsaac.setBounds(0, 0, 434, 261);
		form.getContentPane().add(txtrFeitoPorIsaac);
	}

	public void setVisible(boolean b) {
		form.setVisible(b);
	}

}
