package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import jakarta.annotation.Generated;

/**
 * PrilogInicijative
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class PrilogInicijative {

  private String nazivPriloga;

  private String urlPriloga;

  private Integer sortiranje;

  private Boolean obrisan;

  public PrilogInicijative() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PrilogInicijative(String nazivPriloga, String urlPriloga, Integer sortiranje, Boolean obrisan) {
    this.nazivPriloga = nazivPriloga;
    this.urlPriloga = urlPriloga;
    this.sortiranje = sortiranje;
    this.obrisan = obrisan;
  }

  public PrilogInicijative nazivPriloga(String nazivPriloga) {
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

  public PrilogInicijative urlPriloga(String urlPriloga) {
    this.urlPriloga = urlPriloga;
    return this;
  }

  /**
   * Get urlPriloga
   * @return urlPriloga
  */
  @NotNull 
  @JsonProperty("urlPriloga")
  public String getUrlPriloga() {
    return urlPriloga;
  }

  public void setUrlPriloga(String urlPriloga) {
    this.urlPriloga = urlPriloga;
  }

  public PrilogInicijative sortiranje(Integer sortiranje) {
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

  public PrilogInicijative obrisan(Boolean obrisan) {
    this.obrisan = obrisan;
    return this;
  }

  /**
   * Get obrisan
   * @return obrisan
  */
  @NotNull 
  @JsonProperty("obrisan")
  public Boolean getObrisan() {
    return obrisan;
  }

  public void setObrisan(Boolean obrisan) {
    this.obrisan = obrisan;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrilogInicijative prilogInicijative = (PrilogInicijative) o;
    return Objects.equals(this.nazivPriloga, prilogInicijative.nazivPriloga) &&
        Objects.equals(this.urlPriloga, prilogInicijative.urlPriloga) &&
        Objects.equals(this.sortiranje, prilogInicijative.sortiranje) &&
        Objects.equals(this.obrisan, prilogInicijative.obrisan);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nazivPriloga, urlPriloga, sortiranje, obrisan);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrilogInicijative {\n");
    sb.append("    nazivPriloga: ").append(toIndentedString(nazivPriloga)).append("\n");
    sb.append("    urlPriloga: ").append(toIndentedString(urlPriloga)).append("\n");
    sb.append("    sortiranje: ").append(toIndentedString(sortiranje)).append("\n");
    sb.append("    obrisan: ").append(toIndentedString(obrisan)).append("\n");
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

