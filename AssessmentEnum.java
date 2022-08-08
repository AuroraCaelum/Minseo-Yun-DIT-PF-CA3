public enum AssessmentEnum {
    CA1("CA1", "Continuous Assessment 1 - Quiz", 100, 0.1),
    CA2("CA2", "Common Test", 100, 0.4),
    CA3("CA3", "Individual Assignments", 100, 0.4),
    CA4("CA4", "Continuous Assessment 2 - Quiz", 100, 0.1);

    private final String name;
    private final String description;
    private final double totalMarks;
    private final double weightage;
    AssessmentEnum(String name, String description, double totalMarks, double weightage) {
        this.name = name;
        this.description = description;
        this.totalMarks = totalMarks;
        this.weightage = weightage;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getTotalMarks() {
        return this.totalMarks;
    }

    public double getWeightage() {
        return this.weightage;
    }
}
