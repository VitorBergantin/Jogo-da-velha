package src;

import src.jogador.*;

public class Velha
{
    public static void main (String args []) throws Exception
    {
        Tabuleiro           tabDoJogo;

        Jogador             jogador  = null;
        JogadorHumano       jogadorH = null;
        JogadorAutomatico   jogadorA = null;

        AnalistaSituacional analista;

        boolean jogadorHumanoComeca = (int)(Math.random()*2) == 0;

        do
        {
            try
            {
                tabDoJogo = new Tabuleiro ();

                if (jogadorHumanoComeca)
                {
                    jogadorH = new JogadorHumano (tabDoJogo);
                    jogadorA = new JogadorAutomatico
                              (tabDoJogo,
                               new Marca(jogadorH.getMarca().getSimbolo()=='X'?'O':'X'));
                }
                else
                {
                    jogadorA = new JogadorAutomatico (tabDoJogo);
                    jogadorH = new JogadorHumano
                              (tabDoJogo,
                               new Marca(jogadorA.getMarca().getSimbolo()=='X'?'O':'X'));
                    System.out.println("\n\nJogador automático começa, você é: " + jogadorH.getMarca());
                }

                analista = new AnalistaSituacional (tabDoJogo);

                if (jogadorHumanoComeca)
                    jogador=jogadorH;
                else
                    jogador=jogadorA;

                do
                {
                    System.out.println(jogador);
                    jogador.facaSuaJogada();

                    if (jogador==jogadorH)
                        jogador=jogadorA;
                    else
                        jogador=jogadorH;
                }
                while (!analista.fimDeJogo());

                // tratando a null pointer exception
                Marca vencedor = analista.getMarcaDoVencedor();

                if (vencedor == null)
                    jogadorH.deuVelha();
                else if (vencedor.equals(jogadorH.getMarca()))
                    jogadorH.vcGanhou();
                else
                    jogadorH.vcPerdeu();

                jogadorHumanoComeca = !jogadorHumanoComeca;
            }
            catch (Exception e)
            {
                System.out.println("Deu erro: " + e);
            }
        }
        while (jogadorH.querMaisUmaPartida());
    }
}