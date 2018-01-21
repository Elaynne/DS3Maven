package testes;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;

import persistencia.ReservaPassagensDao;
import dominio.Voo;
import dominio.Passageiro;
import dominio.Passagem;

public class VooTest {

    private static Voo voo;
    private static ReservaPassagensDao mockReservaDao;
    private static ReservaPassagensDao mockRemoverReservaDao;
    private final static int CAPACIDADE_VOO = 10;

    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        mockReservaDao = mock(ReservaPassagensDao.class);
        voo = new Voo(1234, "Aracaju", "Fortaleza", Calendar.getInstance().getTime(), CAPACIDADE_VOO,
        		(ReservaPassagensDao) mockReservaDao);
        mockRemoverReservaDao = mock(ReservaPassagensDao.class);
    }

    
    @After
    public void tearDown() throws Exception {
         reset(mockReservaDao);
    }

    @Test
    public void testFazerReservaComSucesso() {
       Passageiro passageiro = new Passageiro("123123", "Fulano");
       List<Passagem> listaPassagem = new ArrayList<Passagem>();
         when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
         when(mockReservaDao.getPassagem(passageiro, voo)).thenReturn(null);
         when(mockReservaDao.salvarPassagem((Passagem) notNull())).thenReturn(true);
        assertTrue(voo.fazerReserva(passageiro));
    }
   
    //passagerios já tem passagem
    //voo com capacidade excedida
    //passageiro null
    @Test
    public void testePassageiroTemPassagem()
    {
    	Passageiro passageiro2 = new Passageiro("123321", "Cicrano");
    	List<Passagem> listaPassagem = new ArrayList<Passagem>();

        when(mockRemoverReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
    	when(mockRemoverReservaDao.getPassagem(passageiro2, voo)).thenReturn(null);
    	assertTrue(voo.passagemExistente(passageiro2));
    }

    @Test
    public void vooComCapacidadeExcedida()
    {
    	//Voo v = new Voo("123321", "Cicrano");
    	List<Passagem> listaPassagem = new ArrayList<Passagem>();
    }
    
    @Test
    public void passageiroNulo()
    {
    	
    }
    
   /* @Test
    public void testeRemoverReservaComSucesso() {
       Passageiro passageiro2 = new Passageiro("123321", "Beltrano");
       List<Passagem> listaPassagem = new ArrayList<Passagem>();

         when(mockRemoverReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
         when(mockRemoverReservaDao.getPassagem(passageiro2, voo)).thenReturn(null);
         when(mockRemoverReservaDao.removerPassagem((Passagem) notNull())).thenReturn(true);  
        assertTrue(voo.removerReserva(passageiro2));
    }*/
   
}
