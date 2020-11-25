package geneticos;

import java.util.Random;
import javax.swing.JOptionPane;

/** 
 * @author GabrielCruz
 * 29 de Abril de 2012 
 */
public class Geneticos {

    /**
     * @param args the command line arguments
     */
    static int filas=10;
    static int columnas=5;
    static int Nganadores=5;
    
    static String [][] Poblacion = new  String [filas][columnas];
    static String [][] PoblacionTem = new  String [filas][columnas];
    static String []  Parejas = new  String [filas];    
    static String []  Ganadores = new  String [Nganadores];    
    static double sumaToria=0;
    
    /*Inicia la Poblacion de Individuos*/
    public static void IniciarPoblacion(String [][] Poblacion){
      System.out.println("***********************************************************");
      System.out.println("********************Iniciar Poblacion***********************");     
      System.out.println("***********************************************************");                
      String Individuo="";
      Random ri = new Random();
      Random ro = new Random();
      Random ra = new Random();
      
      for(int i=0;i<Parejas.length;i++){
          for(int o=0;o<Parejas.length-4;o++){
              
                  
                Individuo="";               
                Individuo+=ri.nextInt(2)+"," + ro.nextInt(2)+"," + ra.nextInt(2)+"," + ra.nextInt(2)+"," + ra.nextInt(2)+",";   
                //JOptionPane.showInputDialog(""+Individuo);
    
                Poblacion[i][0]=""+i;
                Poblacion[i][1]=Individuo;     
                 
              
          }
         
         //JOptionPane.showInputDialog(""+Individuo);
    
         Poblacion[i][0]=""+i;
         Poblacion[i][1]=Individuo;        
     }//for    
    }//IniciarPoblacion
    //Calculo del valor del individuo de acuerdo a sus genes 0 y 1*/
     public static void convertir_individuo(String [][] Poblacion){   
      double valor=0;
      for(int i=0;i<Parejas.length;i++){
          valor=0;
          String [] valores= Poblacion[i][1].split(",");       
          int indice=0;
          //Cada valor se multiplica por potencias de 2 
          for(int k=valores.length-1;k>=0;k--){ 
            valor=valor+ (Double.parseDouble(valores[k])*Math.pow(2,indice));     
            indice++;
          }//for interno        
          Poblacion[i][2]=""+valor;
          sumaToria+=valor;       
        } //for externo         
    }//convertir_individuo
	//la formula que indica la adaptabilidad del individuo
	
	
     public static double Calidad_Individuo(String [][] Poblacion){
       //columa que tiene el valor del individuo  
       double mayor=Double.parseDouble(Poblacion[0][2]);  
       double valor=0;
      for(int i=0;i<Parejas.length;i++){        
         //se evalua cada uno con la funcion  X elevada al cuadrado 
         valor=funcion_Fx(Double.parseDouble(Poblacion[i][2]));  
         //valor=funcion_Fx2(Double.parseDouble(Poblacion[i][2]));  
         //se coloca en la columna 3
         Poblacion[i][3]=""+valor;  
         //se busca el mayor valor de todos este sera el más adaptado
         if(mayor<valor){
              mayor=valor;
         }
      }//for     
      System.out.println("********************Mejor Adaptado*************************");
      System.out.println("*************************"+ mayor+"****************************");
     //System.out.println("***********************************************************");     
      return(mayor);
	}// Calidad_Individuo
	
