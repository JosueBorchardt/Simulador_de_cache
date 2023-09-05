package main;

public class CacheLRU extends Cache {
    //Controla a quantidade total de posições armazenadas por conjunto
    private int[] posicoesOcupadas;

    public CacheLRU(int nSets, int bSize, int associatividade){
        super(nSets, bSize, associatividade);
        posicoesOcupadas = new int [nSets];
        for(int i = 0; i < nSets; i++){
            posicoesOcupadas[i] = 0;
        }
    }

    @Override
    public void tratarFalta(int indice, int newTag){
        if(posicoesOcupadas[indice] == getAssociatividade()){
            for(int i = 0; i < (getAssociatividade() - 1); i++){
                tag[indice][i] = tag[indice][i+1];
            }
            tag[indice][getAssociatividade() - 1] = newTag;
        }else{
            bitValidade[indice][posicoesOcupadas[indice]] = true;
            tag[indice][posicoesOcupadas[indice]] = newTag;
            posicoesOcupadas[indice]++;
        }
    }

    @Override
    public void atualicaoCasoHit(int indice, int via){
        int newLRU = tag[indice][via];
        for(int i = via; i < (posicoesOcupadas[indice] - 1); i++){
            tag[indice][i] = tag[indice][i+1];
        }
        int novaPosicao = posicoesOcupadas[indice] - 1;
        tag[indice][novaPosicao] = newLRU;
        addHits();
    }
}
