package cr.una.frontendWeb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:04 a. m.
 */
public class Id {
    @JsonProperty("$oid")
    private String $oid;

    public Id() {

    }

    /**
     * @param $oid
     */

    public Id(String $oid) {
        this.$oid = $oid;
    }

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id='" + $oid + '\'' +
                '}';
    }
}
