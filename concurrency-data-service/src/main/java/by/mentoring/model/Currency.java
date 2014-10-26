package by.mentoring.model;

public class Currency extends BaseStoreObject {

    private String name;
    private Double multiplier;

    public Currency() {}

    public Currency(String name, Double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }


    public Currency(Integer id, String name, Double multiplier) {
        super(id);

        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return  new StringBuilder().append(id).append(";").append(name).append(";").append(multiplier).toString();
    }
}
