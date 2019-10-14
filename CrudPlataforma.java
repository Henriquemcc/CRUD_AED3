package packageone;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CrudPlataforma
{
    private static ArquivoIndexado<Plataforma> arqPlataformas;
    private static DecimalFormat df;

    public static void main(String[]args)
    {
        int comando=-1;
        do
        {
            try
            {
                criaArquivo();
                boolean repetir=false;
                do
                {
                    try
                    {
                        exibirMenu();
                        comando=MyIO.readInt();
                        if(comando<0 || comando>5)
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
                    menuListarPlataformas();
                else if(comando==2)
                    menuBuscarPlataformas();
                else if(comando==3)
                    menuIncluirPlataformas();
                else if(comando==4)
                    menuExcluirPlataformas();
                else if(comando==5)
                    menuPovoarBancoDeDados();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        while(comando!=0);
    }

    public static void criaArquivo()throws Exception
    {
        arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");
    }

    public static void exibirMenu()
    {
        MyIO.println("\n\n------------------------------------------------");
        MyIO.println("                    MENU");
        MyIO.println("------------------------------------------------");
        MyIO.println("1 - Listar plataformas");
        MyIO.println("2 - Buscar plataformas");
        MyIO.println("3 - Incluir plataformas");
        MyIO.println("4 - Excluir plataformas");
        MyIO.println("9 - Povoar BD");
        MyIO.println("0 - Sair");
        MyIO.print("\nOpcao: ");
    }

    public static void menuListarPlataformas() throws Exception
    {
        criaArquivo();
        if(arqPlataformas!=null)
        {
            Object plataformas[]=arqPlataformas.listar();
            if(plataformas.length>0)
            {
                for(int i=0;i<plataformas.length;i++)
                {
                    Plataforma plataforma=(Plataforma)plataformas[i];
                    MyIO.print(plataforma.getID()+" "+plataforma.getNome()+" ");
                }
                MyIO.println();
            }
            else
                MyIO.println("Nenhuma plataforma registrada");
        }
        else
            MyIO.println("Nenhuma plataforma registrada");
    }

    public static String listar() throws Exception
    {
        String resp="";
        criaArquivo();
        if(arqPlataformas!=null)
        {
            Object plataformas[]=arqPlataformas.listar();
            if(plataformas.length!=0)
            {
                for(int i=0;i<plataformas.length;i++)
                {
                    Plataforma p=(Plataforma)plataformas[i];
                    resp=p.getID()+" "+p.getNome()+" ";
                }
            }
        }
        return resp;
    }

    public static void menuBuscarPlataformas() throws Exception
    {
        MyIO.println("\nBUSCA");
        int id=0;
        boolean repetir=false;
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
        Plataforma plataforma=(Plataforma)arqPlataformas.buscar(id);
        if(plataforma!=null)
            MyIO.println(plataforma.toString());
        else
            MyIO.println("Plataforma nao encontrada");
    }

    public static void menuIncluirPlataformas() throws Exception
    {
        MyIO.println("\nINCLUSÃO");
        String nome=MyIO.readLine("Nome: ");
        char confirmacao=' ';
        boolean repetir=false;
        do
        {
            try
            {
                confirmacao=MyIO.readLine("\nConfirma inclusão? ").toLowerCase().charAt(0);
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

        MyIO.println("Plataforma incluída com ID: "+incluirPlataforma(nome));
    }

    public static int incluirPlataforma(String nome) throws Exception
    {
        if(arqPlataformas==null)
            criaArquivo();

        Plataforma plataforma=new Plataforma(-1, nome);

        return arqPlataformas.incluir(plataforma);
    }

    public static void menuExcluirPlataformas() throws Exception
    {
        criaArquivo();
        MyIO.println("\nEXCLUSÃO");
        int id=0;
        boolean repetir=false;
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

        Plataforma plataforma=(Plataforma)arqPlataformas.buscar(id);
        if(plataforma!=null)
        {
            MyIO.println(plataforma.toString());
            char confirmacao=' ';
            boolean repetir1;
            do
            {
                try
                {
                    confirmacao=MyIO.readLine("\nConfirma exclusão? ").toLowerCase().charAt(0);
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
                excluirPlataforma(id);
        }
    }

    public static void excluirPlataforma(int id) throws Exception
    {
        arqPlataformas.excluir(id);
    }

    public static void menuPovoarBancoDeDados() throws Exception
    {
        incluirPlataforma("PlayStation 1");
        incluirPlataforma("PlayStation 2");
        incluirPlataforma("PlayStation 3");
        incluirPlataforma("PlayStation 4");
        incluirPlataforma("Xbox 360");
        incluirPlataforma("Xbox One");
        incluirPlataforma("Nintendo Wii");
        incluirPlataforma("Nintendo WiiU");
        incluirPlataforma("Nintendo Switch");
        incluirPlataforma("Windows");
        incluirPlataforma("Mac OS");
        incluirPlataforma("Linux");
        incluirPlataforma("Android");
        incluirPlataforma("iOS");
        incluirPlataforma("Windows Phone");
    }

    public static void buscarPlataforma(int id) throws Exception//tmp
    {
        criaArquivo();
        if(id <=0)
            return;
        Plataforma l;
        if( (l = (Plataforma)arqPlataformas.buscar(id))!=null )
            MyIO.println("Plataforma: "+l.getNome());
        else
            MyIO.println("Plataforma não encontrada");
    }
}
