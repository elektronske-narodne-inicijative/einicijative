package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;


import jakarta.annotation.Generated;

/**
 * InicijatorListaInicijativaOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorListaInicijativaOdgovor {

  @Valid
  private List<@Valid OsnovnoOInicijativi> inicijativeZaFazuObrade;

  public InicijatorListaInicijativaOdgovor inicijativeZaFazuObrade(List<@Valid OsnovnoOInicijativi> inicijativeZaFazuObrade) {
    this.inicijativeZaFazuObrade = inicijativeZaFazuObrade;
    return this;
  }

  public InicijatorListaInicijativaOdgovor addInicijativeZaFazuObradeItem(OsnovnoOInicijativi inicijativeZaFazuObradeItem) {
    if (this.inicijativeZaFazuObrade == null) {
      this.inicijativeZaFazuObrade = new ArrayList<>();
    }
    this.inicijativeZaFazuObrade.add(inicijativeZaFazuObradeItem);
    return this;
  }

  /**
   * Get inicijativeZaFazuObrade
   * @return inicijativeZaFazuObrade
  */
  @Valid 
  @JsonProperty("inicijativeZaFazuObrade")
  public List<@Valid OsnovnoOInicijativi> getInicijativeZaFazuObrade() {
    return inicijativeZaFazuObrade;
  }

  public void setInicijativeZaFazuObrade(List<@Valid OsnovnoOInicijativi> inicijativeZaFazuObrade) {
    this.inicijativeZaFazuObrade = inicijativeZaFazuObrade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorListaInicijativaOdgovor inicijatorListaInicijativaOdgovor = (InicijatorListaInicijativaOdgovor) o;
    return Objects.equals(this.inicijativeZaFazuObrade, inicijatorListaInicijativaOdgovor.inicijativeZaFazuObrade);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inicijativeZaFazuObrade);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorListaInicijativaOdgovor {\n");
    sb.append("    inicijativeZaFazuObrade: ").append(toIndentedString(inicijativeZaFazuObrade)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

