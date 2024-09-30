package prueba.transbordes;

/**
 *
 * @author carlo
 */
public class PruebaTransbordes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int iniciometro = 1;
        int finmetro = 163;
        int finL1MB = 209;
        int finL2MB = 246;
        int finL3MB = 284;
        int finL4MB = 326;
        int finL5MB = 377;
        int finL6MB = 414;
        int finL7MB = 445;
        int costo = 0;
        boolean metro = false;
        boolean MB1 = false;
        boolean MB2 = false;
        boolean MB3 = false;
        boolean MB4 = false;
        boolean MB5 = false;
        boolean MB6 = false;
        boolean MB7 = false;
           
        for (int nodo1 = 0; nodo1 < 410; nodo1++) 
        {
            if (nodo1 < finmetro) 
            {
                costo = 5;
                if (!metro) 
                {
                    costo += 6;
                    metro = true;
                }
            } else if (nodo1 < finL1MB) {
                 if (!MB1) {
                    costo += 6;
                    MB1 = true;
                }
            } else if (nodo1 < finL2MB) {
                if (!MB2) {
                    costo += 6;
                    MB2 = true;
                }  
            }else if (nodo1 < finL3MB) {
                if (!MB3) {
                    costo += 6;
                    MB3 = true;
                }  
            }else if (nodo1 < finL4MB) {
                if (!MB4) {
                    costo += 6;
                    MB4 = true;
                }  
            }else if (nodo1 < finL5MB) {
                if (!MB5) {
                    costo += 6;
                    MB5 = true;
                }  
            }else if (nodo1 < finL6MB) {
                if (!MB6) {
                    costo += 6;
                    MB6 = true;
                }  
            }else if (nodo1 < finL7MB) {
                if (!MB7) {
                    costo += 6;
                    MB7 = true;
                }  
            }
            System.out.println(costo);
        }
    }
}

