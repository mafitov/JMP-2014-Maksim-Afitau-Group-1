package by.mentoring.model.enums;

public enum CurrencyEnum {

  US(9800.0, "Dollar"),
  EU(11300.3, "Euro"),
  RU(280.6, "Ruble");

  private Double multiplier;
  private String title;

  CurrencyEnum(Double multiplier, String title) {
    this.multiplier = multiplier;
    this.title = title;
  }

  public Double getMultiplier() {
    return this.multiplier;
  }

  public String getTitle() {
    return this.title;
  }

}
