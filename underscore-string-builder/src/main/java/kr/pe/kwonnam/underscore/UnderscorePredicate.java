package kr.pe.kwonnam.underscore;

public interface UnderscorePredicate {
    <T extends UnderscoreBuilder> boolean evaluate(T underscoreBuilder);
}
