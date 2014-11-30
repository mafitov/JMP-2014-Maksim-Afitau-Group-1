package by.mentoring.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import by.mentoring.data.CurrencyDao;
import by.mentoring.model.Currency;

@ManagedBean
@FacesConverter(forClass=Currency.class)
public class CurrencyConverter implements Converter {

  @Inject
  private CurrencyDao currencyDao;

  @Override
  public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    return currencyDao.find(Long.valueOf(arg2));
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
    return ((Currency) arg2).getId().toString();
  }

}
