package src;

public class Marca
{
    private char simbolo;

    private void validaMarca(char m) {
        if (m != 'X' && m != 'x' && m != 'O' && m != 'o') 
            throw new IllegalArgumentException("Marca inválida");
    }

    public Marca (char smb)
    {
        validaMarca(smb);
        this.simbolo = Character.toUpperCase(smb);

        // validar smb, garantindo que ele
        // seja 'X' ou 'O' e coloca-lo em
        // simbolo
        // dar excecao em caso de valor invalido
    }

    public char getSimbolo ()
    {
        return this.simbolo;
    }

    @Override
    public String toString ()
    {
        return ""+simbolo;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Marca outro = (Marca) obj;
        return this.simbolo == outro.simbolo;
        // retornar true se this for igual a obj ou false caso contrario
    }

    @Override
    public int hashCode ()
    {
        return Character.hashCode(simbolo);
    }
}