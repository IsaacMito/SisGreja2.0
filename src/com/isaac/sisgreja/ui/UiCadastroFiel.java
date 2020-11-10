package com.isaac.sisgreja.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.isaac.sisgreja.domain.Fiel;
import com.isaac.sisgreja.persist.BancoDados;
import com.isaac.sisgreja.ui.componentes.VerticalFlowLayout;
import com.isaac.sisgreja.util.Constantes;
import com.isaac.sisgreja.util.Funcoes;

public class UiCadastroFiel {

	private Fiel fiel;

	private boolean novo;

	private JDialog form;
	private JPanel pnlCentral;
	private JTextField textCpf;
	private JTextField textNome;
	private JTextField textData;
	private JButton btnPrimeiro;
	private JButton btnAnterio;
	private JButton btnSeguinte;
	private JButton btnUltimo;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnApagar;
	private JButton btnFechar;
	private JTextField textDizimo;

	/**
	 * Create the dialog. tyeste
	 */
	public UiCadastroFiel(JFrame uiPai) {

		initialize(uiPai);
		habilitaNavegadores();
		habilitaControles(true);

		if (BancoDados.fieis.isEmpty()) {

		} else {

			textCpf.setText(Integer.toString(BancoDados.fieis.get(0).getCpf()));
			textNome.setText(BancoDados.fieis.get(0).getNome());
			textData.setText(Funcoes.dateToString(BancoDados.fieis.get(0).getDataNacimento()));
			textDizimo.setText(BancoDados.fieis.get(0).getDisimo() + "");
		}

		btnPrimeiro.doClick();
	}

	private void habilitaNavegadores() {

		if (BancoDados.fieis.isEmpty()) {
			btnPrimeiro.setEnabled(false);
			btnAnterio.setEnabled(false);
			btnSeguinte.setEnabled(false);
			btnUltimo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnAnterio.setEnabled(true);
			btnSeguinte.setEnabled(true);
			btnUltimo.setEnabled(true);
		}
	}

	private void habilitaControles(boolean status) {

		btnAlterar.setEnabled(status);
		btnApagar.setEnabled(status);
		btnNovo.setEnabled(status);
		btnFechar.setEnabled(status);

		btnCancelar.setEnabled(!status);
		btnSalvar.setEnabled(!status);
	}

	private void limpaTela() {
		textCpf.setText("");
		textNome.setText("");
		textData.setText("");
		textDizimo.setText("");
	}

