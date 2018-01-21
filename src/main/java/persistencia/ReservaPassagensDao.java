package persistencia;

import java.util.*;
import dominio.*;


public interface ReservaPassagensDao {

        public boolean salvarVoo(Voo voo);
	    public void removerVoo(Voo voo);
	    public boolean salvarPassagem(Passagem passagem);
	    public boolean removerPassagem(Passagem passagem);
	    public List<Passagem> getPassagensPorVoo(Voo voo);
	    public Passagem getPassagem(Passageiro passageiro, Voo voo);
	
}