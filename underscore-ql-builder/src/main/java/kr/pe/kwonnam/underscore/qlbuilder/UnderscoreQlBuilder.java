package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscorePredicate;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
     * @see UnderscoreQlBuilder#params(Object...)
     */
    public UnderscoreQlParamsTransformer params(Object... params) {
        return underscoreQlParams.params(params);
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

    public boolean not(boolean bool) {
        return !bool;
    }

    public boolean isNull(Object object) {
        return object == null;
    }

    public boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public boolean isBlank(String str) {
        return isEmpty(str) || str.trim().isEmpty();
    }

    public boolean contains(String str, String subStr) {
        if (isEmpty(str)) {
            return false;
        }
        return str.contains(subStr);
    }

    public boolean contains(Collection<?> collection, Object object) {
        return false;
    }

    public boolean contains(Object[] array, Object object) {
        return false;
    }

    public boolean contains(boolean[] array, boolean object) {
        return false;
    }

    public boolean contains(byte[] array, byte object) {
        return false;
    }

    public boolean contains(char[] array, char object) {
        return false;
    }

    public boolean contains(short[] array, short object) {
        return false;
    }

    public boolean contains(int[] array, int object) {
        return false;
    }

    public boolean contains(long[] array, long object) {
        return false;
    }

    public boolean contains(float[] array, float object) {
        return false;
    }

    public boolean contains(double[] array, double object) {
        return false;
    }

    public boolean containsAny(String str, String... subStrs) {
        return false;
    }

    public boolean containsAny(Collection<?> collection, Object... objects) {
        return false;
    }

    public boolean containsAny(Object[] array, Object object) {
        return false;
    }

    public boolean containsAny(boolean[] array, boolean object) {
        return false;
    }

    public boolean containsAny(byte[] array, byte object) {
        return false;
    }

    public boolean containsAny(char[] array, char object) {
        return false;
    }

    public boolean containsAny(short[] array, short object) {
        return false;
    }

    public boolean containsAny(int[] array, int object) {
        return false;
    }

    public boolean containsAny(long[] array, long object) {
        return false;
    }

    public boolean containsAny(float[] array, float object) {
        return false;
    }

    public boolean containsAny(double[] array, double object) {
        return false;
    }

    public boolean containsAll(String str, String... subStrs) {
        return false;
    }

    public boolean containsAll(Collection<?> collection, Object... objects) {
        return false;
    }

    public boolean containsAll(Object[] array, Object object) {
        return false;
    }

    public boolean containsAll(boolean[] array, boolean object) {
        return false;
    }

    public boolean containsAll(byte[] array, byte object) {
        return false;
    }

    public boolean containsAll(char[] array, char object) {
        return false;
    }

    public boolean containsAll(short[] array, short object) {
        return false;
    }

    public boolean containsAll(int[] array, int object) {
        return false;
    }

    public boolean containsAll(long[] array, long object) {
        return false;
    }

    public boolean containsAll(float[] array, float object) {
        return false;
    }

    public boolean containsAll(double[] array, double object) {
        return false;
    }
}
