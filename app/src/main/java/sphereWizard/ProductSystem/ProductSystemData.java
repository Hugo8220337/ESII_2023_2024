package sphereWizard.ProductSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sphereWizard.Exceptions.FlowOutputNotRecognizedException;
import sphereWizard.Exceptions.UnitNotRecognizedException;
import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.FilterData.FilterData;
import sphereWizard.FlowProcessStructure.InputFlow;
import sphereWizard.FlowProcessStructure.OutputFlow;
import sphereWizard.FlowProcessStructure.Process;
import sphereWizard.Interfaces.SendFilteredData;
import sphereWizard.Interfaces.sendFileData;
import sphereWizard.Util.Converter;
import sphereWizard.Util.UnitAssigner;
import sphereWizard.Enums.OutputFlowTypes;

/**
 * The `ProductSystemData` class represents the data structure for storing
 * information about processes,
 * input flows, and output flows. It provides methods for reading data from a
 * CSV file and retrieving
 * the list of processes.
 * 
 * @author Group 22
 * @version 1.0
 */
public class ProductSystemData implements sendFileData {

    /**
     * The list of processes contained in the `ProductSystemData`. Each process
     * represents a series of
     * interconnected flows within a system, including input and output flows.
     */
    private List<Process> processes;

    /**
     * Constructs an empty ProductSystemData.
     */
    public ProductSystemData() {
        this.processes = new ArrayList<>();
    }

    /**
     * Constructs a ProductSystemData with the specified list of processes.
     *
     * @param processes The list of processes to initialize the ProductSystemData.
     */
    public ProductSystemData(ArrayList<Process> processes) {
        this.processes = processes;
    }

    /**
     * Reads a CSV file containing information about processes, input flows, and
     * output flows.
     *
     * @param path      The path to the CSV file.
     * @param delimiter The delimiter used to separate data in the CSV
     *                  file.
     * @throws FlowOutputNotRecognizedException
     * @throws UnitNotRecognizedException
     * @throws IOException
     */
    public void readCsv(String path, String delimiter)
            throws UnitNotRecognizedException, FlowOutputNotRecognizedException,
            NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        try {
            // If the file does not exist
            if (!fileExists(path)) {
                throw new FileNotFoundException("File does not exist: " + path);
            }

            processCsvData(reader, delimiter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }

    }

    private void processCsvData(BufferedReader reader, String delimiter)
            throws UnitNotRecognizedException, FlowOutputNotRecognizedException, IOException {
        String line;

        String processName = null;
        String processCategory = null;
        List<InputFlow> inputFlows = new ArrayList<>();
        List<OutputFlow> outputFlows = new ArrayList<>();
        boolean hasFlows = false;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(delimiter);

            if (isHeaderLine(data)) {
                // If the previous process had flows, add it to the list of processes
                if (hasFlows) {
                    this.processes.add(new Process(processName, processCategory, inputFlows, outputFlows));

                    // Reset for the next process
                    processName = null;
                    inputFlows = new ArrayList<>();
                    outputFlows = new ArrayList<>();
                }

                // Read the name and the category of the new process
                processName = data[3];
                processCategory = data[4];

                // Skip the next two lines because they are useless
                reader.readLine();
                reader.readLine();
            } else {
                processFlowData(data, inputFlows, outputFlows);

                // To indicate that the previous process had flows
                hasFlows = true;

            }
        }

