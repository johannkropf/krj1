package psirights.model;

/**
 * User: krj
 * Date: 21.11.13
 * Time: 12:55
 */
public class Operations {
    String operation;

    public Operations() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "Operatons{" +
                "operation='" + operation + '\'' +
                '}';
    }
}
