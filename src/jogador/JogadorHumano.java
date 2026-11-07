package src.jogador;

import java.io.*;
import src.Marca;
import src.Tabuleiro;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        tabDoJogo = new Tabuleiro(tab);
        while (true) {
            System.out.println("\n\nQual marca você quer ser? (X/O)");
            String resp = teclado.readLine();

            if (resp.length() > 1) {
                System.out.println("\nDigite apenas uma letra");
                continue;
            }
            if (!resp.toUpperCase().equals("X") && !resp.toUpperCase().equals("O")) {
                System.out.println("\nDigite apenas X ou O");
                continue;
            }
            if (resp.toUpperCase().equals("X")) {
                marca = new Marca('X');
                break;
            }
            if (resp.toUpperCase().equals("O")) {
                marca = new Marca('O');
                break;
            }
        }
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
    {
        tabDoJogo = new Tabuleiro(tab);
        marca = new Marca(mrc.getSimbolo());
    }

    public Marca getMarca ()
    {
        return marca;
    }

    public void facaSuaJogada () throws Exception
    {
        while(true) {
            System.out.println("\nOnde você quer jogar?");
            tabDoJogo.toString();
            System.out.print("\n$: ");
            String resp = teclado.readLine();

            try {
                int r = Integer.parseInt(resp);

                if (r < 1 || r > 9) {
                    System.out.println("\nValor númerico inválido");
                    continue;
                }

                int[] pos = tabDoJogo.traduzNumeracao(r);
                if (tabDoJogo.haMarcaNaPosicao(pos[0], pos[1])) {
                    System.out.println("Esta posição já tem uma marca");
                    continue;
                }

                tabDoJogo.setMarcaNaPosicao(marca, pos[0], pos[1]);
                return;

            } catch (Exception e) {
                System.out.println("\nNão digite letras, por favor");
            }
        }
    }

    public void vcGanhou ()
    {
        System.out.println("\n*******\nVocê ganhou! (Impossível)\n*******");
    }

    public void vcPerdeu ()
    {
        System.out.println("\nVocê perdeu! hahahahaha");
    }

    public void deuVelha ()
    {
        System.out.println("\nDeu velha! Bom jogo.");
    }

    public boolean querMaisUmaPartida () throws Exception
    {
        while (true) {
            System.out.println("\nGostaria de jogar de novo? (s/n)");
            String resposta = teclado.readLine();

            if (resposta.length() > 1) {
                System.out.println("\nDigite apenas uma letra");
                continue;
            }
            if (!resposta.toLowerCase().equals("s") && !resposta.toLowerCase().equals("n")) {
                System.out.println("\nDigite apenas s ou n");
                continue;
            }
            if (resposta.toLowerCase().equals("s")) {
                return true;
            }
            if (resposta.toLowerCase().equals("n")) {
                return false;
            }
        }
    }

    @Override 
    public String toString() {
        return "\nSua jogada" + tabDoJogo.toString();
    }
}