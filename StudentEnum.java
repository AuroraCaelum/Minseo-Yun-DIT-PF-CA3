import java.util.ArrayList;

public enum StudentEnum {
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
