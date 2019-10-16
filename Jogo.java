import java.io.*;

public class Jogo implements Registro
{
    private int id;
    private int idGenero;
    private String titulo;
    private byte score;

    //Metodos construtores
    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo Jogo.
     */
    public Jogo()
    {
        this(0, 0, "", (byte)0);
    }//fim do metodo Jogo

    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo Jogo.
     * @param id Id do Jogo.
     * @param idGenero Id do genero.
     * @param titulo Titulo do jogo.
     * @param score Score do jogo (de acordo com o Metacritic).
     */
    public Jogo(int id, int idGenero, String titulo, byte score)
    {
        this.setID(id);
        this.setIdGenero(idGenero);
        this.setTitulo(titulo);
        this.setScore(score);
    }//fim do metodo Jogo

    //Metodos Set(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para alterar o valor do id do objeto Jogo.
     * @param id Novo id.
     */
    public void setID(int id)
    {
        this.id=id;
    }//fim do metodo setID

    /**
     * Funcao do metodo: Este metodo serve para alterar o id do genero do objeto Jogo.
     * @param idGenero Novo id do genero do jogo.
     */
    public void setIdGenero(int idGenero)
    {
        this.idGenero=idGenero;
    }//fim do metodo setIdGenero

    /**
     * Funcao do metodo: Este metodo serve para alterar o titulo do objeto Jogo.
     * @param titulo Novo titulo do Jogo.
     */
    public void setTitulo(String titulo)
    {
        this.titulo=titulo.toUpperCase();
    }//fim do metodo setTitulo

    /**
     * Funcao do metodo: Este metodo serve para alterar a score do objeto Jogo.
     * @param score Nova score do Jogo.
     */
    public void setScore(byte score)
    {
        this.score=score;
    }//fim do metodo setScore

    //Metodos Get(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para obter o id do jogo.
     * @return Id do jogo.
     */
    public int getID()
    {
        return this.id;
    }//fim do metodo getID

    /**
     * Funcao do metodo: Este metodo serve para obter o id do genero do jogo.
     * @return Id do genero do jogo.
     */
    public int getIdGenero()
    {
        return this.idGenero;
    }//fim do metodo getIdGenero

    /**
     * Funcao do metodo: Este metodo serve para obter o titulo do Jogo.
     * @return Titulo do Jogo.
     */
    public String getTitulo()
    {
        return this.titulo;
    }//fim do metodo getTitulo

    /**
     * Funcao do metodo: Este metodo serve para obter a score do Jogo.
     * @return Score do Jogo.
     */
    public byte getScore()
    {
        return this.score;
    }//fim do metodo getScore
    
    //Outros metodos
    /**
     * Funcao do metodo: Este metodo serve para converter o objeto Jogo em uma String.
     * @return String contendo os dados de Jogo.
     */
    public String toString()
    {
        return "\nID: "+this.id+
               "\nTitulo: "+this.titulo+
               "\nScore: "+this.score;
    }//fim do metodo toString

    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um objeto Jogo em um arranjo de bytes.
     * @return Arranjo de bytes.
     * @throws IOException Excecao das classes ByteArrayOutputStream e DataOutputStream, que sera tratada no metodo que chamou este metodo.
     */
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(titulo);
        dos.writeByte(score);
        dos.writeInt(idGenero);        
        return baos.toByteArray();
    }//fim do metodo toByteArray
    
    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um arranjo de bytes em um objeto Jogo.
     * @param b Arranjo de bytes.
     * @throws IOException Excecao das classes ByteArrayInputStream e DataInputStream, que sera tratada no metodo que chamou este metodo.
     */
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.titulo = dis.readUTF();
        this.score = dis.readByte();
        this.idGenero = dis.readInt();
    }//fim do metodo fromByteArray
}