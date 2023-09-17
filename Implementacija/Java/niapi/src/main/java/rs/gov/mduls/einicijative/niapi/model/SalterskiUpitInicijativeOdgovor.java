package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * SalterskiUpitInicijativeOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class SalterskiUpitInicijativeOdgovor {

  private Integer idInicijative;

  private String nazivInicijative;

  private String tipInicijative;

  private String nivoVlasti;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datumAktiviranja;

  public SalterskiUpitInicijativeOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalterskiUpitInicijativeOdgovor(Integer idInicijative, String nazivInicijative, String tipInicijative, String nivoVlasti, LocalDate datumAktiviranja) {
    this.idInicijative = idInicijative;
    this.nazivInicijative = nazivInicijative;
    this.tipInicijative = tipInicijative;
    this.nivoVlasti = nivoVlasti;
    this.datumAktiviranja = datumAktiviranja;
  }

  public SalterskiUpitInicijativeOdgovor idInicijative(Integer idInicijative) {
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

  public SalterskiUpitInicijativeOdgovor nazivInicijative(String nazivInicijative) {
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

  public SalterskiUpitInicijativeOdgovor tipInicijative(String tipInicijative) {
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

  public SalterskiUpitInicijativeOdgovor nivoVlasti(String nivoVlasti) {
    this.nivoVlasti = nivoVlasti;
    return this;
  }

  /**
   * Get nivoVlasti
   * @return nivoVlasti
  */
  @NotNull 
  @JsonProperty("nivoVlasti")
  public String getNivoVlasti() {
    return nivoVlasti;
  }

  public void setNivoVlasti(String nivoVlasti) {
    this.nivoVlasti = nivoVlasti;
  }

  public SalterskiUpitInicijativeOdgovor datumAktiviranja(LocalDate datumAktiviranja) {
    this.datumAktiviranja = datumAktiviranja;
    return this;
  }

  /**
   * Get datumAktiviranja
   * @return datumAktiviranja
  */
  @NotNull @Valid 
  @JsonProperty("datumAktiviranja")
  public LocalDate getDatumAktiviranja() {
    return datumAktiviranja;
  }

  public void setDatumAktiviranja(LocalDate datumAktiviranja) {
    this.datumAktiviranja = datumAktiviranja;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalterskiUpitInicijativeOdgovor salterskiUpitInicijativeOdgovor = (SalterskiUpitInicijativeOdgovor) o;
    return Objects.equals(this.idInicijative, salterskiUpitInicijativeOdgovor.idInicijative) &&
        Objects.equals(this.nazivInicijative, salterskiUpitInicijativeOdgovor.nazivInicijative) &&
        Objects.equals(this.tipInicijative, salterskiUpitInicijativeOdgovor.tipInicijative) &&
        Objects.equals(this.nivoVlasti, salterskiUpitInicijativeOdgovor.nivoVlasti) &&
        Objects.equals(this.datumAktiviranja, salterskiUpitInicijativeOdgovor.datumAktiviranja);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, nazivInicijative, tipInicijative, nivoVlasti, datumAktiviranja);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalterskiUpitInicijativeOdgovor {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    nazivInicijative: ").append(toIndentedString(nazivInicijative)).append("\n");
    sb.append("    tipInicijative: ").append(toIndentedString(tipInicijative)).append("\n");
    sb.append("    nivoVlasti: ").append(toIndentedString(nivoVlasti)).append("\n");
    sb.append("    datumAktiviranja: ").append(toIndentedString(datumAktiviranja)).append("\n");
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

