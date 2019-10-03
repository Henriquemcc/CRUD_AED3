package aed3.grupo01;

import java.util.ArrayList;

public class Genero
{
    private static long proximoId;

    //Atributos
    private long id;
    private String nome;
    private ArrayList<Long> jogos;

    //Construtor
    public Genero(long id, String nome, ArrayList<Long> jogos)
    {
        this.setId(id);
        this.setNome(nome);
        this.setJogos(jogos);
    }

    //Metodos Gets
    public static long getProximoId()
    {
        return proximoId;
    }

    public long getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome;
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

        //Imprimindo os jogos
        for(int i=0;i<this.getJogos().size();i++)
        {
            resp+=getJogos().get(i);
            resp+="|";
        }

        //Imprimindo em resp o cabechalho do fim
        resp+="||";

        return resp;

    }
}
