package cz.sideeffect.testmarket.elements;

/**
 * This class represents a country.
 */
public class Country implements Comparable<Country> {
    private final String name;

    /**
     * 
     * @param name unique name of the country, case sensitive
     */
    public Country(String name) {
        if(!name.equals("")) {
            this.name = name;
        }
        else {
            throw new RuntimeException("Country name cannot be empty string.");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return name.equals(country.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Country country) {
        return name.compareTo(country.name);
    }
}
