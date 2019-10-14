package packageone;

public class Main {

    public static void main(String[] args)
    {
        MyIO.println("Bem vindo ao CRUD de Jogos");
        int comando=0;
        do
        {
            boolean erroDigitacaoComando=false;
            do
            {
                MyIO.println("Digite o CRUD que deseja executar:");
                MyIO.println("3 - CRUD de Genero");
                MyIO.println("2 - CRUD de Plataforma");
                MyIO.println("1 - CRUD de Jogos");
                MyIO.println("0 - Sair");
                try
                {
                    comando=MyIO.readInt();
                    if(comando<0 || comando>3)
                        throw new Exception("Comando inválido!");

                    erroDigitacaoComando=false;
                }
                catch (Exception e)
                {
                    MyIO.println(e.toString());
                    erroDigitacaoComando=true;
                }
            }
            while(erroDigitacaoComando);

            if(comando==1)
                CrudJogos.main(null);
            else if(comando==2)
                CrudPlataforma.main(null);
            else if(comando==3)
                CrudGenero.main(null);
        }
        while(comando!=0);
    }
}
