package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;

/**
 * Uspeh
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class Uspeh {

  private String poruka;

  public Uspeh poruka(String poruka) {
    this.poruka = poruka;
    return this;
  }

  /**
   * Get poruka
   * @return poruka
  */
  
  @JsonProperty("poruka")
  public String getPoruka() {
    return poruka;
  }

  public void setPoruka(String poruka) {
    this.poruka = poruka;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Uspeh uspeh = (Uspeh) o;
    return Objects.equals(this.poruka, uspeh.poruka);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poruka);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Uspeh {\n");
    sb.append("    poruka: ").append(toIndentedString(poruka)).append("\n");
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

