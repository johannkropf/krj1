package psirights.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Anwender
@Entity
@Table(name = "XAWD")
public class Xawd {
	
	@Id
	String xawdphysseq;

	String xawdname;
	String xawdbez;
	
	public String getXawdphysseq() {
		return xawdphysseq;
	}
	public void setXawdphysseq(String xawdphysseq) {
		this.xawdphysseq = xawdphysseq;
	}
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
	
	
}
