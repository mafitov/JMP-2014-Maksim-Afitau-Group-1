package by.mentoring.ejb;

import javax.ejb.Local;
import java.math.BigDecimal;

@Local
public interface MoneyChangeLocal {
    public void change(Integer from, Integer to, BigDecimal amount);
}
