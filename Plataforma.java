
import java.io.*;
import java.text.DecimalFormat;

public class Plataforma implements Registro
{
    //Atributos
    private int id;
    private String nome;

    //Metodos construtores
    public Plataforma()
    {
        this(0, "");        
    }
    
    public Plataforma(int id, String nome)
    {
        this.id=id;
        this.nome=nome;
    }

    //Metodos Set(s)
    @Override
    public void setID(int id)
    {
        this.id=id;
    }

    public void setNome(String nome)
    {
        this.nome=nome.toUpperCase();
    }

    //Metodos Get(s)
    @Override
    public int getID()
    {
        return this.id;
    }
    
    public String toString()
    {
        return "\nID: "+this.id+
               "\nTipo: "+this.nome;
    }

    //Outros metodos
    @Override
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);        
        return baos.toByteArray();
    }

    @Override
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
    }     
}