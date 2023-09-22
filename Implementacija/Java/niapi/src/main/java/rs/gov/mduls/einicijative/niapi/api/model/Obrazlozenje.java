package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.annotation.Generated;

/**
 * Obrazlozenje
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class Obrazlozenje {

  private String obrazlozenje;

  public Obrazlozenje obrazlozenje(String obrazlozenje) {
    this.obrazlozenje = obrazlozenje;
    return this;
  }

  /**
   * Get obrazlozenje
   * @return obrazlozenje
  */
  
  @JsonProperty("obrazlozenje")
  public String getObrazlozenje() {
    return obrazlozenje;
  }

  public void setObrazlozenje(String obrazlozenje) {
    this.obrazlozenje = obrazlozenje;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Obrazlozenje obrazlozenje = (Obrazlozenje) o;
    return Objects.equals(this.obrazlozenje, obrazlozenje.obrazlozenje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(obrazlozenje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Obrazlozenje {\n");
    sb.append("    obrazlozenje: ").append(toIndentedString(obrazlozenje)).append("\n");
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

