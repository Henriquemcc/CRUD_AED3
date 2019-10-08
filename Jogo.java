import java.io.*;

public class Jogo implements Registro
{
    private int id;
    private int idGenero;
    private String titulo;
    private byte score;
    //private dataLancamento;
    //Metodos construtores
    public Jogo()
    {
        this(0, 0, "", (byte)0);
        //dataLancamento;
    }

    public Jogo(int id, int idGenero, String titulo, byte score)
    {
        this.setID(id);
        this.setIdGenero(idGenero);
        this.setTitulo(titulo);
        this.setScore(score);
    }

    //Metodos Set(s)
    @Override
    public void setID(int id)
    {
        this.id=id;
    }

    public void setIdGenero(int idGenero)
    {
        this.idGenero=idGenero;
    }

    public void setTitulo(String titulo)
    {
        this.titulo=titulo.toUpperCase();
    }

    public void setScore(byte score)
    {
        this.score=score;
    }

    //Metodos Get(s)
    @Override
    public int getID()
    {
        return this.id;
    }

    public int getIdGenero()
    {
        return this.idGenero;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public byte getScore()
    {
        return this.score;
    }
    
    //Outros metodos
    public String toString()
    {
        return "\nID: "+this.id+
               "\ntitulo: "+this.titulo+
               "\nScore: "+this.score;
    }

    @Override
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(titulo);
        dos.writeByte(score);
        dos.writeInt(idGenero);        
        return baos.toByteArray();
    }
    
    @Override
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.titulo = dis.readUTF();
        this.score = dis.readByte();
        this.idGenero = dis.readInt();
    }
}