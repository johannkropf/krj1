package psirights.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part {

	@Id
	String artikel_nr;

	String bezeichnung_1;

	public String getArtikel_nr() {
		return artikel_nr;
	}

	public void setArtikel_nr(String artikel_nr) {
		this.artikel_nr = artikel_nr;
	}

	public String getBezeichnung_1() {
		return bezeichnung_1;
	}

	public void setBezeichnung_1(String bezeichnung_1) {
		this.bezeichnung_1 = bezeichnung_1;
	}
}

