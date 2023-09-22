package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * OvliceProfilOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class OvliceProfilOdgovor {

  private String imePrezime;

  private String emailAdresa;

  private String nivoUprave;

  private String opisJediniceUprave;

  public OvliceProfilOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OvliceProfilOdgovor(String imePrezime, String emailAdresa, String nivoUprave) {
    this.imePrezime = imePrezime;
    this.emailAdresa = emailAdresa;
    this.nivoUprave = nivoUprave;
  }

  public OvliceProfilOdgovor imePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
    return this;
  }

  /**
   * Get imePrezime
   * @return imePrezime
  */
  @NotNull 
  @JsonProperty("imePrezime")
  public String getImePrezime() {
    return imePrezime;
  }

  public void setImePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
  }

  public OvliceProfilOdgovor emailAdresa(String emailAdresa) {
    this.emailAdresa = emailAdresa;
    return this;
  }

  /**
   * Get emailAdresa
   * @return emailAdresa
  */
  @NotNull 
  @JsonProperty("emailAdresa")
  public String getEmailAdresa() {
    return emailAdresa;
  }

  public void setEmailAdresa(String emailAdresa) {
    this.emailAdresa = emailAdresa;
  }

  public OvliceProfilOdgovor nivoUprave(String nivoUprave) {
    this.nivoUprave = nivoUprave;
    return this;
  }

  /**
   * Get nivoUprave
   * @return nivoUprave
  */
  @NotNull 
  @JsonProperty("nivoUprave")
  public String getNivoUprave() {
    return nivoUprave;
  }

  public void setNivoUprave(String nivoUprave) {
    this.nivoUprave = nivoUprave;
  }

  public OvliceProfilOdgovor opisJediniceUprave(String opisJediniceUprave) {
    this.opisJediniceUprave = opisJediniceUprave;
    return this;
  }

  /**
   * Get opisJediniceUprave
   * @return opisJediniceUprave
  */
  
  @JsonProperty("opisJediniceUprave")
  public String getOpisJediniceUprave() {
    return opisJediniceUprave;
  }

  public void setOpisJediniceUprave(String opisJediniceUprave) {
    this.opisJediniceUprave = opisJediniceUprave;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OvliceProfilOdgovor ovliceProfilOdgovor = (OvliceProfilOdgovor) o;
    return Objects.equals(this.imePrezime, ovliceProfilOdgovor.imePrezime) &&
        Objects.equals(this.emailAdresa, ovliceProfilOdgovor.emailAdresa) &&
        Objects.equals(this.nivoUprave, ovliceProfilOdgovor.nivoUprave) &&
        Objects.equals(this.opisJediniceUprave, ovliceProfilOdgovor.opisJediniceUprave);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imePrezime, emailAdresa, nivoUprave, opisJediniceUprave);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OvliceProfilOdgovor {\n");
    sb.append("    imePrezime: ").append(toIndentedString(imePrezime)).append("\n");
    sb.append("    emailAdresa: ").append(toIndentedString(emailAdresa)).append("\n");
    sb.append("    nivoUprave: ").append(toIndentedString(nivoUprave)).append("\n");
    sb.append("    opisJediniceUprave: ").append(toIndentedString(opisJediniceUprave)).append("\n");
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

