package psirights.model;

public class Rights {

	String xawdname;
	String xawdbez;
	String rolle;
	String kompetenz;
	String werk;
	String objekt;
    String methode;

	public String getXawdname() {
		return xawdname;
	}

	public void setXawdname(String xawdname) {
		this.xawdname = xawdname;
	}

	public String getXawdbez() {
		return xawdbez;
	}

	public void setXawdbez(String xawdbez) {
		this.xawdbez = xawdbez;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public String getKompetenz() {
		return kompetenz;
	}

	public void setKompetenz(String kompetenz) {
		this.kompetenz = kompetenz;
	}

	public String getWerk() {
		return werk;
	}

	public void setWerk(String werk) {
		this.werk = werk;
	}

	public String getObjekt() {
		return objekt;
	}

	public void setObjekt(String objekt) {
		this.objekt = objekt;
	}

	public String toString(){
		return "Rights toString -> " + this.xawdname;
	}

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }
}
