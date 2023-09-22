package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * ObrazlozenjeSaSednice
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class ObrazlozenjeSaSednice {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datumSednice;

  private String obrazlozenje;

  public ObrazlozenjeSaSednice() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ObrazlozenjeSaSednice(LocalDate datumSednice) {
    this.datumSednice = datumSednice;
  }

  public ObrazlozenjeSaSednice datumSednice(LocalDate datumSednice) {
    this.datumSednice = datumSednice;
    return this;
  }

  /**
   * Get datumSednice
   * @return datumSednice
  */
  @NotNull @Valid 
  @JsonProperty("datumSednice")
  public LocalDate getDatumSednice() {
    return datumSednice;
  }

  public void setDatumSednice(LocalDate datumSednice) {
    this.datumSednice = datumSednice;
  }

  public ObrazlozenjeSaSednice obrazlozenje(String obrazlozenje) {
    this.obrazlozenje = obrazlozenje;
    return this;
  }

  /**
   * Get obrazlozenje
   * @return obrazlozenje
  */
  
  @JsonProperty("obrazlozenje")
  public String getObrazlozenje() {
    return obrazlozenje;
  }

  public void setObrazlozenje(String obrazlozenje) {
    this.obrazlozenje = obrazlozenje;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObrazlozenjeSaSednice obrazlozenjeSaSednice = (ObrazlozenjeSaSednice) o;
    return Objects.equals(this.datumSednice, obrazlozenjeSaSednice.datumSednice) &&
        Objects.equals(this.obrazlozenje, obrazlozenjeSaSednice.obrazlozenje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datumSednice, obrazlozenje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObrazlozenjeSaSednice {\n");
    sb.append("    datumSednice: ").append(toIndentedString(datumSednice)).append("\n");
    sb.append("    obrazlozenje: ").append(toIndentedString(obrazlozenje)).append("\n");
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

