package projeto.homologacao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.homologacao.ConnectionSingleton;
import projeto.homologacao.entity.Cliente;

public class ClienteDAOImpl implements ClienteDAO{
	
	public ClienteDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void adicionar(Cliente c) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO cliente (id, nome, endereco) " + " VALUES (null, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getNome());
			st.setString(2, c.getEndereco());
			st.executeUpdate();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> pesquisarPorNome(String nome) {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM cliente WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,  "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente(rs.getLong("id"), rs.getString("nome"), rs.getString("endereco"));
				lista.add(c);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void remover(long id) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "DELETE FROM cliente WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Cliente c) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE cliente SET nome = ?, endereco = ? WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, c.getNome());
			st.setString(2, c.getEndereco());
			st.setLong(3, c.getId());
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
