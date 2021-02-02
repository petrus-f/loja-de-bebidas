package projeto.homologacao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.homologacao.ConnectionSingleton;
import projeto.homologacao.entity.ItemDeCompra;

public class ItemDeCompraDAOImpl implements ItemDeCompraDAO{

	public ItemDeCompraDAOImpl () {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void adicionar(ItemDeCompra i) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO item_de_compra (id_compra, id_item, quantidade) VALUES (?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, i.getCompra().getId());
			st.setLong(2, i.getItem().getId());
			st.setInt(3, i.getQuantidade());
			st.executeUpdate();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ItemDeCompra> pesquisarPorID(long id_compra, long id_item) {
		List<ItemDeCompra> lista = new ArrayList<ItemDeCompra>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql1 = "SELECT * FROM item_de_compra WHERE id_compra = ? and id_item = ?";
			PreparedStatement st1 = con.prepareStatement(sql1);
			st1.setLong(1, id_compra);
			st1.setLong(2, id_item);
			ResultSet rs1 = st1.executeQuery();
			while(rs1.next()) {
				String sql2 = "SELECT * FROM compra WHERE id = ?";
				PreparedStatement st2 = con.prepareStatement(sql2);
				st2.setLong(1, rs1.getLong("id_compra"));
				ResultSet rs2 = st2.executeQuery();
//				Compra compra = new Compra();		
//				ItemDeCompra i = new ItemDeCompra(rs.getLong(), rs.getString("nome"), rs.getDouble("valor_unit"), rs.getBoolean("plastico"));
//				lista.add(i);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void remover(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(ItemDeCompra i) {
		// TODO Auto-generated method stub
		
	}

}
