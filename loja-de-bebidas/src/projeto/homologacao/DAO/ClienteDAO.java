package projeto.homologacao.DAO;

import java.util.List;

import projeto.homologacao.entity.Cliente;

public interface ClienteDAO {
	
	void adicionar(Cliente c);
	List<Cliente> pesquisarPorNome(String nome);
	void remover(long id);
	void alterar(Cliente c);
}
