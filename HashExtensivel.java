import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class HashExtensivel {
   
    String           nomeArquivoDiretorio;
    String           nomeArquivoCestos;
    RandomAccessFile arqDiretório;
    RandomAccessFile arqCestos;
    int              quantidadeDadosPorCesto;
    Diretório        diretório;
    
    class Cesto {

        byte   profundidadeLocal;   // profundidade local do cesto
        short  quantidade;          // quantidade de pares presentes no cesto
        short  quantidadeMaxima;    // quantidade máxima de pares que o cesto pode conter
        int[]  chaves;              // sequência de chaves armazenadas no cesto
        long[] endereços;           // sequência de endereços correspondentes às chaves
        short  bytesPorPar;         // tamanho fixo de cada par de chave/endereço em bytes
        short  bytesPorCesto;       // tamanho fixo do cesto em bytes

        public Cesto(int qtdmax) throws Exception {
            this(qtdmax, 0);
        }

        public Cesto(int qtdmax, int pl) throws Exception {
            if(qtdmax>32767)
                throw new Exception("Quantidade máxima de 32.767 elementos");
            if(pl>127)
                throw new Exception("Profundidade local máxima de 127 bits");
            profundidadeLocal = (byte)pl;
            quantidade = 0;
            quantidadeMaxima = (short)qtdmax;
            chaves = new int[quantidadeMaxima];
            endereços = new long[quantidadeMaxima];
            bytesPorPar = 12;  // int + long
            bytesPorCesto = (short)(bytesPorPar * quantidadeMaxima + 3);
        }

        public byte[] toByteArray() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeByte(profundidadeLocal);
            dos.writeShort(quantidade);
            int i=0;
            while(i<quantidade) {
                dos.writeInt(chaves[i]);
                dos.writeLong(endereços[i]);
                i++;
            }
            while(i<quantidadeMaxima) {
                dos.writeInt(0);
                dos.writeLong(0);
                i++;
            }
            return baos.toByteArray();            
        }

        public void fromByteArray(byte[] ba) throws IOException {
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(bais);
            profundidadeLocal = dis.readByte();
            quantidade = dis.readShort();
            int i=0;
            while(i<quantidadeMaxima) {
                chaves[i] = dis.readInt();
                endereços[i] = dis.readLong();
                i++;
            }
        }

        public boolean insere(int c, long e) {
            if(cestoCheio())
                return false;
            int i=quantidade-1;
            while(i>=0 && c<chaves[i]) {
                chaves[i+1] = chaves[i];
                endereços[i+1] = endereços[i];
                i--;
            }
            i++;
            chaves[i] = c;
            endereços[i] = e;
            quantidade++;
            return true;
        }

        public long busca(int c) {
            if(cestoVazio())
                return -1;
            int i=0;
            while(i<quantidade && c>chaves[i])
                i++;
            if(i<quantidade && c==chaves[i])
                return endereços[i];
            else
                return -1;        
        }

        public boolean remove(int c) {
            if(cestoVazio())
                return false;
            int i=0;
            while(i<quantidade && c>chaves[i])
                i++;
            if(c==chaves[i]) {
                while(i<quantidade-1) {
                    chaves[i] = chaves[i+1];
                    endereços[i] = endereços[i+1];
                    i++;
                }
                quantidade--;
                return true;
            }
            else
                return false;        
        }

        public boolean cestoVazio() {
            return quantidade == 0;
        }

        public boolean cestoCheio() {
            return quantidade == quantidadeMaxima;
        }

        public String toString() {
            String s = "\nProfundidade Local: "+profundidadeLocal+
                       "\nQuantidade: "+quantidade+
                       "\n| ";
            int i=0;
            while(i<quantidade) {
                s += chaves[i] + ";" + endereços[i] + " | ";
                i++;
            }
            while(i<quantidadeMaxima) {
                s += "-;- | ";
                i++;
            }
            return s;
        }

        public int tamanho() {
            return bytesPorCesto;
        }

    }

    class Diretório {

        byte   profundidadeGlobal;
        long[] endereços;

        public Diretório() {
            profundidadeGlobal = 0;
            endereços = new long[1];
            endereços[0] = 0;
        }

        public boolean atualizaEndereco(int p, long e) {
            if(p>Math.pow(2,profundidadeGlobal))
                return false;
            endereços[p] = e;
            return true;
        }

        public byte[] toByteArray() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeByte(profundidadeGlobal);
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            int i=0;
            while(i<quantidade) {
                dos.writeLong(endereços[i]);
                i++;
            }
            return baos.toByteArray();            
        }

        public void fromByteArray(byte[] ba) throws IOException {
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(bais);
            profundidadeGlobal = dis.readByte();
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            endereços = new long[quantidade];
            int i=0;
            while(i<quantidade) {
                endereços[i] = dis.readLong();
                i++;
            }
        }

        public String toString() {
            String s = "\nProfundidade global: "+profundidadeGlobal;
            int i=0;
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            while(i<quantidade) {
                s += "\n" + i + ": " + endereços[i];
                i++;
            }
            return s;
        }

        protected long endereço(int p) {
            if(p>Math.pow(2,profundidadeGlobal))
                return -1;
            return endereços[p];
        }

        protected boolean duplica() {
            if(profundidadeGlobal==127)
                return false;
            profundidadeGlobal++;
            int q1 = (int)Math.pow(2,profundidadeGlobal-1);
            int q2 = (int)Math.pow(2,profundidadeGlobal);
            long[] novosEnderecos = new long[q2];
            int i=0;
            while(i<q1) {
                novosEnderecos[i]=endereços[i];
                i++;
            }
            while(i<q2) {
                novosEnderecos[i]=endereços[i-q1];
                i++;
            }
            endereços = novosEnderecos;
            return true;
        }

        protected int hash(int chave) {
            return chave % (int)Math.pow(2, profundidadeGlobal);
        }

        protected int hash2(int chave, int pl) { // cálculo do hash para uma dada profundidade local
            return chave % (int)Math.pow(2, pl);            
        }

    }
    
    
    
    public HashExtensivel(int n, String nd, String nc ) throws Exception {
        quantidadeDadosPorCesto = n;
        nomeArquivoDiretorio = nd;
        nomeArquivoCestos = nc;
        
        File d = new File("dados");
        if( !d.exists() )
            d.mkdir();
        
        arqDiretório = new RandomAccessFile("dados/"+nomeArquivoDiretorio,"rw");
        arqCestos = new RandomAccessFile("dados/"+nomeArquivoCestos,"rw");

        // Se o diretório ou os cestos estiverem vazios, cria um novo diretório e lista de cestos
        if(arqDiretório.length()==0 || arqCestos.length()==0) {

            // Cria um novo diretório, com profundidade de 0 bits (1 único elemento)
            diretório = new Diretório();
            byte[] bd = diretório.toByteArray();
            arqDiretório.write(bd);
            
            // Cria um cesto vazio, já apontado pelo único elemento do diretório
            Cesto c = new Cesto(quantidadeDadosPorCesto);
            bd = c.toByteArray();
            arqCestos.seek(0);
            arqCestos.write(bd);
        }
    }
    
    public boolean insere(int chave, long endereço) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int)arqDiretório.length()];
        arqDiretório.seek(0);
        arqDiretório.read(bd);
        diretório = new Diretório();
        diretório.fromByteArray(bd);        
        
        // Identifica a hash do diretório,
        int i = diretório.hash(chave);
        
        // Recupera o cesto
        long endereçoCesto = diretório.endereço(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(endereçoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        // Testa se a chave já não existe no cesto
        if(c.busca(chave)!=-1)
            throw new Exception("Chave já existe");     

        // Testa se o cesto já não está cheio
        // Se não estiver, insere o par de chave e endereço
        if(!c.cestoCheio()) {
            // Insere a chave no cesto e o atualiza 
            c.insere(chave, endereço);
            arqCestos.seek(endereçoCesto);
            arqCestos.write(c.toByteArray());
            return true;        
        }
        
        // Duplica o diretório
        byte pl = c.profundidadeLocal;
        if(pl>=diretório.profundidadeGlobal)
            diretório.duplica();
        byte pg = diretório.profundidadeGlobal;

        // Cria os novos cestos, com os seus endereços no arquivo de cestos
        Cesto c1 = new Cesto(quantidadeDadosPorCesto, pl+1);
        arqCestos.seek(endereçoCesto);
        arqCestos.write(c1.toByteArray());

        Cesto c2 = new Cesto(quantidadeDadosPorCesto, pl+1);
        long novoEndereço = arqCestos.length();
        arqCestos.seek(novoEndereço);
        arqCestos.write(c2.toByteArray());
        
        // Atualiza os endereços no diretório
        int inicio = diretório.hash2(chave, c.profundidadeLocal);
        int deslocamento = (int)Math.pow(2,pl);
        int max = (int)Math.pow(2,pg);
        boolean troca = false;
        for(int j=inicio; j<max; j+=deslocamento) {
            if(troca)
                diretório.atualizaEndereco(j,novoEndereço);
            troca=!troca;
        }
        
        // Atualiza o arquivo do diretório
        bd = diretório.toByteArray();
        arqDiretório.seek(0);
        arqDiretório.write(bd);
        
        // Reinsere as chaves
        for(int j=0; j<c.quantidade; j++) {
            insere(c.chaves[j], c.endereços[j]);
        }
        insere(chave,endereço);
        return false;   

    }
    
    public long busca(int chave) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int)arqDiretório.length()];
        arqDiretório.seek(0);
        arqDiretório.read(bd);
        diretório = new Diretório();
        diretório.fromByteArray(bd);        
        
        // Identifica a hash do diretório,
        int i = diretório.hash(chave);
        
        // Recupera o cesto
        long endereçoCesto = diretório.endereço(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(endereçoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        return c.busca(chave);
    }
    
    public boolean remove(int chave) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int)arqDiretório.length()];
        arqDiretório.seek(0);
        arqDiretório.read(bd);
        diretório = new Diretório();
        diretório.fromByteArray(bd);        
        
        // Identifica a hash do diretório,
        int i = diretório.hash(chave);
        
        // Recupera o cesto
        long endereçoCesto = diretório.endereço(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(endereçoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        // remove a chave
        if(!c.remove(chave))
            return false;
        
        // Atualiza o cesto
        arqCestos.seek(endereçoCesto);
        arqCestos.write(c.toByteArray());
        return true;
    }
    
    public void imprime() {
        try {
            byte[] bd = new byte[(int)arqDiretório.length()];
            arqDiretório.seek(0);
            arqDiretório.read(bd);
            diretório = new Diretório();
            diretório.fromByteArray(bd);   
            System.out.println("\nDIRETÓRIO ------------------");
            System.out.println(diretório);

            System.out.println("\nCESTOS ---------------------");
            arqCestos.seek(0);
            while(arqCestos.getFilePointer() != arqCestos.length()) {
                Cesto c = new Cesto(quantidadeDadosPorCesto);
                byte[] ba = new byte[c.tamanho()];
                arqCestos.read(ba);
                c.fromByteArray(ba);
                System.out.println(c);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
