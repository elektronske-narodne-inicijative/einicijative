package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;


import jakarta.annotation.Generated;

/**
 * OvliceListaZaOdobrenjeOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class OvliceListaZaOdobrenjeOdgovor {

  @Valid
  private List<@Valid OsnovnoOInicijativi> inicijativeZaOdobrenje;

  public OvliceListaZaOdobrenjeOdgovor inicijativeZaOdobrenje(List<@Valid OsnovnoOInicijativi> inicijativeZaOdobrenje) {
    this.inicijativeZaOdobrenje = inicijativeZaOdobrenje;
    return this;
  }

  public OvliceListaZaOdobrenjeOdgovor addInicijativeZaOdobrenjeItem(OsnovnoOInicijativi inicijativeZaOdobrenjeItem) {
    if (this.inicijativeZaOdobrenje == null) {
      this.inicijativeZaOdobrenje = new ArrayList<>();
    }
    this.inicijativeZaOdobrenje.add(inicijativeZaOdobrenjeItem);
    return this;
  }

  /**
   * Get inicijativeZaOdobrenje
   * @return inicijativeZaOdobrenje
  */
  @Valid 
  @JsonProperty("inicijativeZaOdobrenje")
  public List<@Valid OsnovnoOInicijativi> getInicijativeZaOdobrenje() {
    return inicijativeZaOdobrenje;
  }

  public void setInicijativeZaOdobrenje(List<@Valid OsnovnoOInicijativi> inicijativeZaOdobrenje) {
    this.inicijativeZaOdobrenje = inicijativeZaOdobrenje;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OvliceListaZaOdobrenjeOdgovor ovliceListaZaOdobrenjeOdgovor = (OvliceListaZaOdobrenjeOdgovor) o;
    return Objects.equals(this.inicijativeZaOdobrenje, ovliceListaZaOdobrenjeOdgovor.inicijativeZaOdobrenje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inicijativeZaOdobrenje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OvliceListaZaOdobrenjeOdgovor {\n");
    sb.append("    inicijativeZaOdobrenje: ").append(toIndentedString(inicijativeZaOdobrenje)).append("\n");
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

