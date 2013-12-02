package com.pezbailarin.ccest;

public class UnitDetails
{ 
	private int icon;
	private String nombre;
	private String extra;
	private int coste;

	public void setIcon(int i){
		icon=i;
	}
	public void setNombre(String n){
		nombre=n;
	}
	public int getIcon(){
		return icon;
	}
	public String getNombre(){
		return nombre;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public int getCoste() {
		return coste;
	}
	public void setCoste(int coste) {
		this.coste = coste;
	}
}
