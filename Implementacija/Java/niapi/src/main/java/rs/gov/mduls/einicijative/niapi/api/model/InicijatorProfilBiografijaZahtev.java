package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.annotation.Generated;

/**
 * InicijatorProfilBiografijaZahtev
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorProfilBiografijaZahtev {

  private String biografija;

  public InicijatorProfilBiografijaZahtev biografija(String biografija) {
    this.biografija = biografija;
    return this;
  }

  /**
   * Get biografija
   * @return biografija
  */
  
  @JsonProperty("biografija")
  public String getBiografija() {
    return biografija;
  }

  public void setBiografija(String biografija) {
    this.biografija = biografija;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorProfilBiografijaZahtev inicijatorProfilBiografijaZahtev = (InicijatorProfilBiografijaZahtev) o;
    return Objects.equals(this.biografija, inicijatorProfilBiografijaZahtev.biografija);
  }

  @Override
  public int hashCode() {
    return Objects.hash(biografija);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorProfilBiografijaZahtev {\n");
    sb.append("    biografija: ").append(toIndentedString(biografija)).append("\n");
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

