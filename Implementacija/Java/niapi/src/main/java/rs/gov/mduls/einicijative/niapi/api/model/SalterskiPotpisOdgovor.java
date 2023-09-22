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
 * SalterskiPotpisOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class SalterskiPotpisOdgovor {

  private Integer idInicijative;

  private UUID idPotpisa;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnZavodjenjaPotpisa;

  public SalterskiPotpisOdgovor() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SalterskiPotpisOdgovor(Integer idInicijative, UUID idPotpisa, OffsetDateTime trnZavodjenjaPotpisa) {
    this.idInicijative = idInicijative;
    this.idPotpisa = idPotpisa;
    this.trnZavodjenjaPotpisa = trnZavodjenjaPotpisa;
  }

  public SalterskiPotpisOdgovor idInicijative(Integer idInicijative) {
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

  public SalterskiPotpisOdgovor idPotpisa(UUID idPotpisa) {
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

  public SalterskiPotpisOdgovor trnZavodjenjaPotpisa(OffsetDateTime trnZavodjenjaPotpisa) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalterskiPotpisOdgovor salterskiPotpisOdgovor = (SalterskiPotpisOdgovor) o;
    return Objects.equals(this.idInicijative, salterskiPotpisOdgovor.idInicijative) &&
        Objects.equals(this.idPotpisa, salterskiPotpisOdgovor.idPotpisa) &&
        Objects.equals(this.trnZavodjenjaPotpisa, salterskiPotpisOdgovor.trnZavodjenjaPotpisa);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, idPotpisa, trnZavodjenjaPotpisa);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalterskiPotpisOdgovor {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    idPotpisa: ").append(toIndentedString(idPotpisa)).append("\n");
    sb.append("    trnZavodjenjaPotpisa: ").append(toIndentedString(trnZavodjenjaPotpisa)).append("\n");
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

