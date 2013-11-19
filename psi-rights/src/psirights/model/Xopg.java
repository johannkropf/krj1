package psirights.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Kompetenz
@Entity
@Table(name = "XOPG")
public class Xopg implements Serializable{
	
	@OneToMany(mappedBy = "xopg")
	// xopg = Name der variablen im Objekt Xrgz
	List<Xrgz> xrgz;
	
	@OneToMany(mappedBy = "xopg")
	// xopg = Name der variablen im Objekt Xopz
	List<Xopz> xopz;
	
	@Id
	String xopgphysseq;

	String xopgname; // Kompetenz
	String xopgbez; // Bezeichnung
	
	public Xopg(){
	}
	public String getXopgphysseq() {
		return xopgphysseq;
	}
	public void setXopgphysseq(String xopgphysseq) {
		this.xopgphysseq = xopgphysseq;
	}
	public String getXopgname() {
		return xopgname;
	}
	public void setXopgname(String xopgname) {
		this.xopgname = xopgname;
	}
	public String getXopgbez() {
		return xopgbez;
	}
	public void setXopgbez(String xopgbez) {
		this.xopgbez = xopgbez;
	}

	public String to_String(){
		return "Xopg toString -> " + getXopgname() + " " + getXopgbez();
	}


}
