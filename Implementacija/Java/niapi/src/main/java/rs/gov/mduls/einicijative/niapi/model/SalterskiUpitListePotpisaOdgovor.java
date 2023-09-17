package rs.gov.mduls.einicijative.niapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;

import jakarta.annotation.Generated;

/**
 * SalterskiUpitListePotpisaOdgovor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class SalterskiUpitListePotpisaOdgovor {

  @Valid
  private List<@Valid PotpisInicijative> potpisi;

  public SalterskiUpitListePotpisaOdgovor potpisi(List<@Valid PotpisInicijative> potpisi) {
    this.potpisi = potpisi;
    return this;
  }

  public SalterskiUpitListePotpisaOdgovor addPotpisiItem(PotpisInicijative potpisiItem) {
    if (this.potpisi == null) {
      this.potpisi = new ArrayList<>();
    }
    this.potpisi.add(potpisiItem);
    return this;
  }

  /**
   * Get potpisi
   * @return potpisi
  */
  @Valid 
  @JsonProperty("potpisi")
  public List<@Valid PotpisInicijative> getPotpisi() {
    return potpisi;
  }

  public void setPotpisi(List<@Valid PotpisInicijative> potpisi) {
    this.potpisi = potpisi;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalterskiUpitListePotpisaOdgovor salterskiUpitListePotpisaOdgovor = (SalterskiUpitListePotpisaOdgovor) o;
    return Objects.equals(this.potpisi, salterskiUpitListePotpisaOdgovor.potpisi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(potpisi);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalterskiUpitListePotpisaOdgovor {\n");
    sb.append("    potpisi: ").append(toIndentedString(potpisi)).append("\n");
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

