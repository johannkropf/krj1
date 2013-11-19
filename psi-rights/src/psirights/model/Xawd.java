package psirights.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

// Anwender
@Entity
@Table(name = "XAWD")
public class Xawd implements Serializable {

	@OneToMany(mappedBy = "xawd")
	// xawd = Name der variablen im Objekt Xarz
	List<Xarz> xarz;

	@Id
	String xawdphysseq;

	String xawdname;
	String xawdbez;
    Integer xawdtype;
    
	public Xawd() {
	}

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

	public String getXarzName() {
		String ret = "";
		for (Xarz xarzs : xarz) {
			ret = xarzs.getName();
		}
		return ret;
	}
	
	public String toString(){
		return "Xawd toString -> " + getXawdname() + " " + getXawdbez();
	}
}
