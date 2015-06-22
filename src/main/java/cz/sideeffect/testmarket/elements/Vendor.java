package cz.sideeffect.testmarket.elements;

/**
 * This class represents a vendor.
 */
public class Vendor implements Comparable<Vendor> {
    private final String name;

    /**
     * 
     * @param name unique name of the vendor, case sensitive
     */
    public Vendor(String name) {
        if(!name.equals("")) {
            this.name = name;
        }
        else {
            throw new RuntimeException("Vendor name cannot be empty string.");
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

        Vendor vendor = (Vendor) o;

        return name.equals(vendor.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Vendor vendor) {
        return name.compareTo(vendor.name);
    }
}
