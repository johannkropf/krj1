package psirights.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Anwendungsoperationszuordnung
@Entity
@Table(name = "XOPZ")
public class Xopz {
	@Id
	String xopzphysseq;

	String xoprobj;
	String xoprmethode;
	String xopgname;
	
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

	
}
