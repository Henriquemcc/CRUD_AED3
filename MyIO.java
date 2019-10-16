import java.io.*;
import java.nio.charset.*;
import java.util.InputMismatchException;
import java.util.Objects;

class MyIO 
{

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
   private static String charset = "ISO-8859-1";

   /**
    * Funcao do metodo: Este metodo serve para altrar o charset da classe MyIO.
    * @param charset_ Novo charset.
    */
   public static void setCharset(String charset_)
   {
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }//fim do metodo setCharset

   /**
    * Funcao do metodo: Este metodo serve para imprimir um texto na tela do usuario.
    */
   public static void print()
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print("");
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero na tela do usuario.
    * @param x Numero inteiro que sera impresso.
    */
   public static void print(int x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero real na tela do usuario.
    * @param x Numero real que sera impresso na tela do usuario.
   */
   public static void print(double x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }

   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir uma String na tela do usuario.
    * @param x String que sera impressa na tela do usuario.
    */
   public static void print(String x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir um valor booleano na tela do usuario.
    * @param x Valor booleano que sera impresso na tela do usuario.
    */
   public static void print(boolean x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir um caractere na tela do usuario.
    * @param x Caractere que sera impresso na tela do usuario.
    */
   public static void print(char x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo print

   /**
    * Funcao do metodo: Este metodo serve para imprimir uma quebra de linha na tela do usuario.
    */
   public static void println()
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println();
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero inteiro mais uma quebra de linha na tela do usuario.
    * @param x Numero inteiro que sera impresso na tela do usuario.
    */
   public static void println(int x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero inteiro (do tipo byte) mais uma quebra de linha na tela do usuario.
    * @param x Numero inteiro (de tipo byte) que sera impresso na tela do usuario.
    */
   public static void println(byte x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero inteiro (do tipo short) mais uma quebra de linha na tela do usuario.
    * @param x Numero inteiro (de tipo short) que sera impresso na tela do usuario.
    */
   public static void println(short x)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um numero real (de tipo double) na tela do usuario mais uma quebra de linha na tela do usuario.
    * @param x Numero real (de tipo double) que sera impresso na tela do usuario.
    */
   public static void println(double x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir uma String mais uma quebra de linha na tela do usuario.
    * @param x String que sera impressa na tela do usuario.
    */
   public static void println(String x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um valor booleano mais uma quebra de linha na tela do usuario.
    * @param x Valor booleano que sera impresso na tela do usuario.
    */
   public static void println(boolean x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir um caractere mais uma quebra de linha na tela do usuario.
    * @param x Caractere que sera impresso na tela do usuario.
    */
   public static void println(char x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo println

   /**
    * Funcao do metodo: Este metodo serve para imprimir uma string formatada na tela do usuario.
    * @param formato Formato do que sera impresso.
    * @param x Numero real de argumento.
    */
   public static void printf(String formato, double x)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim printf

   /**
    * Funcao do metodo: Este metodo serve para imprimir uma string formatada na tela do usuario.
    * @param formato Formato do que sera imprsso.
    * @param args Argumentos da String formatada.
    */
   public static void printf(String formato, Object ... args)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, args);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo printf

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero real (do tipo double).
    * @return Numero real (do tipo double) lido.
    */
   public static double readDouble()
   {
      boolean repetir=false;
      double d = -1;
      do
      {
         try
         {
            d = Double.parseDouble(readString().trim().replace(",","."));
            repetir=false;
         }
         catch(InputMismatchException e)
         {
            System.out.println("Erro: O valor digitado eh incompativel com o tipo de dado desejado pelo programa: "+e);
            repetir=true;
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);

      return d;
   }//fim do metodo readDouble

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero real (do tipo double).
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return Numero real (do tipo double) lido.
    */
   public static double readDouble(String str)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return readDouble();
   }//fim do metodo readDouble

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero real (do tipo float).
    * @return Numero real (do tipo float) lido.
    */
   public static float readFloat()
   {
      return (float) readDouble();
   }//fim do metodo readFloat

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero real (do tipo float).
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return Numero real (do tipo float) que foi lido.
    */
   public static float readFloat(String str)
   {
      return (float) readDouble(str);
   }//fim do metodo readFloat

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro.
    * @return Numero inteiro que foi lido.
    */
   public static int readInt()
   {
      boolean repetir=false;
      int i = -1;
      do
      {
         try
         {
            i = Integer.parseInt(readString().trim());
            repetir=false;
         }
         catch(InputMismatchException e)
         {
            System.out.println("Erro: O valor digitado eh incompativel com o tipo de dado desejado pelo programa: "+e);
            repetir=true;
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);
      return i;
   }//fim do metodo readInt

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro (do tipo byte).
    * @return Numero inteiro (do tipo byte) que foi lido.
    */
   public static byte readByte()
   {
      boolean repetir=false;
      byte i=-1;
      do
      {
         try
         {
            i=Byte.parseByte(readString().trim());
            repetir=false;
         }
         catch(InputMismatchException e)
         {
            System.out.println("Erro: O valor digitado eh incompativel com o tipo de dado desejado pelo programa: "+e);
            repetir=true;
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);

      return i;
   }//fim do metodo readByte

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro (do tipo short).
    * @return Numero inteiro (do tipo short) que foi lido.
    */
   public static short readShort()
   {
      boolean repetir=false;
      short i=-1;
      do
      {
         try
         {
            i=Short.parseShort(readString().trim());
            repetir=false;
         }
         catch(InputMismatchException e)
         {
            System.out.println("Erro: O valor digitado eh incompativel com o tipo de dado desejado pelo programa: "+e);
            repetir=true;
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);

      return i;
   }//fim do metodo readShort

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro. 
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return Numero inteiro que foi lido.
    */
   public static int readInt(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readInt();
   }

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro (do tipo byte).
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return Numero inteiro (do tipo byte) que foi lido.
    */
   public static byte readByte(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readByte();
   }

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um numero inteiro (do tipo short).
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return Numero inteiro (do tipo short) que foi lido.
    */
   public static short readShort(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readShort();
   }//fim do metodo readShort

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) uma String.
    * @return String que foi lida.
    */
   public static String readString()
   {
      StringBuilder s = new StringBuilder();
      char tmp;
      try
      {
         do
         {
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13)
            {
               s.append(tmp);
            }
         }
         while(tmp != '\n' && tmp != ' ');
      }
      catch(IOException ioe)
      {
         System.out.println("lerPalavra: " + ioe);
      }
      return s.toString();
   }//fim do metodo readString

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) uma String.
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return String que foi lida.
    */
   public static String readString(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readString();
   }//fim do metodo readString

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) uma String de uma linha.
    * @return String de uma linha que foi lida.
    */
   public static String readLine()
   {
      StringBuilder s = new StringBuilder();
      char tmp;
      try
      {
         do
         {
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13)
            {
               s.append(tmp);
            }
         }
         while(tmp != '\n');
      }
      catch(IOException ioe)
      {
         System.out.println("lerPalavra: " + ioe);
      }
      return s.toString();
   }//fim do metodo readLine

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) uma String de uma linha.
    * @param str String que sera impressa na tela do usuario antes de efetuar a leitura.
    * @return String de uma linha que foi lida.
    */
   public static String readLine(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readLine();
   }//fim do metodo readLine

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um caractere.
    * @return Caractere que foi lido.
    */
   public static char readChar()
   {
      boolean repetir=false;
      char resp = ' ';
      do
      {
         try
         {
            resp  = (char)in.read();
            repetir=false;
         }

         catch(InputMismatchException e)
         {
            System.out.println("Erro: O valor digitado eh incompativel com o tipo de dado desejado pelo programa: "+e);
            repetir=true;
         }

         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);

      return resp;
   }//fim do metodo readChar

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um caractere.
    * @param str String que sera impressa na tela do usuario antes de realizar a leitura.
    * @return Caractere que foi lido.
    */
   public static char readChar(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readChar();
   }//fim do metodo readChar

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um valor booleano.
    * @return Valor booleano lido.
    */
   public static boolean readBoolean()
   {
      boolean resp = false;
      boolean repetir=false;
      String str = "";

      do
      {
         try
         {
            str = readString().toLowerCase();
            if(!Objects.equals(str, "true") && !Objects.equals(str, "false") && !Objects.equals(str, "t") && !Objects.equals(str, "f") && !Objects.equals(str, "1") && !Objects.equals(str, "0") && !Objects.equals(str, "verdadeiro") && !Objects.equals(str, "falso"))
               throw new InputMismatchException("O valor digitado nao eh booleano!");
            repetir=false;
         }
         catch(InputMismatchException e)
         {
            e.printStackTrace();
            repetir=true;
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      while(repetir);

      if(str.equals("true") || str.equals("t") || str.equals("1") || str.equals("verdadeiro") || str.equals("v"))
      {
         resp = true;
      }

      return resp;
   }//fim do metodo readBoolean

   /**
    * Funcao do metodo: Este metodo serve para ler da entrada padrao (teclado) um valor booleano.
    * @param str String que sera impressa na tela do usuario antes de realizar a leitura.
    * @return Valor booleano lido.
    */
   public static boolean readBoolean(String str)
   {
      try
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      return readBoolean();
   }//fim do metodo readBoolean

   /**
    * Funcao do metodo: Este metodo serve para pausar a execucao do programa ate que o usuario digite alguma coisa.
    */
   public static void pause()
   {
      try
      {
         in.read();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }//fim do metodo pause

   /**
    * Funcao do metodo: Este metodo serve para pausar a execucao do programa ate que o usuario digite alguma coisa.
    * @param str String que sera impressa na tela do usuario antes de esperar a entrada dele.
    */
   public static void pause(String str)
   {
      try 
      {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }
      catch(UnsupportedEncodingException e)
      {
         System.out.println("Erro: charset invalido: "+e);
      }
      pause();
   }//fim do metodo pause
}