package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;


import jakarta.annotation.Generated;

/**
 * InicijatorInicijativaOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorInicijativaOdgovor {

  private InicijativaDetalji detaljiInicijative;

  public InicijatorInicijativaOdgovor detaljiInicijative(InicijativaDetalji detaljiInicijative) {
    this.detaljiInicijative = detaljiInicijative;
    return this;
  }

  /**
   * Get detaljiInicijative
   * @return detaljiInicijative
  */
  @Valid 
  @JsonProperty("detaljiInicijative")
  public InicijativaDetalji getDetaljiInicijative() {
    return detaljiInicijative;
  }

  public void setDetaljiInicijative(InicijativaDetalji detaljiInicijative) {
    this.detaljiInicijative = detaljiInicijative;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorInicijativaOdgovor inicijatorInicijativaOdgovor = (InicijatorInicijativaOdgovor) o;
    return Objects.equals(this.detaljiInicijative, inicijatorInicijativaOdgovor.detaljiInicijative);
  }

  @Override
  public int hashCode() {
    return Objects.hash(detaljiInicijative);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorInicijativaOdgovor {\n");
    sb.append("    detaljiInicijative: ").append(toIndentedString(detaljiInicijative)).append("\n");
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

