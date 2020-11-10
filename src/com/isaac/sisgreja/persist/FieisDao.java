package com.isaac.sisgreja.persist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.isaac.sisgreja.domain.Fiel;
import com.isaac.sisgreja.jdbc.ConnectionFactory;

public class FieisDao {

	private Connection con;

	public FieisDao() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}

	public void adiciona(Fiel fiel) throws SQLException {

		String sql = "insert into fieis " + "(cpf,nome,dataNascimento, dizimo)" + " values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);

		// prepared statement para inserção

		// seta os valores
		stmt.setInt(1, fiel.getCpf());
		stmt.setString(2, fiel.getNome());
		stmt.setDouble(3, fiel.getDisimo());

		stmt.setDate(4, (Date) fiel.getDataNacimento());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Fiel> getListar() throws SQLException {

		List<Fiel> fieis = new ArrayList<Fiel>();

		PreparedStatement stmt = this.con.prepareStatement("select * from fiel");
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

		String sql = "update contatos set nome=?, dataNascimento=?," + "dizimo=? where cpf=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(4, fiel.getCpf());

			stmt.setString(1, fiel.getNome());
			stmt.setDate(2, (Date) fiel.getDataNacimento());
			stmt.setDouble(3, fiel.getDisimo());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Fiel fiel) {

		try {
			PreparedStatement stmt = con.prepareStatement("delete " + "from contatos where cpf=?");
			stmt.setLong(1, fiel.getCpf());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
