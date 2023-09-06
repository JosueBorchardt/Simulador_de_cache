package main;

public class Cache {
    private int nAcessos;
    private int nMiss;
    private int nHits;
    private int nMissCompulsorio;
    private int nMissCapacidade;
    private int nMissConflito;

    private int posicoesCacheTotal;

    private int associatividade;

    private int tamanhoIndice;
    private int tamanhoOffSet;

    protected boolean[][] bitValidade;
    protected int[][] tag;

    public Cache(){
        
    }

    public Cache(int nSets, int bSize, int associatividade){
        nAcessos = 0;
        nMiss = 0;
        nHits = 0;
        nMissCompulsorio = 0;
        nMissCapacidade = 0;
        nMissConflito = 0;

        posicoesCacheTotal = nSets*associatividade;

        this.associatividade = associatividade;

        tamanhoOffSet = (int) (Math.log(bSize)/Math.log(2));
        tamanhoIndice = (int) (Math.log(nSets)/Math.log(2));
        
        bitValidade = new boolean[nSets][associatividade];
        tag = new int[nSets][associatividade];

        for(int i = 0; i < nSets; i++){
            for(int j = 0; j < associatividade; j++){
               bitValidade[i][j] = false; 
            }            
        }
    }

    public int bytesToInt(byte[] bytes){
        int bits = 0;
        for(int i = 0; i < 4; i++){
            bits = (bits << 8) | (bytes[i] & 0xFF);
        }
        return bits;
    }

    public void executarCache(int endereco){
        int mascara;
        int indice;
        int newTag;
        
        endereco = endereco >> tamanhoOffSet; 
        
        mascara = (int) (Math.pow(2, tamanhoIndice) - 1);
        
        indice = endereco & mascara;
        newTag = endereco >> tamanhoIndice;

        int verificarMiss = 0;
        int verificarMissCompulsorio = 0;
        for(int i = 0; i < associatividade; i++){
           if(bitValidade[indice][i]){
                if(tag[indice][i] == newTag){
                    atualicaoCasoHit(indice, i);
                    break;    
                }           
           }else{
                verificarMissCompulsorio++;
           }
            verificarMiss++; 
        }

        if(verificarMiss == associatividade){
            this.tratarFalta(indice, newTag);
            nMiss++;
            if(verificarMissCompulsorio > 0){
                nMissCompulsorio++;
                
            }else{
                if(nMissCompulsorio == posicoesCacheTotal){
                    nMissCapacidade++;
                }else{
                    nMissConflito++;
                }
            }
        }
        
        nAcessos++;
       
    }

    public void tratarFalta(int indice, int newTag){        
        
    }

    public void atualicaoCasoHit(int indice, int via){
        addHits();
    }

    public void addHits(){
        nHits++;
    }

    public void imprimir(int flagOut){
        double taxaHit, taxaMiss, taxaMissCompulsorio, taxaMissCapacidade, taxaMissConflito;
        taxaMiss = (double) nMiss/nAcessos;
        taxaHit = (double) nHits/nAcessos;
        taxaMissCompulsorio = (double) nMissCompulsorio/nMiss;
        taxaMissCapacidade = (double) nMissCapacidade/nMiss;
        taxaMissConflito = (double) nMissConflito/nMiss;

        taxaMiss = Math.round(taxaMiss * 10000.0)/10000.0;
        taxaHit = Math.round(taxaHit * 10000.0)/10000.0;
        taxaMissCompulsorio = Math.round(taxaMissCompulsorio * 10000.0)/10000.0;
        taxaMissCapacidade = Math.round(taxaMissCapacidade * 10000.0)/10000.0;
        taxaMissConflito = Math.round(taxaMissConflito * 10000.0)/10000.0;

        if(flagOut == 1){
            System.out.println(nAcessos + " " + taxaHit + " "+ taxaMiss +  " " + taxaMissCompulsorio + " " + taxaMissCapacidade + " " + taxaMissConflito);
        }else{
            System.out.println("Número de acessos: " + nAcessos);
            System.out.println("Número de hits: " + nHits);
            System.out.println("Taxa de hits: " + taxaHit);
            System.out.println("Número de misses: " + nMiss);
            System.out.println("Taxa de misses: " + taxaMiss);
            System.out.println("Número de misses compulsórios: " + nMissCompulsorio);
            System.out.println("Taxa de misses compulsórios: " + taxaMissCompulsorio);
            System.out.println("Número de misses de conflito: " + nMissConflito);
            System.out.println("Taxa de misses de conflito: " + taxaMissConflito);
            System.out.println("Número de misses de capacidade: " + nMissCapacidade);
            System.out.println("Taxa de misses de capacidade: " + taxaMissCapacidade);            
        }
    }

    public int getAssociatividade(){
        return associatividade;
    }
}
