import java.io.*;

public class Genero implements Registro
{
    //Atributos
    private int idgenero;
    private String tipo;

    //Construtores
    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo Genero.
     */
    public Genero()
    {
        this(0, "");
    }//fim do metodo Genero

    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo Genero.
     * @param id Id do Genero.
     * @param tipo Nome (tipo) do genero.
     */
    public Genero(int id,String tipo)
    {
        this.setID(id);
        this.setTipo(tipo);
    }//fim do metodo Genero

    //Metodos Get(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para obter o id do Genero.
     * @return id do Genero.
     */
    public int getID()
    {
        return this.idgenero;
    }//fim do metodo getID

    /**
     * Funcao do metodo: Este metodo serve para obter o nome (tipo) do genero.
     * @return Tipo (nome) do genero.
     */
    public String getTipo()
    {
        return this.tipo;
    }//fim do metodo getTipo

    //Metodos Set(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para alterar o id do genero.
     * @param n Novo id do genero.
     */
    public void setID(int n)
    {
        this.idgenero=n;
    }//fim do metodo setID

    /**
     * Funcao do metodo: Este metodo serve para alterar o nome (tipo) do genero.
     * @param tipo Novo Tipo (nome) do genero. 
     */
    public void setTipo(String tipo)
    {
        this.tipo=tipo.toUpperCase();
    }//fim do metodo setTipo

    /**
     * Funcao do metodo: Este metodo serve para converter o objeto Genero em uma String.
     * @return String contendo dados de Genero.
     */
    public String toString()
    {
        return "\nID: "+this.idgenero+
               "\nTipo: "+this.tipo;
    }//fim do metodo toString

    //Outros metodos
    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter o objeto Genero em um arranjo de bytes
     * @return Arranjo de bytes
     * @throws IOException Excecao das classes ByteArrayOutputStream e DataOutputStream, que sera tratada no metodo que chamou este metodo.
     */
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(idgenero);
        dos.writeUTF(tipo);        
        return baos.toByteArray();
    }//fim do metodo toByteArray

    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um arranjo de bytes em um objeto do tipo Genero.
     * @param b Arranjo de bytes.
     * @throws IOException Excecao das classes ByteArrayInputStream e DataInputStream, que sera tratada no metodo que chamou este metodo.
     */
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.idgenero = dis.readInt();
        this.tipo = dis.readUTF();
    }//fim do metodo fromByteArray
}