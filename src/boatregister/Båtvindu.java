package boatregister;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class B�tvindu extends JFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField NavnFelt, AdresseFelt, MedlemsNrFelt, RegNrFelt, TypeFelt, �rsModellFelt, LengdeFelt, HKFelt, FargeFelt;
    private JButton registrerEier, registrerB�t, b�tUtskrift, fjernB�t, fjernEier, infoEier, registrerSkifte;
    private JTextArea utskriftsomr�de;
    private B�tregister register = new B�tregister();

    public B�tvindu()
    {
		//Felt
        NavnFelt = new JTextField(18);
        AdresseFelt = new JTextField(18);
        MedlemsNrFelt = new JTextField(5);
        RegNrFelt = new JTextField(15);
        TypeFelt = new JTextField(15);
        �rsModellFelt = new JTextField(15);
        LengdeFelt = new JTextField(3);
        HKFelt = new JTextField(10);
        FargeFelt = new JTextField(10);
        
        //Knapper
        registrerEier = new JButton("Registrer eier");
        registrerB�t = new JButton("Registrer b�t");
        b�tUtskrift = new JButton("Vis register");
        fjernB�t = new JButton("Slett b�t");
        fjernEier = new JButton("Slett eier");
        infoEier = new JButton("Finn Eier");
        registrerSkifte = new JButton("Skifte eier");

        utskriftsomr�de = new JTextArea( 30, 45 );
        utskriftsomr�de.setEditable( false );

        Container c = getContentPane();
        c.setLayout( new FlowLayout() );
        c.add( new JLabel( "Navn: " ) );
        c.add( NavnFelt );
        c.add( new JLabel( "Adresse: " ) );
        c.add( AdresseFelt );
        c.add( new JLabel( "Medlemsnummer: " ) );
        c.add( MedlemsNrFelt );
        c.add( new JLabel( "Registreringsnummer: " ) );
        c.add( RegNrFelt );
        c.add( new JLabel( "Merke/Type: " ) );
        c.add( TypeFelt );
        c.add( new JLabel( "�rsmodell: " ) );
        c.add( �rsModellFelt );
        c.add( new JLabel( "Lengde i fot: " ) );
        c.add( LengdeFelt );
        c.add( new JLabel( "Hestekrefter: " ) );
        c.add( HKFelt );
        c.add( new JLabel( "Farge: " ) );
        c.add( FargeFelt );
        c.add(new JLabel("             "));
        c.add( registrerEier );
        c.add( registrerB�t );
        c.add( b�tUtskrift );
        c.add( fjernB�t);
        c.add( fjernEier );
        c.add( infoEier );
        c.add( registrerSkifte);
        
        c.add( new JScrollPane( utskriftsomr�de ) );
        
        Knappelytter lytter = new Knappelytter();
        
        registrerEier.addActionListener( lytter );
        registrerB�t.addActionListener( lytter );
        b�tUtskrift.addActionListener( lytter );
        fjernB�t.addActionListener( lytter );
        fjernEier.addActionListener( lytter );
        infoEier.addActionListener( lytter );
        registrerSkifte.addActionListener( lytter );
        setSize( 550, 700 );
        setVisible( true );      
    }
    
    private void visMelding(String melding)
    {
    	JOptionPane.showMessageDialog(this, melding);
    }
  
  		private void slettFelter()
	{
		NavnFelt.setText( "" );
		AdresseFelt.setText( "" );
		MedlemsNrFelt.setText( "" );
		RegNrFelt.setText( "" );
		TypeFelt.setText( "" );
		�rsModellFelt.setText( "" );
		LengdeFelt.setText( "" );
		HKFelt.setText( "" );
		FargeFelt.setText( "" );
	}
    public void eierRegistrer() // Registrerer ny b�teier
    {
        String navn = NavnFelt.getText();
        String adresse = AdresseFelt.getText();
        
        if (navn.length() == 0 || adresse.length() == 0)
        {
          visMelding("Fyll ut tekstfeltet");
          return;
        }
        try
        {
          B�teier eier = new B�teier(navn, adresse);
          register.registrerEier(eier);
          visMelding("Ny eier registrert");
          slettFelter();
        }
      	catch(NumberFormatException n)
        {
          visMelding("Feil med tallformat");
        }
    }
  
  	public void b�tRegistrer() //Registrerer ny b�t
    {
      	String merke = TypeFelt.getText();
        String farge = FargeFelt.getText();
        int regnmr = Integer.parseInt(RegNrFelt.getText());
        int �rsmodell = Integer.parseInt(�rsModellFelt.getText());
        double hestekrefter = Double.parseDouble(HKFelt.getText());
        double lengde = Double.parseDouble(LengdeFelt.getText());
        int mnr = Integer.parseInt(MedlemsNrFelt.getText());
      
      	if(merke.length() == 0 || farge.length() == 0 || regnmr == 0 || �rsmodell == 0 || hestekrefter == 0 || lengde == 0)
        {
          visMelding("Fyll ut tekstfeltet");
          return;
        }
      	try
        {
      	  B�t nyb�t = new B�t( merke, farge, regnmr, �rsmodell, hestekrefter, lengde);
      	  register.registrerB�t(nyb�t, mnr);
          visMelding("Ny b�t er registrert");
          slettFelter();
        }
      	catch(NumberFormatException n)
        {
          visMelding("Feil med tallformat");
        }
     }
    

     public void utskriftB�t()
     {
       register.b�tUtskrift(utskriftsomr�de);
     }
     
     public void b�tFjern() // Fjerner b�t med gitt regnr
     {
    	 try
    	 {
    		 int regNr = Integer.parseInt(RegNrFelt.getText());
    		 if((boolean) register.fjernB�t(regNr))
    		 {
    			 visMelding("B�t har blitt fjernet");
    			 return;
    		 }
    		 else
    		 {
    			 visMelding("Feil: B�ten kunne ikke fjernes");
    			 return;
    		 }
    	 }
    	 catch(NumberFormatException i)
    	 {
    		 visMelding("Feil i tallformat");
    	 }
     }
     
     public void eierFjern() // Fjerner B�teier med gitt medlemsnr
     {
    	 try
    	 {
    		 int medlemsNr = Integer.parseInt(MedlemsNrFelt.getText());
    		 if((boolean) register.fjernEier(medlemsNr))
    		 {
    			 visMelding("Eier har blitt fjernet");
    			 return;
    		 }
    		 else
    		 {
    			 visMelding("Feil: Eieren kunne ikke fjernes");
    			 return;
    		 }
    	 }
    	 catch(NumberFormatException i)
    	 {
    		 visMelding("Feil i tallformat");
    	 }
     }

	public void eierInfo() //Viser informasjon om eier med gitt regnr
     {
		try
		{
		int regnr = Integer.parseInt(RegNrFelt.getText());
		visMelding(register.infoEier(regnr));
		}
		catch( NumberFormatException i )
		{
			visMelding("Feil i tallformat");
		}
  		 
     }
	 
	 public void skifteRegistrer() //Ikke ferdig
    {
		 try 
		 {
	 		int regnr = Integer.parseInt(RegNrFelt.getText());
	 		int medlemsNr = Integer.parseInt(MedlemsNrFelt.getText());
	 		register.registrerSkifte(regnr, medlemsNr);			 
		 }	
		 catch( NumberFormatException i )
		 {
			 visMelding("Feil i tallformat");
		 }
    }
	 
	public void lesObjektFraFil() //Leser Info
 	 {
 		 try(ObjectInputStream inn = new ObjectInputStream(new FileInputStream("b�t.data")))
 		 {
 			 register = (B�tregister) inn.readObject();
 		 }
 		 catch(ClassNotFoundException cnfe)
		 {
 			 visMelding("Finner ikke klassen");
		 }
		 catch(FileNotFoundException fne)
		 {
			 visMelding("Finner ikke fil");
		 }
 		 catch(IOException s)
 		 {
 			 visMelding("Finner ikke str�mmen");
 		 }
 	 }
 	 
 	public void skrivObjektTilFil() //Skriver Info Til Fil
	 {
		 try(ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream("b�t.data")))
		 {
			 ut.writeObject(register);
		 }
		 catch( NotSerializableException nse )
		 {
			 visMelding("Objektet er ikke serialisert");
		 }
		 catch(IOException io)
		 {
			 visMelding("Finner ikke str�mmen");
		 }

	 }
  	 
     private class Knappelytter implements ActionListener
     {
		public void actionPerformed( ActionEvent e )
		{
		    if( e.getSource() == registrerEier )
		        eierRegistrer();
		    else if( e.getSource() == registrerB�t )
		        b�tRegistrer();
		    else if( e.getSource() == b�tUtskrift )
		        utskriftB�t();
		    else if( e.getSource() == fjernB�t )
		        b�tFjern();
		    else if( e.getSource() == fjernEier )
		        eierFjern();
		    else if( e.getSource() == infoEier )
		        eierInfo();
		    else if( e.getSource() == registrerSkifte )
		        skifteRegistrer();
		}
     }
}


