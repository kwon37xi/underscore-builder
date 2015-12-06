package kr.pe.kwonnam.underscore;

public interface UnderscoreTransformer<B extends UnderscoreBuilder, A>  {
    void transform(B underscoreBuilder, A appended);
}
