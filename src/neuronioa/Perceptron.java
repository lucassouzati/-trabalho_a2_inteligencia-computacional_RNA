/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioa;

/**
 *
 * @author Tarcisio
 */
public class Perceptron {
    private int[][][] x;
    private double[][] w;
    private int[][]d;
    private double a, e;
    private int max_int;
    
    private boolean[] neuronios_ok;
    
    private int[][] saidas;

    public Perceptron(int[][][] x, double[][] w, int[][] d, double a, double e, int max_int) {
        this.x = x;
        this.w = w;
        this.d = d;
        this.a = a; //Taxa de aprendizagem
        this.e = e; //Margem de erro
        this.max_int = max_int; //Máximo de iterações
        
        this.neuronios_ok = new boolean[w.length];
        
        this.saidas = new int[w.length][4]; // inicializando matriz de  saidas
        
        for(int i = 0; i < saidas.length; i++){
            for(int j = 0; j < saidas[i].length; j++){
                this.saidas[i][j] = -1;
            }
        }
        
        for (int i = 0; i < w.length;i++)
        {
            this.neuronios_ok[i] = false;
        }
    }
    
    public void treinar(){
        double u=0;
        int epocas=0,y=0,ordem=0;
        
        int saidas_encontradas = 0; //Contador de  saidas encontradas, quando for 4 quer dizer que acertou todas
        
        boolean continua=true;
        while ((epocas<this.max_int)&&(continua)){
            continua=false;
            for(int linha=0; linha<x.length; linha++){
                
                System.out.println("Iteração "+ epocas);
                System.out.println("Neurônio " + linha + ": \n\n");
                saidas_encontradas = 0;
                //ordem = 0;
                
                if (!this.neuronios_ok[linha]){
                    for (int i=0;i<this.x[linha][0].length;i++){
                        //x1*w1+x2*w2+w0 
                        if(linha < x.length)
                        {
                            u=this.x[linha][0][i]*this.w[linha][0] + this.x[linha][1][i]*this.w[linha][1] + this.w[linha][2];

                            //Calculando a saída
                            if (u>=0){
                                y=1;
                            }else{
                                y=0;
                            }

                            //Mostrando a aprendizagem
                            System.out.println("(" + this.x[linha][0][i] + 
                                    "  ," + this.x[linha][1][i] + 
                                    ")  ="+y  + "(esperado: "+ this.d[linha][i] +")"+
                                    "  w"+linha+"1="+ this.w[linha][0] +
                                     "  w"+linha+"2=" + this.w[linha][1] +
                                      "  w0=" + this.w[linha][2]);
                            
                            //System.out.println("Resultado esperado:" + this.d[linha][i]);

                            //Comparando a saída obtida com a desejável
                            if((y==1)&&(this.d[linha][i]==0)){
                                ajustar_pesos(false,ordem, linha); //Deve diminuir os pesos
                               ordem++;
                                continua=true;
                            }else if ((y==0)&&(this.d[linha][i]==1)){

                                ajustar_pesos(true,ordem, linha); //Deve aumentar os pesos
                               ordem++;
                                continua=true;
                            }else
                            {
                                this.saidas[linha][i] = y;
                                saidas_encontradas++;
                            }
                           // ordem++;
                            
                            if (ordem>=this.w[linha].length){
                                ordem=0;
                            }
                            System.out.println("_____________________________");
                        }
                        
//                        if(this.saidas[linha][0] != -1 && this.saidas[linha][1] != -1){
//                            this.neuronios_ok[linha] = true;
//                        }
                    }
                    if (saidas_encontradas == 4)
                    {
                        this.neuronios_ok[linha] = true;
                    }
                }
                System.out.println("Saidas encontradas: [" + this.saidas[linha][0] + ", " + this.saidas[linha][1] + ", " + this.saidas[linha][2]+ ", " + this.saidas[linha][3] + "]");
                System.out.println("_____________________________");
            }
            
           epocas++;
        }
    }
    
    private void ajustar_pesos(boolean aumentar, int ordem, int linha){
        if (aumentar){
            this.w[linha][ordem]+=this.a;
        }else{
            this.w[linha][ordem]-=this.a;
        }
    }
}
