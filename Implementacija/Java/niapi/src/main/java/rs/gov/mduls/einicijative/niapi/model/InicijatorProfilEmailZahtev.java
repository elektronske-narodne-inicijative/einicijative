package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;

/**
 * InicijatorProfilEmailZahtev
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorProfilEmailZahtev {

  private String emailAdresa;

  public InicijatorProfilEmailZahtev emailAdresa(String emailAdresa) {
    this.emailAdresa = emailAdresa;
    return this;
  }

  /**
   * Get emailAdresa
   * @return emailAdresa
  */
  
  @JsonProperty("emailAdresa")
  public String getEmailAdresa() {
    return emailAdresa;
  }

  public void setEmailAdresa(String emailAdresa) {
    this.emailAdresa = emailAdresa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorProfilEmailZahtev inicijatorProfilEmailZahtev = (InicijatorProfilEmailZahtev) o;
    return Objects.equals(this.emailAdresa, inicijatorProfilEmailZahtev.emailAdresa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAdresa);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorProfilEmailZahtev {\n");
    sb.append("    emailAdresa: ").append(toIndentedString(emailAdresa)).append("\n");
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

