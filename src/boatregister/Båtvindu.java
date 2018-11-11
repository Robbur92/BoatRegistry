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

public class Båtvindu extends JFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField NavnFelt, AdresseFelt, MedlemsNrFelt, RegNrFelt, TypeFelt, ÅrsModellFelt, LengdeFelt, HKFelt, FargeFelt;
    private JButton registrerEier, registrerBåt, båtUtskrift, fjernBåt, fjernEier, infoEier, registrerSkifte;
    private JTextArea utskriftsområde;
    private Båtregister register = new Båtregister();

    public Båtvindu()
    {
		//Felt
        NavnFelt = new JTextField(18);
        AdresseFelt = new JTextField(18);
        MedlemsNrFelt = new JTextField(5);
        RegNrFelt = new JTextField(15);
        TypeFelt = new JTextField(15);
        ÅrsModellFelt = new JTextField(15);
        LengdeFelt = new JTextField(3);
        HKFelt = new JTextField(10);
        FargeFelt = new JTextField(10);
        
        //Knapper
        registrerEier = new JButton("Registrer eier");
        registrerBåt = new JButton("Registrer båt");
        båtUtskrift = new JButton("Vis register");
        fjernBåt = new JButton("Slett båt");
        fjernEier = new JButton("Slett eier");
        infoEier = new JButton("Finn Eier");
        registrerSkifte = new JButton("Skifte eier");

        utskriftsområde = new JTextArea( 30, 45 );
        utskriftsområde.setEditable( false );

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
        c.add( new JLabel( "Årsmodell: " ) );
        c.add( ÅrsModellFelt );
        c.add( new JLabel( "Lengde i fot: " ) );
        c.add( LengdeFelt );
        c.add( new JLabel( "Hestekrefter: " ) );
        c.add( HKFelt );
        c.add( new JLabel( "Farge: " ) );
        c.add( FargeFelt );
        c.add(new JLabel("             "));
        c.add( registrerEier );
        c.add( registrerBåt );
        c.add( båtUtskrift );
        c.add( fjernBåt);
        c.add( fjernEier );
        c.add( infoEier );
        c.add( registrerSkifte);
        
        c.add( new JScrollPane( utskriftsområde ) );
        
        Knappelytter lytter = new Knappelytter();
        
        registrerEier.addActionListener( lytter );
        registrerBåt.addActionListener( lytter );
        båtUtskrift.addActionListener( lytter );
        fjernBåt.addActionListener( lytter );
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
		ÅrsModellFelt.setText( "" );
		LengdeFelt.setText( "" );
		HKFelt.setText( "" );
		FargeFelt.setText( "" );
	}
    public void eierRegistrer() // Registrerer ny båteier
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
          Båteier eier = new Båteier(navn, adresse);
          register.registrerEier(eier);
          visMelding("Ny eier registrert");
          slettFelter();
        }
      	catch(NumberFormatException n)
        {
          visMelding("Feil med tallformat");
        }
    }
  
  	public void båtRegistrer() //Registrerer ny båt
    {
      	String merke = TypeFelt.getText();
        String farge = FargeFelt.getText();
        int regnmr = Integer.parseInt(RegNrFelt.getText());
        int årsmodell = Integer.parseInt(ÅrsModellFelt.getText());
        double hestekrefter = Double.parseDouble(HKFelt.getText());
        double lengde = Double.parseDouble(LengdeFelt.getText());
        int mnr = Integer.parseInt(MedlemsNrFelt.getText());
      
      	if(merke.length() == 0 || farge.length() == 0 || regnmr == 0 || årsmodell == 0 || hestekrefter == 0 || lengde == 0)
        {
          visMelding("Fyll ut tekstfeltet");
          return;
        }
      	try
        {
      	  Båt nybåt = new Båt( merke, farge, regnmr, årsmodell, hestekrefter, lengde);
      	  register.registrerBåt(nybåt, mnr);
          visMelding("Ny båt er registrert");
          slettFelter();
        }
      	catch(NumberFormatException n)
        {
          visMelding("Feil med tallformat");
        }
     }
    

     public void utskriftBåt()
     {
       register.båtUtskrift(utskriftsområde);
     }
     
     public void båtFjern() // Fjerner båt med gitt regnr
     {
    	 try
    	 {
    		 int regNr = Integer.parseInt(RegNrFelt.getText());
    		 if((boolean) register.fjernBåt(regNr))
    		 {
    			 visMelding("Båt har blitt fjernet");
    			 return;
    		 }
    		 else
    		 {
    			 visMelding("Feil: Båten kunne ikke fjernes");
    			 return;
    		 }
    	 }
    	 catch(NumberFormatException i)
    	 {
    		 visMelding("Feil i tallformat");
    	 }
     }
     
     public void eierFjern() // Fjerner Båteier med gitt medlemsnr
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
 		 try(ObjectInputStream inn = new ObjectInputStream(new FileInputStream("båt.data")))
 		 {
 			 register = (Båtregister) inn.readObject();
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
 			 visMelding("Finner ikke strømmen");
 		 }
 	 }
 	 
 	public void skrivObjektTilFil() //Skriver Info Til Fil
	 {
		 try(ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream("båt.data")))
		 {
			 ut.writeObject(register);
		 }
		 catch( NotSerializableException nse )
		 {
			 visMelding("Objektet er ikke serialisert");
		 }
		 catch(IOException io)
		 {
			 visMelding("Finner ikke strømmen");
		 }

	 }
  	 
     private class Knappelytter implements ActionListener
     {
		public void actionPerformed( ActionEvent e )
		{
		    if( e.getSource() == registrerEier )
		        eierRegistrer();
		    else if( e.getSource() == registrerBåt )
		        båtRegistrer();
		    else if( e.getSource() == båtUtskrift )
		        utskriftBåt();
		    else if( e.getSource() == fjernBåt )
		        båtFjern();
		    else if( e.getSource() == fjernEier )
		        eierFjern();
		    else if( e.getSource() == infoEier )
		        eierInfo();
		    else if( e.getSource() == registrerSkifte )
		        skifteRegistrer();
		}
     }
}


