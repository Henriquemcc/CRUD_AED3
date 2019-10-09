import java.util.Scanner;

public class CRUDJOGOS
{
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Jogo> arqJogos;
    public static void main(String[] args)
    {

        try
        {
            arqJogos = new ArquivoIndexado<>(Jogo.class.getConstructor(), "jogos.db");
            
           // menu
           int opcao;
           do
           {
                System.out.println("\n\n------------------------------------------------");
                System.out.println("                    MENU");
                System.out.println("------------------------------------------------");
                System.out.println("1 - Listar jogos");
                System.out.println("2 - Buscar jogo");
                System.out.println("3 - Incluir jogo");
                System.out.println("4 - Excluir jogo");
                System.out.println("5 - Listar Jogos por Plataforma");
                //System.out.println("9 - Povoar BD");
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
                    case 1: listarJogo(); break;
                    case 2:
                        System.out.println("\nBUSCA");
                        int id;
                        System.out.print("ID: ");
                        id = Integer.valueOf(console.nextLine());
                        if(!buscarJogo(id)){
                            System.out.println("Jogo nao encontrado");
                        }
                    break;
                    case 3: incluirJogo(); break;
                    case 4: excluirJogo(); break;
                    case 5:
                        CRUDPLATAFORMA.listarPlataforma();
                        System.out.println("DIGITE O NUMERO DE UMA PLATAFORMA");
                        int idplataforma = console.nextInt();
                        CRUDJOGOSDAPLATAFORMA.listarJogosdeumaPlataforma(idplataforma);
                    case 0: break;
                    default: System.out.println("Opcao invalida");
                }
               
           }
           while(opcao!=0);
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }    
    
    public static void listarJogo() throws Exception
    {
        Object[] jogos = arqJogos.listar();
        for(int i=0; i<jogos.length; i++)
        {
            Jogo j = (Jogo)jogos[i];
            System.out.println(j);
            CRUDJOGOSDAPLATAFORMA.listarPlataformasdoJogo(j.getID());
        }
    }
   
    public static boolean buscarJogo(int id) throws Exception
    {
        if(id <=0) 
            return false;
        Jogo l;
        if( (l = (Jogo)arqJogos.buscar(id))!=null ){
            System.out.println(l);
            CRUDJOGOSDAPLATAFORMA.listarPlataformasdoJogo(id);
            return true;
        }
        return false;
    }
    
    public static void incluirJogo() throws Exception
    {
        String titulo;
        byte score;
        int genero;
        int plataformas[] = new int[100];
        System.out.println("\nINCLUSÃO");
        System.out.print("Título: ");
        titulo = console.nextLine();
        System.out.print("Score: ");
        score = Byte.valueOf(console.nextLine());



        System.out.print("Generos Disponiveis: ");
        if(!CRUDGENERO.listarGenero()){
            System.out.print("Por favor registre um novo genero !");
            CRUDGENERO.incluirGenero();
            System.out.print("Generos Disponiveis: ");
            CRUDGENERO.listarGenero();
        }
        System.out.println("Se o genero desejado nao estiver previamente incluido, aperte digite 'S' para inclui-lo, caso contrario digite 'N'");
        char ok = console.nextLine().charAt(0);
        if(ok=='s'||ok=='S'){
            CRUDGENERO.incluirGenero();
            System.out.print("Generos Disponiveis: ");
            CRUDGENERO.listarGenero();
        }
        System.out.println("Digite o ID do genero");
        genero = Integer.valueOf(console.nextLine());



        System.out.print("Plataformas Disponiveis: ");
        if(!CRUDPLATAFORMA.listarPlataforma()){
            System.out.print("Por favor registre uma nova plataforma !");
            CRUDPLATAFORMA.incluirPlataforma();
        }
        System.out.println("Se a plataforma desejada nao estiver previamente incluida, aperte digite 'S' para inclui-la, caso contrario digite 'N'");
        ok = console.nextLine().charAt(0);
        char confirmed;
        do{
            confirmed = 'n';
            if(ok=='s'||ok=='S'){
                CRUDPLATAFORMA.incluirPlataforma();
                System.out.print("Plataformas Disponiveis: ");
                CRUDPLATAFORMA.listarPlataforma();
                System.out.println("Deseja incluir outra plataforma ? Digite [S] ou [N]");
                confirmed = console.nextLine().charAt(0);
            }
        }while(confirmed =='s'||confirmed=='S');
        int cont = 0;
        do{
            System.out.println("Digite o ID da Plataforma");
            plataformas[cont] = Integer.valueOf(console.nextLine());
            cont++;
            System.out.println("Deseja inserir outra Plataforma deste jogo ? Digite [S] ou [N]");
            confirmed = console.nextLine().charAt(0);

        }while(confirmed=='s'||confirmed=='S');


        System.out.print("\nConfirma inclusão? ");
        char confirma = console.nextLine().charAt(0);
        if(confirma=='s' || confirma=='S')
        {
            Jogo l = new Jogo(-1, genero, titulo, score);
            int id = arqJogos.incluir(l);
            for(int i = 0;i<cont;i++)
            CRUDJOGOSDAPLATAFORMA.incluirJogodaPlataforma(id, plataformas[i]);
            System.out.println("Jogo incluído com ID: "+id);
        }
    }
   
    public static void excluirJogo() throws Exception
    {
        System.out.println("\nEXCLUSÃO");
        int id;
        System.out.print("ID: ");
        id = Integer.valueOf(console.nextLine());
        if(id <=0) 
            return;
        Jogo l;
        if( (l = (Jogo)arqJogos.buscar(id))!=null )
        {
            System.out.println(l);
            System.out.print("\nConfirma exclusão? ");
            char confirma = console.nextLine().charAt(0);
            if(confirma=='s' || confirma=='S')
            {
                if( arqJogos.excluir(id) &&CRUDJOGOSDAPLATAFORMA.excluirJogosdaPlataforma(id, false))
                {

                    System.out.println("Jogo excluído.");
                }
            }
        }
        else
            System.out.println("Jogo não encontrado");
    }
   //Jogo l = new Jogo(-1, genero, titulo, score);
   /* public static void povoar() throws Exception
    {
        arqJogos.incluir(new Jogo(-1,,"GTA 5",(byte)95));
        arqJogos.incluir(new Jogo(-1,1,"LOL",(byte)1));
        arqJogos.incluir(new Jogo(-1,2,"CS",(byte)85));
        arqJogos.incluir(new Jogo(-1,300,"FIFA 15",(byte)70));
        arqJogos.incluir(new Jogo(-1,3,"PES 15",(byte)69));  
    }*/
}
