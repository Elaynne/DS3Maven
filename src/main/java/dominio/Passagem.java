package dominio;

public class Passagem {

	   private String numeroBilhete;

	   private Voo voo;

	   private Passageiro passageiro;

	   public Passagem(String numeroBilhete, Voo voo, Passageiro passageiro) {
	       this.setVoo(voo);
	       this.setPassageiro(passageiro);
	       this.setNumeroBilhete(numeroBilhete);
	   }

	public void setNumeroBilhete(String numeroBilhete) {
		this.numeroBilhete = numeroBilhete;
	}

	public String getNumeroBilhete() {
		return numeroBilhete;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}
}
