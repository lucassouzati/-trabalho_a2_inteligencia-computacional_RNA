/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioa;

/**
 *
 * @author lsiqueira
 */
public class Neuronio {
    private double[] pesos;
    private int[][] entradas;
    private int[] saidas;
    private int[] saidas_desejadas;
    private boolean[] saidas_corretas;
    private int ordem;
    private double a;

    public void calcular(){
        double u=0;
        int epocas=0,y=0;
        for (int i=0;i<this.entradas[0].length;i++){
                //x1*w1+x2*w2+w0 
                u=this.entradas[0][i]*this.pesos[0] + this.entradas[1][i]*this.pesos[1] + this.pesos[2];
                
                //Calculando a saída
                if (u>=0){
                    y=1;
                }else{
                    y=0;
                }
                
                //Mostrando a aprendizagem
                System.out.println("(" + this.entradas[0][i] + 
                        "  ," + this.entradas[1][i] + 
                        ")  ="+y  +
                        "  w1="+ this.pesos[0] +
                         "  w2=" + this.pesos[1] +
                          "  w0=" + this.pesos[2]);
                
                //Comparando a saída obtida com a desejável
                if((y==1)&&(this.saidas_desejadas[i]==0)){
                    ajustar_pesos(false); //Deve diminuir os pesos
                    ordem++;
                    //continua=true;
                }else if ((y==0)&&(this.saidas_desejadas[i]==1)){
                    ajustar_pesos(true); //Deve aumentar os pesos
                    ordem++;
                    //continua=true;
                }else
                {
                    this.saidas_corretas[i] = true;
                }
                if (ordem>=this.pesos.length){
                    ordem=0;
                 }
            }
    }
    
    private void ajustar_pesos(boolean aumentar){
        if (aumentar){
            this.pesos[this.ordem]+=this.a;
        }else{
            this.pesos[this.ordem]-=this.a;
        }
    }
    
    
    public Neuronio() {
    }

    public Neuronio(double[] pesos, int[][] entradas, int[] saidas_desejadas, double a) {
        this.pesos = pesos;
        this.entradas = entradas;
        this.saidas = new int[4];
        this.saidas_desejadas = saidas_desejadas;
        this.saidas_corretas = new boolean[4];
        this.ordem = 0;
        this.a = a;
    }

    public double[] getPesos() {
        return pesos;
    }

    public void setPesos(double[] pesos) {
        this.pesos = pesos;
    }

    public int[][] getEntradas() {
        return entradas;
    }

    public void setEntradas(int[][] entradas) {
        this.entradas = entradas;
    }

    public int[] getSaidas() {
        return saidas;
    }

    public void setSaidas(int[] saidas) {
        this.saidas = saidas;
    }

    public int[] getSaidas_desejadas() {
        return saidas_desejadas;
    }

    public void setSaidas_desejadas(int[] saidas_desejadas) {
        this.saidas_desejadas = saidas_desejadas;
    }

    public boolean[] getSaidas_corretas() {
        return saidas_corretas;
    }

    public void setSaidas_corretas(boolean[] saidas_corretas) {
        this.saidas_corretas = saidas_corretas;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    
    
}
