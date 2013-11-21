package psirights.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: krj
 * Date: 21.11.13
 * Time: 14:41
 */

//Anwendungsoperationen
@Entity
@Table(name = "XOPR")
public class Xopr {

    /*
    @OneToMany(mappedBy = "xopr")
    // xopr = Name der variablen im Objekt Xopz
            List<Xopz> xopz;
    */

    @Id
    String xoprphysseq;

    String xoprobj; // Object
    String xoprmethode; // Methode

    public Xopr() {
    }

    public String getXoprphysseq() {
        return xoprphysseq;
    }

    public void setXoprphysseq(String xoprphysseq) {
        this.xoprphysseq = xoprphysseq;
    }

    public String getXoprmethode() {
        return xoprmethode;
    }

    public void setXoprmethode(String xoprmethode) {
        this.xoprmethode = xoprmethode;
    }

    public String getXoprobj() {
        return xoprobj;
    }

    public void setXoprobj(String xoprobj) {
        this.xoprobj = xoprobj;
    }

    @Override
    public String toString() {
        return "Xopr{" +
                "xoprphysseq='" + xoprphysseq + '\'' +
                ", xoprobj='" + xoprobj + '\'' +
                ", xoprmethode='" + xoprmethode + '\'' +
                '}';
    }
}
