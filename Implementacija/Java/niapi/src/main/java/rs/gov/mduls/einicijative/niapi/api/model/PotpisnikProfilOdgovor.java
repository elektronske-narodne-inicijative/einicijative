package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * PotpisnikProfilOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class PotpisnikProfilOdgovor {

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

  public PotpisnikProfilOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PotpisnikProfilOdgovor(IdPolaEnum idPola, String nazivOpstine, Integer godinaRodjenja) {
    this.idPola = idPola;
    this.nazivOpstine = nazivOpstine;
    this.godinaRodjenja = godinaRodjenja;
  }

  public PotpisnikProfilOdgovor idPola(IdPolaEnum idPola) {
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

  public PotpisnikProfilOdgovor nazivOpstine(String nazivOpstine) {
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

  public PotpisnikProfilOdgovor godinaRodjenja(Integer godinaRodjenja) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PotpisnikProfilOdgovor potpisnikProfilOdgovor = (PotpisnikProfilOdgovor) o;
    return Objects.equals(this.idPola, potpisnikProfilOdgovor.idPola) &&
        Objects.equals(this.nazivOpstine, potpisnikProfilOdgovor.nazivOpstine) &&
        Objects.equals(this.godinaRodjenja, potpisnikProfilOdgovor.godinaRodjenja);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idPola, nazivOpstine, godinaRodjenja);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PotpisnikProfilOdgovor {\n");
    sb.append("    idPola: ").append(toIndentedString(idPola)).append("\n");
    sb.append("    nazivOpstine: ").append(toIndentedString(nazivOpstine)).append("\n");
    sb.append("    godinaRodjenja: ").append(toIndentedString(godinaRodjenja)).append("\n");
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

