package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * SalterskiPotpisZahtev
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class SalterskiPotpisZahtev {

  private Integer idInicijative;

  private String podaciSaSalteraPotpisa;

  private String imeKljucaZaOveru;

  private String overaAplikacije;

  public SalterskiPotpisZahtev() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalterskiPotpisZahtev(Integer idInicijative, String podaciSaSalteraPotpisa, String imeKljucaZaOveru, String overaAplikacije) {
    this.idInicijative = idInicijative;
    this.podaciSaSalteraPotpisa = podaciSaSalteraPotpisa;
    this.imeKljucaZaOveru = imeKljucaZaOveru;
    this.overaAplikacije = overaAplikacije;
  }

  public SalterskiPotpisZahtev idInicijative(Integer idInicijative) {
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

  public SalterskiPotpisZahtev podaciSaSalteraPotpisa(String podaciSaSalteraPotpisa) {
    this.podaciSaSalteraPotpisa = podaciSaSalteraPotpisa;
    return this;
  }

  /**
   * Get podaciSaSalteraPotpisa
   * @return podaciSaSalteraPotpisa
  */
  @NotNull 
  @JsonProperty("podaciSaSalteraPotpisa")
  public String getPodaciSaSalteraPotpisa() {
    return podaciSaSalteraPotpisa;
  }

  public void setPodaciSaSalteraPotpisa(String podaciSaSalteraPotpisa) {
    this.podaciSaSalteraPotpisa = podaciSaSalteraPotpisa;
  }

  public SalterskiPotpisZahtev imeKljucaZaOveru(String imeKljucaZaOveru) {
    this.imeKljucaZaOveru = imeKljucaZaOveru;
    return this;
  }

  /**
   * Get imeKljucaZaOveru
   * @return imeKljucaZaOveru
  */
  @NotNull 
  @JsonProperty("imeKljucaZaOveru")
  public String getImeKljucaZaOveru() {
    return imeKljucaZaOveru;
  }

  public void setImeKljucaZaOveru(String imeKljucaZaOveru) {
    this.imeKljucaZaOveru = imeKljucaZaOveru;
  }

  public SalterskiPotpisZahtev overaAplikacije(String overaAplikacije) {
    this.overaAplikacije = overaAplikacije;
    return this;
  }

  /**
   * Get overaAplikacije
   * @return overaAplikacije
  */
  @NotNull 
  @JsonProperty("overaAplikacije")
  public String getOveraAplikacije() {
    return overaAplikacije;
  }

  public void setOveraAplikacije(String overaAplikacije) {
    this.overaAplikacije = overaAplikacije;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalterskiPotpisZahtev salterskiPotpisZahtev = (SalterskiPotpisZahtev) o;
    return Objects.equals(this.idInicijative, salterskiPotpisZahtev.idInicijative) &&
        Objects.equals(this.podaciSaSalteraPotpisa, salterskiPotpisZahtev.podaciSaSalteraPotpisa) &&
        Objects.equals(this.imeKljucaZaOveru, salterskiPotpisZahtev.imeKljucaZaOveru) &&
        Objects.equals(this.overaAplikacije, salterskiPotpisZahtev.overaAplikacije);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, podaciSaSalteraPotpisa, imeKljucaZaOveru, overaAplikacije);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalterskiPotpisZahtev {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    podaciSaSalteraPotpisa: ").append(toIndentedString(podaciSaSalteraPotpisa)).append("\n");
    sb.append("    imeKljucaZaOveru: ").append(toIndentedString(imeKljucaZaOveru)).append("\n");
    sb.append("    overaAplikacije: ").append(toIndentedString(overaAplikacije)).append("\n");
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

