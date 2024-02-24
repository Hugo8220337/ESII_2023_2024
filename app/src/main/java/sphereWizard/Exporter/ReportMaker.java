package sphereWizard.Exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import sphereWizard.Calculator.CircularityCalculator;
import sphereWizard.FlowProcessStructure.Process;
import sphereWizard.Interfaces.sendModeledData;
import sphereWizard.Interfaces.sendResults;
import sphereWizard.FlowProcessStructure.InputFlow;
import sphereWizard.FlowProcessStructure.OutputFlow;

public class ReportMaker {
    sendModeledData ps;
    sendResults calculator;

    public ReportMaker(sendModeledData ps, sendResults cc) {
        this.ps = ps;
        this.calculator = cc;
    }

    public void exportInformationToCsv(String filePath) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath));

            // write processes
            String processesCsv = ";;;Processes;;;\n";
            for (Process process : ps.getProductSystem().getProductSystemData().getProcesses()) {
                processesCsv += processeCsvInfo(process);
            }
            writer.write(processesCsv + '\n');

            // write product system
            writer.write(productSystemCsvData());

            // write calculator
            writer.write(calculatorCsvInfo());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String productSystemCsvData() {
        String productSystemCsv = ";;;Product System Info;;;\n";

        productSystemCsv += "Product Life Time: ;" + ps.getProductSystem().getProductLifetime() + '\n' +
                "Product Utility: ;" + ps.getProductSystem().getProductUtility() + '\n' +
                "Waste Eliminated: ;" + ps.getProductSystem().getWasteEliminated() + '\n' +
                "Recycled Input: ;" + ps.getProductSystem().getRecycledInput() + '\n' +
                "Mass: ;" + ps.getProductSystem().getMass() + '\n';

        return productSystemCsv;
    }

    private String calculatorCsvInfo() {
        String calculatorCsvInfo = ";;;Results;;;\n";
        CircularityCalculator circularityCalculator = calculator.getCircularityCalculator();

        calculatorCsvInfo += "X: ;" + circularityCalculator.getX() + '\n' +
                "Utility Factor: ;" + circularityCalculator.getUtilityFactor() + '\n' +
                "Linear Flow Index: ;" + circularityCalculator.getLinearFlowIndex() + '\n' +
                "Circularity Index: ;" + circularityCalculator.getCircularityIndex() + '\n' ; //+
//                "Total Circularity Index: ;" + calculator.getCircularityIndexTotal() + '\n';

        return calculatorCsvInfo;
    }

    private String processeCsvInfo(Process process) {
        // Name and Category
        String processCsv = ";;;" + process.getName() + ";" + process.getCategory() + ";;\n";

        // headers
        processCsv += "Input;;;;Output;;;\n" +
                "flow;categoria;Quantidade;Unit;flow;categoria;quantidade;unit\n";

        processCsv += processFlowsCsvInfo(process.getInputFlows(), process.getOutputFlows());

        return processCsv;
    }

    private String processFlowsCsvInfo(List<InputFlow> inputFlows, List<OutputFlow> outputFlows) {
        String flowsCsv = "";

        // Loop through flows
        int maxFlowCount = Math.max(inputFlows.size(), outputFlows.size());

        for (int i = 0; i < maxFlowCount; i++) {
            // Input flow
            if (i < inputFlows.size()) {
                InputFlow inputFlow = inputFlows.get(i);
                flowsCsv += inputFlow.getName() + ';' +
                        inputFlow.getCategory() + ';' +
                        inputFlow.getValue() + ';' +
                        inputFlow.getUnit() + ';';
            } else {
                // Empty placeholders for input flow columns
                flowsCsv += ";;;;";
            }

            // Output flow
            if (i < outputFlows.size()) {
                OutputFlow outputFlow = outputFlows.get(i);
                flowsCsv += outputFlow.getName().toString() + ';' +
                        outputFlow.getCategory() + ';' +
                        outputFlow.getValue() + ';' +
                        outputFlow.getUnit();
            } else {
                // Empty placeholders for output flow columns
                flowsCsv += ";;;";
            }

            flowsCsv += '\n';
        }

        return flowsCsv;
    }
}