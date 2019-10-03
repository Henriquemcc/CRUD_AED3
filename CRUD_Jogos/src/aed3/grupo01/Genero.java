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
}
