import java.util.Scanner;

public class tester {

	public static void main(String[] args) {
		ColaPrioridadMediana mediana = new ColaPrioridadMediana();
		Scanner s = new Scanner(System.in);
		System.out.println("Ingrese la cantidad máxima de elementos y/o presione ENTER");
		String answer = s.nextLine();
		if( !(answer.equals(""))  ){
			try{
				int limite = Integer.parseInt(answer);
				mediana = new ColaPrioridadMediana(limite);
			}
			catch(Exception e0){
				System.out.println("ERROR: Debe ingresar un numero entero");
				System.exit(-1);
			}
		}
		impresora(mediana);
		s.close();
	}
	private static void impresora(ColaPrioridadMediana cola){
		Scanner s = new Scanner(System.in);
		System.out.println("Ingrese los numeros de a uno a continuación:");
		System.out.println("Maximo: "+cola.limite);
		while(s.hasNextLine()){
			String line= s.nextLine();
			try{
				double num = Double.parseDouble(line);
				cola.insertar(num);
			}
			catch(Exception e){
				System.out.println("La mediana es: "+cola.getMediana());
				s.close();
				System.exit(-1);
		}
		}
	}
}
