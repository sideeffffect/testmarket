package cz.sideeffect.testmarket.elements;

/**
 * This class represents a percentage of a whole.
 */
public class Share {
    private final float share;

    /**
     * 
     * @param share should be between 0 and 1, otherwise throws RuntimeException
     */
    public Share(float share) {
        if(0 <= share && share <= 1.0) {
            this.share = share;
        }
        else {
            throw new RuntimeException("Out of bounds share <0, 1>, got " + share);
        }
    }

    /**
     * 
     * @param part should be lesser than the total
     * @param total should be greater than the part
     */
    public Share(Number part, Number total){
        share = part.floatValue() / total.floatValue();
    }

    @Override
    public String toString() {
        return String.format("%.1f", share * 100) + " %";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Share share1 = (Share) o;

        return Float.compare(share1.share, share) == 0;

    }

    @Override
    public int hashCode() {
        return (share != +0.0f ? Float.floatToIntBits(share) : 0);
    }
}
