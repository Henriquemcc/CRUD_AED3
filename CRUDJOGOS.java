import java.util.Scanner;

public class CRUDJOGOS
{
    private static Scanner console = new Scanner(System.in);
    private static ArquivoIndexado<Jogo> arqJogos;
    private static ArquivoIndexado<Genero> arqGeneros;
    private static ArquivoIndexado<Plataforma> arqPlataformas;
    private static ArquivoIndexado<JogosDaPlataforma> arqJogosDaPlataforma;

    private static ArvoreBMais idxJogoporGenero;

    private static ArvoreBMais idxJogoparaJdp;

    private static ArvoreBMais idxPlataformaparaJdp;


    public static void main(String[] args)
    {

        try
        {
            arqJogos = new ArquivoIndexado<>(Jogo.class.getConstructor(), "jogos.db");
            arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
            arqJogosDaPlataforma = new ArquivoIndexado<>(JogosDaPlataforma.class.getConstructor(), "jogosdaplataforma.db");
            arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");
            idxJogoporGenero= new ArvoreBMais(5,"jogoporgenero.idx");
            idxJogoparaJdp = new ArvoreBMais(5,"jogoparajdp.idx");
            idxPlataformaparaJdp = new ArvoreBMais(5,"plataformaparajdp.idx");

            
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
                System.out.println("6 - Listar Jogos por Genero");
                System.out.println("7 - Listar Generos de Jogo disponiveis");
                System.out.println("8 - Inserir Genero de Jogo");
                System.out.println("9 - Listar Plataformas disponiveis");
                System.out.println("10 - Inserir Plataforma");
                System.out.println("11 - Povoar BD");
                System.out.println("0 - Sair");
                System.out.print("\nOpcao: ");
                try
                {
                    opcao = Integer.valueOf(console.nextLine());
                }
                catch(NumberFormatException e)
                {
                    e.printStackTrace();
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
                        listarJogosdeumaPlataforma();
                        console.nextLine();
                        break;
                    case 6:
                        listarJogosporGenero();
                        break;
                    case 7:
                        listarGenero();
                        break;
                    case 8:
                        incluirGenero();
                        break;
                    case 9:
                        System.out.println("Plataformas disponiveis");
                        listarPlataforma();
                        break;
                    case 10:
                        incluirPlataforma();
                        break;
                    case 11:
                        povoar();
                        break;
                    default: System.out.println("Opcao invalida");
                        break;
                    case 0: break;
                }
               
           }
           while(opcao!=0);
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    } 
    public static void listarJogosdeumaPlataforma()throws Exception{
            listarPlataforma();
            System.out.println("DIGITE O NUMERO DE UMA PLATAFORMA");
            int idplataforma = console.nextInt();
            int[] idsDosJdp = idxPlataformaparaJdp.lista(idplataforma);
            System.out.println(idsDosJdp.length);
            for(int i=0;i<idsDosJdp.length;i++){
                JogosDaPlataforma jdp = (JogosDaPlataforma)arqJogosDaPlataforma.buscar(idsDosJdp[i]);
                Jogo j = (Jogo) arqJogos.buscar(jdp.getIdJogo());
                System.out.println(j);

            }
    }
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
            int id = arqPlataformas.incluir(l);
            System.out.println("Plataforma incluída com ID: "+id);
        }
    }
   
    public static void buscarPlataforma(int id) throws Exception
    {
        if(id <=0) 
            return;
        Plataforma l;
        if( (l = (Plataforma)arqPlataformas.buscar(id))!=null )
            System.out.println("Plataforma: "+l.getNome());
        else
            System.out.println("Plataforma não encontrada");
    }
    public static boolean listarPlataforma() throws Exception
    {
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
    public static boolean listarGenero() throws Exception
    {
        
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
    public static void listarJogosporGenero()throws Exception{
        System.out.println("Dentre os generos listados abaixo digite o id do genero desejado");
        listarGenero();
        int idGenero = console.nextInt();
        if(idGenero <=0) 
            return;
        Genero g;
        if( (g = (Genero)arqGeneros.buscar(idGenero) )!=null ) {
            System.out.println(g.getID() + ": " + g.getTipo());
            System.out.println("Jogos: -------------");
            
            int[] idsDosJogos = idxJogoporGenero.lista(idGenero);
            for(int i=0; i<idsDosJogos .length; i++) {
                Jogo j = (Jogo)arqJogos.buscar(idsDosJogos[i]);
                System.out.println(j);
            };
        }
        else
            System.out.println("Genero não encontrado");
        console.nextLine();
    }
    public static void listarJogo() throws Exception
    {
        Object[] jogos = arqJogos.listar();
        for(int i=0; i<jogos.length; i++)
        {
            Jogo j = (Jogo)jogos[i];
            System.out.println(j);
            Genero g = (Genero)arqGeneros.buscar(j.getIdGenero());
            System.out.println("Genero: "+g.getTipo());
            int[] idsDosJdp = idxJogoparaJdp.lista(j.getID());
            for(int c = 0;c<idsDosJdp.length;c++){
                JogosDaPlataforma jdp =  (JogosDaPlataforma) arqJogosDaPlataforma.buscar(idsDosJdp[c]);
                buscarPlataforma(jdp.getIdPlataforma());
            }
            //CRUDJOGOSDAPLATAFORMA.listarPlataformasdoJogo(j.getID());
        }
    }
   
    public static boolean buscarJogo(int id) throws Exception
    {
        if(id <=0) 
            return false;
        Jogo j;
        if( (j = (Jogo)arqJogos.buscar(id))!=null ){
            System.out.println(j);
            Genero g = (Genero)arqGeneros.buscar(j.getIdGenero());
            System.out.println("Genero: "+g.getTipo());
            int[] idsDosJdp = idxJogoparaJdp.lista(j.getID());
            for(int c = 0;c<idsDosJdp.length;c++){
                JogosDaPlataforma jdp =  (JogosDaPlataforma) arqJogosDaPlataforma.buscar(idsDosJdp[c]);
                buscarPlataforma(jdp.getIdPlataforma());
            }
            return true;
        }
        return false;
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
        if(!listarGenero()){
            System.out.print("Por favor registre um novo genero !");
            incluirGenero();
            System.out.print("Generos Disponiveis: ");
            listarGenero();
        }
        System.out.println("Se o genero desejado nao estiver previamente incluido, aperte digite 'S' para inclui-lo, caso contrario digite 'N'");
        char ok = console.nextLine().charAt(0);
        if(ok=='s'||ok=='S'){
            incluirGenero();
            System.out.print("Generos Disponiveis: ");
            listarGenero();
        }
        System.out.println("Digite o ID do genero");
        genero = Integer.valueOf(console.nextLine());



        System.out.print("Plataformas Disponiveis: ");
        if(!listarPlataforma()){
            System.out.print("Por favor registre uma nova plataforma !");
            incluirPlataforma();
        }
        System.out.println("Se a plataforma desejada nao estiver previamente incluida, aperte digite 'S' para inclui-la, caso contrario digite 'N'");
        ok = console.nextLine().charAt(0);
        char confirmed;
        do{
            confirmed = 'n';
            if(ok=='s'||ok=='S'){
                incluirPlataforma();
                System.out.print("Plataformas Disponiveis: ");
                listarPlataforma();
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
            idxJogoporGenero.inserir(genero,id);
            for(int i = 0;i<cont;i++){
                JogosDaPlataforma jdp  = new JogosDaPlataforma(-1,plataformas[i],id);
                System.out.println("Id plataforma "+i+" = "+jdp.getIdPlataforma());
                int idJdp = arqJogosDaPlataforma.incluir(jdp);
                idxJogoparaJdp.inserir(id, idJdp);
                idxPlataformaparaJdp.inserir(plataformas[i],idJdp);
            }
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
                    idxJogoporGenero.excluir(l.getIdGenero(),id);
                    int[] idsjdp = idxJogoparaJdp.lista(l.getID());
                    for(int i = 0;i<idsjdp.length;i++){
                        idxJogoparaJdp.excluir(idsjdp[i],l.getID());
                        JogosDaPlataforma jdp = (JogosDaPlataforma)arqJogosDaPlataforma.buscar(idsjdp[i]);
                        idxPlataformaparaJdp.excluir(idsjdp[i],jdp.getIdPlataforma());
                        arqJogosDaPlataforma.excluir(idsjdp[i]);
                    }
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
        arqGeneros.incluir(new Genero(-1,"FPS"));//1
        arqGeneros.incluir(new Genero(-1,"MOBA"));//2
        arqGeneros.incluir(new Genero(-1,"Battle Royale"));//3
        arqGeneros.incluir(new Genero(-1,"Esportes"));//4
        arqGeneros.incluir(new Genero(-1,"Acao"));//5
        arqGeneros.incluir(new Genero(-1,"RPG"));//6
        arqPlataformas.incluir(new Plataforma(-1,"PC"));//1
        arqPlataformas.incluir(new Plataforma(-1,"PS4"));//2
        arqPlataformas.incluir(new Plataforma(-1,"XONE"));//3
        arqPlataformas.incluir(new Plataforma(-1,"Nintendo Switch"));//4
        arqPlataformas.incluir(new Plataforma(-1,"Celular"));//5
        //inclundo cs go
        arqJogos.incluir(new Jogo(-1,1,"Counter Strike: Global Offensive",(byte)83));//1
        idxJogoporGenero.inserir(1,1);
        arqJogosDaPlataforma.incluir(new JogosDaPlataforma(-1,1,1));//1
        idxJogoparaJdp.inserir(1, 1);
        idxPlataformaparaJdp.inserir(1,1);
        //incluindo WoW
        arqJogos.incluir(new Jogo(-1,1,"World of Warcraft",(byte)93));//2
        idxJogoporGenero.inserir(6,2);//primeiro genero dps o jogo
        arqJogosDaPlataforma.incluir(new JogosDaPlataforma(-1, 1,2));//2
        idxJogoparaJdp.inserir(2, 2);//primeiro id jogo segundo id jdp
        idxPlataformaparaJdp.inserir(1,2);//primeiro id plataforma segundo id jdp
        
    }
}
