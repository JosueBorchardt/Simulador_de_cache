package main;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        
        if (args.length != 6){
            System.out.println(args.length);
            System.out.println("Numero de argumentos incorreto. Utilize:");
            System.out.println("cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> <arquivo_de_entrada>");
            System.exit(1);
        }
        
        int nsets = Integer.parseInt(args[0]);
        int bsize = Integer.parseInt(args[1]);
        int assoc = Integer.parseInt(args[2]);
        String subst = args[3];
        int flagOut = Integer.parseInt(args[4]);        
        String fileName = args[5];
          
        Cache cache = new Cache();
        
        switch(subst){
            case "R":
                cache = new CacheRandom(nsets, bsize, assoc);
                break;
            case "L":
                cache = new CacheLRU(nsets, bsize, assoc);
                break;
            case "F":
                cache = new CacheFifo(nsets, bsize, assoc);
        }

        FileInputStream fileInputStream = null;

        try{
            String filePath = "resources/enderecos/" + fileName;
            fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[4];
            int bitsEndereco;
            
            while(fileInputStream.read(bytes) != -1){
                bitsEndereco = cache.bytesToInt(bytes);
                cache.executarCache(bitsEndereco);
            }
            
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);

        }finally{
            try{
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }              
            
        }       
        
        cache.imprimir(flagOut);

    }
}
