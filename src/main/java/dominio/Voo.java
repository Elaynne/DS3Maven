package dominio;

import java.util.*;
import persistencia.ReservaPassagensDao;

public class Voo {
	   private final long idVoo;

	   private final String origem;

	   private final String destino;

	   private final Date data;

	   private final int capacidade;
	   private ReservaPassagensDao reservasDao;

	   public Voo(long idVoo, String origem, String destino,
	     Date data, int capacidade,
	     ReservaPassagensDao reservasDao){
	     this.idVoo = idVoo;
	     this.origem = origem;
	     this.destino = destino;
	     this.data = data;
	     this.capacidade = capacidade;
	     this.reservasDao = reservasDao;
	   }

	   public boolean fazerReserva(Passageiro passageiro) {
		   	if (passageiro==null) {
	    		throw new IllegalArgumentException();
	    	}

	    	if (possuiAssentoDisponivel() && !passagemExistente(passageiro)) {
	    		Passagem passagem = new Passagem(gerarNumeroBilhete(),this,passageiro);
	    		return reservasDao.salvarPassagem(passagem);
	    	}
	    	return  false;
	   }

	   public boolean possuiAssentoDisponivel() {
		   	List<Passagem> listaPassagens = reservasDao.getPassagensPorVoo(this);
		   	return listaPassagens.size() < getCapacidade();
	   }
	   
	   public boolean passagemExistente(Passageiro passageiro) {
		   	Passagem passagem = reservasDao.getPassagem(passageiro, this);
		   	return passagem != null;
	   }
	
	   private String gerarNumeroBilhete() {
		   long numero = System.currentTimeMillis();
		   return Long.toString(numero);
	   }

	   public long getIdVoo() {
		return idVoo;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public Date getData() {
		return data;
	}

	public int getCapacidade() {
		return capacidade;
	}

}