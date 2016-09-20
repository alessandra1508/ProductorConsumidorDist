public class Consumidor implements Runnable{

	Bodega bodega;
	Main ventana;

	 public Consumidor(Bodega bodega, Main ventana){
	 	this.bodega = bodega;
	 	this.ventana = ventana;
	 }

	 public void run(){
	 	while(true){
		 	System.out.println("Consumidor quitando producto");
		 	bodega.sacar();
		 	System.out.println("Total c= "+ bodega.getProductos());
		 	
		 	ventana.setLabelTextConsumidor(""+bodega.getProductos());
		 	
		 	try{
		 		Thread.sleep(1500);
		 	}catch(Exception e){

		 	}
	 	}	 	
	 }
}