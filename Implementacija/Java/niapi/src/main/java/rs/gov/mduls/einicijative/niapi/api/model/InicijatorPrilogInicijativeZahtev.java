package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


import jakarta.annotation.Generated;

/**
 * InicijatorPrilogInicijativeZahtev
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorPrilogInicijativeZahtev {

  private String nazivPriloga;

  private String sadrzajPriloga;

  private Integer sortiranje;

  public InicijatorPrilogInicijativeZahtev() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijatorPrilogInicijativeZahtev(String nazivPriloga, String sadrzajPriloga, Integer sortiranje) {
    this.nazivPriloga = nazivPriloga;
    this.sadrzajPriloga = sadrzajPriloga;
    this.sortiranje = sortiranje;
  }

  public InicijatorPrilogInicijativeZahtev nazivPriloga(String nazivPriloga) {
    this.nazivPriloga = nazivPriloga;
    return this;
  }

  /**
   * Get nazivPriloga
   * @return nazivPriloga
  */
  @NotNull 
  @JsonProperty("nazivPriloga")
  public String getNazivPriloga() {
    return nazivPriloga;
  }

  public void setNazivPriloga(String nazivPriloga) {
    this.nazivPriloga = nazivPriloga;
  }

  public InicijatorPrilogInicijativeZahtev sadrzajPriloga(String sadrzajPriloga) {
    this.sadrzajPriloga = sadrzajPriloga;
    return this;
  }

  /**
   * Get sadrzajPriloga
   * @return sadrzajPriloga
  */
  @NotNull 
  @JsonProperty("sadrzajPriloga")
  public String getSadrzajPriloga() {
    return sadrzajPriloga;
  }

  public void setSadrzajPriloga(String sadrzajPriloga) {
    this.sadrzajPriloga = sadrzajPriloga;
  }

  public InicijatorPrilogInicijativeZahtev sortiranje(Integer sortiranje) {
    this.sortiranje = sortiranje;
    return this;
  }

  /**
   * Get sortiranje
   * @return sortiranje
  */
  @NotNull 
  @JsonProperty("sortiranje")
  public Integer getSortiranje() {
    return sortiranje;
  }

  public void setSortiranje(Integer sortiranje) {
    this.sortiranje = sortiranje;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorPrilogInicijativeZahtev inicijatorPrilogInicijativeZahtev = (InicijatorPrilogInicijativeZahtev) o;
    return Objects.equals(this.nazivPriloga, inicijatorPrilogInicijativeZahtev.nazivPriloga) &&
        Objects.equals(this.sadrzajPriloga, inicijatorPrilogInicijativeZahtev.sadrzajPriloga) &&
        Objects.equals(this.sortiranje, inicijatorPrilogInicijativeZahtev.sortiranje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nazivPriloga, sadrzajPriloga, sortiranje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorPrilogInicijativeZahtev {\n");
    sb.append("    nazivPriloga: ").append(toIndentedString(nazivPriloga)).append("\n");
    sb.append("    sadrzajPriloga: ").append(toIndentedString(sadrzajPriloga)).append("\n");
    sb.append("    sortiranje: ").append(toIndentedString(sortiranje)).append("\n");
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

