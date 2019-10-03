package aed3.grupo01;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<Long> testeArray=new ArrayList<>();
        testeArray.add(Long.valueOf(12));
        testeArray.add(Long.valueOf(14));
        testeArray.add(Long.valueOf(17));
        testeArray.add(Long.valueOf(19));
        testeArray.add(Long.valueOf(200));
        testeArray.add(Long.valueOf(1024));
        testeArray.add(Long.valueOf(400));

        ArrayList<Byte> testeArray2=new ArrayList<>();
        testeArray2.add(Byte.valueOf((byte)12));
        testeArray2.add(Byte.valueOf((byte)2));
        testeArray2.add(Byte.valueOf((byte)15));
        testeArray2.add(Byte.valueOf((byte)13));
        testeArray2.add(Byte.valueOf((byte)14));
        testeArray2.add(Byte.valueOf((byte)15));
        testeArray2.add(Byte.valueOf((byte)16));
        testeArray2.add(Byte.valueOf((byte)18));
        testeArray2.add(Byte.valueOf((byte)19));
        testeArray2.add(Byte.valueOf((byte)20));
        LocalDate dia=LocalDate.of(1,1,1);

        ArrayList<String> testeArray3=new ArrayList<>();
        testeArray3.add("OWFIHFOIEH");
        testeArray3.add("OWFIHFOIEHDOFSO");
        testeArray3.add("OWFIHFOIEHSVDJSVJSDVIO");
        testeArray3.add("OWFIHFOIEHSVVDISJOOIJVIO");

	    Jogo jogo=new Jogo(0, "Assassin's Creed III", 12, 1024, 12, testeArray, testeArray2, dia, (float) 12.0);
	    System.out.println(jogo.toString());

	    Autor autor=new Autor(12, "João da Silva", dia, testeArray3, testeArray);
	    System.out.println(autor.toString());

	    Empresa empresa=new Empresa(0,"Ubisoft", dia, testeArray);
	    System.out.println(empresa);

	    Genero genero=new Genero(0,"Tiro",testeArray);
	    System.out.println(genero.toString());

	    Plataformas plataforma=new Plataformas();

    }
}
