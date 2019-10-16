import java.io.*;
import java.nio.charset.*;
import java.util.InputMismatchException;
import java.util.Objects;

class MyIO 
{

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_)
   {
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

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
   }

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
   }

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

   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

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
   }

   private static double readDouble()
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
   }

   private static double readDouble(String str)
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
   }

   public static float readFloat()
   {
      return (float) readDouble();
   }

   public static float readFloat(String str)
   {
      return (float) readDouble(str);
   }

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
   }

   private static byte readByte()
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
   }

   private static short readShort()
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
   }

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
   }

   private static String readString()
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
   }

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
   }

   private static String readLine()
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
   }

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
   }

   private static char readChar()
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
   }

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
   }

   private static boolean readBoolean()
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
   }

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
   }

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
   }

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
   }
}