package projeto.homologacao.DAO;

import java.util.List;

import projeto.homologacao.entity.Compra;

public interface CompraDAO {
	
	void adicionar(Compra c);
	List<Compra> pesquisarPorID(long id);
	void remover(long id);
	long getMaiorID();
}
