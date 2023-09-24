package rs.gov.mduls.einicijative.niapi.clients.euprava.dto;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.*;


/**
 * DetaljiOGradjaninu
 */

public class DetaljiOGradjaninu {

    /**
     * Gets or Sets idPola
     */
    public enum IdPolaEnum {
        M("М"),

        Z("Ж");

        private String value;

        IdPolaEnum(String value) {
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
        public static IdPolaEnum fromValue(String value) {
            for (IdPolaEnum b : IdPolaEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private UUID idKorisnika;

    private IdPolaEnum idPola;

    private String idOpstine;

    private Integer godinaRodjenja;

    private String imePrezime;

    public DetaljiOGradjaninu() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public DetaljiOGradjaninu(UUID idKorisnika, IdPolaEnum idPola, String idOpstine, Integer godinaRodjenja) {
        this.idPola = idPola;
        this.idOpstine = idOpstine;
        this.godinaRodjenja = godinaRodjenja;
    }


    public DetaljiOGradjaninu idKorisnika(UUID idKorisnika) {
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

    public DetaljiOGradjaninu idPola(IdPolaEnum idPola) {
        this.idPola = idPola;
        return this;
    }

    /**
     * Get idPola
     * @return idPola
     */
    @NotNull
    @JsonProperty("idPola")
    public IdPolaEnum getIdPola() {
        return idPola;
    }

    public void setIdPola(IdPolaEnum idPola) {
        this.idPola = idPola;
    }

    public DetaljiOGradjaninu idOpstine(String idOpstine) {
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

    public DetaljiOGradjaninu godinaRodjenja(Integer godinaRodjenja) {
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

    public DetaljiOGradjaninu imePrezime(String imePrezime) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetaljiOGradjaninu DetaljiOGradjaninu = (DetaljiOGradjaninu) o;
        return Objects.equals(this.idKorisnika, DetaljiOGradjaninu.idKorisnika) &&
                Objects.equals(this.idPola, DetaljiOGradjaninu.idPola) &&
                Objects.equals(this.idOpstine, DetaljiOGradjaninu.idOpstine) &&
                Objects.equals(this.godinaRodjenja, DetaljiOGradjaninu.godinaRodjenja) &&
                Objects.equals(this.imePrezime, DetaljiOGradjaninu.imePrezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnika, idPola, idOpstine, godinaRodjenja, imePrezime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DetaljiOGradjaninu {\n");
        sb.append("    idKorisnika: ").append(toIndentedString(idKorisnika)).append("\n");
        sb.append("    idPola: ").append(toIndentedString(idPola)).append("\n");
        sb.append("    idOpstine: ").append(toIndentedString(idOpstine)).append("\n");
        sb.append("    godinaRodjenja: ").append(toIndentedString(godinaRodjenja)).append("\n");
        sb.append("    imePrezime: ").append(toIndentedString(imePrezime)).append("\n");
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

