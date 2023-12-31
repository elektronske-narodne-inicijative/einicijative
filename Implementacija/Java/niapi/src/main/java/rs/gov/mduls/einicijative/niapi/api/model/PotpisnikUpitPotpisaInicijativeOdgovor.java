package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;

/**
 * PotpisnikUpitPotpisaInicijativeOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class PotpisnikUpitPotpisaInicijativeOdgovor {

  private PotpisInicijative potpis;

  public PotpisnikUpitPotpisaInicijativeOdgovor potpis(PotpisInicijative potpis) {
    this.potpis = potpis;
    return this;
  }

  /**
   * Get potpis
   * @return potpis
  */
  @Valid 
  @JsonProperty("potpis")
  public PotpisInicijative getPotpis() {
    return potpis;
  }

  public void setPotpis(PotpisInicijative potpis) {
    this.potpis = potpis;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PotpisnikUpitPotpisaInicijativeOdgovor potpisnikUpitPotpisaInicijativeOdgovor = (PotpisnikUpitPotpisaInicijativeOdgovor) o;
    return Objects.equals(this.potpis, potpisnikUpitPotpisaInicijativeOdgovor.potpis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(potpis);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PotpisnikUpitPotpisaInicijativeOdgovor {\n");
    sb.append("    potpis: ").append(toIndentedString(potpis)).append("\n");
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

