package sphereWizard.Calculator;

import sphereWizard.Enums.OutputFlowTypes;
import sphereWizard.Interfaces.sendModeledData;
import sphereWizard.Interfaces.sendResults;
import sphereWizard.ProductSystem.ProductSystem;

/**
 * The CircularityCalculator class calculates indices related to the circularity
 * of a product system.
 *
 * <p>
 * This class includes methods to calculate the Linear Flow Index (LFI),
 * Circularity Index (MCIp), and Total Circularity Index (MCItotal).
 * Additionally, it performs calculations related to the utility factor and the
 * value of X.
 * </p>
 *
 * @author Group 22
 * @version 1.0
 */

public class CircularityCalculator implements sendResults {

    /**
     * The associated ProductSystem for circularity calculations.
     */
    private sendModeledData productSystem;

    /**
     * The value of X used in circularity calculations.
     */
    private double X;

    /**
     * The utility factor (F(X)) used in circularity calculations.
     */
    private double utilityFactor;

    /**
     * The Linear Flow Index (LFI) used in circularity calculations.
     */
    private double linearFlowIndex;

    /**
     * The Circularity Index (MCIp) used in circularity calculations.
     */
    private double circularityIndex;

    // /**
    // * The Total Circularity Index (MCItotal) used in circularity calculations.
    // */
    // private double circularityIndexTotal;

    /**
     * Constructor that accepts a product system as a parameter.
     *
     * @param productSystem The product system associated with this calculator.
     */
    public CircularityCalculator(sendModeledData productSystem) {
        this.productSystem = productSystem;
    }

    /**
     * Calculates X based on the product's lifespan and utility values.
     */
    protected void calculateX() {
        // (L / Lavg) * (U / Uavg)
        int processSize = productSystem.getProductSystem().getProductSystemData().getProductSystemData().getProcesses()
                .size();
        double Lavg = 0.0;
        double Uavg = 0.0;

        if (processSize > 0) {
            Lavg = ((double) productSystem.getProductSystem().getProductLifetime() / processSize);
            Uavg = ((double) productSystem.getProductSystem().getProductUtility() / processSize);
        }

        if (Lavg == 0 || Uavg == 0) {
            X = 0.0;
        } else {
            X = ((productSystem.getProductSystem().getProductLifetime() / Lavg)
                    * (productSystem.getProductSystem().getProductUtility() / Uavg));
        }
    }

    /**
     * Calculates the utility factor (F(X)) based on the value of X.
     */
    protected void calculateUtilityFactor() {
        // F(X) = 0.9 / X
        if (X == 0) {
            utilityFactor = 0.0;
        } else {
            utilityFactor = (0.9 / X);
        }
    }

    /**
     * Calculates the Linear Flow Index (LFI) based on the product system data.
     */
    protected void calculateLinearFlowIndex() {
        // LFI = (2V - Rr) / (2M + ((Wf - Wc)/ 2))

        // Get the value of V from the product system
        double V = productSystem.getProductSystem().calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL);

        // Get the value of Rr from the product system
        double Rr = productSystem.getProductSystem().calculateFlowValue(OutputFlowTypes.RECOVERED_RECYCLED_MATERIAL);

        // Get the value of M from the product system
        double M = productSystem.getProductSystem().calculateMass();

