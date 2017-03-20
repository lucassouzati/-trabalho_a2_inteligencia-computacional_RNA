/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuronioa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarcisio
 */
public class Perceptron {
    private int[][] x;
    private double[][] w;
    private int[][]d;
    private double a, e;
    private int max_int;
    
    private boolean[] neuronios_ok;
    
    private int[][] saidas;
    private int[][] resultados_corretos;
    
    private List<Neuronio> neuronios;

    public Perceptron(int[][] x, double[][] w, int[][] d, double a, double e, int max_int) {
        this.x = x;
        this.w = w;
        this.d = d;
        this.a = a; //Taxa de aprendizagem
        this.e = e; //Margem de erro
        this.max_int = max_int; //Máximo de iterações
        
        this.neuronios_ok = new boolean[w.length];
        
        //this.neuronios.get(linha).getSaidas()int[w.length][4]; // inicializando matriz de  saidas
        this.resultados_corretos = new int[w.length][4]; // inicializando matriz de  saidas
        
//        for(int i = 0; i < saidas.length; i++){
//            for(int j = 0; j < saidas[i].length; j++){
//                this.neuronios.get(linha).getSaidas()= -1;
//            }
//        }
        
        for (int i = 0; i < w.length;i++)
        {
            this.neuronios_ok[i] = false;
        }
        this.neuronios = new ArrayList<Neuronio>();
        for (int i = 0; i < w.length;i++)
        {
            Neuronio neuronio = new Neuronio(this.w[i], this.x, this.d[i], this.a);
            this.neuronios.add(neuronio);
        }
    }
    
    public void treinar(){
        double u=0;
        int epocas=0,y=0,ordem=0;
        
        int saidas_encontradas = 0; //Contador de  saidas encontradas, quando for 4 quer dizer que acertou todas
        
        boolean continua=true;
        while ((epocas<this.max_int)&&(continua)){
            //continua=false;
            System.out.println("Época "+ epocas);
            for(int linha=0; linha<neuronios.size(); linha++){
                
                //Verifica se este neurônio já não está ok
                if(!this.neuronios_ok[linha]){
                    if(linha<neuronios.size() - 1){//Caso não seja o último neurônio
                   
                        System.out.println("Neurônio " + (linha+1) + ": \n\n");

                        this.neuronios.get(linha).calcular();

                        System.out.println("Saidas encontradas: [" + this.neuronios.get(linha).getSaidas()[0] + ", " + this.neuronios.get(linha).getSaidas()[1] + ", " + this.neuronios.get(linha).getSaidas()[2]+ ", " + this.neuronios.get(linha).getSaidas()[3] + "]");
                        System.out.println("_____________________________");

                        //Caso todas as saídas deste neurônio estejam de acordo com as desejadas, este neuronio está ok
                        if(this.neuronios.get(linha).getSaidas_corretas()[0] && this.neuronios.get(linha).getSaidas_corretas()[1] && this.neuronios.get(linha).getSaidas_corretas()[2] && this.neuronios.get(linha).getSaidas_corretas()[3]){
                                this.neuronios_ok[linha] = true;
                        }
                    }
                    else{//Caso seja o último neurônio
                        
                        System.out.println("Neurônio " + (linha+1) + ": \n\n");
                         
                        int[][] entradas_ultimo_neuronio = new int[2][4];
                        
                        //Este recebe como entrada, as saídas dos últimos neurônios
                        entradas_ultimo_neuronio[0] = this.neuronios.get(0).getSaidas();
                        entradas_ultimo_neuronio[1] = this.neuronios.get(1).getSaidas();
                        this.neuronios.get(linha).setEntradas(entradas_ultimo_neuronio);
                        
                        this.neuronios.get(linha).calcular();
                        
                        System.out.println("Saidas encontradas: [" + this.neuronios.get(linha).getSaidas()[0] + ", " + this.neuronios.get(linha).getSaidas()[1] + ", " + this.neuronios.get(linha).getSaidas()[2]+ ", " + this.neuronios.get(linha).getSaidas()[3] + "]");
                        System.out.println("_____________________________");
                        
                         if(this.neuronios.get(linha).getSaidas_corretas()[0] && this.neuronios.get(linha).getSaidas_corretas()[1] && this.neuronios.get(linha).getSaidas_corretas()[2] && this.neuronios.get(linha).getSaidas_corretas()[3]){
                                this.neuronios_ok[linha] = true;
                        }
                    }
                }
                
            }
            epocas++;
            System.out.println("_____________________________");
            continua = false;
            
            //Quando todos neurônios estiverem ok, continua fica como falso e o algoritmo para
            for(int i = 0; i< this.neuronios_ok.length; i++){
                if(!neuronios_ok[i])
                    continua = true;
            }
            
           
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
