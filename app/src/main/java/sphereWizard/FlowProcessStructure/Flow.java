package sphereWizard.FlowProcessStructure;

/**
 * The `Flow` class is an abstract class representing a generic flow in a
 * process, with attributes for category, value, and unit.
 * 
 * @author Group 22
 * @version 1.0
 */
public abstract class Flow {
    /**
     * The category of the flow.
     */
    private String category;
    /**
     * The value of the flow.
     */
    private double value;
    /**
     * The unit of the flow.
     */
    private Enum<?> unit;

    /**
     * Constructs a `Flow` object with the specified category, value, and unit.
     *
     * @param category The category of the flow.
     * @param value    The value of the flow.
     * @param unit     The unit of the flow.
     */
    public Flow(String category, double value, Enum<?> unit) {
        this.category = category;
        this.value = value;
        this.unit = unit;
    }

    /**
     * Gets the category of the flow.
     *
     * @return The category of the flow.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the value of the flow.
     *
     * @return The value of the flow.
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the unit of the flow.
     *
     * @return The unit of the flow.
     */
    public Enum<?> getUnit() {
        return unit;
    }

    /**
     * Sets the category of the flow.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the value of the flow.
     *
     * @param value The value to set.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the unit of the flow.
     *
     * @param unit The unit to set.
     */
    public void setUnit(Enum<?> unit) {
        this.unit = unit;
    }

    /**
     * Returns a string representation of the `Flow`.
     *
     * @return A string representation of the `Flow`.
     */
    @Override
    public String toString() {
        return "Flow {" +
                ", category='" + category + '\'' +
                ", value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }

}