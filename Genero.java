
import java.io.*;

public class Genero implements Registro
{
    //Atributos
    private int idgenero;
    private String tipo;

    //Construtores
    public Genero()
    {
        this(0, "");
    }

    public Genero(int id,String tipo)
    {
        this.setID(id);
        this.setTipo(tipo);
    }

    //Metodos Get(s)
    @Override
    public int getID()
    {
        return this.idgenero;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    //Metodos Set(s)
    @Override
    public void setID(int n)
    {
        this.idgenero=n;
    }

    public void setTipo(String tipo)
    {
        this.tipo=tipo.toUpperCase();;
    }
    public String toString()
    {
        return "\nID: "+this.idgenero+
               "\nTipo: "+this.tipo;
    }
    //Outros metodos
    @Override
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idgenero);
        dos.writeUTF(tipo);        
        return baos.toByteArray();
    }
    @Override
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.idgenero = dis.readInt();
        this.tipo = dis.readUTF();
    }
}