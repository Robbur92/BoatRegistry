package boatregister;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


  public class B�tprogram  //Main
  {
   public static void main(String[] args) 
   {
    B�tvindu vindu = new B�tvindu();
    
    vindu.lesObjektFraFil();
    
    vindu.addWindowListener(
         new WindowAdapter() 
           {
           public void windowClosing( WindowEvent e )
           {
        	 vindu.skrivObjektTilFil();
             System.exit( 0 );
           }
         } );
   
    
}
}