    //Combinacion y mutacion en otras se comparten los genes el apareamiento 
    public static void Combinacion_Mutacion(String [][] Poblacion,String [][] PoblacionTem ){        
        System.out.println("********************Combiancion y Mutacion*****************");
        System.out.println("***********************************************************");       
        Random ri = new Random();//aleatorio para el punto de combinacion
        int puntocruce=0;
        String [] IndividuoA;
        String [] IndividuoB;    
		String ParejaA="";
		
        //se hace sólo hasta la mitad porque cada uno tiene una pareja
        for(int i=0;i<Parejas.length/2;i++){        
           IndividuoA=Poblacion[i][1].split(",");
           ParejaA=Parejas[i]; //se obtiene la pareja del vector de parejas
           String cadAdn="";
           IndividuoB=Poblacion[Integer.parseInt(ParejaA)][1].split(",");
           puntocruce=ri.nextInt(4);//punto de cruce aleatorio 
           System.out.println("punto cruce ["+puntocruce+"]["+Poblacion[i][0]+"]"
           +"["+Poblacion[i][1]+"][Cruzado con] ["+Poblacion[Integer.parseInt(ParejaA)][0]+"]"
           +"["+Poblacion[Integer.parseInt(ParejaA)][1]+"]");         
           //Genes del primer Individuo
           for(int t=0;t<puntocruce;t++){            
            cadAdn+=IndividuoA[t]+",";
           } 
          //Genes del segundo individuo
           for(int t=puntocruce;t<IndividuoA.length;t++){            
             cadAdn+=IndividuoB[t]+",";
           }
           System.out.println("Nuevo Individuo ["+cadAdn+"]");          
           PoblacionTem[i][0]=""+i;
           PoblacionTem[i][1]=cadAdn;
         }//for de parejas        
        for(int i=0;i<Parejas.length;i++){
          Poblacion[i][0]=PoblacionTem[i][0];
          Poblacion[i][1]=PoblacionTem[i][1];        
        }
        //se muta en gen despues de la combinacion
        int mutado =(Parejas.length/2)+1;
        IndividuoA=Poblacion[mutado][1].split(",");
        System.out.println("**************************Mutacion********************************");
        System.out.println("****Individuo*****************************Resultado****************");
        //System.out.println("["+Poblacion[mutado][0]+"]"+"["+Poblacion[mutado][1]+"]");
        int gen=ri.nextInt(4);//gen aleatorio
        if(IndividuoA[gen].equals("0")){
            IndividuoA[gen]="1";
        }else{
             IndividuoA[gen]="0";
        }
        //se arma la cadena de ADN para mutar el gen
        String cadAdn="";
        for(int t=0;t<IndividuoA.length;t++){            
            cadAdn+=IndividuoA[t]+",";
        } 
       // System.out.println("mutado-->"+cadAdn);       
       System.out.println("["+Poblacion[mutado][0]+"]"+"["+Poblacion[mutado][1]+"]"+" Gen mutado"+"["+gen+"] Resultado=> ["+Poblacion[mutado][0]+"]"+"["+cadAdn+"]");
       Poblacion[mutado][1]=cadAdn; //se adiciona el mutado a la poblacion 
    }//Combinacion_Mutacion
    
    //Los Mejores individuos pueden copiarse, sacar otra copia de si mismos
    public static void Copiarse(String [][] Poblacion,String [][] PoblacionTem){     
      System.out.println("********************Copiarse*******************************");     
      int indice=0;
      int t=0;
      //se saca del vector de ganadores  
      for(int i=0;i<Ganadores.length;i++){            
         int ganador = Integer.parseInt(Ganadores[i]);
         PoblacionTem[indice][0]=""+(i+t); //Nombre del Individuo
         PoblacionTem[indice+1][0]=""+(i+1+t);//Nombre del Individuo Copiado
         //demas columnas 
         //se copian cada una de las columnas, de poblacion a poblacion temporal
         for(int f=1;f<columnas;f++){
           PoblacionTem[indice][f]=Poblacion[ganador][f];           
           PoblacionTem[indice+1][f]=Poblacion[ganador][f];
         }                  
         indice+=2;
         t++;
     }
     //Se pasa de la Estructura Temporal a la Original
     for(int i=0;i<Parejas.length;i++){
         Poblacion[i][0]=PoblacionTem[i][0];
         Poblacion[i][1]=PoblacionTem[i][1] ;        
      } //for de pasada      
        
    }//Copiarse
    //Un metodo para ver los ganadores de cada Torneo
    public static void verGanadores(String []  Ganadores){
      System.out.println("**************Ganadores********************************");
      int gano=0;
      //for par ver los ganadoers 
      for(int i=0;i<Ganadores.length;i++){         
       gano=Integer.parseInt(Ganadores[i]);
       System.out.println("["+Poblacion[gano][0]+"][ "+Poblacion[gano][1] +"]["+Poblacion[gano][2] +"]["+Poblacion[gano][3]+"]");       
      }//for
    }//verGanadores
    
