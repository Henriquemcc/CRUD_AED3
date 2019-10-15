import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

class ArquivoIndexado<T extends Registro>
{

    private RandomAccessFile arquivo;
    private final Constructor<T> construtor;
    private HashExtensivel indice;
    
    public ArquivoIndexado(Constructor<T> c, String n) throws Exception
    {
        construtor = c;
        File d = new File("dados");
        if( !d.exists() )
            d.mkdir();
        arquivo = new RandomAccessFile("dados/"+ n, "rw");
        if(arquivo.length()<4)
            arquivo.writeInt(0);

        String nomeDiretorio = "diretorio." + n;
        String nomeListaCestos = "cestos." + n;
        indice = new HashExtensivel(4, nomeDiretorio, nomeListaCestos);
    }
    
    public int incluir(T obj) throws Exception
    {
        this.arquivo.seek(0);
        int ultimoID = this.arquivo.readInt();
        ultimoID++;
        arquivo.seek(0);
        arquivo.writeInt(ultimoID);

        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        obj.setID(ultimoID);
        arquivo.writeByte(' ');             // lapide
        byte[] byteArray = obj.toByteArray();
        arquivo.writeInt(byteArray.length); // indicador de tamanho do registro
        arquivo.write(byteArray);           // vetor de bytes que representa o registro
        indice.insere(ultimoID, endereco);
        return obj.getID();
    }
    
    // Metodo apenas para testes, pois geralmente a memoria principal raramente
    // sera suficiente para manter todos os registros simultaneamente
    public Object[] listar() throws Exception
    {
        ArrayList<T> lista = new ArrayList<>();
        arquivo.seek(4);
        byte lapide;
        byte[] byteArray;
        int s;
        T obj;
        while(arquivo.getFilePointer()<arquivo.length())
        {
            obj = construtor.newInstance();
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            byteArray = new byte[s];
            arquivo.read(byteArray);
            obj.fromByteArray(byteArray);
            if(lapide==' ')
                lista.add(obj);
        }
        return lista.toArray();
    }
    
    public Object buscar(int id) throws Exception
    {
        byte lapide;
        byte[] byteArray;
        int s;
        T obj;

        long endereco = indice.busca(id);
        if(endereco!=-1)
        {
            obj = construtor.newInstance();
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            s = arquivo.readInt();
            byteArray = new byte[s];
            arquivo.read(byteArray);
            obj.fromByteArray(byteArray);
            if(lapide==' ' && obj.getID()==id)   // testes redundantes
                return obj;
        }
        return null;
    }
    
    public boolean excluir(int id) throws Exception
    {
        long endereco = indice.busca(id);
        if(endereco!=-1)
        {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.remove(id);
            return true;
        }
        else
            return false;
    }
    
}
