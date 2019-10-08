
import java.io.*;
import java.text.DecimalFormat;

public class Livro implements Registro {
    private int id;
    private String titulo;
    private String autor;
    private float preco;
    
    private DecimalFormat df = new DecimalFormat("#0.00");

    public Livro() {
        id = 0;
        titulo = "";
        autor = "";
        preco = 0;
    }
    public Livro( int c, String t, String a, float p ) {
        id = c;
        titulo = t;
        autor = a;
        preco = p;
    }
    
    @Override
    public int getID(){
        return id;
    }
    
    @Override
    public void setID(int n){
        id = n;
    }
    
    public String toString() {
        return "\nID....: " + id + 
               "\nTitulo: " + titulo + 
               "\nAutor.: " + autor + 
               "\nPreço.: R$ " + df.format(preco);
    }
    
    @Override
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(id);
        dos.writeUTF(titulo);
        dos.writeUTF(autor);
        dos.writeFloat(preco);        
        return baos.toByteArray();
    }
  
    @Override
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        id = dis.readInt();
        titulo = dis.readUTF();
        autor = dis.readUTF();
        preco = dis.readFloat();
    }
    
}