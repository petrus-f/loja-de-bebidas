package projeto.homologacao.entity;

public class Item {
	private long id;
	private String nome;
	private double valor_unitario;
	private boolean plastico;
	private String plasticoString;
	
	public Item(long id,String nome, double valor_unitario, boolean plastico) {
		this.id = id;
		this.nome = nome;
		this.valor_unitario = valor_unitario;
		this.plastico = plastico;
		if(plastico) {
			plasticoString = "Sim";
		}else {
			plasticoString = "Não";
		}
	}
	public String getPlasticoString() {
		return plasticoString;
	}
	public void setPlasticoString(String plasticoString) {
		this.plasticoString = plasticoString;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public boolean isPlastico() {
		return plastico;
	}
	public void setPlastico(boolean plastico) {
		this.plastico = plastico;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
