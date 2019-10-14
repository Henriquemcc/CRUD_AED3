package packageone;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CrudJogos
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
                boolean repetir=false;
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
                /*else if(comando==4)
                    menuExcluirJogo();
                else if(comando==5)
                    menuListarJogosPorPlataforma();
                else if(comando==6)
                    menuListarJogosPorGenero();
                else if(comando==7)
                    menuListarGenerosDeJogosDisponiveis();
                else if(comando==8)
                    menuInserirGeneroDeJogo();
                else if(comando==9)
                    menuListarPlataformasDisponiveis();
                else if(comando==10)
                    menuInserirPlataforma();
                else if(comando==11)
                    menuPovoarBancoDeDados();*/
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        while(comando!=0);
    }

    public static void criarArquivo() throws Exception
    {
        arqJogos = new ArquivoIndexado<>(Jogo.class.getConstructor(), "jogos.db");//Arquivo dos jogos
        arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");//Arquivo dos generos dos jogos
        arqJogosDaPlataforma = new ArquivoIndexado<>(JogosDaPlataforma.class.getConstructor(), "jogosdaplataforma.db");
        arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");//Arquivo da plataforma de jogos
        idxJogoporGenero= new ArvoreBMais(5,"jogoporgenero.idx");//Indice de jogos por generos
        idxJogoparaJdp = new ArvoreBMais(5,"jogoparajdp.idx");//Indice de Jogos por Jogos da Plataforma
        idxPlataformaparaJdp = new ArvoreBMais(5,"plataformaparajdp.idx");//Indice de plataforma para Jogos da Plataforma
    }

    public static void exibirMenu()
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
    }

    public static void menuListarJogo() throws Exception
    {
        Object[] jogos=arqJogos.listar();
        for(int i=0;i<jogos.length;i++)
        {
            Jogo jogo=(Jogo)jogos[i];
            MyIO.println(jogo.toString());
            Genero genero=(Genero)arqGeneros.buscar(jogo.getIdGenero());
            MyIO.println("Genero: "+genero.getTipo());
            int idsDosJogosDasPlataformas[]=idxJogoparaJdp.lista(jogo.getID());
            for(int j=0;j<idsDosJogosDasPlataformas.length;j++)
            {
                JogosDaPlataforma jogosDaPlataforma=(JogosDaPlataforma)arqJogosDaPlataforma.buscar(idsDosJogosDasPlataformas[j]);
                menuBuscarPlataforma(jogosDaPlataforma.getIdPlataforma());
            }
        }
    }

    public static void menuBuscarPlataforma(int id) throws Exception
    {
        if(id>0)
        {
            Plataforma plataforma=buscarPlataforma(id);
            if(plataforma!=null)
                MyIO.println("Plataforma: "+plataforma.getNome());
            else
                MyIO.println("Plataforma não encontrada");
        }
    }

    public static Plataforma buscarPlataforma(int id) throws Exception
    {
        return (Plataforma)arqPlataformas.buscar(id);
    }

    public static void menuBuscarJogo() throws Exception
    {
        MyIO.println("\nBUSCA");
        boolean repetir=false;
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
            int idsDosJogosDaPlataforma[]=idxJogoparaJdp.lista(jogo.getID());
            for(int i=0;i<idsDosJogosDaPlataforma.length;i++)
            {
                JogosDaPlataforma jogosDaPlataforma=(JogosDaPlataforma)arqJogosDaPlataforma.buscar(idsDosJogosDaPlataforma[i]);
                menuBuscarPlataforma(jogosDaPlataforma.getIdPlataforma());
            }
        }
    }

    public static boolean buscarJogo(int id) throws Exception
    {
        boolean resp=false;
        Jogo jogo=(Jogo)arqJogos.buscar(id);
        if(jogo!=null)
            resp=true;
        return resp;
    }

    public static Jogo getJogo(int id) throws Exception
    {
        return (Jogo)arqJogos.buscar(id);
    }

    public static void menuIncluirJogo() throws Exception
    {
        String titulo="";
        byte score=-1;
        String genero="";

        MyIO.println("\nINCLUSÃO");
        titulo=MyIO.readLine("Título: ");

        boolean repetir=false;
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
            boolean repetir1=false;
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

        incluirJogo(titulo, score, genero, plataformas);
    }

    public static void incluirJogo(String titulo, byte score, String nomeDoGenero, ArrayList<String> nomeDaPlataforma) throws  Exception
    {
        Genero genero=getGenero(nomeDoGenero);
        if(genero==null)
        {
            CrudGenero.incluirGenero(nomeDoGenero);
            genero = getGenero(nomeDoGenero);
        }
        int idGenero=genero.getID();

        ArrayList<Integer> idPlataformas=new ArrayList<>();

        for(int i=0;i<nomeDaPlataforma.size();i++)
        {
            Plataforma plataforma=getPlataforma(nomeDaPlataforma.get(i));
            if(plataforma==null)
                CrudPlataforma.incluirPlataforma(nomeDaPlataforma.get(i));
            idPlataformas.add(getPlataforma(nomeDaPlataforma.get(i)).getID());
        }

        Jogo jogo=new Jogo(-1, idGenero, titulo, score);
        int idJogo=arqJogos.incluir(jogo);
        idxJogoporGenero.inserir(idGenero, idJogo);
        for(int i=0;i<idPlataformas.size();i++)
        {
            JogosDaPlataforma jogosDaPlataforma=new JogosDaPlataforma(-1, idPlataformas.get(i), idJogo);
            int idJogosDaPlataforma=arqJogosDaPlataforma.incluir(jogosDaPlataforma);
            idxJogoparaJdp.inserir(idJogo, idJogosDaPlataforma);
            idxPlataformaparaJdp.inserir(idPlataformas.get(i), idJogosDaPlataforma);
        }
    }

    public static Genero getGenero(String nomeDoGenero) throws Exception
    {
        Genero resp=null;
        ArrayList<Genero> genero=new ArrayList<>();
        Object objeto[]=arqGeneros.listar();
        for(int i=0;i<objeto.length;i++)
        {
            Genero tmp=(Genero)objeto[i];
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

    public static boolean ehNumero(char c)
    {
        boolean ehNumero=false;
        if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')
            ehNumero=true;
        return ehNumero;
    }//fim do metodo ehNumero

    public static Plataforma getPlataforma(String nomeDaPlataforma) throws Exception
    {

        Plataforma resp=null;
        ArrayList<Plataforma> plataforma=new ArrayList<>();
        Object objeto[]=arqPlataformas.listar();
        for(int i=0;i<objeto.length;i++)
        {
            Plataforma tmp=(Plataforma)objeto[i];
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
}
