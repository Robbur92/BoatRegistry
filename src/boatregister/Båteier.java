package boatregister;

import java.io.Serializable;

public class B�teier implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String navn, adresse;
	private int medlemsnr;
	private static int nesteMedlem = 1;
	B�teier neste;
	B�t b�t;
	

	public B�teier(String n, String a )
	{
		navn = n;
		adresse = a;
		medlemsnr = nesteMedlem++;
		b�t = null;
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


	public B�t getB�t() 
	{
		return b�t;
	}


	public void setB�t(B�t b�t) 
	{
		this.b�t = b�t;
	}

	public String toString() 
	{
		if(b�t == null)
		{
			return "B�teiers navn: " + navn + ", Adresse: " + adresse + ", Medlemsnr: " + medlemsnr + "\n";
		}
			return "B�teiers navn: " + navn + ", Adresse: " + adresse + ", Medlemsnr: " + medlemsnr + b�t.toString();
	}

}
