package projeto.homologacao.DAO;

import java.util.List;

import projeto.homologacao.entity.Item;

public interface ItemDAO {

	void adicionar(Item i);
	List<Item> pesquisarPorNome(String nome);
	void remover(long id);
	void alterar(Item i);
}
