import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class HashExtensivel {

    private RandomAccessFile arqDiretorio;
    private RandomAccessFile arqCestos;
    private final int              quantidadeDadosPorCesto;
    private Diretorio diretorio;
    
    static class Cesto {

        byte   profundidadeLocal;   // profundidade local do cesto
        short  quantidade;          // quantidade de pares presentes no cesto
        short  quantidadeMaxima;    // quantidade máxima de pares que o cesto pode conter
        int[]  chaves;              // sequência de chaves armazenadas no cesto
        long[] enderecos;           // sequência de endereços correspondentes às chaves
        short  bytesPorPar;         // tamanho fixo de cada par de chave/endereço em bytes
        short  bytesPorCesto;       // tamanho fixo do cesto em bytes

        Cesto(int qtdmax) throws Exception {
            this(qtdmax, 0);
        }

        Cesto(int qtdmax, int pl) throws Exception {
            if(qtdmax>32767)
                throw new Exception("Quantidade maxima de 32.767 elementos");
            if(pl>127)
                throw new Exception("Profundidade local maxima de 127 bits");
            profundidadeLocal = (byte)pl;
            quantidade = 0;
            quantidadeMaxima = (short)qtdmax;
            chaves = new int[quantidadeMaxima];
            enderecos = new long[quantidadeMaxima];
            bytesPorPar = 12;  // int + long
            bytesPorCesto = (short)(bytesPorPar * quantidadeMaxima + 3);
        }

        byte[] toByteArray() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeByte(profundidadeLocal);
            dos.writeShort(quantidade);
            int i=0;
            while(i<quantidade) {
                dos.writeInt(chaves[i]);
                dos.writeLong(enderecos[i]);
                i++;
            }
            while(i<quantidadeMaxima) {
                dos.writeInt(0);
                dos.writeLong(0);
                i++;
            }
            return baos.toByteArray();            
        }

        void fromByteArray(byte[] ba) throws IOException {
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(bais);
            profundidadeLocal = dis.readByte();
            quantidade = dis.readShort();
            int i=0;
            while(i<quantidadeMaxima) {
                chaves[i] = dis.readInt();
                enderecos[i] = dis.readLong();
                i++;
            }
        }

        void insere(int c, long e) {
            if(cestoCheio())
                return;
            int i=quantidade-1;
            while(i>=0 && c<chaves[i]) {
                chaves[i+1] = chaves[i];
                enderecos[i+1] = enderecos[i];
                i--;
            }
            i++;
            chaves[i] = c;
            enderecos[i] = e;
            quantidade++;
        }

        long busca(int c) {
            if(cestoVazio())
                return -1;
            int i=0;
            while(i<quantidade && c>chaves[i])
                i++;
            if(i<quantidade && c==chaves[i])
                return enderecos[i];
            else
                return -1;        
        }

        boolean remove(int c) {
            if(cestoVazio())
                return false;
            int i=0;
            while(i<quantidade && c>chaves[i])
                i++;
            if(c==chaves[i]) {
                while(i<quantidade-1) {
                    chaves[i] = chaves[i+1];
                    enderecos[i] = enderecos[i+1];
                    i++;
                }
                quantidade--;
                return true;
            }
            else
                return false;        
        }

        boolean cestoVazio() {
            return quantidade == 0;
        }

        boolean cestoCheio() {
            return quantidade == quantidadeMaxima;
        }

        public String toString() {
            StringBuilder s = new StringBuilder("\nProfundidade Local: " + profundidadeLocal +
                    "\nQuantidade: " + quantidade +
                    "\n| ");
            int i=0;
            while(i<quantidade) {
                s.append(chaves[i]).append(";").append(enderecos[i]).append(" | ");
                i++;
            }
            while(i<quantidadeMaxima) {
                s.append("-;- | ");
                i++;
            }
            return s.toString();
        }

        int tamanho() {
            return bytesPorCesto;
        }

    }

    static class Diretorio {

        byte   profundidadeGlobal;
        long[] enderecos;

        Diretorio() {
            profundidadeGlobal = 0;
            enderecos = new long[1];
        }

        void atualizaEndereco(int p, long e) {
            if(p>Math.pow(2,profundidadeGlobal))
                return;
            enderecos[p] = e;
        }

        byte[] toByteArray() throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeByte(profundidadeGlobal);
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            int i=0;
            while(i<quantidade) {
                dos.writeLong(enderecos[i]);
                i++;
            }
            return baos.toByteArray();            
        }

        void fromByteArray(byte[] ba) throws IOException {
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(bais);
            profundidadeGlobal = dis.readByte();
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            enderecos = new long[quantidade];
            int i=0;
            while(i<quantidade) {
                enderecos[i] = dis.readLong();
                i++;
            }
        }

        public String toString() {
            StringBuilder s = new StringBuilder("\nProfundidade global: " + profundidadeGlobal);
            int i=0;
            int quantidade = (int)Math.pow(2,profundidadeGlobal);
            while(i<quantidade) {
                s.append("\n").append(i).append(": ").append(enderecos[i]);
                i++;
            }
            return s.toString();
        }

        long endereco(int p) {
            if(p>Math.pow(2,profundidadeGlobal))
                return -1;
            return enderecos[p];
        }

        void duplica() {
            if(profundidadeGlobal==127)
                return;
            profundidadeGlobal++;
            int q1 = (int)Math.pow(2,profundidadeGlobal-1);
            int q2 = (int)Math.pow(2,profundidadeGlobal);
            long[] novosEnderecos = new long[q2];
            int i=0;
            while(i<q1) {
                novosEnderecos[i]= enderecos[i];
                i++;
            }
            while(i<q2) {
                novosEnderecos[i]= enderecos[i-q1];
                i++;
            }
            enderecos = novosEnderecos;
        }

        int hash(int chave) {
            return chave % (int)Math.pow(2, profundidadeGlobal);
        }

    }
    
    
    
    public HashExtensivel(int n, String nd, String nc ) throws Exception {
        quantidadeDadosPorCesto = n;

        File d = new File("dados");
        if( !d.exists() )
            d.mkdir();
        
        arqDiretorio = new RandomAccessFile("dados/"+ nd,"rw");
        arqCestos = new RandomAccessFile("dados/"+ nc,"rw");

        // Se o diretório ou os cestos estiverem vazios, cria um novo diretório e lista de cestos
        if(arqDiretorio.length()==0 || arqCestos.length()==0) {

            // Cria um novo diretório, com profundidade de 0 bits (1 único elemento)
            diretorio = new Diretorio();
            byte[] bd = diretorio.toByteArray();
            arqDiretorio.write(bd);
            
            // Cria um cesto vazio, já apontado pelo único elemento do diretório
            Cesto c = new Cesto(quantidadeDadosPorCesto);
            bd = c.toByteArray();
            arqCestos.seek(0);
            arqCestos.write(bd);
        }
    }
    
    public void insere(int chave, long endereco) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int) arqDiretorio.length()];
        arqDiretorio.seek(0);
        arqDiretorio.read(bd);
        diretorio = new Diretorio();
        diretorio.fromByteArray(bd);
        
        // Identifica a hash do diretório,
        int i = diretorio.hash(chave);
        
        // Recupera o cesto
        long enderecoCesto = diretorio.endereco(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(enderecoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        // Testa se a chave já não existe no cesto
        if(c.busca(chave)!=-1)
            throw new Exception("Chave ja existe");

        // Testa se o cesto já não está cheio
        // Se não estiver, insere o par de chave e endereco
        if(!c.cestoCheio()) {
            // Insere a chave no cesto e o atualiza 
            c.insere(chave, endereco);
            arqCestos.seek(enderecoCesto);
            arqCestos.write(c.toByteArray());
            return;
        }
        
        // Duplica o diretório
        byte pl = c.profundidadeLocal;
        if(pl>= diretorio.profundidadeGlobal)
            diretorio.duplica();
        byte pg = diretorio.profundidadeGlobal;

        // Cria os novos cestos, com os seus endereços no arquivo de cestos
        Cesto c1 = new Cesto(quantidadeDadosPorCesto, pl + 1);
        arqCestos.seek(enderecoCesto);
        arqCestos.write(c1.toByteArray());

        Cesto c2 = new Cesto(quantidadeDadosPorCesto, pl + 1);
        long novoEndereco = arqCestos.length();
        arqCestos.seek(novoEndereco);
        arqCestos.write(c2.toByteArray());
        
        // Atualiza os endereços no diretório
        int deslocamento = (int)Math.pow(2,pl);
        int max = (int)Math.pow(2,pg);
        boolean troca = false;
        for(int j=i; j<max; j+=deslocamento) {
            if(troca)
                diretorio.atualizaEndereco(j,novoEndereco);
            troca=!troca;
        }
        
        // Atualiza o arquivo do diretório
        bd = diretorio.toByteArray();
        arqDiretorio.seek(0);
        arqDiretorio.write(bd);
        
        // Reinsere as chaves
        for(int j=0; j<c.quantidade; j++) {
            insere(c.chaves[j], c.enderecos[j]);
        }
        insere(chave,endereco);

    }
    
    public long busca(int chave) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int) arqDiretorio.length()];
        arqDiretorio.seek(0);
        arqDiretorio.read(bd);
        diretorio = new Diretorio();
        diretorio.fromByteArray(bd);
        
        // Identifica a hash do diretório,
        int i = diretorio.hash(chave);
        
        // Recupera o cesto
        long enderecoCesto = diretorio.endereco(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(enderecoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        return c.busca(chave);
    }
    
    public void remove(int chave) throws Exception {
        
        //Carrega o diretório
        byte[] bd = new byte[(int) arqDiretorio.length()];
        arqDiretorio.seek(0);
        arqDiretorio.read(bd);
        diretorio = new Diretorio();
        diretorio.fromByteArray(bd);
        
        // Identifica a hash do diretório,
        int i = diretorio.hash(chave);
        
        // Recupera o cesto
        long enderecoCesto = diretorio.endereco(i);
        Cesto c = new Cesto(quantidadeDadosPorCesto);
        byte[] ba = new byte[c.tamanho()];
        arqCestos.seek(enderecoCesto);
        arqCestos.read(ba);
        c.fromByteArray(ba);
        
        // remove a chave
        if(!c.remove(chave))
            return;
        
        // Atualiza o cesto
        arqCestos.seek(enderecoCesto);
        arqCestos.write(c.toByteArray());
    }
    

    public void imprime() {
        try {
            byte[] bd = new byte[(int)arqDiretorio.length()];
            arqDiretorio.seek(0);
            arqDiretorio.read(bd);
            diretorio = new Diretorio();
            diretorio.fromByteArray(bd);
            System.out.println("\nDIRETORIO ------------------");
            System.out.println(diretorio);

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
