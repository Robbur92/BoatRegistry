package boatregister;

import java.io.Serializable;

import javax.swing.JTextArea;

public class Båtregister implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Båt båt;
	Båteier båteier;
	
	private Båteier første;
	
	public Båtregister()
	{
		første = null;
	}
	
	public void registrerBåt(Båt nybåt, int mnr) //Registrerer ny båt
	{
		Båteier løper = første;
		
		if( første == null )
		{
			return;
		}
		if( første.getMedlemsnr() == mnr && første.getBåt() == null )
		{
			første.setBåt(nybåt);
			return;
		}
		while(løper.neste != null)
		{
			if( løper.neste.getMedlemsnr() == mnr && løper.neste.getBåt() == null )
			{
				løper.neste.setBåt(nybåt);
				return;
			}
			løper = løper.neste;
		}
	}
	
	public boolean fjernBåt(int regnr) // Fjerner båt
	{
		Båteier  løper = første;
		
		if ( første == null )
		{
			return false;
		}
		
		if( første.getBåt() != null )
		{
			if( første.getBåt().getRegnmr() == regnr )
			{
				første.setBåt(null);
				return true;
			}
		}
		while( løper.neste != null )
		{
			if(løper.neste.getBåt() != null)
			{
				if(løper.neste.getBåt().getRegnmr() == regnr )
				{
					løper.neste.setBåt(null);
					return true;
				}
			}
			løper = løper.neste;
		}
		return false;
	}	
	
	public void registrerEier(Båteier ny) // Registrerer ny Eier
	{
		if( første == null )
		{
			første = ny;
			return;
		}
		Båteier løper = første;
		
		while(løper.neste != null)
		{
			løper = løper.neste;
		}
		
		løper.neste = ny;
	}
	
	public boolean fjernEier(int mnr) // Fjerner Eier
	{
		Båteier løper = første;
		
		if(løper == null)
		{
			return false;
		}
		if(første.getMedlemsnr() == mnr && første.getBåt() == null )
		{
			første = første.neste;
			return true;
		}
		while(løper.neste != null)
		{
			if(løper.neste.getMedlemsnr() == mnr && løper.neste.getBåt() == null)
			{
				løper.neste = løper.neste.neste;
				return true;
				
			}
			løper = løper.neste;
		}
		return false;
	}
	
	public Båteier getBåtEier( int regnr )
	{
		Båteier løper = første;
		
		if ( første == null )
		{
			return null;
		}
		
		while( løper != null )
		{
			if( løper.getBåt() != null )
			{
				if( løper.getBåt().getRegnmr() == regnr )
				{
					return løper;
				}
				løper = løper.neste;
			}
		}
		return null;
	}

	public boolean registrerSkifte(int regnr, int medlemsnr) // Skifter eier for båt med gitt regnr
	{
		Båteier løper = første;
		Båt båt = null;
		Båteier eier = null;
		
		while ( løper != null)
		{
			if( løper.getBåt() != null && løper.getBåt().getRegnmr() == regnr )
			{
				båt = løper.getBåt();
				eier = løper;
			}
			løper = løper.neste;
		}
		
		if( eier == null )
		{
			return false;
		}
		
		løper = første;
		
		while( løper != null )
		{
			if ( løper.getMedlemsnr() == medlemsnr && løper.getBåt() == null )
			{
				løper.setBåt(båt);
				eier.setBåt(null);
				return true;
			}
			løper = løper.neste;
		}
		return false;
	}
	
	public String infoEier(int regnr) // Viser informasjon om eier med gitt regnr
	{
		Båteier løper = første;
		
		while(løper != null)
		{
			if(løper.getBåt().getRegnmr() == regnr)
			{
				return løper.toString();
			}
			løper = løper.neste;
		}
		return "Det er ikke registrert en person med dette registreringsnummeret!";
		
	}
	
	public void båtUtskrift(JTextArea båter)
	{
		if(første == null)
		{
			båter.append("Det er ingen båt i registeret");
		}
		båter.setText("");
		Båteier løper = første;
		while(løper != null)
		{
			båter.append(løper.toString() + "\n");
			løper = løper.neste;
		}
	}
}
