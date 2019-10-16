import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

class CrudJogos
{
    private static ArquivoIndexado<Jogo> arqJogos;
    private static ArquivoIndexado<Genero> arqGeneros;
    private static ArquivoIndexado<Plataforma> arqPlataformas;
    private static ArquivoIndexado<JogosDaPlataforma> arqJogosDaPlataforma;
    private static ArvoreBMais idxJogoporGenero;
    private static ArvoreBMais idxJogoparaJdp;
    private static ArvoreBMais idxPlataformaparaJdp;

    public static void main(String[]args)
    {
        int comando=-1;
        do
        {
            try
            {
                criarArquivo();
                boolean repetir;
                do
                {
                    try
                    {
                        exibirMenu();
                        comando=MyIO.readInt();
                        if(comando<0 || comando>12)
                            throw new InputMismatchException("Erro: Opcao Invalida!");
                        repetir=false;
                    }
                    catch(InputMismatchException e)
                    {
                        MyIO.println(e.toString());
                        repetir=true;
                    }
                }
                while(repetir);

                if(comando==1)
                    menuListarJogo();
                else if(comando==2)
                    menuBuscarJogo();
                else if(comando==3)
                    menuIncluirJogo();
                else if(comando==4)
                    menuExcluirJogo();
                else if(comando==5)
                    menuListarJogosPorPlataforma();
                else if(comando==6)
                    menuListarJogosPorGenero();
                else if(comando==7)
                    menuListarGenero();
                else if(comando==8)
                    menuInserirGeneroDeJogo();
                else if(comando==9)
                    menuListarPlataformasDisponiveis();
                else if(comando==10)
                    menuInserirPlataforma();
                else if(comando==11)
                    povoarBancoDeDados();
                else if(comando==12) 
                    menuAlterarJogo();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        while(comando!=0);
    }
    /*
        metodo que cria todos os arquivos necessários para o CRUD:
            4 arquivos indexados: arqJogos, arqGeneros, arqJogosDaPlataforma, arqPlataformas
            3 arvores B+ : idxJogoporGenero, idxJogoparaJdp , idxPlataformaparaJdp

    */
    private static void criarArquivo() throws Exception
    {
        arqJogos = new ArquivoIndexado<>(Jogo.class.getConstructor(), "jogos.db");//Arquivo dos jogos
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");//Arquivo dos generos dos jogos
        arqJogosDaPlataforma = new ArquivoIndexado<>(JogosDaPlataforma.class.getConstructor(), "jogosdaplataforma.db");
        arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");//Arquivo da plataforma de jogos
        idxJogoporGenero= new ArvoreBMais(5,"jogoporgenero.idx");//Indice de jogos por generos
        idxJogoparaJdp = new ArvoreBMais(5,"jogoparajdp.idx");//Indice de Jogos por Jogos da Plataforma
        idxPlataformaparaJdp = new ArvoreBMais(5,"plataformaparajdp.idx");//Indice de plataforma para Jogos da Plataforma
    }
    /*
        metodo que exibe o menu com todas as opções do CRUD
    */

    private static void exibirMenu()
    {
        MyIO.println("\n\n------------------------------------------------");
        MyIO.println("                    MENU");
        MyIO.println("------------------------------------------------");
        MyIO.println("1 - Listar jogos");
        MyIO.println("2 - Buscar jogo");
        MyIO.println("3 - Incluir jogo");
        MyIO.println("4 - Excluir jogo");
        MyIO.println("5 - Listar Jogos por Plataforma");
        MyIO.println("6 - Listar Jogos por Genero");
        MyIO.println("7 - Listar Generos de Jogo disponiveis");
        MyIO.println("8 - Inserir Genero de Jogo");
        MyIO.println("9 - Listar Plataformas disponiveis");
        MyIO.println("10 - Inserir Plataforma");
        MyIO.println("11 - Povoar BD");
        MyIO.println("12 - Alterar jogo");
        MyIO.println("0 - Sair");
        MyIO.print("\nOpcao: ");
    }
    /**
     * metodo que lista para o usuario todos os jogos disponiveis 
     */
    private static void menuListarJogo() throws Exception
    {
        Object[] jogos=arqJogos.listar();
        for (Object o : jogos) {
            Jogo jogo = (Jogo) o;
            MyIO.println(jogo.toString());
            Genero genero = (Genero) arqGeneros.buscar(jogo.getIdGenero());
            MyIO.println("Genero: " + genero.getTipo());
            int[] idsDosJogosDasPlataformas = idxJogoparaJdp.lista(jogo.getID());
            for (int idsDosJogosDasPlataforma : idsDosJogosDasPlataformas) {
                JogosDaPlataforma jogosDaPlataforma = (JogosDaPlataforma) arqJogosDaPlataforma.buscar(idsDosJogosDasPlataforma);
                menuBuscarPlataforma(jogosDaPlataforma.getIdPlataforma());
            }
        }
    }
    /** 
     *  metodo que busca uma determinada plataforma. 
     *  Caso ela exista, escrever o seu nome. Caso ela não exista, escrever para o usuário plataforma não encontrada
     *  @param: int id 
    */
    private static void menuBuscarPlataforma(int id) throws Exception
    {
        if(id>0)
        {
            Plataforma plataforma=buscarPlataforma(id);
            if(plataforma!=null)
                MyIO.println("Plataforma: "+plataforma.getNome());
            else
                MyIO.println("Plataforma nao encontrada");
        }
    }
    
    /** 
     * metodo que retorna um determino objeto do tipo plataforma
     * @param: int id
     * @return: Plataforma 
    */
    private static Plataforma buscarPlataforma(int id) throws Exception
    {
        return (Plataforma)arqPlataformas.buscar(id);
    }
    /**
     * metodo que exibe na tela do usuario a opção de buscar um jogo
     */
    private static void menuBuscarJogo() throws Exception
    {
        MyIO.println("\nBUSCA");
        boolean repetir;
        int id=-1;
        do
        {
            try
            {
                id=MyIO.readInt("ID: ");
                if(id<=0)
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        if(buscarJogo(id))
        {
            Jogo jogo=getJogo(id);
            MyIO.println(jogo.toString());
            Genero genero=(Genero)arqGeneros.buscar(jogo.getIdGenero());
            MyIO.println("Genero: "+genero.getTipo());
            int[] idsDosJogosDaPlataforma =idxJogoparaJdp.lista(jogo.getID());
            for (int value : idsDosJogosDaPlataforma) {
                JogosDaPlataforma jogosDaPlataforma = (JogosDaPlataforma) arqJogosDaPlataforma.buscar(value);
                menuBuscarPlataforma(jogosDaPlataforma.getIdPlataforma());
            }
        }
    }
    /**
     * metodo que retorna se um objeto do tipo jogo existe a partir de uma id
     * @param: int id
     * @return: boolean resp
     */
    public static boolean buscarJogo(int id) throws Exception
    {
        boolean resp=false;
        Jogo jogo=(Jogo)arqJogos.buscar(id);
        if(jogo!=null)
            resp=true;
        return resp;
    }
    /**
     * metodo que retorna um objeto do tipo jogo a partir de uma id
     * @param: int id
     * @return: Jogo
     */
    private static Jogo getJogo(int id) throws Exception
    {
        return (Jogo)arqJogos.buscar(id);
    }
    /**
     * metodo que possui um menu de inclusão de um determinado jogo para um usuário
     */
    private static void menuIncluirJogo() throws Exception
    {
        String titulo;
        byte score=-1;
        String genero;

        MyIO.println("\nINCLUSAO");
        titulo=MyIO.readLine("Titulo: ");

        boolean repetir;
        do
        {
            try
            {
                score=MyIO.readByte("Score: ");
                if(score<0 || score>100)
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        genero=MyIO.readLine("Genero: ");

        ArrayList<String> plataformas=new ArrayList<>();
        char adicionarMaisPlataformas=' ';
        MyIO.println("Plataformas: ");
        do
        {
            plataformas.add(MyIO.readLine("Plataforma: "));
            boolean repetir1;
            do
            {
                try
                {
                    adicionarMaisPlataformas=MyIO.readLine("Deseja adicionar outra plataforma? ").toLowerCase().charAt(0);
                    if(adicionarMaisPlataformas!='s' && adicionarMaisPlataformas!='n')
                        throw new InputMismatchException("Entrada Invalida");
                    repetir1=false;
                }
                catch(InputMismatchException e)
                {
                    MyIO.println(e.toString());
                    repetir1=true;
                }
            }
            while(repetir1);
        }
        while(adicionarMaisPlataformas=='s');

        inserirJogo(titulo, score, genero, plataformas);
    }
    /**
     * metodo que insere um novo objeto do tipo jogo a partir de um titulo, score, genero e plataforma
     * @param: String titulo, byte score, String nomeDoGenero, ArrayList<String> nomeDaPlataforma
     */
    private static void inserirJogo(String titulo, byte score, String nomeDoGenero, ArrayList<String> nomeDaPlataforma) throws  Exception
    {
        Genero genero=getGenero(nomeDoGenero);
        if(genero==null)
        {
            inserirGenero(nomeDoGenero);
            genero = getGenero(nomeDoGenero);
        }
        int idGenero=genero.getID();

        ArrayList<Integer> idPlataformas=new ArrayList<>();

        for (String s : nomeDaPlataforma) {
            Plataforma plataforma = getPlataforma(s);
            if (plataforma == null)
                inserirPlataforma(s);
            idPlataformas.add(getPlataforma(s).getID());
        }

        Jogo jogo=new Jogo(-1, idGenero, titulo, score);
        int idJogo=arqJogos.incluir(jogo);
        idxJogoporGenero.inserir(idGenero, idJogo);
        for (Integer idPlataforma : idPlataformas) {
            JogosDaPlataforma jogosDaPlataforma = new JogosDaPlataforma(-1, idPlataforma, idJogo);
            int idJogosDaPlataforma = arqJogosDaPlataforma.incluir(jogosDaPlataforma);
            idxJogoparaJdp.inserir(idJogo, idJogosDaPlataforma);
            idxPlataformaparaJdp.inserir(idPlataforma, idJogosDaPlataforma);
        }
    }
    /**
     * metodo que insere um novo objeto do tipo jogo a partir de um titulo, score, genero e plataforma
     * @param: String titulo, byte score, String nomeDoGenero, ArrayList<String> nomeDaPlataforma
     */
    private static void inserirJogo(String titulo, byte score, String nomeDoGenero, String... nomeDaPlataforma) throws Exception
    {
        ArrayList<String> plataforma = new ArrayList<>(Arrays.asList(nomeDaPlataforma));
        inserirJogo(titulo, score, nomeDoGenero, plataforma);
    }
    /**
     * metodo que retorna um objeto do tipo genero a partir de uma busca em todos os generos.
     */
    private static Genero getGenero(String nomeDoGenero) throws Exception
    {
        Genero resp=null;
        ArrayList<Genero> genero=new ArrayList<>();
        Object[] objeto =arqGeneros.listar();
        for (Object o : objeto) {
            Genero tmp = (Genero) o;
            genero.add(tmp);
        }

        int i=0;
        boolean encontrado=false;
        while(i<genero.size() && !encontrado)
        {
            if(genero.get(i).getTipo().toUpperCase().equals(nomeDoGenero.toUpperCase()))
                encontrado=true;
            else
                i++;
        }

        if(encontrado)
            resp=genero.get(i);

        return resp;
    }
    /**
     * metodo que retorna se um caracter é um numero
     * @param: char c
     * @return: boolean ehNumero
     */
    public static boolean ehNumero(char c)
    {
        boolean ehNumero=false;
        if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')
            ehNumero=true;
        return ehNumero;
    }//fim do metodo ehNumero
    /**
     * metodo que busca um objeto do tipo plataforma a partir de uma busca em todas as plataformas
     * @param: String nomeDaPlataforma
     * @return: Plataforma
     */
    private static Plataforma getPlataforma(String nomeDaPlataforma) throws Exception
    {

        Plataforma resp=null;
        ArrayList<Plataforma> plataforma=new ArrayList<>();
        Object[] objeto =arqPlataformas.listar();
        for (Object o : objeto) {
            Plataforma tmp = (Plataforma) o;
            plataforma.add(tmp);
        }
        int i=0;
        boolean encontrado=false;
        while(i<plataforma.size() && !encontrado)
        {
            if(plataforma.get(i).getNome().toUpperCase().equals(nomeDaPlataforma.toUpperCase()))
                encontrado=true;
            else
                i++;
        }

        if(encontrado)
            resp=plataforma.get(i);

        return resp;
    }
    /**
     * metodo que possui um menu de exclusao permitindo ao usuario a exclusao de um determinado jogo
     */
    private static void menuExcluirJogo() throws Exception
    {
        MyIO.println("\nEXCLUSAO");

        int id=0;
        boolean repetir;
        do
        {
            try
            {
                id=MyIO.readInt("ID: ");
                if(id<=0)
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        Jogo jogo=(Jogo)arqJogos.buscar(id);
        if(jogo!=null)
        {
            MyIO.println(jogo.toString());
            char confirmacao=' ';
            boolean repetir1;
            do
            {
                try
                {
                    confirmacao=MyIO.readLine("\nConfirma exclusao? ").toLowerCase().charAt(0);
                    if(confirmacao!='s' && confirmacao!='n')
                        throw new InputMismatchException("Entrada Invalida!");
                    repetir1=false;
                }
                catch(InputMismatchException e)
                {
                    MyIO.println(e.toString());
                    repetir1=true;
                }
            }
            while(repetir1);

            if(confirmacao=='s')
                excluirJogo(id);
        }
    }
    /**
     * metodo que exclui um determinado jogo a partir de uma id
     * @param: int id
     */
    private static void excluirJogo(int id) throws Exception
    {
        Jogo jogo=(Jogo)arqJogos.buscar(id);
        boolean resp=false;
        if(arqJogos.excluir(id))
        {
            idxJogoporGenero.excluir(jogo.getIdGenero(),id);
            int[] idsjdp = idxJogoparaJdp.lista(jogo.getID());
            for (int value : idsjdp) {
                idxJogoparaJdp.excluir(value, jogo.getID());
                JogosDaPlataforma jdp = (JogosDaPlataforma) arqJogosDaPlataforma.buscar(value);
                idxPlataformaparaJdp.excluir(value, jdp.getIdPlataforma());
                arqJogosDaPlataforma.excluir(value);
            }
            resp=true;
        }
    }

    private static void menuAlterarJogo() throws Exception
    {
        menuListarJogo();
        MyIO.println();
        MyIO.print("Digite o id que voce deseja alterar:");  
        int id = MyIO.readInt(); 
        excluirJogo(id);
        menuIncluirJogo();

    }
    /**
     * metodo que possui um menu em que o usuario digita o numero da plataforma e é exibido os jogos dessa determinada plataforma
     */
    private static void menuListarJogosPorPlataforma() throws Exception
    {
        menuListarPlataformas();
        int idplataforma=-1;
        boolean repetir;
        do
        {
            try
            {
                idplataforma=MyIO.readInt("DIGITE O NUMERO DE UMA PLATAFORMA");
                if(idplataforma<=0)
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);
        int[] idsDosJdp = idxPlataformaparaJdp.lista(idplataforma);
        MyIO.println(idsDosJdp.length);
        for (int value : idsDosJdp) {
            JogosDaPlataforma jogosDaPlataforma = (JogosDaPlataforma) arqJogosDaPlataforma.buscar(value);
            Jogo jogo = (Jogo) arqJogos.buscar(jogosDaPlataforma.getIdJogo());
            MyIO.println(jogo.toString());
        }
    }
    /**
     * metodo que lista todas as plataformas 
     */
    private static void menuListarPlataformas() throws Exception
    {
        if(arqPlataformas==null)
            MyIO.println("Nenhuma plataforma registrada");
        else
        {
            Object[] plataformas =arqPlataformas.listar();
            if(plataformas.length==0)
                MyIO.println("Nenhuma plataforma registrada");
            else
            {
                for (Object o : plataformas) {
                    Plataforma plataforma = (Plataforma) o;
                    MyIO.println(plataforma.getID() + " " + plataforma.getNome() + " ");
                }
                MyIO.println();
            }
        }
    }
    /**
     * metodo que lista todos os jogos de um determinado genero
     */
    private static void menuListarJogosPorGenero() throws Exception
    {
        MyIO.println("Dentre os generos listados abaixo digite o id do genero desejado");
        menuListarGenero();
        int idGenero=-1;
        boolean repetir;
        do
        {
            try
            {
                idGenero=MyIO.readInt();
                if(idGenero<=0)
                    throw new InputMismatchException("Entrada Invalida");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
                menuListarGenero();
            }
        }
        while(repetir);

        Genero genero=(Genero)arqGeneros.buscar(idGenero);
        if(genero!=null)
        {
            MyIO.println(genero.getID() + ": " + genero.getTipo());
            MyIO.println("Jogos: -------------");

            int[] idsDosJogos = idxJogoporGenero.lista(idGenero);
            for (int idsDosJogo : idsDosJogos) {
                Jogo jogo = (Jogo) arqJogos.buscar(idsDosJogo);
                MyIO.println(jogo.toString());
            }
        }
        else
            MyIO.println("Genero nao encontrado");
        MyIO.pause();
    }
    /**
     * metodo que lista todos os generos disponiveis
     */
    private static void menuListarGenero() throws Exception
    {
        if(arqGeneros==null)
            MyIO.println("Nenhum genero registrado");
        else
        {
            Object[] generos =arqGeneros.listar();
            if(generos.length==0)
                MyIO.println("Nenhum genero registrado");
            else
            {
                for (Object o : generos) {
                    Genero genero = (Genero) o;
                    MyIO.print(genero.getID() + " " + genero.getTipo() + " ");
                }
                MyIO.println();
            }
        }
    }
    /**
     * metodo que exibe um menu para uma nova inserção de um genero de um jogo
     */
    private static void menuInserirGeneroDeJogo() throws Exception
    {
        MyIO.println("\nINCLUSAO");
        String tipo=MyIO.readLine("Tipo: ");
        char confirmacao=' ';
        boolean repetir;
        do
        {
            try
            {
                confirmacao=MyIO.readLine("\nConfirma inclusao? ").toLowerCase().charAt(0);
                if(confirmacao!='s' && confirmacao!='n')
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        if(confirmacao=='s')
            MyIO.println("Genero incluido com ID: "+inserirGenero(tipo));
    }
    /**
     * metodo que insere um novo genero e retorna sua id.
     * Caso o genero já exista, é retornado o id -1
     * @param: String nomeDoGenero
     * @return: int resp
     */
    private static int inserirGenero(String nomeDoGenero) throws Exception
    {
        int resp=-1;
        if(getGenero(nomeDoGenero)==null)
        {
            Genero genero=new Genero(-1, nomeDoGenero);
            resp=arqGeneros.incluir(genero);
        }
        else
            resp=getGenero(nomeDoGenero).getID();

        return resp;
    }
    /**
     * Menu que lista todas as plataformas disponiveis
     */
    private static void menuListarPlataformasDisponiveis() throws Exception
    {
        MyIO.println("Plataformas disponiveis");
        menuListarPlataformas();
    }
    /**
     * Menu de inserção de uma nova plataforma
     */
    private static void menuInserirPlataforma() throws Exception
    {
        MyIO.println("\nINCLUSAO");
        String nome=MyIO.readLine("Nome: ");
        char confirmacao=' ';
        boolean repetir;
        do
        {
            try
            {
                confirmacao=MyIO.readLine("\nConfirma inclusao? ").toLowerCase().charAt(0);
                if(confirmacao!='s' && confirmacao!='n')
                    throw new InputMismatchException("Entrada Invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        if(confirmacao=='s')
            MyIO.println("Plataforma incluida com ID: "+inserirPlataforma(nome));

    }
    /**
     * metodo que insere uma nova plataforma e retorna sua id.
     * Caso a plataforma já exista, é retornado o id -1
     * @param: String nomeDaPlataforma
     * @return: int resp
     */
    private static int inserirPlataforma(String nomeDaPlataforma) throws Exception
    {
        int resp=-1;
        if(getPlataforma(nomeDaPlataforma)==null)
        {
            Plataforma plataforma=new Plataforma(-1, nomeDaPlataforma);
            resp=arqPlataformas.incluir(plataforma);
        }
        else
            resp=getPlataforma(nomeDaPlataforma).getID();

        return resp;
    }
    /**
     * metodo que povoa o banco de dados com plataformas, generos e jogos
     */
    private static void povoarBancoDeDados() throws Exception
    {
        //Plataformas
        /*
        inserirPlataforma("PlayStation One");
        inserirPlataforma("PlayStation 2");
        inserirPlataforma("PlayStation 3");
        inserirPlataforma("PlayStation 4");
        inserirPlataforma("Xbox");
        inserirPlataforma("Xbox 360");
        inserirPlataforma("Xbox One");
        inserirPlataforma("Nintendo GameCube");
        inserirPlataforma("Nintendo Wii");
        inserirPlataforma("Nintendo WiiU");
        inserirPlataforma("Nintendo DS");
        inserirPlataforma("Nintendo 3DS");
        inserirPlataforma("Nintendo Switch");
        inserirPlataforma("Windows");
        inserirPlataforma("Mac OS");
        inserirPlataforma("Linux");
        inserirPlataforma("Android");
        inserirPlataforma("iOS");
        inserirPlataforma("Windows Phone");
        */

        //Generos
        /*
        inserirGenero("FPS");
        inserirGenero("MOBA");
        inserirGenero("Battle Royale");
        inserirGenero("Esportes");
        inserirGenero("Acao");
        inserirGenero("Aventura");
        inserirGenero("RPG");
        */
        //Jogos
        inserirJogo("Counter Strike: Global Offensive", (byte)83,"FPS" ,"Windows", "Mac OS", "Linux", "Xbox", "Xbox 360");
        inserirJogo("World of Warcraft", (byte)93, "MOBA","Windows", "Mac OS");
        inserirJogo("WatchDogs 2", (byte)82, "Acao", "Windows", "PlayStation 4", "Xbox One");
        inserirJogo("Assassin's Creed Unity", (byte)70, "Acao", "Windows", "PlayStation 4", "Xbox One");
        //inserirJogo("Assassin's Creed III", (byte)72, "Acao", "Windows", "PlayStation 3", "PlayStation 4", "Xbox 360", "Xbox One", "WiiU");
        //inserirJogo("Overwatch", (byte)91, "FPS", "Windows", "PlayStation 4", "Xbox One", "Nintendo Switch");
        /*inserirJogo("Assassin's Creed Rougue", (byte)74, "Acao", "PlayStation 3", "Xbox 360", "Windows", "PlayStation 4", "Xbox One", "Nintendo Switch");
        inserirJogo("Battlefield 1", (byte)88, "FPS", "Windows", "PlayStation 4", "Xbox One");
        inserirJogo("FarCry 4", (byte)85, "FPS", "Windows", "PlayStation 4", "PlayStation 3", "Xbox 360", "Xbox One");
        inserirJogo("Need for Speed Rivals", (byte)80, "Corrida", "Windows", "PlayStation 3", "PlayStation 4", "Xbox 360", "Xbox One");
        inserirJogo("Call of Duty Black Ops III", (byte)81, "FPS", "Windows", "Mac OS", "PlayStation 3", "PlayStation 4", "Xbox 360", "Xbox One");*/
    }
}
