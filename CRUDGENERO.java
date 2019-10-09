import java.text.DecimalFormat;
import java.util.Scanner;

public class CRUDGENERO {
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Genero> arqGeneros;
    private static DecimalFormat df;
    public static void main(String[] args)
    {

        try
        {
            criaArquivo();
            
           // menu
           int opcao;
           do
           {
                System.out.println("\n\n------------------------------------------------");
                System.out.println("                    MENU");
                System.out.println("------------------------------------------------");
                System.out.println("1 - Listar generos");
                System.out.println("2 - Buscar generos");
                System.out.println("3 - Incluir generos");
                System.out.println("4 - Excluir generos");
                System.out.println("9 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                try
                {
                    opcao = Integer.valueOf(console.nextLine());
                }
                catch(NumberFormatException e)
                {
                    opcao = -1;
                }

                switch(opcao)
                {
                    case 1: listarGenero(); break;
                    case 2: buscarGenero(); break;
                    case 3: incluirGenero(); break;
                    case 4: excluirGenero(); break;
                    case 9: povoar(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida");
                }
               
           }
           while(opcao!=0);
            
            
            
        } catch(Exception e)
        {
            e.printStackTrace();
        }        
    }    
    public static void criaArquivo()throws Exception{
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
    }
    public static boolean listarGenero() throws Exception
    {
        criaArquivo();
        if(arqGeneros==null){
            System.out.println("Nenhum genero registrado");
            return false;
        }
        Object[] generos = arqGeneros.listar();
        if(generos.length==0){
            System.out.println("Nenhum genero registrado");
            return false;
        }
        else{
            for(int i=0; i<generos.length; i++)
            {
                Genero g = (Genero)generos[i];
                System.out.print(g.getID()+" "+g.getTipo()+" ");
            }
            System.out.println("");
        }
        return true;
    }
    //RECEBE O NOME DE UM GENERO E RETORNA O SEU ID
    public static int buscaGeneroPorNome(String nomeGenero)throws Exception{
        Object[] generos = arqGeneros.listar();
        int resp=-1;
        for(int i=0; i<generos.length; i++)
        {
            Genero g = (Genero)generos[i];
            if(g.getTipo().equals(nomeGenero)){
                resp=g.getID();
                i=generos.length;
            }
        }
        return resp;
    }
    public static void buscarGenero() throws Exception
    {
        System.out.println("\nBUSCA");
        int id;
        System.out.print("ID: ");
        id = Integer.valueOf(console.nextLine());
        if(id <=0) 
            return;
        Genero l;
        if( (l = (Genero)arqGeneros.buscar(id))!=null )
            System.out.println(l);
        else
            System.out.println("Jogo não encontrado");
    }
    
   public static void incluirGenero() throws Exception
   {
        System.out.println("\nINCLUSÃO");
        System.out.print("Tipo: ");
        String tipo = console.nextLine();
        System.out.print("\nConfirma inclusão? ");
        char confirma = console.nextLine().charAt(0);
        if(confirma=='s' || confirma=='S')
        {
            Genero l = new Genero(-1, tipo);
            if(arqGeneros==null)
                criaArquivo();
            int id = arqGeneros.incluir(l);
            System.out.println("Genero incluido com ID: "+id);
        }
   }
   
   public static void excluirGenero() throws Exception
   {
        System.out.println("\nEXCLUSÃO");
        int id;
        System.out.print("ID: ");
        id = Integer.valueOf(console.nextLine());
        if(id <=0) 
            return;
        Genero l;
        if( (l = (Genero)arqGeneros.buscar(id))!=null )
        {
            System.out.println(l);
            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S')
            {
                if( arqGeneros.excluir(id) )
                {
                    System.out.println("Genero excluído.");
                }
            }
        }
        else
            System.out.println("Genero não encontrado");
    }
   //Jogo l = new Jogo(-1, genero, titulo, score);
    public static void povoar() throws Exception
    {
        arqGeneros.incluir(new Genero(-1,"FPS"));
        arqGeneros.incluir(new Genero(-1,"RPG"));
        arqGeneros.incluir(new Genero(-1,"MOBA"));
        arqGeneros.incluir(new Genero(-1,"Adventure"));
        arqGeneros.incluir(new Genero(-1,"Puzzle"));
    }
    
}
