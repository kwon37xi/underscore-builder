package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscorePredicate;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Build QL(SQL,JPQL,HQL and etc) with {@link UnderscoreStringBuilder} and {@link UnderscoreQlParams}.
 */
public class UnderscoreQlBuilder implements CharSequence, Appendable {
    private UnderscoreStringBuilder underscoreStringBuilder = new UnderscoreStringBuilder();
    private UnderscoreQlParams underscoreQlParams = new UnderscoreQlParams();

    public void setUnderscoreStringBuilder(UnderscoreStringBuilder underscoreStringBuilder) {
        this.underscoreStringBuilder = underscoreStringBuilder;
    }

    public void setUnderscoreQlParams(UnderscoreQlParams underscoreQlParams) {
        this.underscoreQlParams = underscoreQlParams;
    }

    /**
     * @see UnderscoreQlParams#params(Object...)
     */
    public UnderscoreQlParamsTransformer params(Object... params) {
        return underscoreQlParams.params(params);
    }

    /**
     * @see UnderscoreQlParams#inParams(boolean[])
     */
    public UnderscoreQlInParamsTransformer inParams(boolean[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(byte[])
     */
    public UnderscoreQlInParamsTransformer inParams(byte[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(char[])
     */
    public UnderscoreQlInParamsTransformer inParams(char[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(short[])
     */
    public UnderscoreQlInParamsTransformer inParams(short[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(int[])
     */
    public UnderscoreQlInParamsTransformer inParams(int[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(long[])
     */
    public UnderscoreQlInParamsTransformer inParams(long[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(float[])
     */
    public UnderscoreQlInParamsTransformer inParams(float[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(double[])
     */
    public UnderscoreQlInParamsTransformer inParams(double[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(Object[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlBuilder#inParams(Iterable)
     */
    public UnderscoreQlInParamsTransformer inParams(Iterable<?> inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#isWithPositionalIndex()
     */
    public boolean isWithPositionalIndex() {
        return underscoreQlParams.isWithPositionalIndex();
    }

    /**
     * @see UnderscoreQlParams#getQueryParameters()
     */
    public List<Object> getQueryParameters() {
        return underscoreQlParams.getQueryParameters();
    }

    /**
     * @see UnderscoreQlParams#getQueryParameterArray()
     */
    public Object[] getQueryParameterArray() {
        return underscoreQlParams.getQueryParameterArray();
    }

    /**
     * @see UnderscoreQlParams#bindParameters(PreparedStatement)
     */
    public void bindParameters(PreparedStatement preparedStatement) throws SQLException {
        underscoreQlParams.bindParameters(preparedStatement);
    }

    /**
     * @see UnderscoreStringBuilder#__(Object)
     */
    public <A> UnderscoreStringBuilder __(A appendee) {
        return underscoreStringBuilder.__(appendee);
    }

    /**
     * @see UnderscoreStringBuilder#__(boolean, Object)
     */
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee) {
        return underscoreStringBuilder.__(appendable, appendee);
    }

    /**
     * @see UnderscoreStringBuilder#__(Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<A> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(appendee, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#__(boolean, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(appendable, appendee, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#__(UnderscorePredicate, Object)
     */
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee) {
        return underscoreStringBuilder.__(predicate, appendee);
    }

    /**
     * @see UnderscoreStringBuilder#__(UnderscorePredicate, Object, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(predicate, appendee, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#sub(UnderscoreSubBuild)
     */
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(subBuild);
    }

    /**
     * @see UnderscoreStringBuilder#sub(UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(subBuild, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#sub(boolean, UnderscoreSubBuild)
     */
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(appendable, subBuild);
    }

    /**
     * @see UnderscoreStringBuilder#sub(boolean, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(appendable, subBuild, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#sub(UnderscorePredicate, UnderscoreSubBuild)
     */
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(predicate, subBuild);
    }

    /**
     * @see UnderscoreStringBuilder#sub(UnderscorePredicate, UnderscoreSubBuild, UnderscoreTransformer, UnderscoreTransformer[])
     */
    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(predicate, subBuild, transformer, extraTransformers);
    }

    /**
     * @see UnderscoreStringBuilder#prefix(String)
     */
    public UnderscoreStringBuilder prefix(String prefix) {
        return underscoreStringBuilder.prefix(prefix);
    }

    /**
     * @see UnderscoreStringBuilder#prefixOff()
     */
    public UnderscoreStringBuilder prefixOff() {
        return underscoreStringBuilder.prefixOff();
    }

    /**
     * @see UnderscoreStringBuilder#prefixNewLine()
     */
    public UnderscoreStringBuilder prefixNewLine() {
        return underscoreStringBuilder.prefixNewLine();
    }

    /**
     * @see UnderscoreStringBuilder#suffix(String)
     */
    public UnderscoreStringBuilder suffix(String suffix) {
        return underscoreStringBuilder.suffix(suffix);
    }

    /**
     * @see UnderscoreStringBuilder#suffixOff()
     */
    public UnderscoreStringBuilder suffixOff() {
        return underscoreStringBuilder.suffixOff();
    }

    /**
     * @see UnderscoreStringBuilder#suffixNewLine()
     */
    public UnderscoreStringBuilder suffixNewLine() {
        return underscoreStringBuilder.suffixNewLine();
    }

    @Override
    public int length() {
        return underscoreStringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return underscoreStringBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return underscoreStringBuilder.subSequence(start, end);
    }

    @Override
    public UnderscoreStringBuilder append(CharSequence csq) throws IOException {
        return underscoreStringBuilder.append(csq);
    }

    @Override
    public UnderscoreStringBuilder append(CharSequence csq, int start, int end) throws IOException {
        return underscoreStringBuilder.append(csq, start, end);
    }

    @Override
    public UnderscoreStringBuilder append(char c) throws IOException {
        return underscoreStringBuilder.append(c);
    }

    @Override
    public String toString() {
        return underscoreStringBuilder.toString();
    }
}
