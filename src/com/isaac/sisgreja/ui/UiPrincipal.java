package com.isaac.sisgreja.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.isaac.sisgreja.util.Constantes;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class UiPrincipal {

	private JFrame form;

	/**
	 * Create the application.
	 */
	public UiPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		form = new JFrame();
		form.setTitle(Constantes.NOME_SISTEMA);
		form.setBounds(100, 100, 643, 410);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblSisGreja = new JLabel("SIS GREJA");
		lblSisGreja.setHorizontalAlignment(SwingConstants.CENTER);
		lblSisGreja.setFont(new Font("Tahoma", Font.BOLD, 57));
		form.getContentPane().add(lblSisGreja, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		form.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem mntmFiel = new JMenuItem("Fiel");
		mntmFiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					new UiCadastroFiel(form).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCadastro.add(mntmFiel);

		JMenuItem mntmPastor = new JMenuItem("Pastor");
		mntmPastor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					new UiCadastroPastor(form).setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCadastro.add(mntmPastor);

		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnCadastro.add(mntmSair);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UiSobre(form).setVisible(true);
			}
		});
		mnAjuda.add(mntmSobre);
	}

	public void setVisible(boolean b) {
		form.setVisible(true);
	}
}
