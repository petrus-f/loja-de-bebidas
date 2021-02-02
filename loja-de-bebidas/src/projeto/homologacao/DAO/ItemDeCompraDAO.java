package projeto.homologacao.DAO;

import java.util.List;

import projeto.homologacao.entity.ItemDeCompra;

public interface ItemDeCompraDAO {
	
	void adicionar(ItemDeCompra i);
	List<ItemDeCompra> pesquisarPorID(long id_compra, long id_item);
	void remover(long id);
	void alterar(ItemDeCompra i);
	
}
