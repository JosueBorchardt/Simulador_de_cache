package main;

import java.util.Random;

public class CacheRandom extends Cache {
    private int[] posicoesOcupadas;

    public CacheRandom(int nSets, int bSize, int associatividade){
        super(nSets, bSize, associatividade);
        posicoesOcupadas = new int[nSets];
        for(int i = 0; i < nSets; i++){
            posicoesOcupadas[i] = 0;
        }
    }

    @Override
    public void tratarFalta(int indice, int newTag){
        if(posicoesOcupadas[indice] == getAssociatividade()){
            Random rand = new Random();
            int posicaoAleatoria = rand.nextInt(getAssociatividade());
            blocos[indice][posicaoAleatoria].setBitValidade(true);
            blocos[indice][posicaoAleatoria].setTag(newTag);
        }else{
            blocos[indice][posicoesOcupadas[indice]].setBitValidade(true);;
            blocos[indice][posicoesOcupadas[indice]].setTag(newTag);            
            posicoesOcupadas[indice]++;
        }
        
    }

}
