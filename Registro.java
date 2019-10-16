import java.io.IOException;

interface Registro
{
    
    int getID();
    void setID(int n);
    byte[] toByteArray() throws IOException;
    void fromByteArray(byte[] ba) throws IOException;
}
