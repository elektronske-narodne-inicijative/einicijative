package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * PotpisnikPotpisOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class PotpisnikPotpisOdgovor {

  private Integer idInicijative;

  private UUID idPotpisa;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnZavodjenjaPotpisa;

  private String imeKljucaZaOveru;

  private String overaAplikacije;

  public PotpisnikPotpisOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PotpisnikPotpisOdgovor(Integer idInicijative, UUID idPotpisa, OffsetDateTime trnZavodjenjaPotpisa, String imeKljucaZaOveru, String overaAplikacije) {
    this.idInicijative = idInicijative;
    this.idPotpisa = idPotpisa;
    this.trnZavodjenjaPotpisa = trnZavodjenjaPotpisa;
    this.imeKljucaZaOveru = imeKljucaZaOveru;
    this.overaAplikacije = overaAplikacije;
  }

  public PotpisnikPotpisOdgovor idInicijative(Integer idInicijative) {
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

  public PotpisnikPotpisOdgovor idPotpisa(UUID idPotpisa) {
    this.idPotpisa = idPotpisa;
    return this;
  }

  /**
   * Get idPotpisa
   * @return idPotpisa
  */
  @NotNull @Valid 
  @JsonProperty("idPotpisa")
  public UUID getIdPotpisa() {
    return idPotpisa;
  }

  public void setIdPotpisa(UUID idPotpisa) {
    this.idPotpisa = idPotpisa;
  }

  public PotpisnikPotpisOdgovor trnZavodjenjaPotpisa(OffsetDateTime trnZavodjenjaPotpisa) {
    this.trnZavodjenjaPotpisa = trnZavodjenjaPotpisa;
    return this;
  }

  /**
   * Get trnZavodjenjaPotpisa
   * @return trnZavodjenjaPotpisa
  */
  @NotNull @Valid 
  @JsonProperty("trnZavodjenjaPotpisa")
  public OffsetDateTime getTrnZavodjenjaPotpisa() {
    return trnZavodjenjaPotpisa;
  }

  public void setTrnZavodjenjaPotpisa(OffsetDateTime trnZavodjenjaPotpisa) {
    this.trnZavodjenjaPotpisa = trnZavodjenjaPotpisa;
  }

  public PotpisnikPotpisOdgovor imeKljucaZaOveru(String imeKljucaZaOveru) {
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

  public PotpisnikPotpisOdgovor overaAplikacije(String overaAplikacije) {
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
    PotpisnikPotpisOdgovor potpisnikPotpisOdgovor = (PotpisnikPotpisOdgovor) o;
    return Objects.equals(this.idInicijative, potpisnikPotpisOdgovor.idInicijative) &&
        Objects.equals(this.idPotpisa, potpisnikPotpisOdgovor.idPotpisa) &&
        Objects.equals(this.trnZavodjenjaPotpisa, potpisnikPotpisOdgovor.trnZavodjenjaPotpisa) &&
        Objects.equals(this.imeKljucaZaOveru, potpisnikPotpisOdgovor.imeKljucaZaOveru) &&
        Objects.equals(this.overaAplikacije, potpisnikPotpisOdgovor.overaAplikacije);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, idPotpisa, trnZavodjenjaPotpisa, imeKljucaZaOveru, overaAplikacije);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PotpisnikPotpisOdgovor {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    idPotpisa: ").append(toIndentedString(idPotpisa)).append("\n");
    sb.append("    trnZavodjenjaPotpisa: ").append(toIndentedString(trnZavodjenjaPotpisa)).append("\n");
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

