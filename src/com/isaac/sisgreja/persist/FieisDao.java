package com.isaac.sisgreja.persist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.isaac.sisgreja.domain.Fiel;
import com.isaac.sisgreja.jdbc.ConnectionFactory;
import com.isaac.sisgreja.ui.UiCadastroFiel;

public class FieisDao {

	private Connection con = null;

	public FieisDao() throws SQLException {
		con = new ConnectionFactory().getConnection();
	}
	
	public void FieisDaoConecta() throws SQLException {
		con = new ConnectionFactory().getConnection();
	}

	public void adiciona(Fiel fiel, JDialog form) throws SQLException {
		try {
			String sql = "insert into fiel " + "(cpf,nome,data_nascimento, dizimo)" + " values (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, fiel.getCpf());
			
			stmt.setString(2, fiel.getNome());
			
			stmt.setDate(3, new Date(fiel.getDataNacimento().getTime()));
			
			stmt.setDouble(4, fiel.getDisimo());

			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(form, "Cadastrado efetuado com sucesso");
			
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
		JOptionPane.showMessageDialog(form, "Conta ja exitente");
		} catch (Exception e) {
			
		}

		
	}

	public List<Fiel> getListar() throws SQLException {

		List<Fiel> fieis = new ArrayList<Fiel>();

		PreparedStatement stmt = con.prepareStatement("select * from fiel");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Fiel fiel = new Fiel();

			fiel.setCpf(rs.getInt("cpf"));
			fiel.setNome(rs.getString("nome"));
			fiel.setDataNacimento(rs.getDate("data_nascimento"));
			fiel.setDisimo(rs.getDouble("dizimo"));

			fieis.add(fiel);
		}
		rs.close();
		stmt.close();
		return fieis;

	}

	public void altera(Fiel fiel) {

		String sql = "update fiel set nome=?, data_nascimento=?," + "dizimo=? where cpf=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(4, fiel.getCpf());

			stmt.setString(1, fiel.getNome());
			stmt.setDate(2, new Date(fiel.getDataNacimento().getTime()));
			stmt.setDouble(3, fiel.getDisimo());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Fiel fiel) {

		try {
			PreparedStatement stmt = con.prepareStatement("delete " + "from fiel where cpf=?");
			stmt.setLong(1, fiel.getCpf());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
