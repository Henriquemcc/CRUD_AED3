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
            arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
            
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
    
    public static void listarGenero() throws Exception
    {
        Object[] generos = arqGeneros.listar();
        for(int i=0; i<generos.length; i++)
        {
            System.out.println((Genero)generos[i]);
        }
        
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
            int id = arqGeneros.incluir(l);
            System.out.println("Genero incluído com ID: "+id);
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
