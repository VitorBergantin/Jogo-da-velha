package src;

public class AnalistaSituacional
{
    private Tabuleiro tabDoJogo;

    public AnalistaSituacional (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception("Tabuleiro inválido");

        tabDoJogo = tab;
        // colocar tab em tabDoJogo
    }

    public Marca getMarcaDoVencedor () throws Exception
    {
        int[][] tabAnalise = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Marca marca = tabDoJogo.getMarcaNaPosicao(i, j);

                if (marca == null) {
                    tabAnalise[i][j] = 0;
                }
                else if (marca.getSimbolo() == 'X') {
                    tabAnalise[i][j] = 1;
                }
                else if (marca.getSimbolo() == 'O') {
                    tabAnalise[i][j] = -1;
                }
            }
        }
        
        // linhas e colunas
        for (int i = 0; i < 3; i++) {
            int somaLinha = 0;
            int somaColuna = 0;

            for (int j = 0; j < 3; j++) {
                somaLinha += tabAnalise[i][j];
                somaColuna += tabAnalise[j][i];
            }

            if (somaLinha == 3 || somaColuna == 3)
                return new Marca('X');
            if (somaLinha == -3 || somaColuna == -3)
                return new Marca('O');
        }

        // diagonais
        int diagonalPrincipal = tabAnalise[0][0] + tabAnalise[1][1] + tabAnalise[2][2];

        int diagonalSecundaria = tabAnalise[0][2] + tabAnalise[1][1] + tabAnalise[2][0];

        if (diagonalPrincipal == 3 || diagonalSecundaria == 3)
            return new Marca('X');
        if (diagonalPrincipal == -3 || diagonalSecundaria == -3)
            return new Marca('O');
        return null;
    }

    private boolean tabuleiroCheio () throws Exception
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabDoJogo.getMarcaNaPosicao(i, j) == null)
                    return false;
            }
        }
        return true;
    }

    public boolean fimDeJogo () throws Exception
    {
        if (getMarcaDoVencedor() != null) {
            System.out.println("\nPosição final do jogo:");
            System.out.println(tabDoJogo);
            return true;
        }
        if (tabuleiroCheio() == true) {
            System.out.println("\nPosição final do jogo:");
            System.out.println(tabDoJogo);
            return true;
        }
        return false;
    }

    @Override
    public String toString ()
    {
        return tabDoJogo.toString();
    }

    @Override
    public boolean equals (Object obj)
    {
        return tabDoJogo.equals(obj);
    }

    @Override
    public int hashCode ()
    {
        return tabDoJogo.hashCode();
    }
}