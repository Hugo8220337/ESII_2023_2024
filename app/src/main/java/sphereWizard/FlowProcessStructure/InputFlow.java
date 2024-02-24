package sphereWizard.FlowProcessStructure;

/**
 * The `InputFlow` class represents an input flow in a process, extending the
 * generic `Flow` class and adding a name attribute.
 * 
 * @author Group 22
 * @version 1.0
 */
public class InputFlow extends Flow {
    /**
     * The name of the input flow.
     */
    private String name;

    /**
     * Constructs an `InputFlow` object with the specified name, category, value,
     * and unit.
     *
     * @param name     The name of the input flow.
     * @param category The category of the input flow.
     * @param value    The value of the input flow.
     * @param unit     The unit of the input flow.
     */
    public InputFlow(String name, String category, double value, Enum<?> unit) {
        super(category, value, unit);
        this.name = name;
    }

    /**
     * Gets the name of the input flow.
     *
     * @return The name of the input flow.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the input flow.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the `InputFlow`.
     *
     * @return A string representation of the `InputFlow`.
     */
    @Override
    public String toString() {
        return "InputFlow {" +
                "name='" + name + '\'' +
                ", category='" + getCategory() + '\'' +
                ", value=" + getValue() +
                ", unit='" + getUnit() + '\'' +
                '}';
    }

}
