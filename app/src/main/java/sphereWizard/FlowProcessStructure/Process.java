package sphereWizard.FlowProcessStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Process` class represents a process, containing input flows and output
 * flows.
 * 
 * @author Group 22
 * @version 1.0
 */
public class Process {
    /**
     * The name of the process.
     */
    private String name;

    /**
     * category of the process
     */
    private String category;

    /**
     * The list of input flows in the process.
     */
    private List<InputFlow> inputFlows;
    /**
     * The list of output flows in the process.
     */
    private List<OutputFlow> outputFlows;

    /**
     * Constructs a `Process` object with the specified name.
     *
     * @param name The name of the process.
     * @param category The category of the process
     */
    public Process(String name, String category) {
        this.name = name;
        this.category = category;
        this.inputFlows = new ArrayList<>();
        this.outputFlows = new ArrayList<>();
    }

    /**
     * Constructs a `Process` object with the specified name, input flows, and
     * output flows.
     *
     * @param name        The name of the process.
     * @param category    The category of the process
     * @param inputFlows  The list of input flows in the process.
     * @param outputFlows The list of output flows in the process.
     */
    public Process(String name, String category, List<InputFlow> inputFlows, List<OutputFlow> outputFlows) {
        this.name = name;
        this.category = category;
        this.inputFlows = new ArrayList<>(inputFlows);
        this.outputFlows = new ArrayList<>(outputFlows);
    }
    
    /**
     * Gets the name of the process.
     *
     * @return The name of the process.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of the process
     * 
     * @return The category of the process
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the list of input flows in the process.
     *
     * @return The list of input flows in the process.
     */
    public List<InputFlow> getInputFlows() {
        return inputFlows;
    }

    /**
     * Gets the list of output flows in the process.
     *
     * @return The list of output flows in the process.
     */
    public List<OutputFlow> getOutputFlows() {
        return outputFlows;
    }

    /**
     * Returns a string representation of the `Process`, including its name, input
     * flows, and output flows.
     *
     * @return A string representation of the `Process`.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Process: " + name + "\nCategory: " + category +"\n");
        result.append("Input Flows:\n");
        for (Flow inputFlow : inputFlows) {
            result.append(inputFlow.toString()).append("\n");
        }
        result.append("Output Flows:\n");
        for (Flow outputFlow : outputFlows) {
            result.append(outputFlow.toString()).append("\n");
        }
        return result.toString();
    }


}
