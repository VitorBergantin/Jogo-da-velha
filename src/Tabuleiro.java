package src;

import java.util.Objects;

public class Tabuleiro implements Cloneable
{
    // a principio, toda igual a null
    private Marca matriz [][] = new Marca [3][3];

    public Tabuleiro() {
        this.matriz = new Marca[3][3];
    }

    public Tabuleiro(Marca matrizVelha[][]) throws Exception {
        if (matrizVelha == null) 
            throw new Exception("Matriz passada é inválida");

        this.matriz = new Marca[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = matrizVelha[i][j];
            }
        }
    }

    public void validaLinCol(int lin, int col) throws Exception {
        if (lin < 0 || lin > 2 || col < 0 || col > 2)
            throw new Exception("Valor de posição inválido");
    }

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        validaLinCol(lin, col);

        if (matriz[lin][col] == null)
            return false;
        return true;
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        validaLinCol(lin, col);

        return matriz[lin][col];
    }

    public int[] traduzNumeracao(int num) {
        int linha = (num - 1) / 3;
        int coluna = (num - 1) % 3;

        return new int[]{linha, coluna};
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        validaLinCol(lin, col);

        if (mrc == null)
            throw new Exception("Valor de marca inválido");

        matriz[lin][col] = mrc;
    }

    @Override
    public String toString() {
        return "\nEstado Atual do tabuleiro: \n-------------\n| " +
        (matriz[0][0] == null ? "1" : matriz[0][0]) + " | " +
        (matriz[0][1] == null ? "2" : matriz[0][1]) + " | " +
        (matriz[0][2] == null ? "3" : matriz[0][2]) + " |" +

        "\n| " +
        (matriz[1][0] == null ? "4" : matriz[1][0]) + " | " +
        (matriz[1][1] == null ? "5" : matriz[1][1]) + " | " +
        (matriz[1][2] == null ? "6" : matriz[1][2]) + " |" +

        "\n| " +
        (matriz[2][0] == null ? "7" : matriz[2][0]) + " | " +
        (matriz[2][1] == null ? "8" : matriz[2][1]) + " | " +
        (matriz[2][2] == null ? "9" : matriz[2][2]) + " |" +

        "\n-------------";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tabuleiro outro = (Tabuleiro) obj;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!Objects.equals(this.matriz[i][j], outro.matriz[i][j]))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode ()
    {
        int base = 7;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                base = base * matriz[i][j].hashCode();
            }
        }

        return base;
    }

    public Tabuleiro(Tabuleiro modelo) throws Exception {
        this.matriz = modelo.matriz;
    }

    @Override
    public Object clone ()
    {
        try {
            return new Tabuleiro(this);
        } catch (Exception e) {
            throw new AssertionError("Erro ao clonar tabuleiro");
        }
    }
}