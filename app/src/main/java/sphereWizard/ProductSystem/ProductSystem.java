package sphereWizard.ProductSystem;

import sphereWizard.Enums.OutputFlowTypes;
import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.FlowProcessStructure.OutputFlow;
import sphereWizard.FlowProcessStructure.Process;
import sphereWizard.Interfaces.sendFileData;
import sphereWizard.Interfaces.sendModeledData;

/**
 * The systemProduct class represents a system for managing product-related
 * information and calculations.
 * It includes attributes such as product lifetime, product utility, waste
 * eliminated, recycled input, and mass.
 * The class provides methods to perform calculations related to waste
 * elimination, recycled input, and mass.
 * Additionally, it includes getters and setters for accessing and modifying the
 * attributes.
 *
 * @author Group 22
 * @version 1.0
 */
public class ProductSystem implements sendModeledData {

    /**
     * The ProductSystem associated with this product system.
     */
    private sendFileData productSystemData;
    /**
     * The useful life of the product (L).
     */
    private int productLifetime;
    /**
     * The usefulness of the product (number of uses) (U).
     */
    private int productUtility;
    /**
     * The total waste to be eliminated  (W) - W = V -Rr.
     */
    private double wasteEliminated; 
    /**
     * The input of recycled value (Ri) – Ri = R + Rr.
     */
    private double recycledInput;
    /**
     * The mass (M), calculated as M = V + R.
     */
    private double mass;
    /**
     * The necessary energy required for primary materials in the product life
     * cycle.
     */
    private double necessaryEnergyForPrimaryMaterials;

    /**
     * The necessary energy required for secondary materials in the product life
     * cycle.
     */
    private double necessaryEnergyForSecundaryMaterials;


    /**
     * Constructs a ProductSystem object with the specified ProductSystem.
     *
     * @param productSystemData The ProductSystem associated with this product
     *                          system.
     */
    public ProductSystem(sendFileData productSystemData) {
        this.productSystemData = productSystemData.getProductSystemData();
    }

    /**
     * Constructs a ProductSystem object with the specified ProductSystem, product
     * lifetime, and product utility.
     *
     * @param productSystemData The ProductSystem associated with this product system.
     * @param productLifetime   The lifetime of the product (L).
     * @param productUtility    The utility of the product (number of uses) (U).
     */
    public ProductSystem(sendFileData productSystemData, int productLifetime, int productUtility) throws EmptyListException {
        this.setProductSystemDataFilter(productSystemData.getProductSystemData());
        this.productLifetime = productLifetime;
        this.productUtility = productUtility;
    }

    /*
     * This method calls on the filter method from productSystemData class and sets the productSystem
     * to its correct version depending on input given.
     * @return correct version of productSystemData.
     * @throws EmptyListException if productSystemData is empty.
     */
    public void setProductSystemDataFilter(sendFileData psd) throws EmptyListException {
        this.productSystemData = psd.getProductSystemData().filter(1, "");
    }

    // Calculations ...

    /**
     * Calculates the total waste to be eliminated (W).
     *
     * @return The waste eliminated.
     */
    public double calculateWasteEliminated() {
        // W = V - Rr

        double totalVirginMaterial = calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL);
        double totalRecoveredMaterialRecycled = calculateFlowValue(OutputFlowTypes.RECOVERED_RECYCLED_MATERIAL);

