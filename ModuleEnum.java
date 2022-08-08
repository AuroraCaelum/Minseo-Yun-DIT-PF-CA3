public enum ModuleEnum {
    BSP("Programming Fundamentals", "ITSD001", "BSP", 4),
    PS("Problem Solving", "ITSD002", "PS", 4),
    CN("Communications and Networks", "ITSD003", "CN", 4),
    PF("Programming Fundamentals", "ITSD004", "PF", 4);

    private final String name;
    private final String moduleCode;
    private final String description;
    private final int creditUnits;
    ModuleEnum(String name, String moduleCode, String description, int creditUnits) {
        this.name = name;
        this.moduleCode = moduleCode;
        this.description = description;
        this.creditUnits = creditUnits;
    }

    public String getName() {
        return this.name;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCreditUnits() {
        return this.creditUnits;
    }
}
