package boatregister;

import java.io.Serializable;

import javax.swing.JTextArea;

public class B�tregister implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	B�t b�t;
	B�teier b�teier;
	
	private B�teier f�rste;
	
	public B�tregister()
	{
		f�rste = null;
	}
	
	public void registrerB�t(B�t nyb�t, int mnr) //Registrerer ny b�t
	{
		B�teier l�per = f�rste;
		
		if( f�rste == null )
		{
			return;
		}
		if( f�rste.getMedlemsnr() == mnr && f�rste.getB�t() == null )
		{
			f�rste.setB�t(nyb�t);
			return;
		}
		while(l�per.neste != null)
		{
			if( l�per.neste.getMedlemsnr() == mnr && l�per.neste.getB�t() == null )
			{
				l�per.neste.setB�t(nyb�t);
				return;
			}
			l�per = l�per.neste;
		}
	}
	
	public boolean fjernB�t(int regnr) // Fjerner b�t
	{
		B�teier  l�per = f�rste;
		
		if ( f�rste == null )
		{
			return false;
		}
		
		if( f�rste.getB�t() != null )
		{
			if( f�rste.getB�t().getRegnmr() == regnr )
			{
				f�rste.setB�t(null);
				return true;
			}
		}
		while( l�per.neste != null )
		{
			if(l�per.neste.getB�t() != null)
			{
				if(l�per.neste.getB�t().getRegnmr() == regnr )
				{
					l�per.neste.setB�t(null);
					return true;
				}
			}
			l�per = l�per.neste;
		}
		return false;
	}	
	
	public void registrerEier(B�teier ny) // Registrerer ny Eier
	{
		if( f�rste == null )
		{
			f�rste = ny;
			return;
		}
		B�teier l�per = f�rste;
		
		while(l�per.neste != null)
		{
			l�per = l�per.neste;
		}
		
		l�per.neste = ny;
	}
	
	public boolean fjernEier(int mnr) // Fjerner Eier
	{
		B�teier l�per = f�rste;
		
		if(l�per == null)
		{
			return false;
		}
		if(f�rste.getMedlemsnr() == mnr && f�rste.getB�t() == null )
		{
			f�rste = f�rste.neste;
			return true;
		}
		while(l�per.neste != null)
		{
			if(l�per.neste.getMedlemsnr() == mnr && l�per.neste.getB�t() == null)
			{
				l�per.neste = l�per.neste.neste;
				return true;
				
			}
			l�per = l�per.neste;
		}
		return false;
	}
	
	public B�teier getB�tEier( int regnr )
	{
		B�teier l�per = f�rste;
		
		if ( f�rste == null )
		{
			return null;
		}
		
		while( l�per != null )
		{
			if( l�per.getB�t() != null )
			{
				if( l�per.getB�t().getRegnmr() == regnr )
				{
					return l�per;
				}
				l�per = l�per.neste;
			}
		}
		return null;
	}

	public boolean registrerSkifte(int regnr, int medlemsnr) // Skifter eier for b�t med gitt regnr
	{
		B�teier l�per = f�rste;
		B�t b�t = null;
		B�teier eier = null;
		
		while ( l�per != null)
		{
			if( l�per.getB�t() != null && l�per.getB�t().getRegnmr() == regnr )
			{
				b�t = l�per.getB�t();
				eier = l�per;
			}
			l�per = l�per.neste;
		}
		
		if( eier == null )
		{
			return false;
		}
		
		l�per = f�rste;
		
		while( l�per != null )
		{
			if ( l�per.getMedlemsnr() == medlemsnr && l�per.getB�t() == null )
			{
				l�per.setB�t(b�t);
				eier.setB�t(null);
				return true;
			}
			l�per = l�per.neste;
		}
		return false;
	}
	
	public String infoEier(int regnr) // Viser informasjon om eier med gitt regnr
	{
		B�teier l�per = f�rste;
		
		while(l�per != null)
		{
			if(l�per.getB�t().getRegnmr() == regnr)
			{
				return l�per.toString();
			}
			l�per = l�per.neste;
		}
		return "Det er ikke registrert en person med dette registreringsnummeret!";
		
	}
	
	public void b�tUtskrift(JTextArea b�ter)
	{
		if(f�rste == null)
		{
			b�ter.append("Det er ingen b�t i registeret");
		}
		b�ter.setText("");
		B�teier l�per = f�rste;
		while(l�per != null)
		{
			b�ter.append(l�per.toString() + "\n");
			l�per = l�per.neste;
		}
	}
}
