package kr.pe.kwonnam.underscore;

public interface UnderscoreFilter<B extends UnderscoreBuilder, A>  {
    void filter(B underscoreBuilder, A appended);
}
