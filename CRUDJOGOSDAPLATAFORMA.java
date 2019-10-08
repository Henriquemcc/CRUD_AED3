import java.text.DecimalFormat;
import java.util.Scanner;

public class CRUDJOGOSDAPLATAFORMA
{
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<JogosDaPlataforma> arqJogosDaPlataforma;
    private static DecimalFormat df;
    
    //LISTA TODOS OS JOGOS DE UMA PLATAFORMA
    public static void listarJogosdeumaPlataforma(int idPlataforma) throws Exception
    {
        Object[] JogosDaPlataforma = arqJogosDaPlataforma.listar();
        JogosDaPlataforma jdp;
        for(int i=0; i<JogosDaPlataforma.length; i++)
        {
            jdp = (JogosDaPlataforma)JogosDaPlataforma[i];
            if(jdp.getIdPlataforma()==idPlataforma){
                CRUDJOGOS.buscarJogo(jdp.getIdJogo());
            }
        }
        
    }
    //LISTA TODAS AS PLATAFORMAS DE UM JOGO
    public static void listarPlataformasdoJogo(int idJogo) throws Exception
    {
        Object[] JogosDaPlataforma = arqJogosDaPlataforma.listar();
        JogosDaPlataforma jdp;
        for(int i=0; i<JogosDaPlataforma.length; i++)
        {
            jdp = (JogosDaPlataforma)JogosDaPlataforma[i];
            if(jdp.getIdPlataforma()==idJogo){
                CRUDPLATAFORMA.buscar(jdp.getIdJogo());
            }
        }
        
    }
   /*
    public static void buscarPlataforma() throws Exception
    {
        System.out.println("\nBUSCA");
        int id;
        System.out.print("ID: ");
        id = Integer.valueOf(console.nextLine());
        if(id <=0) 
            return;
        Plataforma l;
        if( (l = (Plataforma)arqPlataformas.buscar(id))!=null )
            System.out.println(l);
        else
            System.out.println("Plataforma não encontrada");
    }*/
    
    public static void incluirJogodaPlataforma(int idJogo, int idPlataforma) throws Exception
    {
        JogosDaPlataforma l = new JogosDaPlataforma(-1,idPlataforma,idJogo);
        arqJogosDaPlataforma.incluir(l);
    }
   /*
    public static void excluirPlataforma() throws Exception
    {
        System.out.println("\nEXCLUSÃO");
        int id;
        System.out.print("ID: ");
        id = Integer.valueOf(console.nextLine());
        if(id <=0) 
            return;
        Plataforma l;
        if( (l = (Plataforma)arqPlataformas.buscar(id))!=null )
        {
            System.out.println(l);
            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S')
            {
                if( arqPlataformas.excluir(id) )
                {
                    System.out.println("Plataforma excluída.");
                }
            }
        }
        else
            System.out.println("Plataforma não encontrada");
    }*/
}
