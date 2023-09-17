package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InicijatorInicijativaZahtev
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijatorInicijativaZahtev {

  private String tipInicijative;

  private String nazivInicijative;

  private String tekstInicijative;

  private String emailZaKontakt;

  private String nivoVlasti;

  private String jedinicaVlasti;

  public InicijatorInicijativaZahtev() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijatorInicijativaZahtev(String tipInicijative, String nazivInicijative, String tekstInicijative, String emailZaKontakt, String nivoVlasti) {
    this.tipInicijative = tipInicijative;
    this.nazivInicijative = nazivInicijative;
    this.tekstInicijative = tekstInicijative;
    this.emailZaKontakt = emailZaKontakt;
    this.nivoVlasti = nivoVlasti;
  }

  public InicijatorInicijativaZahtev tipInicijative(String tipInicijative) {
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

  public InicijatorInicijativaZahtev nazivInicijative(String nazivInicijative) {
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

  public InicijatorInicijativaZahtev tekstInicijative(String tekstInicijative) {
    this.tekstInicijative = tekstInicijative;
    return this;
  }

  /**
   * Get tekstInicijative
   * @return tekstInicijative
  */
  @NotNull 
  @JsonProperty("tekstInicijative")
  public String getTekstInicijative() {
    return tekstInicijative;
  }

  public void setTekstInicijative(String tekstInicijative) {
    this.tekstInicijative = tekstInicijative;
  }

  public InicijatorInicijativaZahtev emailZaKontakt(String emailZaKontakt) {
    this.emailZaKontakt = emailZaKontakt;
    return this;
  }

  /**
   * Get emailZaKontakt
   * @return emailZaKontakt
  */
  @NotNull 
  @JsonProperty("emailZaKontakt")
  public String getEmailZaKontakt() {
    return emailZaKontakt;
  }

  public void setEmailZaKontakt(String emailZaKontakt) {
    this.emailZaKontakt = emailZaKontakt;
  }

  public InicijatorInicijativaZahtev nivoVlasti(String nivoVlasti) {
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

  public InicijatorInicijativaZahtev jedinicaVlasti(String jedinicaVlasti) {
    this.jedinicaVlasti = jedinicaVlasti;
    return this;
  }

  /**
   * Get jedinicaVlasti
   * @return jedinicaVlasti
  */
  
  @JsonProperty("jedinicaVlasti")
  public String getJedinicaVlasti() {
    return jedinicaVlasti;
  }

  public void setJedinicaVlasti(String jedinicaVlasti) {
    this.jedinicaVlasti = jedinicaVlasti;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijatorInicijativaZahtev inicijatorInicijativaZahtev = (InicijatorInicijativaZahtev) o;
    return Objects.equals(this.tipInicijative, inicijatorInicijativaZahtev.tipInicijative) &&
        Objects.equals(this.nazivInicijative, inicijatorInicijativaZahtev.nazivInicijative) &&
        Objects.equals(this.tekstInicijative, inicijatorInicijativaZahtev.tekstInicijative) &&
        Objects.equals(this.emailZaKontakt, inicijatorInicijativaZahtev.emailZaKontakt) &&
        Objects.equals(this.nivoVlasti, inicijatorInicijativaZahtev.nivoVlasti) &&
        Objects.equals(this.jedinicaVlasti, inicijatorInicijativaZahtev.jedinicaVlasti);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipInicijative, nazivInicijative, tekstInicijative, emailZaKontakt, nivoVlasti, jedinicaVlasti);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijatorInicijativaZahtev {\n");
    sb.append("    tipInicijative: ").append(toIndentedString(tipInicijative)).append("\n");
    sb.append("    nazivInicijative: ").append(toIndentedString(nazivInicijative)).append("\n");
    sb.append("    tekstInicijative: ").append(toIndentedString(tekstInicijative)).append("\n");
    sb.append("    emailZaKontakt: ").append(toIndentedString(emailZaKontakt)).append("\n");
    sb.append("    nivoVlasti: ").append(toIndentedString(nivoVlasti)).append("\n");
    sb.append("    jedinicaVlasti: ").append(toIndentedString(jedinicaVlasti)).append("\n");
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

