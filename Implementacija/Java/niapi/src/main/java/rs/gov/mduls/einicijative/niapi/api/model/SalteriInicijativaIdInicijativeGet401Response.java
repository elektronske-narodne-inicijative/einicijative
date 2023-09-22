package rs.gov.mduls.einicijative.niapi.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.annotation.Generated;

/**
 * SalteriInicijativaIdInicijativeGet401Response
 */

@JsonTypeName("_salteri_inicijativa__idInicijative__get_401_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-17T13:23:05.888263500+01:00[Europe/London]")
public class SalteriInicijativaIdInicijativeGet401Response {

  /**
   * Gets or Sets resultType
   */
  public enum ResultTypeEnum {
    NEISPRAVNASESIJA("NeispravnaSesija"),
    
    ISTEKLASESIJA("IsteklaSesija");

    private String value;

    ResultTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResultTypeEnum fromValue(String value) {
      for (ResultTypeEnum b : ResultTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ResultTypeEnum resultType;

  private String resultCode;

  public SalteriInicijativaIdInicijativeGet401Response resultType(ResultTypeEnum resultType) {
    this.resultType = resultType;
    return this;
  }

  /**
   * Get resultType
   * @return resultType
  */
  
  @JsonProperty("resultType")
  public ResultTypeEnum getResultType() {
    return resultType;
  }

  public void setResultType(ResultTypeEnum resultType) {
    this.resultType = resultType;
  }

  public SalteriInicijativaIdInicijativeGet401Response resultCode(String resultCode) {
    this.resultCode = resultCode;
    return this;
  }

  /**
   * Get resultCode
   * @return resultCode
  */
  
  @JsonProperty("resultCode")
  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalteriInicijativaIdInicijativeGet401Response salteriInicijativaIdInicijativeGet401Response = (SalteriInicijativaIdInicijativeGet401Response) o;
    return Objects.equals(this.resultType, salteriInicijativaIdInicijativeGet401Response.resultType) &&
        Objects.equals(this.resultCode, salteriInicijativaIdInicijativeGet401Response.resultCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultType, resultCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SalteriInicijativaIdInicijativeGet401Response {\n");
    sb.append("    resultType: ").append(toIndentedString(resultType)).append("\n");
    sb.append("    resultCode: ").append(toIndentedString(resultCode)).append("\n");
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

