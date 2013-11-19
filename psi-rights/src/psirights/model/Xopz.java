package psirights.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// Anwendungsoperationszuordnung
@Entity
@Table(name = "XOPZ")
public class Xopz implements Serializable{

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "xopgname", referencedColumnName = "xopgname")
	Xopg xopg;

	@Id
	String xopzphysseq;

	String xoprobj;
	String xoprmethode;
	String xopgname;  // Kompetenz

	public String getXopzphysseq() {
		return xopzphysseq;
	}

	public void setXopzphysseq(String xopzphysseq) {
		this.xopzphysseq = xopzphysseq;
	}

	public String getXoprobj() {
		return xoprobj;
	}

	public void setXoprobj(String xoprobj) {
		this.xoprobj = xoprobj;
	}

	public String getXoprmethode() {
		return xoprmethode;
	}

	public void setXoprmethode(String xoprmethode) {
		this.xoprmethode = xoprmethode;
	}

	public String getXopgname() {
		return xopgname;
	}

	public void setXopgname(String xopgname) {
		this.xopgname = xopgname;
	}
	public String toString(){
		return "Xopz toString -> " + getXoprobj() + " " + getXoprmethode() + " " + getXopgname();
	}
}
