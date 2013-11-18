package psirights.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Anwenderzuordnung
@Entity
@Table(name = "XARZ")
public class Xarz {
	
	@Id
	String xarzphysseq;

	String xawdname;
	String name; // Rolle
	String xunbname; // Bereich
	String validstart;
	String validend;
	
	public String getXarzphysseq() {
		return xarzphysseq;
	}
	public void setXarzphysseq(String xarzphysseq) {
		this.xarzphysseq = xarzphysseq;
	}
	public String getXawdname() {
		return xawdname;
	}
	public void setXawdname(String xawdname) {
		this.xawdname = xawdname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXunbname() {
		return xunbname;
	}
	public void setXunbname(String xunbname) {
		this.xunbname = xunbname;
	}
	public String getValidstart() {
		return validstart;
	}
	public void setValidstart(String validstart) {
		this.validstart = validstart;
	}
	public String getValidend() {
		return validend;
	}
	public void setValidend(String validend) {
		this.validend = validend;
	}
	
	
}
