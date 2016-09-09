//comment
public class ColaPrioridadMediana {
	double[] menores;
	double[] mayores;
	//variables privadas del sistema
	private int largoM;
	private int largom;
	private int d_limite = 20;
	int limite = d_limite;
	private boolean cap = false;
	
	public ColaPrioridadMediana(){
		menores = new double[d_limite/2+1];
		mayores = new double[d_limite/2+1];
		largom = largoM = 0;
	}
	
	public ColaPrioridadMediana(int Cap){
		limite=Cap;
		cap = true;
		menores = new double[Cap/2+1];
		mayores = new double[Cap/2+1];
		largom = largoM = 0;
	}

	public void insertar(double numero){
		if(cap){
			if(largoTotal() >= limite) Error1();
		}
		else{
			if(largoTotal() >= d_limite) Error2();
		}
		if(largom==0 && largoM==0 ){
			menores[0]= numero; // caso base
			largom++;
		}
		else if(largom==1 && largoM==0){
			if(numero<menores[0]){
				double aux = menores[0];
				menores[0]=numero;
				mayores[0]=aux;
				largoM++;
			}
			else{
				mayores[0]=numero;
				largoM++;
			}
		}
		else{// los dos son mayores que 1
			if(largom == largoM){
				if(numero>=menores[0]){
					mayores = heapifyUpM(mayores,numero);
					//inserta en heap donde el menor tiene mayor prioridad
					largoM++;
				}
				else{
					menores = heapifyUpm(menores,numero);
					//inserta en heap donde el mayor tiene mayor prioridad
					largom++;
				}
			}
			else if(largom < largoM){ //mayores es m�s largo
				if(numero <= mayores[0]){
					menores = heapifyUpm(menores,numero);
					largom++;
				}
				else{
					double temp1 = mayores[0]; 
					menores = heapifyUpm(menores, temp1);
					largom++;
					mayores[0] = numero;
					heapifyDownM(0);//
				}	
			}
			else{
				if(numero >= menores[0]){
					mayores = heapifyUpm(menores,numero);
					largoM++;
				}
				else{
					double temp2 = menores[0];
					mayores = heapifyUpM(mayores, temp2);
					largoM++;
					menores[0] = numero;
					heapifyDownm(0);
				}
			}
		}
	}
	
	private void Error1() {
		System.out.print("Error: conjunto de datos no puede superar los ");
		System.out.println(limite+" elementos");
		System.exit(-1);
	}

	private void Error2() {
		System.out.print("Error: conjunto de datos no puede superar los ");
		System.out.println(d_limite+" elementos");
		System.exit(-1);
	}
	
	private int largoTotal(){
		return largom+largoM;
	}

	public double getMediana(){
		if(largom > largoM) return menores[0];
		else if(largom < largoM) return mayores[0];
		else return (mayores[0] + menores[0])/2.0;
	}

	private double[] heapifyUpM(double[] arreglo, double x) {
		double auxm = x;
		int hijoInd = largoM;
		while(hijoInd>0 && auxm < arreglo[ padre(hijoInd)]){
			arreglo[hijoInd] = arreglo[ padre(hijoInd) ];
			hijoInd = padre(hijoInd);
		}
		arreglo[hijoInd]= auxm;
		return arreglo;
	}

	private double[] heapifyUpm(double[] arreglo, double x) {
		double auxm = x;
		int hijoInd = largom;
		while(hijoInd>0 && auxm > arreglo[ padre(hijoInd)]){
			arreglo[hijoInd] = arreglo[ padre(hijoInd) ];
			hijoInd = padre(hijoInd);
		}
		arreglo[hijoInd]= auxm;
		return arreglo;
	}

	private int padre(int i){
		return (i-1)/2;
	}
	
    private void heapifyDownm(int ind){
        int hijo;
        double auxiliar = menores[ 0 ];
        while (kHijo(ind, 1) < largom){
            hijo = minHijom(ind);
            if (menores[hijo] > auxiliar)menores[ind] = menores[hijo];
            else break;
            ind = hijo;
        }
        menores[ind] = auxiliar;
    }
	
	private int kHijo(int i, int k){
		return 2*i+k;
	}
	
	private int minHijom(int ind){
		int mejorHijo = kHijo(ind, 1);
		int posible = kHijo(ind,2); 
		while(posible<largom){
			if(menores[posible] > menores[mejorHijo]) mejorHijo = posible;
		}
		return mejorHijo;
	}
	
    private void heapifyDownM(int ind){
        int hijo;
        double auxiliar = mayores[ 0 ];
        while (kHijo(ind, 1) < largoM){
            hijo = minHijoM(ind);
            if (mayores[hijo] < auxiliar)mayores[ind] = mayores[hijo];
            else break;
            ind = hijo;
        }
        mayores[ind] = auxiliar;
    }
	
	private int minHijoM(int ind){
		int mejorHijo = kHijo(ind, 1);
		int posible = kHijo(ind,2); 
		while(posible<largoM){
			if(mayores[posible] < mayores[mejorHijo]) mejorHijo = posible;
		}
		return mejorHijo;
	}
}