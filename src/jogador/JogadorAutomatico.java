package src.jogador;

import src.Marca;
import src.Tabuleiro;

public class JogadorAutomatico implements Jogador
{
    private Marca     marca     = null;
    private Tabuleiro tabDoJogo = null;

    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {   
        tabDoJogo = tab;

        if ((int)(Math.random()*2) == 0)
            marca=new Marca('X');
        else
            marca=new Marca('O');
    }

    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        tabDoJogo = tab;
        
         marca = new Marca(mrc.getSimbolo());
    }

    public Marca getMarca ()
    {
        return marca;
    }

    public void facaSuaJogada ()
    {
        char atual = marca.getSimbolo();
        try
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

            int diagonalPrincipal = tabAnalise[0][0] + tabAnalise[1][1] + tabAnalise[2][2];

            int diagonalSecundaria = tabAnalise[0][2] + tabAnalise[1][1] + tabAnalise[2][0];

            // prioridade para o jogadorA ganhar, vou tratar todos os casos onde ganha primeiro
            // ganhando diagonal
            if ((atual == 'X' && diagonalPrincipal == 2) || (atual == 'O' && diagonalPrincipal == -2)) {
                jogaDiagonalPrincipal();
                return;
            }
            else if ((atual == 'X' && diagonalSecundaria == 2) || (atual == 'O' && diagonalSecundaria == -2)) { 
               jogaDiagonalSecundaria();
                return;
            }

            int j;
            // ganhando linha ou coluna
            for (int i = 0; i < 3; i++) {
                int somaLinha = 0;
                int somaColuna = 0;

                for (j = 0; j < 3; j++) {
                    somaLinha += tabAnalise[i][j];
                    somaColuna += tabAnalise[j][i];
                }

                if ((atual == 'X' && (somaLinha == 2 || somaColuna == 2)) ||
                    (atual == 'O' && (somaLinha == -2 || somaColuna == -2))) {

                    if (atual == 'X') {
                        if (somaLinha == 2)
                            jogaLinha(i);
                        else
                            jogaColuna(i);
                    } else {
                        if (somaLinha == -2)
                            jogaLinha(i);
                        else
                            jogaColuna(i);
                    }

                    return;
                }
            }
            // perdendo linha ou coluna
            for (int i = 0; i < 3; i++) {
                int somaLinha = 0;
                int somaColuna = 0;

                for (j = 0; j < 3; j++) {
                    somaLinha += tabAnalise[i][j];
                    somaColuna += tabAnalise[j][i];
                }

                if ((atual == 'X' && (somaLinha == -2 || somaColuna == -2)) ||
                    (atual == 'O' && (somaLinha == 2 || somaColuna == 2))) {

                    if (atual == 'X') {
                        if (somaLinha == -2)
                            jogaLinha(i);
                        else
                            jogaColuna(i);
                    } else {
                        if (somaLinha == 2)
                            jogaLinha(i);
                        else
                            jogaColuna(i);
                    }

                    return;
                }
            }

            // perdendo diagonal
            if (diagonalPrincipal == -2 || diagonalPrincipal == 2) {
                jogaDiagonalPrincipal();
                return;
            }

            else if (diagonalSecundaria == -2 || diagonalSecundaria == 2) {
                jogaDiagonalSecundaria();
                return;
            } 

            //jogando esperto (foco nos cantos e meio, se não der, aleatório)
            if (tabDoJogo.haMarcaNaPosicao(1, 1)) {
                if (!jogaNosCantos()) {
                    while (true) {
                        int num = (int)(Math.random() * 9) + 1;
                        int[] aleatorio =  tabDoJogo.traduzNumeracao(num);

                        if (tabDoJogo.haMarcaNaPosicao(aleatorio[0], aleatorio[1]))
                            continue;

                        tabDoJogo.setMarcaNaPosicao(marca, aleatorio[0], aleatorio[1]);
                        return;
                    }
                }
                return;
            } else {
                tabDoJogo.setMarcaNaPosicao(marca, 1, 1);
            }
        }
        catch (Exception erro)
        {} // ignorando pois sei que n o estou usando posicao invalida nem comparando com equals um null
    }

    private void jogaDiagonalPrincipal() throws Exception {
        if (!tabDoJogo.haMarcaNaPosicao(0, 0))
            tabDoJogo.setMarcaNaPosicao(marca, 0, 0);
        else if (!tabDoJogo.haMarcaNaPosicao(1, 1))
            tabDoJogo.setMarcaNaPosicao(marca, 1, 1);
        else if (!tabDoJogo.haMarcaNaPosicao(2, 2))
            tabDoJogo.setMarcaNaPosicao(marca, 2, 2);
    }

    private void jogaLinha(int l) throws Exception {
        if (!tabDoJogo.haMarcaNaPosicao(l, 0)) {
            tabDoJogo.setMarcaNaPosicao(marca, l, 0);
            return;
        }
        if (!tabDoJogo.haMarcaNaPosicao(l, 1)) {
            tabDoJogo.setMarcaNaPosicao(marca, l, 1);
            return;
        }
        if (!tabDoJogo.haMarcaNaPosicao(l, 2)) {
            tabDoJogo.setMarcaNaPosicao(marca, l, 2);
            return;
        }
    }

    private void jogaColuna(int c) throws Exception {
        if (!tabDoJogo.haMarcaNaPosicao(0, c)) {
            tabDoJogo.setMarcaNaPosicao(marca, 0, c);
            return;
        }
        if (!tabDoJogo.haMarcaNaPosicao(1, c)) {
            tabDoJogo.setMarcaNaPosicao(marca, 1, c);
            return;
        }
        if (!tabDoJogo.haMarcaNaPosicao(2, c)) {
            tabDoJogo.setMarcaNaPosicao(marca, 2, c);
            return;
        }
    }

    private void jogaDiagonalSecundaria() throws Exception {
        if (!tabDoJogo.haMarcaNaPosicao(2, 0))
            tabDoJogo.setMarcaNaPosicao(marca, 2, 0);
        else if (!tabDoJogo.haMarcaNaPosicao(1, 1))
            tabDoJogo.setMarcaNaPosicao(marca, 1, 1);
        else if (!tabDoJogo.haMarcaNaPosicao(0, 2))
            tabDoJogo.setMarcaNaPosicao(marca, 0, 2);
    }

    private boolean jogaNosCantos() throws Exception {
        int ordem = (int) (Math.random() * 4) + 1;

        switch (ordem) {
            case 1: {
                if (!tabDoJogo.haMarcaNaPosicao(2, 2)) {
                    tabDoJogo.setMarcaNaPosicao(marca, 2, 2);
                    return true;
                }
                break;
            }
            case 2: {
               if (!tabDoJogo.haMarcaNaPosicao(0, 2)) {
                    tabDoJogo.setMarcaNaPosicao(marca, 0, 2);
                    return true;
                }
                break;
            }  
            case 3: {
                if (!tabDoJogo.haMarcaNaPosicao(2, 0)) {
                    tabDoJogo.setMarcaNaPosicao(marca, 2, 0);
                    return true;
                }
                break;
            }
            case 4: {
                if (!tabDoJogo.haMarcaNaPosicao(0, 0)) {
                    tabDoJogo.setMarcaNaPosicao(marca, 0, 0);
                    return true;
                }
                break;
            }
            default:
        }
        if (!tabDoJogo.haMarcaNaPosicao(2, 2)) {
            tabDoJogo.setMarcaNaPosicao(marca, 2, 2);
            return true;
        }
        if (!tabDoJogo.haMarcaNaPosicao(0, 2)) {
            tabDoJogo.setMarcaNaPosicao(marca, 0, 2);
            return true;
        }
        if (!tabDoJogo.haMarcaNaPosicao(2, 0)) {
            tabDoJogo.setMarcaNaPosicao(marca, 2, 0);
            return true;
        }
        if (!tabDoJogo.haMarcaNaPosicao(0, 0)) {
            tabDoJogo.setMarcaNaPosicao(marca, 0, 0);
            return true;
        }

        return false;
    }

    @Override 
    public String toString() {
        return "\nJogada do computador" + tabDoJogo.toString();
    }
}