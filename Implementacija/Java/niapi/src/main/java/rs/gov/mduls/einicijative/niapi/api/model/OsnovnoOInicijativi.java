package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * OsnovnoOInicijativi
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class OsnovnoOInicijativi {

  private Integer idInicijative;

  private String nazivInicijative;

  private String tipInicijative;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnZahteva;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnPodnosenja;

  private Integer brInicijatora;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datumPokretanja;

  private Integer brPotpisa;

  public OsnovnoOInicijativi() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OsnovnoOInicijativi(Integer idInicijative, String nazivInicijative, String tipInicijative, OffsetDateTime trnZahteva, Integer brInicijatora) {
    this.idInicijative = idInicijative;
    this.nazivInicijative = nazivInicijative;
    this.tipInicijative = tipInicijative;
    this.trnZahteva = trnZahteva;
    this.brInicijatora = brInicijatora;
  }

  public OsnovnoOInicijativi idInicijative(Integer idInicijative) {
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

  public OsnovnoOInicijativi nazivInicijative(String nazivInicijative) {
    this.nazivInicijative = nazivInicijative;
    return this;
  }

  /**
   * Get nazivInicijative
   * @return nazivInicijative
  */
  @NotNull 
  @JsonProperty("nazivInicijative")
  public String getNazivInicijative() {
    return nazivInicijative;
  }

  public void setNazivInicijative(String nazivInicijative) {
    this.nazivInicijative = nazivInicijative;
  }

  public OsnovnoOInicijativi tipInicijative(String tipInicijative) {
    this.tipInicijative = tipInicijative;
    return this;
  }

  /**
   * Get tipInicijative
   * @return tipInicijative
  */
  @NotNull 
  @JsonProperty("tipInicijative")
  public String getTipInicijative() {
    return tipInicijative;
  }

  public void setTipInicijative(String tipInicijative) {
    this.tipInicijative = tipInicijative;
  }

  public OsnovnoOInicijativi trnZahteva(OffsetDateTime trnZahteva) {
    this.trnZahteva = trnZahteva;
    return this;
  }

  /**
   * Get trnZahteva
   * @return trnZahteva
  */
  @NotNull @Valid 
  @JsonProperty("trnZahteva")
  public OffsetDateTime getTrnZahteva() {
    return trnZahteva;
  }

  public void setTrnZahteva(OffsetDateTime trnZahteva) {
    this.trnZahteva = trnZahteva;
  }

  public OsnovnoOInicijativi trnPodnosenja(OffsetDateTime trnPodnosenja) {
    this.trnPodnosenja = trnPodnosenja;
    return this;
  }

  /**
   * Get trnPodnosenja
   * @return trnPodnosenja
  */
  @Valid 
  @JsonProperty("trnPodnosenja")
  public OffsetDateTime getTrnPodnosenja() {
    return trnPodnosenja;
  }

  public void setTrnPodnosenja(OffsetDateTime trnPodnosenja) {
    this.trnPodnosenja = trnPodnosenja;
  }

  public OsnovnoOInicijativi brInicijatora(Integer brInicijatora) {
    this.brInicijatora = brInicijatora;
    return this;
  }

  /**
   * Get brInicijatora
   * @return brInicijatora
  */
  @NotNull 
  @JsonProperty("brInicijatora")
  public Integer getBrInicijatora() {
    return brInicijatora;
  }

  public void setBrInicijatora(Integer brInicijatora) {
    this.brInicijatora = brInicijatora;
  }

  public OsnovnoOInicijativi datumPokretanja(LocalDate datumPokretanja) {
    this.datumPokretanja = datumPokretanja;
    return this;
  }

  /**
   * Get datumPokretanja
   * @return datumPokretanja
  */
  @Valid 
  @JsonProperty("datumPokretanja")
  public LocalDate getDatumPokretanja() {
    return datumPokretanja;
  }

  public void setDatumPokretanja(LocalDate datumPokretanja) {
    this.datumPokretanja = datumPokretanja;
  }

  public OsnovnoOInicijativi brPotpisa(Integer brPotpisa) {
    this.brPotpisa = brPotpisa;
    return this;
  }

  /**
   * Get brPotpisa
   * @return brPotpisa
  */
  
  @JsonProperty("brPotpisa")
  public Integer getBrPotpisa() {
    return brPotpisa;
  }

  public void setBrPotpisa(Integer brPotpisa) {
    this.brPotpisa = brPotpisa;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OsnovnoOInicijativi osnovnoOInicijativi = (OsnovnoOInicijativi) o;
    return Objects.equals(this.idInicijative, osnovnoOInicijativi.idInicijative) &&
        Objects.equals(this.nazivInicijative, osnovnoOInicijativi.nazivInicijative) &&
        Objects.equals(this.tipInicijative, osnovnoOInicijativi.tipInicijative) &&
        Objects.equals(this.trnZahteva, osnovnoOInicijativi.trnZahteva) &&
        Objects.equals(this.trnPodnosenja, osnovnoOInicijativi.trnPodnosenja) &&
        Objects.equals(this.brInicijatora, osnovnoOInicijativi.brInicijatora) &&
        Objects.equals(this.datumPokretanja, osnovnoOInicijativi.datumPokretanja) &&
        Objects.equals(this.brPotpisa, osnovnoOInicijativi.brPotpisa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, nazivInicijative, tipInicijative, trnZahteva, trnPodnosenja, brInicijatora, datumPokretanja, brPotpisa);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OsnovnoOInicijativi {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    nazivInicijative: ").append(toIndentedString(nazivInicijative)).append("\n");
    sb.append("    tipInicijative: ").append(toIndentedString(tipInicijative)).append("\n");
    sb.append("    trnZahteva: ").append(toIndentedString(trnZahteva)).append("\n");
    sb.append("    trnPodnosenja: ").append(toIndentedString(trnPodnosenja)).append("\n");
    sb.append("    brInicijatora: ").append(toIndentedString(brInicijatora)).append("\n");
    sb.append("    datumPokretanja: ").append(toIndentedString(datumPokretanja)).append("\n");
    sb.append("    brPotpisa: ").append(toIndentedString(brPotpisa)).append("\n");
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

