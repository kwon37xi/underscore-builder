package kr.pe.kwonnam.underscore.stringbuilder;

/**
 * UnderScoreStringBuilder builds string.
 *
 * @param <T>
 */
public class UnderScoreStringBuilder<T extends UnderScoreStringBuilder<T>> {

    private StringBuilder stringBuilder;

    public UnderScoreStringBuilder() {
        this(new StringBuilder());
    }

    public UnderScoreStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @SuppressWarnings("unchecked")
    public T __(Object obj) {
        stringBuilder.append(obj);
        return (T) this;
    }


    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        UnderScoreStringBuilder b = new UnderScoreStringBuilder();

    }

    public static class UnderScoreQlBuilder extends UnderScoreStringBuilder<UnderScoreQlBuilder> {

        public static void main(String[] args) {
            UnderScoreQlBuilder b = new UnderScoreQlBuilder();
            String s = b
                .__("hello world")
                .__("nice~~")
                .__("하하하")
                .toString();
            System.out.println(s);
        }
    }
}
