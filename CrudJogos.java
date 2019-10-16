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

    /**
     * Funcao do metodo: Este metodo serve para executar o CrudJogos.
     * @param args Parametros padrao do metodo main.
     */
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
                        if(comando<0 || comando>11)
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
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        while(comando!=0);
    }//fim do metodo main

    /**
     * Funcao do metodo: Este metodo serve para criar todos os arquivos necessarios para o CRUD.
     * 4 arquivos indexados: arqJogos, arqGeneros, arqJogosDaPlataforma, arqPlataformas.
     * 3 arvores B+ : idxJogoporGenero, idxJogoparaJdp , idxPlataformaparaJdp.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo criarArquivo

    /**
     * Funcao do metodo: Este metodo serve para exibir o menu com todas as opcoes do CRUD.
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
        MyIO.println("0 - Sair");
        MyIO.print("\nOpcao: ");
    }//fim do metodo exibirMenu

    /**
     * Funcao do metodo: Este metodo serve para listar para o usuario todos os jogos disponiveis.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuListarJogo

    /**
     * Funcao do metodo: Este metodo serve para buscar por uma plataforma com o id como chave de pesquisa.
     * @param id Chave de pesquisa id.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuBuscarPlataforma
    
    /**
     * Funcao do metodo: Este metodo serve para buscar uma plataforma passando como chave de pesquisa o id.
     * @param id Chave de pesquisa id.
     * @return Objeto do tipo Plataforma com a plataforma procurada.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static Plataforma buscarPlataforma(int id) throws Exception
    {
        return (Plataforma)arqPlataformas.buscar(id);
    }//fim do metodo buscarPlataforma

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de busca de jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuBuscarJogo

    /**
     * Funcao do metodo: Este metodo serve para buscar um jogo passando como chave de pesquisa o id.
     * @param id Chave de pesquisa id.
     * @return Valor booleano indicando se o jogo procurado foi encontrado.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    public static boolean buscarJogo(int id) throws Exception
    {
        boolean resp=false;
        Jogo jogo=(Jogo)arqJogos.buscar(id);
        if(jogo!=null)
            resp=true;
        return resp;
    }//fim do metodo buscarJogo
    
    /**
     * Funcao do metodo: Este metodo serve para obter um objeto do tipo Jogo do arquivo passando como chave de pesquisa o id.
     * @param id Chave de pesquisa id.
     * @return Objeto do tipo Jogo com o jogo encontrado.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static Jogo getJogo(int id) throws Exception
    {
        return (Jogo)arqJogos.buscar(id);
    }//fim do metodo getJogo

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de inclusao de jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuIncluirJogo

    /**
     * Funcao do metodo: Este metodo serve para inserir um jogo no arquivo de jogos.
     * @param titulo Titulo do jogo.
     * @param score Score do jogo (de acordo com o metacritic).
     * @param nomeDoGenero Nome do genero do jogo.
     * @param nomeDaPlataforma Plataformas do jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo inserirJogo

    /**
     * Funcao do metodo: Este metodo serve para inserir um jogo no arquivo de jogos.
     * @param titulo Titulo do jogo.
     * @param score Score do jogo (de acordo com o metacritic).
     * @param nomeDoGenero Nome do genero do jogo.
     * @param nomeDaPlataforma Nome da plataforma do jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void inserirJogo(String titulo, byte score, String nomeDoGenero, String... nomeDaPlataforma) throws Exception
    {
        ArrayList<String> plataforma = new ArrayList<>(Arrays.asList(nomeDaPlataforma));
        inserirJogo(titulo, score, nomeDoGenero, plataforma);
    }//fim do metodo inserirJogo

    /**
     * metodo que retorna um objeto do tipo genero a partir de uma busca em todos os generos.
     */
    /**
     * Funcao do metodo: Este metodo serve para obter um objeto do tipo Genero passando como chave de pesquisa o nome do genero.
     * @param nomeDoGenero Chave de pesquisa.
     * @return Objeto do tipo Genero encontrado.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo getGenero

    /**
     * Funcao do metodo: Este metodo serve para obter um objeto do tipo Plataforma passando como chave de pesquisa o nome da plataforma.
     * @param nomeDaPlataforma Chave de pesquisa.
     * @return Objeto do tipo Plataforma encontrado.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo getPlataforma

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de exclusao de jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuExcluirJogo
    
    /**
     * Funcao do metodo: Este metodo serve para realizar a exclusao de um jogo do arquivo passando como chave o id.
     * @param id Chave de exclusao.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo excluirJogo

    /**
     * Funcao do metodo: Este metodo serve para exibir o menu de listagem de jogos por plataforma.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuListarJogosPorPlataforma

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de listagem das plataformas.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuListarPlataformas

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de listagem de jogos por genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuListarJogosPorGenero

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de listagem de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuListarGenero

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de insercao de genero de um jogo.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo menuInserirGenerosDeJogo

    /**
     * Funcao do metodo: Este metodo serve para inserir um genero no arquivo de genero.
     * @param nomeDoGenero Nome do genero que sera inserido no arquivo de genero.
     * @return Id do genero inserido.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo inserirGenero

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de listagem de plataformas disponiveis.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void menuListarPlataformasDisponiveis() throws Exception
    {
        MyIO.println("Plataformas disponiveis");
        menuListarPlataformas();
    }//fim do metodo menuListarPlataformasDisponiveis

    /**
     * Funcao do metodo: Este metodo serve para exibir na tela do usuario o menu de insercao de plataforma.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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

    }//fim do metodo menuInserirPlataforma

    /**
     * Funcao do metodo: Este metodo serve para inserir uma plataforma no arquivo de plataformas.
     * @param nomeDaPlataforma Nome da plataforma que sera inserida.
     * @return Id da plataforma inserida.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo inserirPlataforma

    /**
     * Funcao do metodo: Este metodo serve para povoar o banco de dados.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
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
    }//fim do metodo povoarBancoDeDados
}
