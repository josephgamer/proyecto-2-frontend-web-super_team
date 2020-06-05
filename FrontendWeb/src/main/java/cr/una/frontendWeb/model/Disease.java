package cr.una.frontendWeb.model;

/**
 * @author Esteban
 * @created 05/06/2020 - 09:07 a. m.
 */
public class Disease {
    private String name;

    public Disease(){

    }

    /**
     *
     * @param name
     */

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                '}';
    }
}