        // Get the value of Wf from the product system, 5% the virgin material is wf
        double Wf = productSystem.getProductSystem().calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL) * 0.05;

        // Get the value of Wc from the product system, 5% the recicled material is wc
        double Wc = productSystem.getProductSystem().calculateFlowValue(OutputFlowTypes.RECYCLED_MATERIAL) * 0.05;

        linearFlowIndex = (2 * V - Rr) / (2 * M + ((Wf - Wc) / 2));
    }

    /**
     * Calculates the Circularity Index (MCIp) based on the provided formulas.
     *
     * @return The calculated Circularity Index (MCIp).
     */
    public double calculateCircularityIndex() {
        // MCIp = 1 - LFI * F(X)

        calculateLinearFlowIndex();
        calculateX();
        calculateUtilityFactor();
        circularityIndex = 1 - (linearFlowIndex * utilityFactor);

        return circularityIndex;
    }

    /**
     * Calculates the Total Circularity Index (MCItotal) based on the provided
     * formulas.
     *
     * @return The calculated Total Circularity Index (MCItotal).
     */
    // public double calculateCircularityIndexTotal() {
    // // MCItotal = (Vpet * Mpet + Vld-Pe * Mld-pe + Vpur*Mpur)/(Mpet + MLd-pe +
    // Mpur)
    // // MCItotal = (MCIP * M+ MCIP*M+MCIp)/(M+M+M)
    //
    // //double Vpet =
    // productSystem.calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL);
    // double Vpet = this.calculateCircularityIndex();
    // double Mpet = productSystem.calculateMass();
    // //double VldPe =
    // productSystem.calculateFlowValue(OutputFlowTypes.SECONDARY_MATERIALS_PRODUCTION_ENERGY);
    // double VldPe = this.calculateCircularityIndex();
    // double MldPe = productSystem.calculateMass();
    // double Vpur =
    // productSystem.calculateFlowValue(OutputFlowTypes.RECYCLED_MATERIAL);
    // double Mpur = productSystem.calculateMass();
    //
    // double V = productSystem.calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL);
    // double M = productSystem.calculateMass();;
    //
    // double denominator = Mpet + MldPe + Mpur;
    // if (denominator == 0) {
    // circularityIndexTotal = 0.0;
    // } else {
    // circularityIndexTotal = (Vpet * Mpet + VldPe * MldPe + Vpur * Mpur) /
    // denominator;
    // }
    //
    // return circularityIndexTotal;
    // }

    /**
     * Gets the associated ProductSystem.
     *
     * @return The ProductSystem associated with this CircularityCalculator.
     */
    public ProductSystem getProductSystem() {
        return productSystem.getProductSystem();
    }

    /**
     * Sets the associated ProductSystem.
     *
     * @param productSystem The ProductSystem to associate with this
     *                      CircularityCalculator.
     */
    public void setProductSystem(ProductSystem productSystem) {
        this.productSystem = productSystem;
    }

    /**
     * Gets the utility factor (F(X)) used in circularity calculations.
     *
     * @return The utility factor (F(X)).
     */
    public double getX() {
        return X;
    }

    /**
     * Gets the utility factor (F(X)) used in circularity calculations.
     *
     * @return The utility factor (F(X)).
     */
    public double getUtilityFactor() {
        return utilityFactor;
    }

    /**
     * Gets the Linear Flow Index (LFI) used in circularity calculations.
     *
     * @return The Linear Flow Index (LFI).
     */
    public double getLinearFlowIndex() {
        return linearFlowIndex;
    }

    /**
     * Gets the Circularity Index (MCIp) calculated by the CircularityCalculator.
     *
     * @return The Circularity Index (MCIp).
     */
    public double getCircularityIndex() {
        return circularityIndex;
    }

    // /**
    // * Gets the Total Circularity Index (MCItotal) calculated by the
    // * CircularityCalculator.
    // *
    // * @return The Total Circularity Index (MCItotal).
    // */
    // public double getCircularityIndexTotal() {
    // return circularityIndexTotal;
    // }

    /**
     * Retrieves the Circularity Calculator
     *
     * @return The Circularity Calculator
     */
    @Override
    public CircularityCalculator getCircularityCalculator() {
        return this;
    }

    /**
     * Gets the result of the Total Circularity Index (MCItotal) calculated by the
     * calculator.
     *
     * @return The Total Circularity Index (MCItotal).
     */
    // @Override
    // public double getResultCircularityIndexTotal() {
    // return circularityIndexTotal;
    // }

}
