package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * InicijativaZaPotpis
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijativaZaPotpis {

  private Integer idInicijative;

  public InicijativaZaPotpis() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijativaZaPotpis(Integer idInicijative) {
    this.idInicijative = idInicijative;
  }

  public InicijativaZaPotpis idInicijative(Integer idInicijative) {
    this.idInicijative = idInicijative;
    return this;
  }

  /**
   * Get idInicijative
   * @return idInicijative
  */
  @NotNull 
  @JsonProperty("idInicijative")
  public Integer getIdInicijative() {
    return idInicijative;
  }

  public void setIdInicijative(Integer idInicijative) {
    this.idInicijative = idInicijative;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijativaZaPotpis inicijativaZaPotpis = (InicijativaZaPotpis) o;
    return Objects.equals(this.idInicijative, inicijativaZaPotpis.idInicijative);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijativaZaPotpis {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
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

