package aed3.grupo01;
import java.time.LocalDate;
import java.util.ArrayList;
public class Empresa
{
    private static long proximoId;

    //Atributos
    private long id;
    private String nome;
    private LocalDate dataFundacao;
    private ArrayList<Long> jogos;

    //Construtores
    public Empresa(long id, String nome, LocalDate dataFundacao, ArrayList<Long> jogos)
    {

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

    public LocalDate getDataFundacao()
    {
        return this.dataFundacao;
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

    public void setDataFundacao(LocalDate dataFundacao)
    {
        this.dataFundacao=dataFundacao;
    }

    public void setJogos(ArrayList<Long> jogos)
    {
        this.jogos=jogos;
    }

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

        //Imprimindo a data da fundacao
        resp+=this.getDataFundacao().toString();

        //Imprimindo em resp o | divisor
        resp+="||";

        //Imprimindo os jogos
        for(int i=0;i<this.getJogos().size();i++)
        {
            resp+=this.getJogos().get(i);
            resp+="|";
        }

        //Imprimindo o cabechalho do fim
        resp+="||";

        return resp;
    }


}
