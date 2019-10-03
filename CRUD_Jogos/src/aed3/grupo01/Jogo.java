package aed3.grupo01;
import java.time.LocalDate;
import java.util.ArrayList;

public class Jogo
{
    private static long proximoId;

    //Atributos
    private long id;
    private String nome;
    private long diretor;
    private long desenvolvedor;
    private long publisher;
    private ArrayList<Long> genero;
    private ArrayList<Byte> plataformasDisponiveis;
    private LocalDate dataLancamento;
    private float preco;

    //Construtor
    public Jogo(long id, String nome, long diretor, long desenvolvedor, long publisher, ArrayList<Long> genero, ArrayList<Byte> plataformasDisponiveis, LocalDate dataLancamento, float preco)
    {
        this.setId(id);
        this.setNome(nome);
        this.setDiretor(diretor);
        this.setDesenvolvedor(desenvolvedor);
        this.setPublisher(publisher);
        this.setGenero(genero);
        this.setPlataformasDisponiveis(plataformasDisponiveis);
        this.setDataLancamento(dataLancamento);
        this.setPreco(preco);
    }

    //Metodos Gets

    public static long getProximoId()
    {
        return proximoId;
    }

    public long getId()
    {
        return this.id;
    }

    public String getNome()
    {
        return this.nome;
    }

    public long getDiretor()
    {
        return this.diretor;
    }

    public long getDesenvolvedor()
    {
        return this.desenvolvedor;
    }

    public long getPublisher()
    {
        return this.publisher;
    }

    public ArrayList<Long> getGenero()
    {
        return this.genero;
    }

    public ArrayList<Byte> getPlataformasDisponiveis()
    {
        return  this.plataformasDisponiveis;
    }

    public LocalDate getDataLancamento()
    {
        return this.dataLancamento;
    }

    public float getPreco()
    {
        return this.preco;
    }

    //Metodos Sets
    public static void setProximoId(long proximoId1)
    {
        proximoId=proximoId1;
    }

    public void setId(long id)
    {
        this.id=id;
    }

    public void setNome(String nome)
    {
        this.nome=nome.toUpperCase();
    }

    public void setDiretor(long diretor)
    {
        this.diretor=diretor;
    }

    public void setDesenvolvedor(long desenvolvedor)
    {
        this.desenvolvedor=desenvolvedor;
    }

    public void setPublisher(long publisher)
    {
        this.publisher=publisher;
    }

    public void setGenero(ArrayList<Long> genero)
    {
        this.genero=genero;
    }

    public void setPlataformasDisponiveis(ArrayList<Byte> plataformasDisponiveis)
    {
        this.plataformasDisponiveis=plataformasDisponiveis;
    }

    public void setDataLancamento(LocalDate dataLancamento)
    {
        this.dataLancamento=dataLancamento;
    }

    public void setPreco(float preco)
    {
        this.preco=preco;
    }

    //Outros metodos
    public String toString()
    {
        //Criando a variavel resp
        String resp="";

        //Imprimindo em resp o cabechalho
        resp+="|||";

        //Imprimindo em resp o id
        resp+=this.getId();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp o nome
        resp+=this.getNome();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp os diretores
        resp+=this.getDiretor();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp o desenvolvedor
        resp+=this.getDesenvolvedor();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp o publisher
        resp+=this.getPublisher();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp os generos
        for(int i=0;i<this.getGenero().size();i++)
        {
            resp+=this.getGenero().get(i);
            resp+="|";
        }

        //Imprimindo em resp o | divisor
        resp+="|";

        //Imprimindo em resp as plataformas
        for(int i=0;i<this.getPlataformasDisponiveis().size();i++)
        {
            resp+=this.getPlataformasDisponiveis().get(i);
            resp+="|";
        }

        //Imprimindo em resp o | divisor
        resp+="|";

        //Imprimindo a data
        resp+=this.getDataLancamento().toString();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp o preco
        resp+=this.getPreco();

        //Imprimindo o cabechalho do fim
        resp+="|||";

        return  resp;
    }
}
