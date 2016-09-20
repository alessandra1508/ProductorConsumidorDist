import javax.swing.JFrame;

public class Productor implements Runnable{

	 Bodega bodega;
	 Main ventana;

	 public Productor(Bodega bodega, Main ventana){
	 	this.bodega = bodega;
	 	this.ventana = ventana;
	 }

	 public void run(){
	 	while(true){
		 	
	 		System.out.println("Productor agregando producto");
		 	
	 		bodega.agregar();
		 	
	 		System.out.println("Total p = "+ bodega.getProductos());
	 		
	 		ventana.setLabelTextProductor(""+bodega.getProductos());
		 	
	 		try{
		 		Thread.sleep(1000);
		 	}catch(Exception e){

		 	}
		 	
	 	}
	 }
	 
	 
}