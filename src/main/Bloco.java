package main;

public class Bloco {
    private boolean bitValidade;
    private int tag;

    public Bloco(){
        bitValidade = false;
        tag = 0;
    }

    public boolean getBitValidade(){
        return bitValidade;
    }

    public int getTag(){
        return tag;
    }

    public void setBitValidade(boolean bitValidade){
        this.bitValidade = bitValidade;
    }

    public void setTag(int tag){
        this.tag = tag;
    }
}
