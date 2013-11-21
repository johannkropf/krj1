package psirights.model;

/**
 * User: krj
 * Date: 21.11.13
 * Time: 12:55
 */
public class Operations {
    String methode;

    public Operations() {
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    @Override
    public String toString() {
        return "Operatons{" +
                "methode='" + methode + '\'' +
                '}';
    }
}
