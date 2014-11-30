package by.mentoring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table
public class Currency implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  private Double multiplier;

  @OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
  private List<Account> accounts;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getAccounts() == null) ? 0 : getAccounts().hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result
        + ((multiplier == null) ? 0 : multiplier.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    Boolean result = true;
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Currency other = (Currency) obj;
    if (id == null) {
      if (other.id != null) {
        result = false;
      }
    } else if (!id.equals(other.id)) {
      result = false; }
    if (multiplier == null) {
      if (other.multiplier != null) {
        result = false;
      }
    } else if (!multiplier.equals(other.multiplier)) {
      result = false;
    }
    if (name == null) {
      if (other.name != null) {
        result = false;
      }
    } else if (!name.equals(other.name)) {
      result = false;
    }
    return result;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }





}
