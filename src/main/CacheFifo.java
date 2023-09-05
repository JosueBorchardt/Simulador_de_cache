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
        bitValidade[indice][posicao] = true;
        tag[indice][posicao] = newTag;
        posicaoDeSubstituicao[indice] = (posicaoDeSubstituicao[indice] + 1)%this.getAssociatividade();
    }
    
}
