import java.text.DecimalFormat;
import java.util.Scanner;

public class CRUDJOGOS
{
    
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Jogo> arqJogos;
    private static DecimalFormat df;
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
                    case 1: listarJogo(); break;
                    case 2:
                        System.out.println("\nBUSCA");
                        int id;
                        System.out.print("ID: ");
                        id = Integer.valueOf(console.nextLine());
                        buscarJogo(id);
                    break;
                    case 3: incluirJogo(); break;
                    case 4: excluirJogo(); break;
                    case 9: povoar(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida");
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
            System.out.println((Jogo)jogos[i]);
        }
    }
   
    public static void buscarJogo(int id) throws Exception
    {
        if(id <=0) 
            return;
        Jogo l;
        if( (l = (Jogo)arqJogos.buscar(id))!=null )
            System.out.println(l);
        else
            System.out.println("Jogo não encontrado");
    }
    
    public static void incluirJogo() throws Exception
    {
        String titulo;
        byte score;
        int genero;
        System.out.println("\nINCLUSÃO");
        System.out.print("Título: ");
        titulo = console.nextLine();
        System.out.print("Score: ");
        score = Byte.valueOf(console.nextLine());
        System.out.print("Genero: ");
        genero = Integer.valueOf(console.nextLine());
        System.out.print("\nConfirma inclusão? ");
        char confirma = console.nextLine().charAt(0);
        if(confirma=='s' || confirma=='S')
        {
            Jogo l = new Jogo(-1, genero, titulo, score);
            int id = arqJogos.incluir(l);
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
                if( arqJogos.excluir(id) )
                {
                    System.out.println("Jogo excluído.");
                }
            }
        }
        else
            System.out.println("Jogo não encontrado");
    }
   //Jogo l = new Jogo(-1, genero, titulo, score);
    public static void povoar() throws Exception
    {
        arqJogos.incluir(new Jogo(-1,1,"GTA 5",(byte)95));
        arqJogos.incluir(new Jogo(-1,1,"LOL",(byte)1));
        arqJogos.incluir(new Jogo(-1,2,"CS",(byte)85));
        arqJogos.incluir(new Jogo(-1,300,"FIFA 15",(byte)70));
        arqJogos.incluir(new Jogo(-1,3,"PES 15",(byte)69));
        /*
        arqLivros.incluir(new Livro(-1,"1984","George Orwell",(float)32.8));
        arqLivros.incluir(new Livro(-1,"A Odisséia","Homero",(float)35.9));
        arqLivros.incluir(new Livro(-1,"Sherlock Holmes","Arthur Conan Doyle",(float)24));
        arqLivros.incluir(new Livro(-1,"Joyland","Stephen King",(float)17.9));
        arqLivros.incluir(new Livro(-1,"Objetos Cortantes","Gillian Flynn",(float)16.9));
        arqLivros.incluir(new Livro(-1,"A Lista Negra","Jennifer Brown",(float)16.9));
        arqLivros.incluir(new Livro(-1,"Garota Exemplar","Gillian Flynn",(float)14.9));
        arqLivros.incluir(new Livro(-1,"O Iluminado","Stephen King",(float)14.9));
        arqLivros.incluir(new Livro(-1,"Queda de Gigantes","Ken Follett",(float)23.67));
        arqLivros.incluir(new Livro(-1,"Eternidade Por Um Fio","Ken Follett",(float)30.1));
        arqLivros.incluir(new Livro(-1,"Inverno do Mundo","Ken Follett",(float)29.99));
        arqLivros.incluir(new Livro(-1,"A Guerra dos Tronos","George R. R. Martin",(float)22.76));
        arqLivros.incluir(new Livro(-1,"A Revolução dos Bichos","George Orwell",(float)27.36));
        arqLivros.incluir(new Livro(-1,"O Mundo de Sofia","Jostein Gaarder",(float)28.2));
        arqLivros.incluir(new Livro(-1,"O Velho e o Mar","Ernest Hemingway",(float)16.9));
        arqLivros.incluir(new Livro(-1,"Escuridão Total Sem Estrelas","Stephen King",(float)28.4));
        arqLivros.incluir(new Livro(-1,"O Pintassilgo","Donna Tartt",(float)21.63));
        arqLivros.incluir(new Livro(-1,"Se Eu Ficar","Gayle Forman",(float)13.54));
        arqLivros.incluir(new Livro(-1,"Toda Luz Que Não Podemos Ver","Anthony Doerr",(float)24.38));
        arqLivros.incluir(new Livro(-1,"Eu, Você e a Garota Que Vai Morrer","Jesse Andrews",(float)14.9));
        arqLivros.incluir(new Livro(-1,"Na Natureza Selvagem","Jon Krakauer",(float)14.9));
        arqLivros.incluir(new Livro(-1,"Eu, Robô","Isaac Asimov",(float)20.15));
        arqLivros.incluir(new Livro(-1,"O Demonologista","Andrew Pyper",(float)32.47));
        arqLivros.incluir(new Livro(-1,"O Último Policial","Ben Winters",(float)27.6));
        arqLivros.incluir(new Livro(-1,"A Febre","Megan Abbott",(float)27.9));   
        */
    }
}
