package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * InicijatorPozivnicaOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorPozivnicaOdgovor {

  private String url;

  public InicijatorPozivnicaOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijatorPozivnicaOdgovor(String url) {
    this.url = url;
  }

  public InicijatorPozivnicaOdgovor url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  @NotNull 
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorPozivnicaOdgovor inicijatorPozivnicaOdgovor = (InicijatorPozivnicaOdgovor) o;
    return Objects.equals(this.url, inicijatorPozivnicaOdgovor.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorPozivnicaOdgovor {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

