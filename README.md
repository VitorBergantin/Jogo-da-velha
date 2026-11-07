# Jogo da velha

- Aluno: Vítor Bergantin Ribeiro
- Ra: 25006595

## Instruções de compilação e execução:
Não é necessário nada demais para que o projeto funcione, apenas colocar todos os arquivos dentro de uma pasta comum, que na versão original (esta) é a pasta src, dentro de uma pasta pai, que aqui é a pasta Velha. Depois, coloque todos os arquivos que começam com jogador dentro de uma pasta jogador, assim já deve ser possível utilizar o programa.  

Embora seja estranho ter uma pasta aninhada na outra, o Java não conseguiu reconhecer que jogador estava na mesma pasta que o Tabuleiro e afins, por isso o intermediário src.

## Processo de pensamento do Jogador Automático

### São feitas 4 checagens antes de qualquer coisa  

#### -> Se estiver prestes a ganhar, joga para ganhar

**Ganhando na diagonal principal**: 

    if ((atual == 'X' && diagonalPrincipal == 2) || (atual == 'O' && diagonalPrincipal == -2))  

**Ganhando na diagonal secundaria**:  

    else if ((atual == 'X' && diagonalSecundaria == 2) || (atual == 'O' && diagonalSecundaria == -2))  

**Ganhando na linha**: 

    if (atual == 'X' && (somaLinha == 2 || somaColuna == 2))

**Ganhando na coluna**:  

    if (atual == 'O' && (somaLinha == -2 || somaColuna == -2))

É checado nas diagonais primeiro, pois para checar nas linhas e colunas, eu preciso fazer em sequência, enquanto a 
lógica das diagonais é feita fora do For, então pode ser quebrada ao meio, caso não fosse quebrada ao meio, eu pararia 
de dar preferência a ganhar  

#### -> Se o inimigo estiver prestes a ganhar, defende

**Perdendo na linha**: 

    if (atual == 'X' && (somaLinha == -2 || somaColuna == -2))

**Perdendo na coluna**:  

    if (atual == 'O' && (somaLinha == 2 || somaColuna == 2))

**Perdendo na diagonal principal**: 

    if (diagonalPrincipal == -2 || diagonalPrincipal == 2)  

**Perdendo na diagonal secundaria**:  

    else if (diagonalSecundaria == -2 || diagonalSecundaria == 2) 

#### -> Se não há uma vitória rápida, joga no meio
Vendo se o meio está livre, caso esteja, jogo lá:

    if (tabDoJogo.haMarcaNaPosicao(1, 1))
        //////
    else {
        tabDoJogo.setMarcaNaPosicao(marca, 1, 1);
    } 

#### -> Caso já tenham pego o meio, jogo nos cantos
Jogando nos cantos se conseguir:  

    if (tabDoJogo.haMarcaNaPosicao(1, 1)) {
        if (!jogaNosCantos()) ///
        ///
    }

#### Em último caso, jogo aleatório
Esta parte é bem rara de acontecer, pq para que isso aconteça, tenho que ter o meio preenchido, os 4 cantos e nenhuma 
vitória imediata, ou seja, só se aplica para semicentros quando o jogo já deu velha (5 ou mais jogadas e nenhuma 
vitória próxima)

        while (true) {
            int num = (int)(Math.random() * 9) + 1;
            int[] aleatorio =  tabDoJogo.traduzNumeracao(num);

            if (tabDoJogo.haMarcaNaPosicao(aleatorio[0], aleatorio[1]))
                continue;

            tabDoJogo.setMarcaNaPosicao(marca, aleatorio[0], aleatorio[1]);
            return;
        }
