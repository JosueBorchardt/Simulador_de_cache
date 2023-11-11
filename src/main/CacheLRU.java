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
                blocos[indice][i].setTag(blocos[indice][i+1].getTag());
            }
            blocos[indice][getAssociatividade() - 1].setTag(newTag);
        }else{
            blocos[indice][posicoesOcupadas[indice]].setBitValidade(true);
            blocos[indice][posicoesOcupadas[indice]].setTag(newTag);
            posicoesOcupadas[indice]++;
        }
    }

    @Override
    public void atualicaoCasoHit(int indice, int via){
        int newLRU = blocos[indice][via].getTag();
        for(int i = via; i < (posicoesOcupadas[indice] - 1); i++){
            blocos[indice][i].setTag(blocos[indice][i+1].getTag());
        }
        int novaPosicao = posicoesOcupadas[indice] - 1;
        blocos[indice][novaPosicao].setTag(newLRU);
        addHits();
    }
}
