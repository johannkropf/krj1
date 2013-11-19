package psirights.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Rolle
@Entity
@Table(name = "XROL")
public class Xrol implements Serializable{
	
	@OneToMany(mappedBy = "xrol")
	// xrol = Name der variablen im Objekt Xarz
	List<Xarz> xarz;

	@OneToMany(mappedBy = "xrol")
	// xrol = Name der variablen im Objekt Xrgz
	List<Xrgz> xrgz;

	@Id
	String xrolphysseq;

	String name; // Rolle
	String description;
	
	public Xrol(){
	}
	
	public String getXrolphysseq() {
		return xrolphysseq;
	}
	public void setXrolphysseq(String xrolphysseq) {
		this.xrolphysseq = xrolphysseq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		return "Xrol toString -> " + getName() + " " + getDescription();
	}

}
