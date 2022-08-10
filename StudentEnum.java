/*
# Env       -  JDK 1.8.0_331
# File      -  StudentEnum.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

public enum StudentEnum {
    // Pre-defined Student Values
    YUN("Yun Minseo", "10240311");

    private final String name;
    private final String id;
    StudentEnum(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
