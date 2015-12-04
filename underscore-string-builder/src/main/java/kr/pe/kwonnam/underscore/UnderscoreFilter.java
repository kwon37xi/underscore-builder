package kr.pe.kwonnam.underscore;

public interface UnderscoreFilter<T extends UnderscoreBuilder>  {
    void filter(T underscoreBuilder, Object appended);
}
