package projeto.homologacao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.homologacao.ConnectionSingleton;
import projeto.homologacao.entity.Item;

public class ItemDAOImpl implements ItemDAO{

	public ItemDAOImpl () {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void adicionar(Item i) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO item (id, nome, valor_unit, plastico) " + " VALUES (null, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, i.getNome());
			st.setDouble(2, i.getValor_unitario());
			st.setBoolean(3, i.isPlastico());
			st.executeUpdate();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Item> pesquisarPorNome(String nome) {
		List<Item> lista = new ArrayList<Item>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM item WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,  "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Item i = new Item(rs.getLong("id"), rs.getString("nome"), rs.getDouble("valor_unit"), rs.getBoolean("plastico"));
				lista.add(i);
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
			String sql = "DELETE FROM item WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void alterar(Item i) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE item SET nome = ?, valor_unit = ?, plastico = ? WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, i.getNome());
			st.setDouble(2, i.getValor_unitario());
			st.setBoolean(3, i.isPlastico());
			st.setLong(4, i.getId());
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
