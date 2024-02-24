package sphereWizard.Enums;

public enum OutputFlowTypes {
    VIRGIN_MATERIAL("Virgin Material"),
    RECYCLED_MATERIAL("Recycled Material"),

    RECOVERED_RECYCLED_MATERIAL("Recovered Recycled Material"),
    MAIN_MATERIALS_PRODUCTION_ENERGY("Energy for Main Materials Production"),
    SECONDARY_MATERIALS_PRODUCTION_ENERGY("Energy for Secondary Materials Production"),
    TRANSPORT_MATERIAL("Transportation");

    private final String name;

    OutputFlowTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}