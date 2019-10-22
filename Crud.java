import java.io.IOException;

public class Crud
{
    protected static ArquivoIndexado<Jogo> arqJogos;
    protected static ArquivoIndexado<Genero> arqGeneros;
    protected static ArquivoIndexado<Plataforma> arqPlataformas;
    protected static ArquivoIndexado<JogosDaPlataforma> arqJogosDaPlataforma;
    protected static ArvoreBMais idxJogoporGenero;
    protected static ArvoreBMais idxJogoparaJdp;
    protected static ArvoreBMais idxPlataformaparaJdp;

    /**
     * FUncao do metodo: Este metodo serve para criar todos os arquivos necessarios para o CRUD.
     * @throws Exception Todas excecoes que ocorrerem serao tratadas no metodo que chamar.
     */
    protected static void criarArquivo() throws Exception
    {
        if(arqJogos==null)
            arqJogos = new ArquivoIndexado<>(Jogo.class.getConstructor(), "jogos.db");//Arquivo dos jogos
        if(arqGeneros==null)
            arqGeneros = new ArquivoIndexado<>(Genero.class.getConstructor(), "generos.db");//Arquivo dos generos dos jogos
        if(arqJogosDaPlataforma==null)
            arqJogosDaPlataforma = new ArquivoIndexado<>(JogosDaPlataforma.class.getConstructor(), "jogosdaplataforma.db");
        if(arqPlataformas==null)
            arqPlataformas = new ArquivoIndexado<>(Plataforma.class.getConstructor(), "plataformas.db");//Arquivo da plataforma de jogos
        if(idxJogoporGenero==null)
            idxJogoporGenero= new ArvoreBMais(5,"jogoporgenero.idx");//Indice de jogos por generos
        if(idxJogoparaJdp==null)
            idxJogoparaJdp = new ArvoreBMais(5,"jogoparajdp.idx");//Indice de Jogos por Jogos da Plataforma
        if(idxPlataformaparaJdp==null)
            idxPlataformaparaJdp = new ArvoreBMais(5,"plataformaparajdp.idx");//Indice de plataforma para Jogos da Plataforma
    }//fim do metodo criarArquivo
}
