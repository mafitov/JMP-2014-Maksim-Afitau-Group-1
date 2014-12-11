package by.jmp.model;

public enum EmployeeStatus {
    PROJECT(1, "On project"),
    BENCH(2, "On bench"),
    VACATION(3, "On vacation");

    private Integer id;
    private String name;

    EmployeeStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  static EmployeeStatus fromId(Integer id) {
        for (EmployeeStatus status : EmployeeStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum was found for " + id);
    }
}
