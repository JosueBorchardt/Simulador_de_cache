package main;

public class CacheFifo extends Cache {
    private int[] posicaoDeSubstituicao;

    public CacheFifo(int nSets, int bSize, int associatividade){
        super(nSets, bSize, associatividade);
        posicaoDeSubstituicao = new int[nSets];
        for(int i = 0; i < nSets; i++){
            posicaoDeSubstituicao[i] = 0;
        }
    }

    @Override
    public void tratarFalta(int indice, int newTag){
        int posicao = posicaoDeSubstituicao[indice];
        blocos[indice][posicao].setBitValidade(true);
        blocos[indice][posicao].setTag(newTag);
        posicaoDeSubstituicao[indice] = (posicaoDeSubstituicao[indice] + 1)%this.getAssociatividade();
    }
    
}
