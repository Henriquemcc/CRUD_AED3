import java.text.DecimalFormat;
import java.util.InputMismatchException;

class CrudGenero extends Crud
{
    /**
     * Funcao do metodo: Este metodo serve para listar para o usuario todos os generos disponiveis.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    public static void menuListarGenero() throws Exception
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
    public static void menuBuscarGenero() throws Exception
    {
        criarArquivo();
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
    public static void menuIncluirGenero() throws Exception
    {
        criarArquivo();
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
        criarArquivo();
        Genero genero=new Genero(-1, tipo);
        if(arqGeneros==null)
            criarArquivo();
        arqGeneros.incluir(genero);
    }//fim do metodo incluirGenero

    /**
     * Funcao do metodo: Este metodo serve para exbir o menu de exclusao de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    public static void menuExcluirGenero() throws Exception
    {
        criarArquivo();
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
        criarArquivo();
        arqGeneros.excluir(id);
    }//fim do metodo excluirGenero


    /**
     * Funcao do metodo: Este metodo serve para povoar o banco de dados de genero.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    public static void povoarBancoDeDados() throws Exception
    {
        criarArquivo();
        incluirGenero("FPS");
        incluirGenero("RPG");
        incluirGenero("MOBA");
        incluirGenero("Adventure");
        incluirGenero("Puzzle");
    }//fim do metodo povoarBancoDeDados    
}
