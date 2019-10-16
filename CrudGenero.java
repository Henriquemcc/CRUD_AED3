import java.text.DecimalFormat;
import java.util.InputMismatchException;

class CrudGenero
{
    private static ArquivoIndexado<Genero> arqGeneros;

    /**
     * Funcao do metodo: Este metodo serve para executar o CrudGenero.
     * @param args Argumentos padrao do main.
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
                        if(comando<0 || comando>9)
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
                    menuListarGenero();
                else if(comando==2)
                    menuBuscarGenero();
                else if(comando==3)
                    menuIncluirGenero();
                else if(comando==4)
                    menuExcluirGenero();
                else if(comando==9)
                    povoarBancoDeDados();
                else
                    MyIO.println("Opcao Invalida!");

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
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void criarArquivo() throws Exception
    {
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
    }//fim do metodo criarArquivo

    /**
     * Funcao do metodo: Este metodo serve para exibir o menu com todas as opcoes do CRUD.
     */
    private static void exibirMenu()
    {
        MyIO.println("\n\n------------------------------------------------");
        MyIO.println("                    MENU");
        MyIO.println("------------------------------------------------");
        MyIO.println("1 - Listar generos");
        MyIO.println("2 - Buscar generos");
        MyIO.println("3 - Incluir generos");
        MyIO.println("4 - Excluir generos");
        MyIO.println("9 - Povoar BD");
        MyIO.println("0 - Sair");
        MyIO.print("\nOpcao: ");
    }//fim do metodo exibirMenu

    /**
     * Funcao do metodo: Este metodo serve para listar para o usuario todos os generos disponiveis.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void menuListarGenero() throws Exception
    {
        criarArquivo();
        if(arqGeneros!=null)
        {
            Object[] generos =arqGeneros.listar();
            if(generos.length!=0)
            {
                for (Object genero : generos) {
                    Genero g = (Genero) genero;
                    MyIO.print(g.getID() + " " + g.getTipo() + " ");
                }
                MyIO.println();
            }
            else
                MyIO.println("Nenhum genero registrado");
        }
        else
            MyIO.println("Nenhum genero registrado");
    }//fim do metodo menuListarGenero

    /**
     * Funcao do metodo: Este metodo serve para exibir o menu de busca de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void menuBuscarGenero() throws Exception
    {
        MyIO.println("\nBUSCA");
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
        Genero genero=(Genero)arqGeneros.buscar(id);
        if(genero!=null)
            MyIO.println(genero.toString());
        else
            MyIO.println("Genero nao encontrado");
    }//fim do metodo menuBuscarGenero

    /**
     * Funcao do metodo: Este metodo serve para exibir o menu de inclusao de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void menuIncluirGenero() throws Exception
    {
        MyIO.println("\nINCLUSAO");
        String tipo=MyIO.readLine("Tipo: ");
        char confirmacao=MyIO.readLine("\nConfirma inclusao? ").toLowerCase().charAt(0);
        if(confirmacao=='s')
            incluirGenero(tipo);

    }//fim do metodo menuIncluirGenero

    /**
     * Funcao do metodo: Este metodo serve para realizar a inclusao do genero.
     * @param tipo String do tipo (nome) do genero a ser incluido.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void incluirGenero(String tipo) throws Exception
    {
        Genero genero=new Genero(-1, tipo);
        if(arqGeneros==null)
            criarArquivo();
        arqGeneros.incluir(genero);
    }//fim do metodo incluirGenero

    /**
     * Funcao do metodo: Este metodo serve para exbir o menu de exclusao de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void menuExcluirGenero() throws Exception
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
                    throw new InputMismatchException("Entrada invalida!");
                repetir=false;
            }
            catch(InputMismatchException e)
            {
                MyIO.println(e.toString());
                repetir=true;
            }
        }
        while(repetir);

        Genero genero=(Genero)arqGeneros.buscar(id);
        if(genero!=null)
        {
            MyIO.println(genero.toString());
            boolean repetir1;
            char confirmacao=' ';
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
                excluirGenero(id);
        }

    }//fim do metodo menuExcluirGenero

    /**
     * Funcao do metodo: Este metodo serve para realizar a exclusao de um genero.
     * @param id Chave de exclusao.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void excluirGenero(int id) throws Exception
    {
        arqGeneros.excluir(id);
    }//fim do metodo excluirGenero


    /**
     * Funcao do metodo: Este metodo serve para povoar o banco de dados de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    private static void povoarBancoDeDados() throws Exception
    {
        incluirGenero("FPS");
        incluirGenero("RPG");
        incluirGenero("MOBA");
        incluirGenero("Adventure");
        incluirGenero("Puzzle");
    }//fim do metodo povoarBancoDeDados    
}
