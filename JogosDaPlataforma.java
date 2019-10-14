
import java.io.*;

public class JogosDaPlataforma implements Registro
{
    //Atributos
    private int id;
    private int idPlataforma;
    private int idJogo;

    //Metodos construtores
    public JogosDaPlataforma()
    {
        this(0, 0, 0);
    }

    public JogosDaPlataforma(int id,int idPlataforma,int idJogo)
    {
        this.setID(id);
        this.setIdPlataforma(idPlataforma);
        this.setIdJogo(idJogo);
    }

    //Metodos Set(s)
    @Override
    public void setID(int id)
    {
        this.id=id;
    }

    public void setIdPlataforma(int idPlataforma)
    {
        this.idPlataforma=idPlataforma;
    }

    public void setIdJogo(int idJogo)
    {
        this.idJogo=idJogo;
    }

    //Metodos Get(s)
    @Override
    public int getID()
    {
        return this.id;
    }

    public int getIdPlataforma()
    {
        return this.idPlataforma;
    }

    public int getIdJogo()
    {
        return this.idJogo;
    }

    //Outros metodos
    @Override
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeInt(idPlataforma);
        dos.writeInt(idJogo);        
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.idPlataforma = dis.readInt();
        this.idJogo = dis.readInt();
    }
}