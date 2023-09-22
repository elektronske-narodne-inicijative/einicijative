package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;

/**
 * OvliceListaZaIshodOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class OvliceListaZaIshodOdgovor {

  @Valid
  private List<@Valid OsnovnoOInicijativi> inicijativeZaIshod;

  public OvliceListaZaIshodOdgovor inicijativeZaIshod(List<@Valid OsnovnoOInicijativi> inicijativeZaIshod) {
    this.inicijativeZaIshod = inicijativeZaIshod;
    return this;
  }

  public OvliceListaZaIshodOdgovor addInicijativeZaIshodItem(OsnovnoOInicijativi inicijativeZaIshodItem) {
    if (this.inicijativeZaIshod == null) {
      this.inicijativeZaIshod = new ArrayList<>();
    }
    this.inicijativeZaIshod.add(inicijativeZaIshodItem);
    return this;
  }

  /**
   * Get inicijativeZaIshod
   * @return inicijativeZaIshod
  */
  @Valid 
  @JsonProperty("inicijativeZaIshod")
  public List<@Valid OsnovnoOInicijativi> getInicijativeZaIshod() {
    return inicijativeZaIshod;
  }

  public void setInicijativeZaIshod(List<@Valid OsnovnoOInicijativi> inicijativeZaIshod) {
    this.inicijativeZaIshod = inicijativeZaIshod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OvliceListaZaIshodOdgovor ovliceListaZaIshodOdgovor = (OvliceListaZaIshodOdgovor) o;
    return Objects.equals(this.inicijativeZaIshod, ovliceListaZaIshodOdgovor.inicijativeZaIshod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inicijativeZaIshod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OvliceListaZaIshodOdgovor {\n");
    sb.append("    inicijativeZaIshod: ").append(toIndentedString(inicijativeZaIshod)).append("\n");
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

