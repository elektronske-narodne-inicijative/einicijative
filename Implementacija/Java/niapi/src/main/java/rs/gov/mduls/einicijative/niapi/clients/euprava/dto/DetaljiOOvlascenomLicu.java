package rs.gov.mduls.einicijative.niapi.clients.euprava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

/**
 * DetaljiOOvlascenomLicu
 */

public class DetaljiOOvlascenomLicu {

    /**
     * Gets or Sets idNivoaUprave
     */
    public enum IdNivoaUpraveEnum {
        Republika("Р"),
        Pokrajina("П"),
        Opstina("О");

        private String value;

        IdNivoaUpraveEnum(String value) {
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
        public static IdNivoaUpraveEnum fromValue(String value) {
            for (IdNivoaUpraveEnum b : IdNivoaUpraveEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private UUID idKorisnika;
    private String imePrezime;
    private String emailAdresa;
    private IdNivoaUpraveEnum idNivoaUprave;
    private String idOpstine;

    public DetaljiOOvlascenomLicu() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DetaljiOOvlascenomLicu(UUID idKorisnika, String imePrezime, String emailAdresa, IdNivoaUpraveEnum idNivoaUprave) {
        this.idKorisnika = idKorisnika;
        this.imePrezime = imePrezime;
        this.emailAdresa = emailAdresa;
        this.idNivoaUprave = idNivoaUprave;
    }


    public DetaljiOOvlascenomLicu idKorisnika(UUID idKorisnika) {
        this.idKorisnika = idKorisnika;
        return this;
    }

    /**
     * Get idKorisnika
     * @return idKorisnika
     */
    @NotNull
    @JsonProperty("idKorisnika")
    public UUID getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(UUID idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public DetaljiOOvlascenomLicu imePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
        return this;
    }

    /**
     * Get imePrezime
     * @return imePrezime
     */

    @JsonProperty("imePrezime")
    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public DetaljiOOvlascenomLicu emailAdresa(String emailAdresa) {
        this.emailAdresa = emailAdresa;
        return this;
    }

    /**
     * Get emailAdresa
     * @return emailAdresa
     */

    @JsonProperty("emailAdresa")
    public String getEmailAdresa() {
        return emailAdresa;
    }

    public void setEmailAdresa(String emailAdresa) {
        this.emailAdresa = emailAdresa;
    }

    public DetaljiOOvlascenomLicu idNivoaUprave(IdNivoaUpraveEnum idNivoaUprave) {
        this.idNivoaUprave = idNivoaUprave;
        return this;
    }

    /**
     * Get idNivoaUprave
     * @return idNivoaUprave
     */
    @NotNull
    @JsonProperty("idNivoaUprave")
    public IdNivoaUpraveEnum getIdNivoaUprave() {
        return idNivoaUprave;
    }

    public void setIdNivoaUprave(IdNivoaUpraveEnum idNivoaUprave) {
        this.idNivoaUprave = idNivoaUprave;
    }

    public DetaljiOOvlascenomLicu idOpstine(String idOpstine) {
        this.idOpstine = idOpstine;
        return this;
    }

    /**
     * Get idOpstine
     * @return idOpstine
     */
    @NotNull
    @JsonProperty("idOpstine")
    public String getIdOpstine() {
        return idOpstine;
    }

    public void setIdOpstine(String idOpstine) {
        this.idOpstine = idOpstine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetaljiOOvlascenomLicu DetaljiOOvlascenomLicu = (DetaljiOOvlascenomLicu) o;
        return Objects.equals(this.idKorisnika, DetaljiOOvlascenomLicu.idKorisnika) &&
                Objects.equals(this.imePrezime, DetaljiOOvlascenomLicu.imePrezime) &&
                Objects.equals(this.emailAdresa, DetaljiOOvlascenomLicu.emailAdresa) &&
                Objects.equals(this.idNivoaUprave, DetaljiOOvlascenomLicu.idNivoaUprave) &&
                Objects.equals(this.idOpstine, DetaljiOOvlascenomLicu.idOpstine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnika, idNivoaUprave, imePrezime, idOpstine);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DetaljiOOvlascenomLicu {\n");
        sb.append("    idKorisnika: ").append(toIndentedString(idKorisnika)).append("\n");
        sb.append("    imePrezime: ").append(toIndentedString(imePrezime)).append("\n");
        sb.append("    emailAdresa: ").append(toIndentedString(emailAdresa)).append("\n");
        sb.append("    idNivoaUprave: ").append(toIndentedString(idNivoaUprave)).append("\n");
        sb.append("    idOpstine: ").append(toIndentedString(idOpstine)).append("\n");
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

