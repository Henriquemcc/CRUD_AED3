import java.text.DecimalFormat;
import java.util.Scanner;

public class CRUDPLATAFORMA
{
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Plataforma> arqPlataformas;
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
                System.out.println("1 - Listar plataformas");
                System.out.println("2 - Buscar plataformas");
                System.out.println("3 - Incluir plataformas");
                System.out.println("4 - Excluir plataformas");
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
                    case 1: listarPlataforma(); break;
                    case 2:
                        System.out.println("\nBUSCA");
                        int id;
                        System.out.print("ID: ");
                        id = Integer.valueOf(console.nextLine());
                        buscarPlataforma(id);
                    break;
                    case 3: incluirPlataforma(); break;
                    case 4: excluirPlataforma(); break;
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
        arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");
    }
    public static boolean listarPlataforma() throws Exception
    {
        criaArquivo();
        if(arqPlataformas==null){
            System.out.println("Nenhuma plataforma registrada");
            return false;
        }
        Object[] plataformas = arqPlataformas.listar();
        if(plataformas.length==0){
            System.out.println("Nenhuma plataforma registrada");
            return false;
        }
        else{
            for(int i=0; i<plataformas.length; i++)
            {
                Plataforma p = (Plataforma)plataformas[i];
                System.out.print(p.getID()+" "+p.getNome()+" ");
            }
            System.out.println("");
        }
        return true;
    }
   
    public static void buscarPlataforma(int id) throws Exception
    {
        criaArquivo();
        if(id <=0) 
            return;
        Plataforma l;
        if( (l = (Plataforma)arqPlataformas.buscar(id))!=null )
            System.out.println("Plataforma: "+l.getNome());
        else
            System.out.println("Plataforma não encontrada");
    }
    
    public static void incluirPlataforma() throws Exception
    {
        System.out.println("\nINCLUSÃO");
        System.out.print("Nome: ");
        String nome = console.nextLine();
        System.out.print("\nConfirma inclusão? ");
        char confirma = console.nextLine().charAt(0);
        if(confirma=='s' || confirma=='S')
        {
            Plataforma l = new Plataforma(-1, nome);
            if(arqPlataformas==null)
                criaArquivo();
            int id = arqPlataformas.incluir(l);
            System.out.println("Plataforma incluída com ID: "+id);
        }
    }
   
    public static void excluirPlataforma() throws Exception
    {
        criaArquivo();
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
    }

    public static void povoar() throws Exception
    {
        arqPlataformas.incluir(new Plataforma(-1,"PS4"));
        arqPlataformas.incluir(new Plataforma(-1,"XONE"));
        arqPlataformas.incluir(new Plataforma(-1,"PC"));
    }
}
