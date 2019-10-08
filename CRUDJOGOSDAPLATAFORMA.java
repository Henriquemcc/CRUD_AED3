
public class CRUDJOGOSDAPLATAFORMA
{
    
    private static ArquivoIndexado<JogosDaPlataforma> arqJogosDaPlataforma;
    
    //LISTA TODOS OS JOGOS DE UMA PLATAFORMA
    public static void listarJogosdeumaPlataforma(int idPlataforma) throws Exception
    {
        Object[] JogosDaPlataforma = arqJogosDaPlataforma.listar();
        JogosDaPlataforma jdp;
        for(int i=0; i<JogosDaPlataforma.length; i++)
        {
            jdp = (JogosDaPlataforma)JogosDaPlataforma[i];
            if(jdp.getIdPlataforma()==idPlataforma){
                CRUDJOGOS.buscarJogo(jdp.getIdJogo());
            }
        }
        
    }
    //LISTA TODAS AS PLATAFORMAS DE UM JOGO
    public static void listarPlataformasdoJogo(int idJogo) throws Exception
    {
        Object[] JogosDaPlataforma = arqJogosDaPlataforma.listar();
        JogosDaPlataforma jdp;
        for(int i=0; i<JogosDaPlataforma.length; i++)
        {
            jdp = (JogosDaPlataforma)JogosDaPlataforma[i];
            if(jdp.getIdJogo()==idJogo){
                CRUDPLATAFORMA.buscarPlataforma(jdp.getIdPlataforma());
            }
        }
        
    }

    public static void incluirJogodaPlataforma(int idJogo, int idPlataforma) throws Exception
    {
        JogosDaPlataforma l = new JogosDaPlataforma(-1,idPlataforma,idJogo);
        arqJogosDaPlataforma.incluir(l);
    }
    //Funcionamento mode
    //Mode = true procura todas as plataformas com o id enviado e remove as entidades
    //Mode = false procura todos os jogos com o id enviado e remove as entidades
    public static void excluirJogosdaPlataforma(int id,boolean mode) throws Exception
    {
        Object[] jdps = arqJogosDaPlataforma.listar();
        JogosDaPlataforma jdp;
        if(mode){
            for(int i=0;i<jdps.length;i++){
                jdp = (JogosDaPlataforma)jdps[i];
                if(jdp.getIdPlataforma()==id){
                    if( arqJogosDaPlataforma.buscar(jdp.getID())!=null ){
                        arqJogosDaPlataforma.excluir(jdp.getID());
                    }
                }
            }
        }
        else{
            for(int i=0;i<jdps.length;i++){
                jdp = (JogosDaPlataforma)jdps[i];
                if(jdp.getIdJogo()==id){
                    if( arqJogosDaPlataforma.buscar(jdp.getID())!=null ){
                        arqJogosDaPlataforma.excluir(jdp.getID());
                    }
                }
            }
        }
    }
}