        wasteEliminated = (totalVirginMaterial - totalRecoveredMaterialRecycled);
        return wasteEliminated;
    }

    /**
     * Calculates the input of recycled value (Ri).
     *
     * @return The recycled input.
     */
    public double calculateRecycledInput() {
        // Ri = R + Rr

        double totalMaterialRecycled = calculateFlowValue(OutputFlowTypes.RECYCLED_MATERIAL);
        double totalRecoveredMaterialRecycled = calculateFlowValue(OutputFlowTypes.RECOVERED_RECYCLED_MATERIAL);

        recycledInput = totalMaterialRecycled + totalRecoveredMaterialRecycled ;
        return recycledInput;
    }

    /**
     * Calculates the mass (M).
     *
     * @return The mass.
     */
    public double calculateMass() {
        // M = V + Ri

        double totalVirginMaterial = calculateFlowValue(OutputFlowTypes.VIRGIN_MATERIAL);
        double totalRecycledInput = calculateRecycledInput();

        mass = totalVirginMaterial + totalRecycledInput;
        return mass;
    }

    /**
     * Calculates the necessary energy for primary materials.
     *
     * @return The necessary energy for primary materials.
     */
    public double calculateNecessaryEnergyForPrimaryMaterials() {

        necessaryEnergyForPrimaryMaterials = calculateFlowValue(OutputFlowTypes.MAIN_MATERIALS_PRODUCTION_ENERGY);
        return necessaryEnergyForPrimaryMaterials;

    }

    /**
     * Calculates the necessary energy for secondary materials.
     *
     * @return The necessary energy for secondary materials.
     */
    public double calculateNecessaryEnergyForSecondaryMaterials() {

        necessaryEnergyForSecundaryMaterials = calculateFlowValue(OutputFlowTypes.SECONDARY_MATERIALS_PRODUCTION_ENERGY);
        return necessaryEnergyForSecundaryMaterials;
    }

    // Gets and Sets

    /**
     * Gets the associated ProductSystem.
     *
     * @return The ProductSystem.
     */
    public ProductSystemData getProductSystemData() {
        return productSystemData.getProductSystemData();
    }

    /**
     * Sets the associated ProductSystem.
     *
     * @param productSystemData The ProductSystem to set.
     */
    public void setProductSystemData(ProductSystemData productSystemData) {
        this.productSystemData = productSystemData;
    }

    /**
     * Gets the product lifetime.
     *
     * @return The product lifetime (L).
     */
    public int getProductLifetime() {
        return productLifetime;
    }

    /**
     * Sets the product lifetime.
     *
     * @param productLifetime The product lifetime (L) to set.
     */
    public void setProductLifetime(int productLifetime) {
        this.productLifetime = productLifetime;
    }

    /**
     * Gets the product utility.
     *
     * @return The product utility (U).
     */
    public int getProductUtility() {
        return productUtility;
    }

    /**
     * Sets the product utility.
     *
     * @param productUtility The product utility (U) to set.
     */
    public void setProductUtility(int productUtility) {
        this.productUtility = productUtility;
    }

    /**
     * Get the waste eliminated
     *
     * @return the waste eliminated (W).
     */
    public double getWasteEliminated() {
        return wasteEliminated;
    }

    /**
     * Get the recycled input
     *
     * @return the recycled input (Ri)
     */
    public double getRecycledInput() {
        return recycledInput;
    }

    /**
     * Get the mass
     *
     * @return the mass (M)
     */
    public double getMass() {
        return mass;
    }

    @Override
    public ProductSystem getProductSystem() {
        return this;
    }


    // Method to help Calculations

    /**
     * Calculates the total value of a specific output flow type in the product system.
     * <p>
     * This method iterates through all processes in the product system and sums up the values
     * of the specified output flow type.
     *
     * @param flowType The type of the output flow for which the total value is calculated.
     * @return The total value of the specified output flow type in the product system.
     */
    public double calculateFlowValue(OutputFlowTypes flowType) {
        // Method for Output Flows (OutputFlow):
        double totalFlowValue = 0.0;
        for (Process process : productSystemData.getProductSystemData().getProcesses()) {
            for (OutputFlow outputFlow : process.getOutputFlows()) {
                if ((outputFlow != null) && outputFlow.getName().equals(flowType)){
                    totalFlowValue += outputFlow.getValue();
                }
            }
        }
        return totalFlowValue;
    }

    /**
     * Calculates various methods related to the product system.
     * These calculations include waste eliminated, recycled input, mass, necessary
     * energy for primary materials, and necessary energy for secondary materials.
     *
     * This method serves as a convenience method to calculate all the related methods
     * in one call.
     */
    public void calculateAllMethods(){
        // Calculate waste eliminated
        this.calculateWasteEliminated();

        // Calculate recycled input
        this.calculateRecycledInput();

        // Calculate mass
        this.calculateMass();

        // Calculate necessary energy for primary materials
        this.calculateNecessaryEnergyForPrimaryMaterials();

        // Calculate necessary energy for secondary materials
        this.calculateNecessaryEnergyForSecondaryMaterials();
    }
}
