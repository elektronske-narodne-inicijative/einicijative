package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * InicijatorProfilOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorProfilOdgovor {

  /**
   * Gets or Sets idPola
   */
  public enum IdPolaEnum {
    M("М"),
    
    Z("Ж");

    private String value;

    IdPolaEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IdPolaEnum fromValue(String value) {
      for (IdPolaEnum b : IdPolaEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private IdPolaEnum idPola;

  private String nazivOpstine;

  private Integer godinaRodjenja;

  private String imePrezime;

  private String emailAdresa;

  private String biografija;

  public InicijatorProfilOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijatorProfilOdgovor(IdPolaEnum idPola, String nazivOpstine, Integer godinaRodjenja) {
    this.idPola = idPola;
    this.nazivOpstine = nazivOpstine;
    this.godinaRodjenja = godinaRodjenja;
  }

  public InicijatorProfilOdgovor idPola(IdPolaEnum idPola) {
    this.idPola = idPola;
    return this;
  }

  /**
   * Get idPola
   * @return idPola
  */
  @NotNull 
  @JsonProperty("idPola")
  public IdPolaEnum getIdPola() {
    return idPola;
  }

  public void setIdPola(IdPolaEnum idPola) {
    this.idPola = idPola;
  }

  public InicijatorProfilOdgovor nazivOpstine(String nazivOpstine) {
    this.nazivOpstine = nazivOpstine;
    return this;
  }

  /**
   * Get nazivOpstine
   * @return nazivOpstine
  */
  @NotNull 
  @JsonProperty("nazivOpstine")
  public String getNazivOpstine() {
    return nazivOpstine;
  }

  public void setNazivOpstine(String nazivOpstine) {
    this.nazivOpstine = nazivOpstine;
  }

  public InicijatorProfilOdgovor godinaRodjenja(Integer godinaRodjenja) {
    this.godinaRodjenja = godinaRodjenja;
    return this;
  }

  /**
   * Get godinaRodjenja
   * @return godinaRodjenja
  */
  @NotNull 
  @JsonProperty("godinaRodjenja")
  public Integer getGodinaRodjenja() {
    return godinaRodjenja;
  }

  public void setGodinaRodjenja(Integer godinaRodjenja) {
    this.godinaRodjenja = godinaRodjenja;
  }

  public InicijatorProfilOdgovor imePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
    return this;
  }

  /**
   * Get imePrezime
   * @return imePrezime
  */
  
  @JsonProperty("imePrezime")
  public String getImePrezime() {
    return imePrezime;
  }

  public void setImePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
  }

  public InicijatorProfilOdgovor emailAdresa(String emailAdresa) {
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

  public InicijatorProfilOdgovor biografija(String biografija) {
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
    InicijatorProfilOdgovor inicijatorProfilOdgovor = (InicijatorProfilOdgovor) o;
    return Objects.equals(this.idPola, inicijatorProfilOdgovor.idPola) &&
        Objects.equals(this.nazivOpstine, inicijatorProfilOdgovor.nazivOpstine) &&
        Objects.equals(this.godinaRodjenja, inicijatorProfilOdgovor.godinaRodjenja) &&
        Objects.equals(this.imePrezime, inicijatorProfilOdgovor.imePrezime) &&
        Objects.equals(this.emailAdresa, inicijatorProfilOdgovor.emailAdresa) &&
        Objects.equals(this.biografija, inicijatorProfilOdgovor.biografija);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPola, nazivOpstine, godinaRodjenja, imePrezime, emailAdresa, biografija);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorProfilOdgovor {\n");
    sb.append("    idPola: ").append(toIndentedString(idPola)).append("\n");
    sb.append("    nazivOpstine: ").append(toIndentedString(nazivOpstine)).append("\n");
    sb.append("    godinaRodjenja: ").append(toIndentedString(godinaRodjenja)).append("\n");
    sb.append("    imePrezime: ").append(toIndentedString(imePrezime)).append("\n");
    sb.append("    emailAdresa: ").append(toIndentedString(emailAdresa)).append("\n");
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