    public static void Torneo(String [][] Poblacion){
     System.out.println("***********************************************************");
     System.out.println("********************Torneo*********************************");          
     String desempenoA="";
     String ParejaA="";
     String desempenoB="";
     int indP=0;
     //Torneo entre las parejas, se realiza hasta la mitad porque cada uno tiene su pareja
     for(int i=0;i<Parejas.length/2;i++){
       //Desempeño de los dos individuos, de acuerdo a la funcion actual  
       desempenoA=Poblacion[i][3];
       ParejaA=Parejas[i];          
       desempenoB=Poblacion[Integer.parseInt(ParejaA)][3];       
       System.out.println("["+Poblacion[i][0]+"][ "+Poblacion[i][1] +"]["+Poblacion[i][2] +"][ " +desempenoA+"] contra ["+Poblacion[Integer.parseInt(ParejaA)][0]+"] ["+Poblacion[Integer.parseInt(ParejaA)][1]+"]["+Poblacion[Integer.parseInt(ParejaA)][2] +"] ["+desempenoB+"]");
       //Se comparan, aqui compiten 
       if(Double.parseDouble(desempenoA)>=Double.parseDouble(desempenoB)){
          Ganadores[indP]=Poblacion[i][0];
       }else{
          Ganadores[indP]=ParejaA;
       }
        indP++; //indice de incremento         
     }//for de parejas
    }//Torneo
    //Aqui es donde se seleccionan las tareas, se realiza una Rotación 
    //La última con la primera,la antepenultima con la segunda...y asi    
    public static void Seleccion_Parejas(String [][] Poblacion){
       System.out.println("***********************************************************");
       System.out.println("********************Seleccion Parejas**********************");
        String aux=Poblacion[1][0];
        for(int i=0;i<Parejas.length;i++){            
            
          Parejas[(Parejas.length-1)-i]=Poblacion[i][0];  
          
        }//for
    }//Seleccion_Parejas   
    //calcula la probabilidad de adaptabilidad del cada individuo
    public static void adaptabilidad(String [][] Poblacion,double sumatoria){
      for(int i=0;i<Parejas.length;i++){        
         Poblacion[i][4]=""+(Double.parseDouble(Poblacion[i][2])/sumatoria);                    
    }//for        
    }//adaptabilidad
    
    //Metodo que muestra la poblacion de Individuos y sus valores
    public static void verPoblacion(String [][] Poblacion,boolean pareja){    
     System.out.println("********************Pablacion Actual***********************");         
     String Cadena="";
     //Hasta el numero de individuos,filas
      for(int i=0;i<filas;i++){
        for(int k=0;k<columnas;k++){     
          Cadena+="["+Poblacion[i][k]+"]";      
        }
        //si se visualiza con parejas o no 
        if(pareja)
          Cadena+="  Pareja "+Parejas[i]+"\n"; 
        else
         Cadena+=""+"\n"; 
     }//for
      System.out.print(Cadena);     
   }//verPoblacion
    
     //funcion de X elevado al Cuadrado
    public static double funcion_Fx(double X){
       return(X*X);    
    }
    public static double funcion_Fx2(double X){
       return(X*X*X);    
    }
    
    public static void main(String[] args) {       
        IniciarPoblacion(Poblacion);
         verPoblacion(Poblacion,false);
       double adaptados=0;
        while(adaptados<961){            
          convertir_individuo(Poblacion);
          adaptados=Calidad_Individuo(Poblacion);
          adaptabilidad(Poblacion,sumaToria);
          verPoblacion(Poblacion,true);         
          Seleccion_Parejas(Poblacion);                
          Torneo(Poblacion);
          verGanadores(Ganadores);
          Copiarse(Poblacion,PoblacionTem);
          verPoblacion(PoblacionTem,true);       
          Seleccion_Parejas(Poblacion);               
          Combinacion_Mutacion(Poblacion,PoblacionTem); 
        }
         adaptados=Calidad_Individuo(Poblacion);
         verPoblacion(Poblacion,false); 
         
    }
}