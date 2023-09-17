package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InicijativaDetalji
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class InicijativaDetalji {

  private Integer idInicijative;

  private String tipInicijative;

  private String inicijator;

  private String nazivInicijative;

  private String tekstInicijative;

  private String fazaObrade;

  private String emailZaKontakt;

  private String nivoVlasti;

  private String jedinicaVlasti;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnZahteva;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnOdbijanjaZahteva;

  private String razlogOdbijanjaZahteva;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime trnPodnosenja;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datumPokretanja;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate datumOdluke;

  private Boolean prihvacena;

  private String beleskaSaSednice;

  @Valid
  private List<@Valid ClanInicijativnogOdbora> clanoviInicijativnogOdbora;

  @Valid
  private List<@Valid PrilogInicijative> prilozi;

  @Valid
  private List<@Valid DnevnikPromena> promene;

  public InicijativaDetalji() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InicijativaDetalji(Integer idInicijative, String tipInicijative, String inicijator, String nazivInicijative, String tekstInicijative, String fazaObrade, String emailZaKontakt, String nivoVlasti, OffsetDateTime trnZahteva) {
    this.idInicijative = idInicijative;
    this.tipInicijative = tipInicijative;
    this.inicijator = inicijator;
    this.nazivInicijative = nazivInicijative;
    this.tekstInicijative = tekstInicijative;
    this.fazaObrade = fazaObrade;
    this.emailZaKontakt = emailZaKontakt;
    this.nivoVlasti = nivoVlasti;
    this.trnZahteva = trnZahteva;
  }

  public InicijativaDetalji idInicijative(Integer idInicijative) {
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

  public InicijativaDetalji tipInicijative(String tipInicijative) {
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

  public InicijativaDetalji inicijator(String inicijator) {
    this.inicijator = inicijator;
    return this;
  }

  /**
   * Get inicijator
   * @return inicijator
  */
  @NotNull 
  @JsonProperty("inicijator")
  public String getInicijator() {
    return inicijator;
  }

  public void setInicijator(String inicijator) {
    this.inicijator = inicijator;
  }

  public InicijativaDetalji nazivInicijative(String nazivInicijative) {
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

  public InicijativaDetalji tekstInicijative(String tekstInicijative) {
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

  public InicijativaDetalji fazaObrade(String fazaObrade) {
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

  public InicijativaDetalji emailZaKontakt(String emailZaKontakt) {
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

  public InicijativaDetalji nivoVlasti(String nivoVlasti) {
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

  public InicijativaDetalji jedinicaVlasti(String jedinicaVlasti) {
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

  public InicijativaDetalji trnZahteva(OffsetDateTime trnZahteva) {
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

  public InicijativaDetalji trnOdbijanjaZahteva(OffsetDateTime trnOdbijanjaZahteva) {
    this.trnOdbijanjaZahteva = trnOdbijanjaZahteva;
    return this;
  }

  /**
   * Get trnOdbijanjaZahteva
   * @return trnOdbijanjaZahteva
  */
  @Valid 
  @JsonProperty("trnOdbijanjaZahteva")
  public OffsetDateTime getTrnOdbijanjaZahteva() {
    return trnOdbijanjaZahteva;
  }

  public void setTrnOdbijanjaZahteva(OffsetDateTime trnOdbijanjaZahteva) {
    this.trnOdbijanjaZahteva = trnOdbijanjaZahteva;
  }

  public InicijativaDetalji razlogOdbijanjaZahteva(String razlogOdbijanjaZahteva) {
    this.razlogOdbijanjaZahteva = razlogOdbijanjaZahteva;
    return this;
  }

  /**
   * Get razlogOdbijanjaZahteva
   * @return razlogOdbijanjaZahteva
  */
  
  @JsonProperty("razlogOdbijanjaZahteva")
  public String getRazlogOdbijanjaZahteva() {
    return razlogOdbijanjaZahteva;
  }

  public void setRazlogOdbijanjaZahteva(String razlogOdbijanjaZahteva) {
    this.razlogOdbijanjaZahteva = razlogOdbijanjaZahteva;
  }

  public InicijativaDetalji trnPodnosenja(OffsetDateTime trnPodnosenja) {
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

  public InicijativaDetalji datumPokretanja(LocalDate datumPokretanja) {
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

  public InicijativaDetalji datumOdluke(LocalDate datumOdluke) {
    this.datumOdluke = datumOdluke;
    return this;
  }

  /**
   * Get datumOdluke
   * @return datumOdluke
  */
  @Valid 
  @JsonProperty("datumOdluke")
  public LocalDate getDatumOdluke() {
    return datumOdluke;
  }

  public void setDatumOdluke(LocalDate datumOdluke) {
    this.datumOdluke = datumOdluke;
  }

  public InicijativaDetalji prihvacena(Boolean prihvacena) {
    this.prihvacena = prihvacena;
    return this;
  }

  /**
   * Get prihvacena
   * @return prihvacena
  */
  
  @JsonProperty("prihvacena")
  public Boolean getPrihvacena() {
    return prihvacena;
  }

  public void setPrihvacena(Boolean prihvacena) {
    this.prihvacena = prihvacena;
  }

  public InicijativaDetalji beleskaSaSednice(String beleskaSaSednice) {
    this.beleskaSaSednice = beleskaSaSednice;
    return this;
  }

  /**
   * Get beleskaSaSednice
   * @return beleskaSaSednice
  */
  
  @JsonProperty("beleskaSaSednice")
  public String getBeleskaSaSednice() {
    return beleskaSaSednice;
  }

  public void setBeleskaSaSednice(String beleskaSaSednice) {
    this.beleskaSaSednice = beleskaSaSednice;
  }

  public InicijativaDetalji clanoviInicijativnogOdbora(List<@Valid ClanInicijativnogOdbora> clanoviInicijativnogOdbora) {
    this.clanoviInicijativnogOdbora = clanoviInicijativnogOdbora;
    return this;
  }

  public InicijativaDetalji addClanoviInicijativnogOdboraItem(ClanInicijativnogOdbora clanoviInicijativnogOdboraItem) {
    if (this.clanoviInicijativnogOdbora == null) {
      this.clanoviInicijativnogOdbora = new ArrayList<>();
    }
    this.clanoviInicijativnogOdbora.add(clanoviInicijativnogOdboraItem);
    return this;
  }

  /**
   * Get clanoviInicijativnogOdbora
   * @return clanoviInicijativnogOdbora
  */
  @Valid 
  @JsonProperty("clanoviInicijativnogOdbora")
  public List<@Valid ClanInicijativnogOdbora> getClanoviInicijativnogOdbora() {
    return clanoviInicijativnogOdbora;
  }

  public void setClanoviInicijativnogOdbora(List<@Valid ClanInicijativnogOdbora> clanoviInicijativnogOdbora) {
    this.clanoviInicijativnogOdbora = clanoviInicijativnogOdbora;
  }

  public InicijativaDetalji prilozi(List<@Valid PrilogInicijative> prilozi) {
    this.prilozi = prilozi;
    return this;
  }

  public InicijativaDetalji addPriloziItem(PrilogInicijative priloziItem) {
    if (this.prilozi == null) {
      this.prilozi = new ArrayList<>();
    }
    this.prilozi.add(priloziItem);
    return this;
  }

  /**
   * Get prilozi
   * @return prilozi
  */
  @Valid 
  @JsonProperty("prilozi")
  public List<@Valid PrilogInicijative> getPrilozi() {
    return prilozi;
  }

  public void setPrilozi(List<@Valid PrilogInicijative> prilozi) {
    this.prilozi = prilozi;
  }

  public InicijativaDetalji promene(List<@Valid DnevnikPromena> promene) {
    this.promene = promene;
    return this;
  }

  public InicijativaDetalji addPromeneItem(DnevnikPromena promeneItem) {
    if (this.promene == null) {
      this.promene = new ArrayList<>();
    }
    this.promene.add(promeneItem);
    return this;
  }

  /**
   * Get promene
   * @return promene
  */
  @Valid 
  @JsonProperty("promene")
  public List<@Valid DnevnikPromena> getPromene() {
    return promene;
  }

  public void setPromene(List<@Valid DnevnikPromena> promene) {
    this.promene = promene;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InicijativaDetalji inicijativaDetalji = (InicijativaDetalji) o;
    return Objects.equals(this.idInicijative, inicijativaDetalji.idInicijative) &&
        Objects.equals(this.tipInicijative, inicijativaDetalji.tipInicijative) &&
        Objects.equals(this.inicijator, inicijativaDetalji.inicijator) &&
        Objects.equals(this.nazivInicijative, inicijativaDetalji.nazivInicijative) &&
        Objects.equals(this.tekstInicijative, inicijativaDetalji.tekstInicijative) &&
        Objects.equals(this.fazaObrade, inicijativaDetalji.fazaObrade) &&
        Objects.equals(this.emailZaKontakt, inicijativaDetalji.emailZaKontakt) &&
        Objects.equals(this.nivoVlasti, inicijativaDetalji.nivoVlasti) &&
        Objects.equals(this.jedinicaVlasti, inicijativaDetalji.jedinicaVlasti) &&
        Objects.equals(this.trnZahteva, inicijativaDetalji.trnZahteva) &&
        Objects.equals(this.trnOdbijanjaZahteva, inicijativaDetalji.trnOdbijanjaZahteva) &&
        Objects.equals(this.razlogOdbijanjaZahteva, inicijativaDetalji.razlogOdbijanjaZahteva) &&
        Objects.equals(this.trnPodnosenja, inicijativaDetalji.trnPodnosenja) &&
        Objects.equals(this.datumPokretanja, inicijativaDetalji.datumPokretanja) &&
        Objects.equals(this.datumOdluke, inicijativaDetalji.datumOdluke) &&
        Objects.equals(this.prihvacena, inicijativaDetalji.prihvacena) &&
        Objects.equals(this.beleskaSaSednice, inicijativaDetalji.beleskaSaSednice) &&
        Objects.equals(this.clanoviInicijativnogOdbora, inicijativaDetalji.clanoviInicijativnogOdbora) &&
        Objects.equals(this.prilozi, inicijativaDetalji.prilozi) &&
        Objects.equals(this.promene, inicijativaDetalji.promene);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idInicijative, tipInicijative, inicijator, nazivInicijative, tekstInicijative, fazaObrade, emailZaKontakt, nivoVlasti, jedinicaVlasti, trnZahteva, trnOdbijanjaZahteva, razlogOdbijanjaZahteva, trnPodnosenja, datumPokretanja, datumOdluke, prihvacena, beleskaSaSednice, clanoviInicijativnogOdbora, prilozi, promene);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InicijativaDetalji {\n");
    sb.append("    idInicijative: ").append(toIndentedString(idInicijative)).append("\n");
    sb.append("    tipInicijative: ").append(toIndentedString(tipInicijative)).append("\n");
    sb.append("    inicijator: ").append(toIndentedString(inicijator)).append("\n");
    sb.append("    nazivInicijative: ").append(toIndentedString(nazivInicijative)).append("\n");
    sb.append("    tekstInicijative: ").append(toIndentedString(tekstInicijative)).append("\n");
    sb.append("    fazaObrade: ").append(toIndentedString(fazaObrade)).append("\n");
    sb.append("    emailZaKontakt: ").append(toIndentedString(emailZaKontakt)).append("\n");
    sb.append("    nivoVlasti: ").append(toIndentedString(nivoVlasti)).append("\n");
    sb.append("    jedinicaVlasti: ").append(toIndentedString(jedinicaVlasti)).append("\n");
    sb.append("    trnZahteva: ").append(toIndentedString(trnZahteva)).append("\n");
    sb.append("    trnOdbijanjaZahteva: ").append(toIndentedString(trnOdbijanjaZahteva)).append("\n");
    sb.append("    razlogOdbijanjaZahteva: ").append(toIndentedString(razlogOdbijanjaZahteva)).append("\n");
    sb.append("    trnPodnosenja: ").append(toIndentedString(trnPodnosenja)).append("\n");
    sb.append("    datumPokretanja: ").append(toIndentedString(datumPokretanja)).append("\n");
    sb.append("    datumOdluke: ").append(toIndentedString(datumOdluke)).append("\n");
    sb.append("    prihvacena: ").append(toIndentedString(prihvacena)).append("\n");
    sb.append("    beleskaSaSednice: ").append(toIndentedString(beleskaSaSednice)).append("\n");
    sb.append("    clanoviInicijativnogOdbora: ").append(toIndentedString(clanoviInicijativnogOdbora)).append("\n");
    sb.append("    prilozi: ").append(toIndentedString(prilozi)).append("\n");
    sb.append("    promene: ").append(toIndentedString(promene)).append("\n");
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

