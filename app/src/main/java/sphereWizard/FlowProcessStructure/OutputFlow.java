package sphereWizard.FlowProcessStructure;

import sphereWizard.Enums.OutputFlowTypes;

/**
 * The `OutputFlow` class represents an output flow in a process, extending the
 * generic `Flow` class and adding an output flow type attribute.
 * 
 * @author Group 22
 * @version 1.0
 */
public class OutputFlow extends Flow {
    /**
     * The output flow type.
     */
    private OutputFlowTypes name;

    /**
     * Constructs an `OutputFlow` object with the specified output flow type,
     * category, value, and unit.
     *
     * @param name     The output flow type.
     * @param category The category of the output flow.
     * @param value    The value of the output flow.
     * @param unit     The unit of the output flow.
     */
    public OutputFlow(OutputFlowTypes name, String category, double value, Enum<?> unit) {
        super(category, value, unit);
        this.name = name;
    }

    /**
     * Gets the output flow type.
     *
     * @return The output flow type.
     */
    public OutputFlowTypes getName() {
        return name;
    }

    /**
     * Sets the output flow type.
     *
     * @param name The output flow type to set.
     */
    public void setName(OutputFlowTypes name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the `OutputFlow`.
     *
     * @return A string representation of the `OutputFlow`.
     */
    @Override
    public String toString() {
        return "OutputFlow {" +
                "name=" + name +
                ", category='" + getCategory() + '\'' +
                ", value=" + getValue() +
                ", unit='" + getUnit() + '\'' +
                '}';
    }
}
