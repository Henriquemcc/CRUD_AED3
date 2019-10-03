package aed3.grupo01;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
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


	    Jogo teste=new Jogo(0, "Assassin's Creed III", 12, 1024, 12, generoTmp, plataformas, dia, (float) 12.0);
	    System.out.println(teste.toString());

    }
}
