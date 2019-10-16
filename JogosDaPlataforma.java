import java.io.*;

public class JogosDaPlataforma implements Registro
{
    //Atributos
    private int id;
    private int idPlataforma;
    private int idJogo;

    //Metodos construtores
    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo JogosDaPlataforma.
     */
    public JogosDaPlataforma()
    {
        this(0, 0, 0);
    }//fim do metodo JogosDaPlataforma

    /**
     * Funcao do metodo: Este metodo serve para construir um novo objeto do tipo JogosDaPlataforma.
     * @param id id do registro jogos da plataforma.
     * @param idPlataforma id da plataforma.
     * @param idJogo id do jogo.
     */
    public JogosDaPlataforma(int id,int idPlataforma,int idJogo)
    {
        this.setID(id);
        this.setIdPlataforma(idPlataforma);
        this.setIdJogo(idJogo);
    }//fim do metodo JogosDaPlataforma

    //Metodos Set(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para alterar a id do JogosDaPlataforma.
     * @param id Novo id do JogosDaPlataforma.
     */
    public void setID(int id)
    {
        this.id=id;
    }//fim do metodo setID

    /**
     * Funcao do metodo: Este metodo serve para alterar o id da plataforma do JogosDaPlataforma.
     * @param idPlataforma Novo id da plataforma.
     */
    public void setIdPlataforma(int idPlataforma)
    {
        this.idPlataforma=idPlataforma;
    }//fim do metodo setIdPlataforma

    /**
     * Funcao do metodo: Este metodo serve para alterar o id do jogo do JogosDaPlataforma.
     * @param idJogo Novo id do jogo.
     */
    public void setIdJogo(int idJogo)
    {
        this.idJogo=idJogo;
    }//fim do metodo setIdJogo

    //Metodos Get(s)
    @Override
    /**
     * Funcao do metodo: Este metodo serve para obter o id do objeto JogosDaPlataforma.
     * @return Id do JogoDaPlataforma.
     */
    public int getID()
    {
        return this.id;
    }//fim do metodo getID

    /**
     * Funcao do metodo: Este metodo serve para obter o id da plataforma.
     * @return Id da plataforma.
     */
    public int getIdPlataforma()
    {
        return this.idPlataforma;
    }//fim do metodo getIdPlataforma.

    /**
     * Funcao do metodo: Este metodo serve para obter o id do jogo.
     * @return Id do jogo.
     */
    public int getIdJogo()
    {
        return this.idJogo;
    }//fim do metodo getIdJogo.

    //Outros metodos
    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um objeto JogosDaPlataforma em um arranjo de bytes.
     * @return Arranjo de bytes com os dados do objeto.
     * @throws IOException Excecao das classes ByteArrayOutputStream e DataOutputStream, que sera tratada no metodo que chamou este metodo.
     */
    public byte[] toByteArray()throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeInt(idPlataforma);
        dos.writeInt(idJogo);        
        return baos.toByteArray();
    }//fim do metodo toByteArray

    @Override
    /**
     * Funcao do metodo: Este metodo serve para converter um arranjo de bytes no objeto JogosDaPlataforma.
     * @param b Arranjo de bytes.
     * @throws IOException Excecao das classes ByteArrayInputStream e DataInputStream, que sera tratada no metodo que chamou este metodo
     */
    public void fromByteArray(byte[]b)throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.idPlataforma = dis.readInt();
        this.idJogo = dis.readInt();
    }//fim do metodo fromByteArray
}