        // When the loop ends, there may still be a process to insert, ensuring that
        // nothing is left to add
        if (hasFlows) {
            this.processes.add(new Process(processName, processCategory, inputFlows, outputFlows));
        }
    }

    /**
     * Checks whether a file exists at the specified path.
     *
     * @param filePath The path to the file to be checked for existence.
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    private static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Checks if a line is a header line.
     *
     * @param data The array representing the line data.
     * @return True if the line is a header line, false otherwise.
     */
    private boolean isHeaderLine(String[] data) {
        return data[0].trim().isEmpty();
    }

    /**
     * Processes the flow data from a line and adds it to the input and output flow
     * lists.
     *
     * @param data        The array representing the line data.
     * @param inputFlows  The list of input flows to add to.
     * @param outputFlows The list of output flows to add to.
     * @throws UnitNotRecognizedException
     * @throws FlowOutputNotRecognizedException
     */
    private void processFlowData(String[] data, List<InputFlow> inputFlows, List<OutputFlow> outputFlows)
            throws UnitNotRecognizedException, FlowOutputNotRecognizedException {
        UnitAssigner unitAssigner = new UnitAssigner();

        String inputName = data[0];
        String inputCategoria = data[1];
        double inputQuantidade = Double.parseDouble(data[2]);
        Enum<?> inputUnit = unitAssigner.assignEnum(data[3]);

        if (inputQuantidade == 0) {
            // Handle the case where quantity is 0
            throw new IllegalArgumentException("Quantity should not be 0 for input flow: " + inputName);
        }

        OutputFlowTypes outputName = readOutputFlowType(data[4]);
        String outputCategoria = data[5];
        double outputQuantidade = Double.parseDouble(data[6]);
        Enum<?> outputUnit = unitAssigner.assignEnum(data[7]);

        if (Math.ceil(outputQuantidade) == 0) {
            // Handle the case where quantity is 0
            throw new IllegalArgumentException("Quantity should not be 0 for output flow: " + outputName);
        }

        Converter converter = new Converter();

        InputFlow inputFlow = new InputFlow(inputName, inputCategoria, inputQuantidade, inputUnit);
        OutputFlow outputFlow = new OutputFlow(outputName, outputCategoria, outputQuantidade, outputUnit);

        converter.convert(inputFlow);
        converter.convert(outputFlow);

        inputFlows.add(inputFlow);
        outputFlows.add(outputFlow);
    }

    /**
     * Reads the output flow type from the provided data and maps it to the
     * corresponding enum value.
     *
     * @param data The string representing the output flow type.
     * @return The OutputFlowTypes enum value corresponding to the provided data.
     * @throws FlowOutputNotRecognizedException If the output flow type is not
     *                                          recognized.
     */
    private OutputFlowTypes readOutputFlowType(String data) throws FlowOutputNotRecognizedException {
        OutputFlowTypes type = null;

        switch (data) {
            case "Virgin Material":
                type = OutputFlowTypes.VIRGIN_MATERIAL;
                break;
            case "Recycled Material":
                type = OutputFlowTypes.RECYCLED_MATERIAL;
                break;
            case "Recovered Recycled Material":
                type = OutputFlowTypes.RECOVERED_RECYCLED_MATERIAL;
                break;
            case "Energy for Main Materials Production":
                type = OutputFlowTypes.MAIN_MATERIALS_PRODUCTION_ENERGY;
                break;
            case "Energy for Secondary Materials Production":
                type = OutputFlowTypes.SECONDARY_MATERIALS_PRODUCTION_ENERGY;
                break;
            case "Transportation":
                type = OutputFlowTypes.TRANSPORT_MATERIAL;
                break;
            default:
                // Handle the case where the output type is not recognized
                throw new FlowOutputNotRecognizedException("Not recognized: " + data);
        }

        return type;
    }

    /**
     * Retrieves the list of processes contained in the ProductSystemData.
     *
     * @return An ArrayList containing the processes in the ProductSystemData.
     */
    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    /**
     * Returns a string representation of the ProductSystemData.
     *
     * @return A string representation of the ProductSystemData.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ProductSystem:\n");
        for (Process process : processes) {
            result.append(process.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a filtered version of the ProductSystemData object with the following
     * rules :
     * for option 1 -> the method returns the entire productSystemData object;
     * for option 2 -> the method returns the processes in productSystemData with
     * the given category as string;
     * for option 3 -> the method returns the processes in productSystemData with
     * the given name as string;
     *
     * @param option         can be 1,2 or 3 and represents " send all ", " send by
     *                       category ", "send by name " respectively
     * @param nameOrCategory String that represents the name of a process or
     *                       category of processes.
     * @return null
     * @throws EmptyListException if productSystemData object is empty.
     */
    public ProductSystemData filter(int option, String nameOrCategory) throws EmptyListException { // posso receber
                                                                                                   // string e comparar
                                                                                                   // com processos ou
                                                                                                   // categorias e se
                                                                                                   // nenhum dos dois,
                                                                                                   // envio tudo

        SendFilteredData filterData = new FilterData(this);
        ProductSystemData toSend = this;

        // System.out.println(" 1 - Enviar tudo; ");
        // System.out.println(" 2 - Enviar processos por categoria ");
        // System.out.println(" 3 - Enviar processos por nome; ");

        if (option == 1) {
            toSend.setProcesses(filterData.sendAll().getProcesses());
        } else if (option == 2) {
            toSend.setProcesses(filterData.sendByCategory(nameOrCategory));
        } else if (option == 3) {
            toSend.setProcesses(filterData.sendByName(nameOrCategory));
        }
        return toSend;
    }

    @Override
    public ProductSystemData getProductSystemData() {
        return this;
    }
}
