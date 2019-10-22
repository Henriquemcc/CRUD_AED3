class Main {

    /**
     * Funcao do metodo: Este metodo serve para executar o CRUD.
     * @param args Parametro padrao do main.
     */
    public static void main(String[] args)
    {
        try
        {
            MyIO.println("Bem vindo ao CRUD de Jogos");
            int comando=0;
            do
            {
                boolean erroDigitacaoComando;
                do
                {
                    MyIO.println("Digite o CRUD que deseja executar:");
                    MyIO.println("Operacoes com Jogos:");
                    MyIO.println("\t1-  Listar jogos.");
                    MyIO.println("\t2-  Buscar jogo.");
                    MyIO.println("\t3-  Incluir jogo.");
                    MyIO.println("\t4-  Excluir jogo.");
                    MyIO.println("\t5-  Alterar jogo.");
                    MyIO.println("\t6-  Povoar banco de dados de jogos.");
                    MyIO.println("Operacoes com Generos:");
                    MyIO.println("\t7-  Listar generos.");
                    MyIO.println("\t8-  Buscar genero.");
                    MyIO.println("\t9- Incluir genero.");
                    MyIO.println("\t10- Excluir genero.");
                    MyIO.println("\t11- Alterar genero.");
                    MyIO.println("\t12- Povoar banco de dados de generos.");
                    MyIO.println("Operacoes com Plataformas:");
                    MyIO.println("\t13- Listar plataformas.");
                    MyIO.println("\t14- Buscar plataforma.");
                    MyIO.println("\t15- Incluir plataforma.");
                    MyIO.println("\t16- Excluir plataforma.");
                    MyIO.println("\t17- Alterar plataforma.");
                    MyIO.println("\t18- Povoar banco de dados de plataformas.");
                    MyIO.println("Outras Operacoes: ");
                    MyIO.println("\t19- Povoar todos os bancos de dados.");
                    MyIO.println("\t0-  Sair");
                    try
                    {
                        comando=MyIO.readInt();
                        if(comando<0 || comando>19)
                            throw new Exception("Comando invalido!");

                        erroDigitacaoComando=false;
                    }
                    catch (Exception e)
                    {
                        MyIO.println(e.toString());
                        erroDigitacaoComando=true;
                    }
                }
                while(erroDigitacaoComando);

                if(comando==0)
                    MyIO.println("Saindo...");
                else if(comando==1)
                    CrudJogos.menuListarJogo();
                else if(comando==2)
                    CrudJogos.menuBuscarJogo();
                else if(comando==3)
                    CrudJogos.menuIncluirJogo();
                else if(comando==4)
                    CrudJogos.menuExcluirJogo();
                else if(comando==5)
                    MyIO.println("Nao implementado!");//NAO IMPLEMENTADO
                else if(comando==6)
                    CrudJogos.povoarBancoDeDados();
                else if(comando==7)
                    CrudGenero.menuListarGenero();
                else if(comando==8)
                    CrudGenero.menuBuscarGenero();
                else if(comando==9)
                    CrudGenero.menuIncluirGenero();
                else if(comando==10)
                    CrudGenero.menuExcluirGenero();
                else if(comando==11)
                    MyIO.println("Nao implementado!");//NAO IMPLEMENTADO
                else if(comando==12)
                    CrudGenero.povoarBancoDeDados();
                else if(comando==13)
                    CrudPlataforma.menuListarPlataformas();
                else if(comando==14)
                    CrudPlataforma.menuBuscarPlataformas();
                else if(comando==15)
                    CrudPlataforma.menuIncluirPlataformas();
                else if(comando==16)
                    CrudPlataforma.menuExcluirPlataformas();
                else if(comando==17)
                    MyIO.println("Nao implementado!");//NAO IMPLEMENTADO
                else if(comando==18)
                    CrudPlataforma.povoarBancoDeDados();
                else if(comando==19)
                {
                    CrudPlataforma.povoarBancoDeDados();
                    CrudGenero.povoarBancoDeDados();
                    CrudJogos.povoarBancoDeDados();
                }
            }
            while(comando!=0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//fim do metodo main
}
