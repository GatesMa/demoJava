package typedmap;

/**
 * TypedKey
 *
 * @author by gatesma.
 */
public class TypedKey<A> {

    private String displayName;

    public TypedKey(String displayName) {
        this.displayName = displayName;
    }

    public String getKey() {
        return displayName;
    }

    public static <A> TypedKey<A> create(String displayName) {
        return new TypedKey<>(displayName);
    }

}
