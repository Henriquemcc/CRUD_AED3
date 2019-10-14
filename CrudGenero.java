package packageone;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CrudGenero
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
                boolean repetir=false;
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

    public static void criaArquivo() throws Exception
    {
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");
    }

    public static void exibirMenu()
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

    public static void menuListarGenero() throws Exception
    {
        criaArquivo();
        if(arqGeneros!=null)
        {
            Object generos[]=arqGeneros.listar();
            if(generos.length!=0)
            {
                for(int i=0;i<generos.length;i++)
                {
                    Genero g=(Genero)generos[i];
                    MyIO.print(g.getID()+" "+g.getTipo()+" ");
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
            Object generos[] = arqGeneros.listar();
            if (generos.length != 0)
            {
                for (int i = 0; i < generos.length; i++)
                {
                    Genero g = (Genero) generos[i];
                    resp=g.getID() + " " + g.getTipo() + " ";
                }
            }
        }
        return resp;
    }

    public static void menuBuscarGenero() throws Exception
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
        Genero genero=(Genero)arqGeneros.buscar(id);
        if(genero!=null)
            MyIO.println(genero.toString());
        else
            MyIO.println("Genero nao encontrado");
    }

    public static void menuIncluirGenero() throws Exception
    {
        MyIO.println("\nINCLUSÃO");
        String tipo=MyIO.readLine("Tipo: ");
        char confirmacao=MyIO.readLine("\nConfirma inclusão? ").toLowerCase().charAt(0);
        if(confirmacao=='s')
            incluirGenero(tipo);

    }

    public static int incluirGenero(String tipo) throws Exception
    {
        Genero genero=new Genero(-1, tipo);
        if(arqGeneros==null)
            criaArquivo();
        return arqGeneros.incluir(genero);
    }

    public static void menuExcluirGenero() throws Exception
    {
        MyIO.println("\nEXCLUSÃO");
        int id=0;
        boolean repetir=false;
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
            boolean repetir1=false;
            char confirmacao=' ';
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
                excluirGenero(id);
        }

    }

    public static void excluirGenero(int id) throws Exception
    {
        arqGeneros.excluir(id);
    }

    public static void menuPovoar() throws Exception
    {
        incluirGenero("FPS");
        incluirGenero("RPG");
        incluirGenero("MOBA");
        incluirGenero("Adventure");
        incluirGenero("Puzzle");
    }

    
}
