package Tax;

public class Expenses {
    private String name;
    private double value;

    /**
     * Constructor to initialize an expense with a name and a value.
     * @param name The name of the expense.
     * @param value The value of the expense.
     */
    public Expenses(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Getter for the expense name.
     * @return The name of the expense.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the expense name.
     * @param name The name of the expense to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the expense value.
     * @return The value of the expense.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for the expense value.
     * @param value The value of the expense to set.
     */
    public void setValue(double value) {
        this.value = value;
    }
}
