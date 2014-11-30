package by.mentoring.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import by.mentoring.data.CurrencyDao;
import by.mentoring.model.Currency;

@Path("/currencies")
@RequestScoped
public class CurrencyRESTService {

  @Inject
  private CurrencyDao currencyDao;

  private final Logger log = Logger.getLogger("CurrencyRESTService");

  @GET
  @Path("from/{fromId}/to/{toId}/amount/{amount}")
  @Produces("text/json")
  public String convertCurrencies(@PathParam("fromId") long fromId,
      @PathParam("toId") long toId, @PathParam("amount") double amount) {

    log.info("Called for from " + fromId + " to " + toId + " and amount "
        + amount);
    String result = "";

    Currency fromCurrecy = currencyDao.find(fromId);
    Currency toCurrency = currencyDao.find(toId);
    BigDecimal prevAmount = new BigDecimal(amount);

    Boolean isNotNull = fromCurrecy != null && toCurrency != null;
    Boolean isNotEquals = isNotNull
        && fromCurrecy.getId() != toCurrency.getId();
    if (isNotEquals) {

      result = prevAmount
          .multiply(new BigDecimal(fromCurrecy.getMultiplier()))
          .divide(new BigDecimal(toCurrency.getMultiplier()), 10,
              RoundingMode.HALF_UP).toString();
    }

    return result;
  }
}
