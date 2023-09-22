package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * DnevnikPromena
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class DnevnikPromena {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnPromene;

  private String fazaObrade;

  private String detaljiPromene;

  public DnevnikPromena() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DnevnikPromena(OffsetDateTime trnPromene, String fazaObrade, String detaljiPromene) {
    this.trnPromene = trnPromene;
    this.fazaObrade = fazaObrade;
    this.detaljiPromene = detaljiPromene;
  }

  public DnevnikPromena trnPromene(OffsetDateTime trnPromene) {
    this.trnPromene = trnPromene;
    return this;
  }

  /**
   * Get trnPromene
   * @return trnPromene
  */
  @NotNull @Valid 
  @JsonProperty("trnPromene")
  public OffsetDateTime getTrnPromene() {
    return trnPromene;
  }

  public void setTrnPromene(OffsetDateTime trnPromene) {
    this.trnPromene = trnPromene;
  }

  public DnevnikPromena fazaObrade(String fazaObrade) {
    this.fazaObrade = fazaObrade;
    return this;
  }

  /**
   * Get fazaObrade
   * @return fazaObrade
  */
  @NotNull 
  @JsonProperty("fazaObrade")
  public String getFazaObrade() {
    return fazaObrade;
  }

  public void setFazaObrade(String fazaObrade) {
    this.fazaObrade = fazaObrade;
  }

  public DnevnikPromena detaljiPromene(String detaljiPromene) {
    this.detaljiPromene = detaljiPromene;
    return this;
  }

  /**
   * Get detaljiPromene
   * @return detaljiPromene
  */
  @NotNull 
  @JsonProperty("detaljiPromene")
  public String getDetaljiPromene() {
    return detaljiPromene;
  }

  public void setDetaljiPromene(String detaljiPromene) {
    this.detaljiPromene = detaljiPromene;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DnevnikPromena dnevnikPromena = (DnevnikPromena) o;
    return Objects.equals(this.trnPromene, dnevnikPromena.trnPromene) &&
        Objects.equals(this.fazaObrade, dnevnikPromena.fazaObrade) &&
        Objects.equals(this.detaljiPromene, dnevnikPromena.detaljiPromene);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trnPromene, fazaObrade, detaljiPromene);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DnevnikPromena {\n");
    sb.append("    trnPromene: ").append(toIndentedString(trnPromene)).append("\n");
    sb.append("    fazaObrade: ").append(toIndentedString(fazaObrade)).append("\n");
    sb.append("    detaljiPromene: ").append(toIndentedString(detaljiPromene)).append("\n");
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

