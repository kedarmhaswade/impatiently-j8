package practice.ej;

/**
 * Created by kedar on 5/9/17.
 */
public final class PhoneNumber {
    private final short areaCode, prefix, lineNumber;
    private volatile int hash; // cached
    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        // validate
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }
    @Override public boolean equals(Object o) {
        if (o == this)
            return true; // optimization
        if (! (o instanceof PhoneNumber))
            return false;
        PhoneNumber that = (PhoneNumber) o;
        return this.areaCode == that.areaCode
            && this.prefix == that.prefix
            && this.lineNumber == that.lineNumber;
    }
    @Override public int hashCode() {
        int h = hash; // atomic read
        if (h == 0) {
            h = 17;
            h = 31 * h + areaCode;
            h = 31 * h + prefix;
            h = 31 * h + lineNumber;
            hash = h; // atomic write
        }
        return h;
    }
}
