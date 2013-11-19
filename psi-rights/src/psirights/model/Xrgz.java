package psirights.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Kompetenzzuordnung
@Entity
@Table(name = "XRGZ")
public class Xrgz {

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "name", referencedColumnName = "name")
	Xrol xrol;

	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, name = "xopgname", referencedColumnName = "xopgname")
	Xopg xopg;

	@Id
	String xrgzphysseq;

	String name; // Rolle
	String xopgname; // Kompetenz
	String xunbname; // Bereich
	String siteid; // Werk

	public Xrgz() {

	}

	public String getXrgzphysseq() {
		return xrgzphysseq;
	}

	public void setXrgzphysseq(String xrgzphysseq) {
		this.xrgzphysseq = xrgzphysseq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXopgname() {
		return xopgname;
	}

	public void setXopgname(String xopgname) {
		this.xopgname = xopgname;
	}

	public String getXunbname() {
		return xunbname;
	}

	public void setXunbname(String xunbname) {
		this.xunbname = xunbname;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public String toString() {
		return "Xrgz toString -> " + getName() + " " + getXopgname() + " "
				+ getXunbname();
	}

}
