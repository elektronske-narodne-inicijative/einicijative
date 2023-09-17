package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ClanInicijativnogOdbora
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class ClanInicijativnogOdbora {

  private Boolean aktivan;

  private String imePrezime;

  private String pol;

  private Integer godinaRodjenja;

  private String emailZaKontakt;

  private String biografija;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnPrihvatanjaClanstva;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnOdustajanjaOdClanstva;

  public ClanInicijativnogOdbora() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ClanInicijativnogOdbora(Boolean aktivan, String imePrezime, String pol, Integer godinaRodjenja) {
    this.aktivan = aktivan;
    this.imePrezime = imePrezime;
    this.pol = pol;
    this.godinaRodjenja = godinaRodjenja;
  }

  public ClanInicijativnogOdbora aktivan(Boolean aktivan) {
    this.aktivan = aktivan;
    return this;
  }

  /**
   * Get aktivan
   * @return aktivan
  */
  @NotNull 
  @JsonProperty("aktivan")
  public Boolean getAktivan() {
    return aktivan;
  }

  public void setAktivan(Boolean aktivan) {
    this.aktivan = aktivan;
  }

  public ClanInicijativnogOdbora imePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
    return this;
  }

  /**
   * Get imePrezime
   * @return imePrezime
  */
  @NotNull 
  @JsonProperty("imePrezime")
  public String getImePrezime() {
    return imePrezime;
  }

  public void setImePrezime(String imePrezime) {
    this.imePrezime = imePrezime;
  }

  public ClanInicijativnogOdbora pol(String pol) {
    this.pol = pol;
    return this;
  }

  /**
   * Get pol
   * @return pol
  */
  @NotNull 
  @JsonProperty("pol")
  public String getPol() {
    return pol;
  }

  public void setPol(String pol) {
    this.pol = pol;
  }

  public ClanInicijativnogOdbora godinaRodjenja(Integer godinaRodjenja) {
    this.godinaRodjenja = godinaRodjenja;
    return this;
  }

  /**
   * Get godinaRodjenja
   * @return godinaRodjenja
  */
  @NotNull 
  @JsonProperty("godinaRodjenja")
  public Integer getGodinaRodjenja() {
    return godinaRodjenja;
  }

  public void setGodinaRodjenja(Integer godinaRodjenja) {
    this.godinaRodjenja = godinaRodjenja;
  }

  public ClanInicijativnogOdbora emailZaKontakt(String emailZaKontakt) {
    this.emailZaKontakt = emailZaKontakt;
    return this;
  }

  /**
   * Get emailZaKontakt
   * @return emailZaKontakt
  */
  
  @JsonProperty("emailZaKontakt")
  public String getEmailZaKontakt() {
    return emailZaKontakt;
  }

  public void setEmailZaKontakt(String emailZaKontakt) {
    this.emailZaKontakt = emailZaKontakt;
  }

  public ClanInicijativnogOdbora biografija(String biografija) {
    this.biografija = biografija;
    return this;
  }

  /**
   * Get biografija
   * @return biografija
  */
  
  @JsonProperty("biografija")
  public String getBiografija() {
    return biografija;
  }

  public void setBiografija(String biografija) {
    this.biografija = biografija;
  }

  public ClanInicijativnogOdbora trnPrihvatanjaClanstva(OffsetDateTime trnPrihvatanjaClanstva) {
    this.trnPrihvatanjaClanstva = trnPrihvatanjaClanstva;
    return this;
  }

  /**
   * Get trnPrihvatanjaClanstva
   * @return trnPrihvatanjaClanstva
  */
  @Valid 
  @JsonProperty("trnPrihvatanjaClanstva")
  public OffsetDateTime getTrnPrihvatanjaClanstva() {
    return trnPrihvatanjaClanstva;
  }

  public void setTrnPrihvatanjaClanstva(OffsetDateTime trnPrihvatanjaClanstva) {
    this.trnPrihvatanjaClanstva = trnPrihvatanjaClanstva;
  }

  public ClanInicijativnogOdbora trnOdustajanjaOdClanstva(OffsetDateTime trnOdustajanjaOdClanstva) {
    this.trnOdustajanjaOdClanstva = trnOdustajanjaOdClanstva;
    return this;
  }

  /**
   * Get trnOdustajanjaOdClanstva
   * @return trnOdustajanjaOdClanstva
  */
  @Valid 
  @JsonProperty("trnOdustajanjaOdClanstva")
  public OffsetDateTime getTrnOdustajanjaOdClanstva() {
    return trnOdustajanjaOdClanstva;
  }

  public void setTrnOdustajanjaOdClanstva(OffsetDateTime trnOdustajanjaOdClanstva) {
    this.trnOdustajanjaOdClanstva = trnOdustajanjaOdClanstva;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClanInicijativnogOdbora clanInicijativnogOdbora = (ClanInicijativnogOdbora) o;
    return Objects.equals(this.aktivan, clanInicijativnogOdbora.aktivan) &&
        Objects.equals(this.imePrezime, clanInicijativnogOdbora.imePrezime) &&
        Objects.equals(this.pol, clanInicijativnogOdbora.pol) &&
        Objects.equals(this.godinaRodjenja, clanInicijativnogOdbora.godinaRodjenja) &&
        Objects.equals(this.emailZaKontakt, clanInicijativnogOdbora.emailZaKontakt) &&
        Objects.equals(this.biografija, clanInicijativnogOdbora.biografija) &&
        Objects.equals(this.trnPrihvatanjaClanstva, clanInicijativnogOdbora.trnPrihvatanjaClanstva) &&
        Objects.equals(this.trnOdustajanjaOdClanstva, clanInicijativnogOdbora.trnOdustajanjaOdClanstva);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aktivan, imePrezime, pol, godinaRodjenja, emailZaKontakt, biografija, trnPrihvatanjaClanstva, trnOdustajanjaOdClanstva);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClanInicijativnogOdbora {\n");
    sb.append("    aktivan: ").append(toIndentedString(aktivan)).append("\n");
    sb.append("    imePrezime: ").append(toIndentedString(imePrezime)).append("\n");
    sb.append("    pol: ").append(toIndentedString(pol)).append("\n");
    sb.append("    godinaRodjenja: ").append(toIndentedString(godinaRodjenja)).append("\n");
    sb.append("    emailZaKontakt: ").append(toIndentedString(emailZaKontakt)).append("\n");
    sb.append("    biografija: ").append(toIndentedString(biografija)).append("\n");
    sb.append("    trnPrihvatanjaClanstva: ").append(toIndentedString(trnPrihvatanjaClanstva)).append("\n");
    sb.append("    trnOdustajanjaOdClanstva: ").append(toIndentedString(trnOdustajanjaOdClanstva)).append("\n");
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

