# Underscore Builders for Java
Build strings and SQL/JPQL/HQL more conveniently.

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
  * join
  * format // String.format
  * dateFormat // with SimpleDateFormat
  * wrap
  * multiply
  * trim : see [Trim](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/transformers/trim/Trim.java) and [TrimOpts](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/transformers/trim/TrimOpts.java)

### Usage examples
```java
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.*;

UnderscoreStringBuilder underscoreStringBuilder = new UnderscoreStringBuilder();

int[] ints = new int[] { 1, 2, 3, 4, 5};

underscoreStringBuilder
    .__("hello! ") // append "hello! "
    .__(user.getName() != null, user.getName()) // append user.getName() when user.getName() is not null
    .__(", ", join(ints)) // append "1, 2, 3, 4, 5"
    .__("=", multiply(20, "-"), wrap("[", "]")) // append "[=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=]"
    .__("Hello [%10s] [%7d]~", format("World", 12345)) // append ""Hello [     World] [  12345]~"
    .sub(user != null, new UnderscoreSubBuild() { // append underscoreSubBuilder result when user is not null
           @Override
           public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
               underscoreSubBuilder
                    .suffix("\n") // suffix every appendee with "\n"
                    .__(user.getName())
                    .__(user.getBirthday(), dateFormat("yyyy/MM/dd"));
           }
       });
```

## Underscore QL Params
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params/badge.svg)](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params)

Build SQL/JPQL/HQL with Underscore String Builder in a easy way.
