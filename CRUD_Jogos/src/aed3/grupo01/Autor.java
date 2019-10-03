package aed3.grupo01;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Autor
{
    private static long proximoId;

    //Atributos
    private long id;
    private String nome;
    private LocalDateTime dataNascimento;
    private ArrayList<String> nacionalidades;
    private ArrayList<Long> jogos;

    //Construtor
    public Autor(long id, String nome, LocalDateTime dataNascimento, ArrayList<String> nacionalidades, ArrayList<Long> jogos)
    {
        this.setId(id);
        this.setNome(nome);
        this.setDataNascimento(dataNascimento);
        this.setNacionalidades(nacionalidades);
        this.setJogos(jogos);
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
        return  this.nome;
    }

    public LocalDateTime getDataNascimento()
    {
        return this.dataNascimento;
    }

    public ArrayList<String> getNacionalidades()
    {
        return this.nacionalidades;
    }

    public ArrayList<Long> getJogos()
    {
        return this.jogos;
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

    public void setDataNascimento(LocalDateTime dataNascimento)
    {
        this.dataNascimento=dataNascimento;
    }

    public void setNacionalidades(ArrayList<String> nacionalidades)
    {
        this.nacionalidades=nacionalidades;
    }

    public void setJogos(ArrayList<Long> jogos)
    {
        this.jogos=jogos;
    }

    //Outros metodos
    public String toString()
    {
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

        resp+=getDataNascimento().toString();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo em resp as nacionalidades
        for(int i=0;i<this.getNacionalidades().size();i++)
        {
            resp+=this.getNacionalidades().get(i);
            resp+="|";
        }

        //Imprimindo em resp o | divisor
        resp+="|";

        //Imprimindo em resp os jogos
        for(int i=0;i<this.getJogos().size();i++)
        {
            resp+=this.getJogos().get(i);
            resp+="|";
        }

        //Imprimindo em resp o cabechalho do fim
        resp+="||";

        return resp;
    }
}
