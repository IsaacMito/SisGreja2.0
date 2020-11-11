package com.isaac.sisgreja.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

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

import com.isaac.sisgreja.domain.Pastor;
import com.isaac.sisgreja.persist.PastoresDao;
import com.isaac.sisgreja.ui.componentes.VerticalFlowLayout;
import com.isaac.sisgreja.util.Constantes;
import com.isaac.sisgreja.util.Funcoes;

public class UiCadastroPastor {

	private Pastor pastor;
	private PastoresDao pastoresDao;
	private List<Pastor> pastores;

	private boolean novo;

	private JDialog form;
	private JPanel pnlCentral;
	private JTextField textCpf;
	private JTextField textNome;
	private JTextField textData;
	private JButton btnPrimeiro;
	private JButton btnAnterio;
	private JButton btnUltimo;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnApagar;
	private JButton btnFechar;
	private JTextField textSalario;

	/**
	 * Create the dialog. tyeste
	 * 
	 * @throws SQLException
	 */
	public UiCadastroPastor(JFrame uiPai) throws SQLException {

		initialize(uiPai);
		habilitaNavegadores();
		habilitaControles(true);
		carregaFieis();

		btnPrimeiro.doClick();
	}

	private void carregaFieis() throws SQLException {

		if (pastoresDao == null) {
			pastoresDao = new PastoresDao();
		}
		pastores = pastoresDao.getListar();
	}

	private void habilitaNavegadores() {

		btnPrimeiro.setEnabled(true);
		btnAnterio.setEnabled(true);
		btnUltimo.setEnabled(true);

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
		textSalario.setText("");
	}

	private void habilitaText(boolean status) {

		textNome.setEditable(status);
		textData.setEditable(status);
		textSalario.setEditable(status);
	}

	private void prencheUi() {
		textCpf.setText(Integer.toString(pastor.getCpf()));
		textNome.setText(pastor.getNome());
		textData.setText(Funcoes.dateToString(pastor.getDataNacimento()));
		textSalario.setText(Double.toString(pastor.getSalario()));
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
				try {
					pastor = pastores.get(0);

					prencheUi();
				} catch (IndexOutOfBoundsException ignore) {
					// Faz nada
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(form, e2.getMessage());
				}
			}
		});
		pnlNavegador.add(btnPrimeiro);

		btnAnterio = new JButton("<");
		btnAnterio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = pastores.indexOf(pastor);
					pastor = pastores.get(pos - 1);

					prencheUi();
				} catch (IndexOutOfBoundsException ignore) {
					// Faz nada
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(form, e2.getMessage());
				}
			}
		});
		btnAnterio.setEnabled(false);
		pnlNavegador.add(btnAnterio);

		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int pos = pastores.size();
					pastor = pastores.get(pos - 1);

					prencheUi();
				} catch (IndexOutOfBoundsException ignore) {
					// Faz nada
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(form, e2.getMessage());
				}
			}
		});

		JButton btnSeguinte = new JButton(">");
		btnSeguinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int pos = pastores.indexOf(pastor);
					pastor = pastores.get(pos + 1);

					prencheUi();
				} catch (IndexOutOfBoundsException ignore) {
					// Faz nada
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(form, e2.getMessage());
				}
			}
		});
		pnlNavegador.add(btnSeguinte);
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

		JLabel lblTitulo = new JLabel("Cadastro de Pastor");
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
		textCpf.setEditable(false);
		textCpf.setBounds(12, 40, 114, 30);
		pnlCentral.add(textCpf);
		textCpf.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(12, 82, 55, 16);
		pnlCentral.add(lblNewLabel_1);

		textNome = new JTextField();
		textNome.setEditable(false);
		textNome.setBounds(12, 110, 459, 30);
		pnlCentral.add(textNome);
		textNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(12, 142, 55, 16);
		pnlCentral.add(lblNewLabel_2);

		textData = new JTextField();
		textData.setEditable(false);
		textData.setBounds(12, 171, 175, 30);
		pnlCentral.add(textData);
		textData.setColumns(10);

		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setBounds(12, 212, 46, 14);
		pnlCentral.add(lblSalario);

		textSalario = new JTextField();
		textSalario.setEditable(false);
		textSalario.setColumns(10);
		textSalario.setBounds(12, 237, 175, 30);
		pnlCentral.add(textSalario);

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
				try {

					pastor = new Pastor();

					pastor.setCpf(Integer.parseInt(textCpf.getText()));
					pastor.setNome(textNome.getText());
					pastor.setSalario(Double.parseDouble(textSalario.getText()));

					try {
						pastor.setDataNacimento(Funcoes.stringToDate(textData.getText()));
					} catch (ParseException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(form, "Deletado");
					pastoresDao.remove(pastor);
					try {
						carregaFieis();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						pastor = pastores.get(0);

						prencheUi();
					} catch (java.lang.IndexOutOfBoundsException e4) {
						limpaTela();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(form, e2.getMessage());
					}
				} catch (java.lang.NumberFormatException e2) {
					JOptionPane.showMessageDialog(form, "Nem uma pessoa para deletar");
				} catch (Exception e2) {
					e2.printStackTrace();
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

				try {
					pastor = pastores.get(0);

					prencheUi();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(form, e2.getMessage());
				}

				textCpf.setEditable(false);
				habilitaControles(true);
				habilitaText(false);
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (novo) {
					try {
						pastor = new Pastor();

						pastor.setCpf(Integer.parseInt(textCpf.getText()));
						pastor.setNome(textNome.getText());
						pastor.setSalario(Double.parseDouble(textSalario.getText()));
						
						try {
							pastor.setDataNacimento(Funcoes.stringToDate(textData.getText()));
						} catch (ParseException e1) {

							e1.printStackTrace();
						}

						try {
							pastoresDao.adiciona(pastor);
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

						habilitaControles(true);
						habilitaNavegadores();
						habilitaText(false);
						textCpf.setEditable(false);

						try {
							carregaFieis();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} catch (java.lang.NumberFormatException ew) {

						JOptionPane.showMessageDialog(form, "Credenciais vasias ou incorretas");
					}

				} else {

					pastor = new Pastor();

					pastor.setCpf(Integer.parseInt(textCpf.getText()));
					pastor.setNome(textNome.getText());
					pastor.setSalario(Double.parseDouble(textSalario.getText()));
					
					try {
						pastor.setDataNacimento(Funcoes.stringToDate(textData.getText()));
					} catch (ParseException e1) {

						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(form, "Alterado com sucesso");
					habilitaControles(true);
					pastoresDao.altera(pastor);
					try {
						carregaFieis();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				habilitaText(true);
				habilitaControles(false);
				novo = false;
			}
		});

		btnNovo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				textCpf.setEditable(true);
				habilitaText(true);
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