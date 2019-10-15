import java.text.DecimalFormat;
import java.util.InputMismatchException;

class CrudGenero
{
    private static ArquivoIndexado<Genero> arqGeneros;
    private static DecimalFormat df;

    public static void main(String[]args)
    {
        int comando=-1;
        do
        {
            try
            {
                criaArquivo();
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
                    menuPovoar();
                else
                    MyIO.println("Opcao Invalida!");

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        while(comando!=0);
    }

    private static void criaArquivo() throws Exception
    {
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
    }

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
    }

    private static void menuListarGenero() throws Exception
    {
        criaArquivo();
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
    }

    public static String listar() throws Exception
    {
        String resp="";
        criaArquivo();
        if(arqGeneros!=null)
        {
            Object[] generos = arqGeneros.listar();
            if (generos.length != 0)
            {
                for (Object genero : generos) {
                    Genero g = (Genero) genero;
                    resp = g.getID() + " " + g.getTipo() + " ";
                }
            }
        }
        return resp;
    }

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
    }

    private static void menuIncluirGenero() throws Exception
    {
        MyIO.println("\nINCLUSAO");
        String tipo=MyIO.readLine("Tipo: ");
        char confirmacao=MyIO.readLine("\nConfirma inclusao? ").toLowerCase().charAt(0);
        if(confirmacao=='s')
            incluirGenero(tipo);

    }

    private static void incluirGenero(String tipo) throws Exception
    {
        Genero genero=new Genero(-1, tipo);
        if(arqGeneros==null)
            criaArquivo();
        arqGeneros.incluir(genero);
    }

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

    }

    private static void excluirGenero(int id) throws Exception
    {
        arqGeneros.excluir(id);
    }

    private static void menuPovoar() throws Exception
    {
        incluirGenero("FPS");
        incluirGenero("RPG");
        incluirGenero("MOBA");
        incluirGenero("Adventure");
        incluirGenero("Puzzle");
    }

    
}
