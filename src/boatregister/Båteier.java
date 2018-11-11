package boatregister;

import java.io.Serializable;

public class Båteier implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String navn, adresse;
	private int medlemsnr;
	private static int nesteMedlem = 1;
	Båteier neste;
	Båt båt;
	

	public Båteier(String n, String a )
	{
		navn = n;
		adresse = a;
		medlemsnr = nesteMedlem++;
		båt = null;
		neste = null;
	}

	public String getNavn() 
	{
		return navn;
	}


	public void setNavn(String navn) 
	{
		this.navn = navn;
	}


	public String getAdresse() 
	{
		return adresse;
	}


	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}


	public int getMedlemsnr() 
	{
		return medlemsnr;
	}


	public void setMedlemsnr(int medlemsnr) 
	{
		this.medlemsnr = medlemsnr;
	}


	public Båt getBåt() 
	{
		return båt;
	}


	public void setBåt(Båt båt) 
	{
		this.båt = båt;
	}

	public String toString() 
	{
		if(båt == null)
		{
			return "Båteiers navn: " + navn + ", Adresse: " + adresse + ", Medlemsnr: " + medlemsnr + "\n";
		}
			return "Båteiers navn: " + navn + ", Adresse: " + adresse + ", Medlemsnr: " + medlemsnr + båt.toString();
	}

}
