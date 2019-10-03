package aed3.grupo01;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        ArrayList<Long> desenvolvedorTmp=new ArrayList<>();
        desenvolvedorTmp.add(Long.valueOf(100));
        desenvolvedorTmp.add(Long.valueOf(101));
        desenvolvedorTmp.add(Long.valueOf(102));
        desenvolvedorTmp.add(Long.valueOf(1020));
        ArrayList<Long> generoTmp=new ArrayList<>();
        generoTmp.add(Long.valueOf(12));
        generoTmp.add(Long.valueOf(14));
        generoTmp.add(Long.valueOf(17));
        generoTmp.add(Long.valueOf(19));
        generoTmp.add(Long.valueOf(200));
        generoTmp.add(Long.valueOf(1024));
        generoTmp.add(Long.valueOf(400));
        ArrayList<Byte> plataformas=new ArrayList<>();
        plataformas.add(Byte.valueOf((byte)12));
        plataformas.add(Byte.valueOf((byte)30));
        plataformas.add(Byte.valueOf((byte)100));
        plataformas.add(Byte.valueOf((byte)200));
        plataformas.add(Byte.valueOf((byte)400));
        plataformas.add(Byte.valueOf((byte)600));
        LocalDateTime dia=LocalDateTime.of(1, 1, 1, 1, 1, 1);


	    Jogo teste=new Jogo(0, "Assassin's Creed III", 12, desenvolvedorTmp, 12, generoTmp, plataformas, dia, (float) 12.0);
	    System.out.println(teste.toString());

    }
}
