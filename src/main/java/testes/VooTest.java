package testes;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import persistencia.ReservaPassagensDao;
import dominio.Voo;
import dominio.Passageiro;
import dominio.Passagem;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class VooTest {

    private static Voo voo;
    private static ReservaPassagensDao mockReservaDao;
    private final static int CAPACIDADE_VOO = 10;

    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        mockReservaDao = mock(ReservaPassagensDao.class);
        voo = new Voo(1234, "Aracaju", "Fortaleza", Calendar.getInstance().getTime(), CAPACIDADE_VOO,
        		(ReservaPassagensDao) mockReservaDao);
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
    
	/**
 	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
 	 * retorna null - para facilitar em casos onde formulários podem ter campos
 	 * de datas vazios.
 	 * @param data String no formato dd/MM/yyyy a ser formatada
 	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
 	 * @throws Exception Caso a String esteja no formato errado
 	 */
 	private java.sql.Date formataData(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd H:m:s");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
   
    //passageiros já tem passagem, voo com capacidade excedida e passageiro null
    @Test
    public void testePassageiroTemPassagem()
    {
    	Passageiro passageiro2 = new Passageiro("123321", "Cicrano");
    	List<Passagem> listaPassagem = new ArrayList<Passagem>();
        when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
    	when(mockReservaDao.getPassagem(passageiro2, voo)).thenReturn(null);
    	assertTrue(voo.passagemExistente(passageiro2));
    }

    @Test
    public void vooComCapacidadeExcedida() throws Exception
    {
    	Date data = formataData("2018-01-21 15:00:00");  	
    	Voo voo = new Voo(100, "Aracaju", "Guarulhos",data, 45, mockReservaDao);
    	
    	List<Passagem> listaPassagem = new ArrayList<Passagem>();
    	when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
    	assertTrue(voo.possuiAssentoDisponivel());
    }
    
    @Test
    public void passageiroNulo() throws Exception
    {
    	Passageiro passageiro3 = null;
    	Date data = formataData("2018-01-21 15:00:00"); 
    	Voo voo = new Voo(101, "Guarulhos", "Aracaju",data, 20, mockReservaDao);
    	//List<Passagem> listaPassagem = new ArrayList<Passagem>();
    	//when(mockReservaDao.getPassagensPorVoo(voo)).thenReturn(listaPassagem);
    	assertTrue(voo.passagemExistente(passageiro3));
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
