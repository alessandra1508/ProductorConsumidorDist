
public class Bodega {
	
	private int productos = 0;
	private int CAPACIDAD_MAX = 10;
  
	 
	 public int getProductos(){
	 	return productos;
	 }

	 public synchronized void agregar(){
		 while (productos >= CAPACIDAD_MAX) 
	        {
	            try
	            {
	                wait();
	            } 
	            catch (InterruptedException e) 
	            {
	                System.err.println("Contenedor: Error en put -> " + e.getMessage());
	            }
	        }

		  productos++;		 		
		  notify();
	 }

	 public synchronized void sacar(){

		 while (productos <= 0)
	      {
	            try
	            {
	                wait();
	            } 
	            catch (InterruptedException e) 
	            {
	                System.err.println("Contenedor: Error en get -> " + e.getMessage());
	            }
	        }
		 
		 
		 productos--;	
		 notify();	        
	 }

}
