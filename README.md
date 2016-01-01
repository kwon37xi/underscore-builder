# Underscore Builders for Java
Build strings and sql/jpql/hql in a easy way.

## Requirements
* Java 6 or later

## Underscore String Builder
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-string-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-string-builder)

[UnderscoreStringBuilder](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilder.java) is a replacement for [java.util.StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html).
Actually I made this for building dynamic SQL/JPQL/HQL with pure java codes. Please refer to Underscore QL Params.

### Core API
There are two core methods in [UnderscoreStringBuilder](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilder.java).

* `public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers)`
* `public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers)`

These two methods append the `appendee` or the result of `subBuild` to the `UnderscoreStringBuilder` character sequence only when `appendable == true`, also transform the `appendde` with `transformer` and `extraTransformers`.

* `prefix*()` : prefix the `appendde` every time until `prefixOff()` called.
* `suffix*()` : suffix the `appendee` every time unti `suffixOff()` called.

* [UnderscoreStringBuilderTransformers](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilderTransformers.java) : Built-in transformers. you can join, multiply, (String)format, dateFormat, wrap, trim and etc.

### Usage examples
```java

```


## Underscore QL Params
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params/badge.svg)](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params)

Build SQL/JPQL/HQL with Underscore String Builder in a easy way.
