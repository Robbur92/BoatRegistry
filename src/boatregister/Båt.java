package boatregister;

import java.io.Serializable;

public class BÂt implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merke, farge;
	private int regnmr, Ârsmodell;
	private double hestekrefter, lengde;
	BÂt neste;
	
	public BÂt(String m, String f, int r, int Â, double h, double l)
	{
		merke = m;
		farge = f;
		regnmr = r;
		Ârsmodell = Â;
		hestekrefter = h;
		lengde = l;
		neste = null;
	}

	public String getMerke() 
	{
		return merke;
	}

	public void setMerke(String merke) 
	{
		this.merke = merke;
	}

	public String getFarge() 
	{
		return farge;
	}

	public void setFarge(String farge) 
	{
		this.farge = farge;
	}

	public int getRegnmr() 
	{
		return regnmr;
	}

	public void setRegnmr(int regnmr) 
	{
		this.regnmr = regnmr;
	}

	public int get≈rsmodell() 
	{
		return Ârsmodell;
	}

	public void set≈rsmodell(int Ârsmodell) 
	{
		this.Ârsmodell = Ârsmodell;
	}

	public double getHestekrefter() 
	{
		return hestekrefter;
	}

	public void setHestekrefter(double hestekrefter) 
	{
		this.hestekrefter = hestekrefter;
	}

	public double getLengde() 
	{
		return lengde;
	}

	public void setLengde(double lengde) 
	{
		this.lengde = lengde;
	}

	public String toString() 
	{
		return "\nBÂt Merke: " + merke + ", Farge: " + farge + ", Registreringsnummer: "
				+ regnmr + ", ≈rsmodell: " + Ârsmodell + ", \nHestekrefter: "
				+ hestekrefter + ", Lengde i fot: " + lengde + "\n";
	}
}
