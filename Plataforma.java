import java.io.*;

public class Plataforma implements Registro
{
    //Atributos
    private int id;
    private String nome;

    //Metodos construtores
    /**
     * Funcao do metodo: Este metodo serve para criar um novo objeto do tipo Plataforma.
     */
    public Plataforma()
    {
        this(0, "");        
    }//fim do metodo Plataforma
    
    /**
     * Funcao do metodo: Este metodo serve para criar um novo objeto do tipo Plataforma.
     * @param id Id da plataforma.
     * @param nome Nome da plataforma.
     */
    public Plataforma(int id, String nome)
    {
        this.setID(id);
        this.setNome(nome);
    }//fim do metodo Plataforma

    //Metodos Set(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para alterar o id da Plataforma.
     * @param id Novo id da plataforma.
     */
    public void setID(int id)
    {
        this.id=id;
    }//fim do metodo setID

    /**
     * Funcao do metodo: Este metodo serve para alterar o nome da plataforma.
     * @param nome Novo nome da plataforma.
     */
    public void setNome(String nome)
    {
        this.nome=nome.toUpperCase();
    }//fim do metodo setNome

    //Metodos Get(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para obter o id da Plataforma.
     * @return Id da Plataforma
     */
    public int getID()
    {
        return this.id;
    }//fim do metodo getID

    /**
     * Funcao do metodo: Este metodo serve para obter o nome da Plataforma.
     * @return Nome da Plataforma.
     */
    public String getNome()
    {
        return this.nome;
    }//fim do metodo getNome

    /**
     * Funcao do metodo: Este metodo serve para transformar o objeto do tipo Plataforma em uma String.
     * @return String contendo as informacoes do objeto do tipo Plataforma.
     */
    public String toString()
    {
        return "\nID: "+this.id+
               "\nTipo: "+this.nome;
    }//fim do metodo toString

    //Outros metodos
    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter o objeto do tipo Plataforma em um arranjo de bytes.
     * @return Arranjo de bytes
     * @throws IOException Excecao das classes ByteArrayOutputStream e DataOutputStream, que sera tratada no metodo que chamou este metodo.
     */
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(nome);        
        return baos.toByteArray();
    }//fim do metodo toByteArray

    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um arranjo de bytes no objeto do tipo Plataforma.
     * @param b Arranjo de bytes.
     * @throws IOException Excecao das classes ByteArrayInputStream e DataInputStream, que sera tratada no metodo que chamou este metodo
     */
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
    }//fim do metodo fromByteArray
}