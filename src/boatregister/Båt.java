package boatregister;

import java.io.Serializable;

public class B�t implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merke, farge;
	private int regnmr, �rsmodell;
	private double hestekrefter, lengde;
	B�t neste;
	
	public B�t(String m, String f, int r, int �, double h, double l)
	{
		merke = m;
		farge = f;
		regnmr = r;
		�rsmodell = �;
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

	public int get�rsmodell() 
	{
		return �rsmodell;
	}

	public void set�rsmodell(int �rsmodell) 
	{
		this.�rsmodell = �rsmodell;
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
		return "\nB�t Merke: " + merke + ", Farge: " + farge + ", Registreringsnummer: "
				+ regnmr + ", �rsmodell: " + �rsmodell + ", \nHestekrefter: "
				+ hestekrefter + ", Lengde i fot: " + lengde + "\n";
	}
}