	private void initialize(JFrame uiPai) {

		form = new JDialog(uiPai);
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.setTitle(Constantes.NOME_SISTEMA);
		form.setBounds(100, 100, 584, 436);
		form.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlNavegador = new JPanel();
		pnlNavegador.setBorder(new LineBorder(new Color(0, 0, 0)));
		form.getContentPane().add(pnlNavegador, BorderLayout.SOUTH);

		btnPrimeiro = new JButton("<<");
		btnPrimeiro.setEnabled(false);
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (BancoDados.fieis.isEmpty()) {

				} else {

					textCpf.setText(Integer.toString(BancoDados.fieis.get(0).getCpf()));
					textNome.setText(BancoDados.fieis.get(0).getNome());
					textData.setText(Funcoes.dateToString(BancoDados.fieis.get(0).getDataNacimento()));
					textDizimo.setText(Double.toString(BancoDados.fieis.get(0).getDisimo()));
				}
			}
		});
		pnlNavegador.add(btnPrimeiro);

		btnAnterio = new JButton("<");
		btnAnterio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					for (int i = 0; i < BancoDados.fieis.size(); i++) {

						if (BancoDados.fieis.get(i).getCpf() == Integer.parseInt(textCpf.getText())) {

							textCpf.setText(Integer.toString(BancoDados.fieis.get(i - 1).getCpf()));
							textNome.setText(BancoDados.fieis.get(i - 1).getNome());
							textData.setText(Funcoes.dateToString(BancoDados.fieis.get(i - 1).getDataNacimento()));
							textDizimo.setText(Double.toString(BancoDados.fieis.get(i - 1).getDisimo()));

							return;
						}
					}
				} catch (java.lang.IndexOutOfBoundsException w) {

				}

			}
		});
		btnAnterio.setEnabled(false);
		pnlNavegador.add(btnAnterio);

		btnSeguinte = new JButton(">");
		btnSeguinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					for (int i = 0; i < BancoDados.fieis.size(); i++) {

						if (BancoDados.fieis.get(i).getCpf() == Integer.parseInt(textCpf.getText())) {

							textCpf.setText(Integer.toString(BancoDados.fieis.get(i + 1).getCpf()));
							textNome.setText(BancoDados.fieis.get(i + 1).getNome());
							textData.setText(Funcoes.dateToString(BancoDados.fieis.get(i + 1).getDataNacimento()));
							textDizimo.setText(Double.toString(BancoDados.fieis.get(i + 1).getDisimo()));

							return;
						}
					}
				} catch (java.lang.IndexOutOfBoundsException w) {

				}

			}
		});
		btnSeguinte.setEnabled(false);
		pnlNavegador.add(btnSeguinte);

		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (BancoDados.fieis.isEmpty()) {

				} else {

					textCpf.setText(Integer.toString(BancoDados.fieis.get(BancoDados.fieis.size() - 1).getCpf()));
					textNome.setText(BancoDados.fieis.get(BancoDados.fieis.size() - 1).getNome());
					textData.setText(
							Funcoes.dateToString(BancoDados.fieis.get(BancoDados.fieis.size() - 1).getDataNacimento()));
					textDizimo.setText(Double.toString(BancoDados.fieis.get(BancoDados.fieis.size() - 1).getDisimo()));
				}

			}
		});
		btnUltimo.setEnabled(false);
		pnlNavegador.add(btnUltimo);

		JPanel pnlNavegadores = new JPanel();
		form.getContentPane().add(pnlNavegadores, BorderLayout.EAST);
		pnlNavegadores.setLayout(new BoxLayout(pnlNavegadores, BoxLayout.Y_AXIS));

		JSeparator separator_4 = new JSeparator();
		separator_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pnlNavegadores.add(separator_4);

		JSeparator separator_1 = new JSeparator();
		pnlNavegadores.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		pnlNavegadores.add(separator_2);

		JSeparator separator = new JSeparator();
		pnlNavegadores.add(separator);

		JSeparator separator_3 = new JSeparator();
		pnlNavegadores.add(separator_3);

		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		form.getContentPane().add(pnlTitulo, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Cadastro de Fiel");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlTitulo.add(lblTitulo);

		pnlCentral = new JPanel();
		pnlCentral.setBorder(new LineBorder(new Color(0, 0, 0)));
		form.getContentPane().add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(null);

		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 12, 55, 16);
		pnlCentral.add(lblNewLabel);

		textCpf = new JTextField();
		textCpf.setBounds(12, 40, 114, 30);
		pnlCentral.add(textCpf);
		textCpf.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(12, 82, 55, 16);
		pnlCentral.add(lblNewLabel_1);

		textNome = new JTextField();
		textNome.setBounds(12, 110, 459, 30);
		pnlCentral.add(textNome);
		textNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(12, 142, 55, 16);
		pnlCentral.add(lblNewLabel_2);

		textData = new JTextField();
		textData.setBounds(12, 171, 175, 30);
		pnlCentral.add(textData);
		textData.setColumns(10);

		JLabel lblDisimo = new JLabel("D\u00EDzimo");
		lblDisimo.setBounds(12, 212, 46, 14);
		pnlCentral.add(lblDisimo);

		textDizimo = new JTextField();
		textDizimo.setColumns(10);
		textDizimo.setBounds(12, 237, 175, 30);
		pnlCentral.add(textDizimo);

		JPanel pnlControles = new JPanel();
		pnlControles.setBorder(new LineBorder(new Color(0, 0, 0)));
		form.getContentPane().add(pnlControles, BorderLayout.EAST);
		pnlControles.setLayout(new VerticalFlowLayout(VerticalFlowLayout.CENTER, VerticalFlowLayout.TOP, 5, 5));

		JLabel lblEspaco1 = new JLabel(" ");
		pnlControles.add(lblEspaco1);

		btnNovo = new JButton("Novo");
		btnNovo.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnNovo);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnAlterar);

		JLabel lblEspaco2 = new JLabel(" ");
		pnlControles.add(lblEspaco2);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnCancelar);

		JLabel lblEspaco3 = new JLabel(" ");
		pnlControles.add(lblEspaco3);

		btnApagar = new JButton("Apagar");
		btnApagar.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnApagar);
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < BancoDados.fieis.size(); i++) {

					if (BancoDados.fieis.get(i).getCpf() == Integer.parseInt(textCpf.getText())) {

						BancoDados.fieis.remove(BancoDados.fieis.get(i));

						JOptionPane.showMessageDialog(form, "deletado com sucesso!");
						
						textCpf.setText(Integer.toString(BancoDados.fieis.get(i).getCpf()));
						textNome.setText(BancoDados.fieis.get(i).getNome());
						textData.setText(Funcoes.dateToString(BancoDados.fieis.get(i).getDataNacimento()));
						textDizimo.setText(Double.toString(BancoDados.fieis.get(i).getDisimo()));
						
						return;
					}
				}
			}
		});

		JLabel lblEspaco4 = new JLabel(" ");
		pnlControles.add(lblEspaco4);

		btnFechar = new JButton("Fechar");
		btnFechar.setPreferredSize(new Dimension(75, 23));
		pnlControles.add(btnFechar);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form.setVisible(false);
			}
		});
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				habilitaControles(true);
				limpaTela();
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (novo) {

					for (int i = 0; i < BancoDados.fieis.size(); i++) {

						if (BancoDados.fieis.get(i).getCpf() == Integer.parseInt(textCpf.getText())) {

							JOptionPane.showMessageDialog(form, "Conta já registrada");
							return;
						}
					}
					fiel = new Fiel();

					fiel.setCpf(Integer.parseInt(textCpf.getText()));
					fiel.setNome(textNome.getText());
					fiel.setDisimo(Double.parseDouble(textDizimo.getText()));

					BancoDados.fieis.add(fiel);

					JOptionPane.showMessageDialog(form, "Cadastrado com sucesso");
					habilitaControles(true);
					habilitaNavegadores();

					try {
						fiel.setDataNacimento(Funcoes.stringToDate(textData.getText()));
					} catch (ParseException ignore) {
					}

				} else {

					for (int i = 0; i < BancoDados.fieis.size(); i++) {

						if (BancoDados.fieis.get(i).getCpf() == Integer.parseInt(textCpf.getText())) {

							BancoDados.fieis.get(i).setNome(textNome.getText());
							try {
								BancoDados.fieis.get(i).setDataNacimento(Funcoes.stringToDate(textData.getText()));
							} catch (ParseException ignore) {
							}
							BancoDados.fieis.get(i).setDisimo(Double.parseDouble(textDizimo.getText()));

							JOptionPane.showMessageDialog(form, "Salvo com sucesso");
							habilitaControles(true);
							habilitaNavegadores();
						}
					}
				}
			}

		});
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				habilitaControles(false);
				novo = false;
			}
		});

		btnNovo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				habilitaControles(false);
				limpaTela();
				novo = true;
			}
		});

	}

	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}
