package src.jogador;

import src.*;

public interface Jogador
{
    public Marca getMarca();

    public void facaSuaJogada() throws Exception;

    public String toString();
}