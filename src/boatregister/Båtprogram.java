package boatregister;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


  public class Båtprogram  //Main
  {
   public static void main(String[] args) 
   {
    Båtvindu vindu = new Båtvindu();
    
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