package com.isaac.sisgreja.persist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.isaac.sisgreja.domain.Pastor;
import com.isaac.sisgreja.jdbc.ConnectionFactory;

public class PastoresDao {

	private Connection con;

	public PastoresDao() throws SQLException {
		con = new ConnectionFactory().getConnection();
	}

	public void adiciona(Pastor pastor) throws SQLException {

		String sql = "insert into pastor " + "(cpf,nome,data_nascimento, salario)" + " values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);

		// prepared statement para inserção

		// seta os valores
		stmt.setInt(1, pastor.getCpf());
		
		stmt.setString(2, pastor.getNome());
		
		stmt.setDate(3,  new Date(pastor.getDataNacimento().getTime()));

		stmt.setDouble(4, pastor.getSalario());
		// executa
		stmt.execute();
		stmt.close();
	}

	public List<Pastor> getListar() throws SQLException {

		List<Pastor> pastores = new ArrayList<Pastor>();

		PreparedStatement stmt = con.prepareStatement("select * from pastor");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			 Pastor  pastor = new Pastor();

			 pastor.setCpf(rs.getInt("cpf"));
			 pastor.setNome(rs.getString("nome"));
			 pastor.setDataNacimento(rs.getDate("data_nascimento"));
			 pastor.setSalario(rs.getDouble("salario"));

			pastores.add(pastor);
		}
		rs.close();
		stmt.close();
		return pastores;

	}

	public void altera(Pastor pastor) {

		String sql = "update pastor set nome=?, data_nascimento=?," + "salario=? where cpf=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(4, pastor.getCpf());

			stmt.setString(1, pastor.getNome());
			stmt.setDate(2, new Date(pastor.getDataNacimento().getTime()));
			stmt.setDouble(3, pastor.getSalario());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Pastor pastor) {

		try {
			PreparedStatement stmt = con.prepareStatement("delete " + "from pastor where cpf=?");
			stmt.setLong(1, pastor.getCpf());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
