
package dominio;

public class Passageiro {
	private String rg;

	private String nome;

	public Passageiro(String rg, String nome) {
	    this.setRg(rg);
	    this.setNome(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}
}