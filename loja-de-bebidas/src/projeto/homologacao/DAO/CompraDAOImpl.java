package projeto.homologacao.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import projeto.homologacao.ConnectionSingleton;
import projeto.homologacao.entity.Compra;
import projeto.homologacao.entity.ItemDeCompra;

public class CompraDAOImpl implements CompraDAO{
	
	public CompraDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adicionar(Compra c) {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO compra (id_compra, id_cliente) VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, c.getId());
			st.setLong(2, c.getCliente().getId());
			st.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		ItemDeCompraDAO daoItemDeCompra = new ItemDeCompraDAOImpl();
		for(ItemDeCompra ic: c.getItens()) {
			daoItemDeCompra.adicionar(ic);
		}
		
	}

	@Override
	public List<Compra> pesquisarPorID(long id) {
		return null;
	}

	@Override
	public void remover(long id) {
		
	}
	
	@Override
	public long getMaiorID() {
		long id = -1;
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT MAX(id_compra) as maximo from compra";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				id = rs.getLong("maximo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return id;
	}
	
	
}
