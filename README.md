## Case Study

The case study is similar to the one used in the paper before. We consider three different packages. The package *com.google.common.base* (Guava), *ru.lanwen.verbalregex* (VerbalExpression), and the package *example*.

The package *com.google.common.base* contains the class *Splitter* facilitates splitting a string based on a regular expression and iterating through the resulting list.

The package *ru.lanwen.verbalregex* contains some classes that can help facilitate writing difficult regular expression by replacing complex notations with simple method invocations in natural language. The class *VerbalExpression* would be used in the class *Splitter* to replace Java's class *Pattern*. This is done to illustrate how diamond dependency can be handled with Batakjava.

Lastly, the package *example* is the application where both the other packages would be mixed in.

## Scenario

First, we suppose that the VerbalExpression library would first be defined. We'll suppose that it is the version 1 of the package.

### VerbalExpression Ver.1

We suppose that there package's version management is done through directory management. In this case, we will suppose that VerbalExpression's developers would have a directory tree that looks as follow:

````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
````
VerbalExpression's developers then add declare two classes: VerbalExpression and VerbalExpressionBuilder.
````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
         |- VerbalExpression.batakjava
         |- VerbalExpression.batakjava 
````

**VerbalExpression.batakjava**
````
package ru.lanwen.verbalregex version 1;
public class VerbalExpression {
  public boolean testExact(String pToTest) {...}
}
````
**VerbalExpressionBuilder.batakjava**
````
package ru.lanwen.verbalregex version 1;
public class VerbalExpressionBuilder {
  public VerbalExpressionBuilder add(String pValue) {...}
}
````

If both pass the test check, two overview classes would be produced in the parent directory. The directory tree would look as follow:
````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
      |  |- VerbalExpression.batakjava
      |  |- VerbalExpressionBuilder.batakjava
      |- VerbalExpression.overview
      |- VerbalExpressionBuilder.overview
````
**VerbalExpression.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpression {
  boolean testExact(String pToTest) in version 1;
}
````
**VerbalExpressionBuilder.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpressionBuilder {
  VerbalExpressionBuilder add(String pValue) in version 1;
  VerbalExpression build() in version 1;
}
````
The transpiler then produces Java files for from the .batakjava and .overview files. The resulting directory tree may look as follows:
````
main
|- batakjava-src
| |- ru.lanwen.verbalregex
|     |- 1
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- VerbalExpression.overview
|     |- VerbalExpressionBuilder.overview
|- java-src
   |- ru.lanwen.verbalregex
      |- VerbalExpression.java
      |- VerbalExpressionBuilder.java
      |- VerbalExpression_1.java
      |- VerbalExpressionBuilder_1.java
````
The name ending with *_1* denotes the class that was defined in the directory *ru/lanwen/verbalregex/1*, while those that don't have any ending are generated from the overview files.

### Guava Ver.28

Next we have the Guava library. The library is fully packed with many utility classes, but here I would only show the class that is intertwined in the dependency, namely the class *Splitter*. I will skip the directory tree explanation, because it works the same way as in VerbalExpression.

**Splitter.batakjava**
````
package com.google.common.base version 28;
import ru.lanwen.verbalregex requires version 1;

public class Splitter {
  public static Splitter on(VerbalExpression separator) {
    ... separator.testExact("") ...
    ... separator.getPattern() ...   
  }
}
````

**Splitter.overview**
````
package com.google.common.base;
import ru.lanwen.verbalregex;

overview class Splitter {
  version 1 requires ru.lanwen.verbalregex version 1;
  static Splitter on(VerbalExpression separator);
}
````

### Application Ver.1

Following that, we have the Application that uses both Guava and VerbalExpression. Let's suppose we have a class called *Test*.

````
package example version 1;
import ru.lanwen.verbalregex.* requires version 1;
import com.google.common.base.Splitter requires version 28;

public class Test {
  public static void main(String[] args) {
    VerbalExpression regex = new VerbalExpressionBuilder()....build();
    new Splitter(...).on(regex);
  }
}
````

### VerbalExpression Ver.2

Next the developer of VerbalExpression update the library. On their directory tree they add a new directory for the new version, so that it will look as follow:
````
main
|- batakjava-src
| |- ru.lanwen.verbalregex
|     |- 1
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- 2
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- VerbalExpression.overview
|     |- VerbalExpressionBuilder.overview
|- java-src
   |- ...
````

**../2/VerbalExpression.batakjava**
````
package ru.lanwen.verbalregex version 2;
public class VerbalExpression {
  public boolean match(String pToTest) {...}    // new method
}
````

**../2/VerbalExpressionBuilder.batakjava**
````
package ru.lanwen.verbalregex version 2;
public class VerbalExpressionBuilder {
  public VerbalExpressionBuilder add(String pValue) {...}
  public VerbalExpressionBuilder append(String pValue) {...}    // new method
}
````
After both of these classes passed through type check, instead of creating two new overviews, because *VerbalExpression.overview* and *VerbalExpressionBuilder.overview* are already generated, those two overviews would be updated with the new functionalities that are introduced in the second version.

**VerbalExpression.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpression {
  boolean testExact(String pToTest) in version 1;
  boolean match(String pToTest) in version 2;
}
````
**VerbalExpressionBuilder.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpressionBuilder {
  VerbalExpressionBuilder add(String pValue) in version 1, 2;
  VerbalExpressionBuilder append(String pValue) in version 2;
  VerbalExpression build() in version 1, 2;
}
````

### Application Ver. 2

Now that VerbalExpression is updated, we want to update our application to use the new VerbalExpression.

**../2/Test.batakjava**
````
package example version 2;
import ru.lanwen.verbalregex.* requires version 2;
import com.google.common.base.Splitter requires version 28;

public class Test {
  public static void main(String[] args) {
    VerbalExpression regexNG = new VerbalExpressionBuilder()... .append(...).build();    
    VerbalExpression regexOK = new VerbalExpressionBuilder()... .add(...).build();
    new Splitter(...).on(regexNG);        // result in type error
    new Splitter(...).on(regexOK);        // no problem 
  }
}
````

The first results in type error because when VersionExpressionBuilder invokes append, the context of the object is restricted to the version 2 of *ru.lanwen.verbalregex*, so the resulting object VerbalExpression would also be restricted to the version 2. When this is passed as an argument ot Splitter's method on invocation, this would be a problem because the class Splitter requires version 1 (as denoted by the priority declaration).
## Case Study

The case study is similar to the one used in the paper before. We consider three different packages. The package *com.google.common.base* (Guava), *ru.lanwen.verbalregex* (VerbalExpression), and the package *example*.

The package *com.google.common.base* contains the class *Splitter* facilitates splitting a string based on a regular expression and iterating through the resulting list.

The package *ru.lanwen.verbalregex* contains some classes that can help facilitate writing difficult regular expression by replacing complex notations with simple method invocations in natural language. The class *VerbalExpression* would be used in the class *Splitter* to replace Java's class *Pattern*. This is done to illustrate how diamond dependency can be handled with Batakjava.

Lastly, the package *example* is the application where both the other packages would be mixed in.

## Scenario

First, we suppose that the VerbalExpression library would first be defined. We'll suppose that it is the version 1 of the package.

### VerbalExpression Ver.1

We suppose that there package's version management is done through directory management. In this case, we will suppose that VerbalExpression's developers would have a directory tree that looks as follow:

````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
````
VerbalExpression's developers then add declare two classes: VerbalExpression and VerbalExpressionBuilder.
````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
         |- VerbalExpression.batakjava
         |- VerbalExpression.batakjava 
````

**VerbalExpression.batakjava**
````
package ru.lanwen.verbalregex version 1;
public class VerbalExpression {
  public boolean testExact(String pToTest) {...}
}
````
**VerbalExpressionBuilder.batakjava**
````
package ru.lanwen.verbalregex version 1;
public class VerbalExpressionBuilder {
  public VerbalExpressionBuilder add(String pValue) {...}
}
````

If both pass the test check, two overview classes would be produced in the parent directory. The directory tree would look as follow:
````
main
|- batakjava-src
   |- ru.lanwen.verbalregex
      |- 1
      |  |- VerbalExpression.batakjava
      |  |- VerbalExpressionBuilder.batakjava
      |- VerbalExpression.overview
      |- VerbalExpressionBuilder.overview
````
**VerbalExpression.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpression {
  boolean testExact(String pToTest) in version 1;
}
````
**VerbalExpressionBuilder.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpressionBuilder {
  VerbalExpressionBuilder add(String pValue) in version 1;
  VerbalExpression build() in version 1;
}
````
The transpiler then produces Java files for from the .batakjava and .overview files. The resulting directory tree may look as follows:
````
main
|- batakjava-src
| |- ru.lanwen.verbalregex
|     |- 1
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- VerbalExpression.overview
|     |- VerbalExpressionBuilder.overview
|- java-src
   |- ru.lanwen.verbalregex
      |- VerbalExpression.java
      |- VerbalExpressionBuilder.java
      |- VerbalExpression_1.java
      |- VerbalExpressionBuilder_1.java
````
The name ending with *_1* denotes the class that was defined in the directory *ru/lanwen/verbalregex/1*, while those that don't have any ending are generated from the overview files.

### Guava Ver.28

Next we have the Guava library. The library is fully packed with many utility classes, but here I would only show the class that is intertwined in the dependency, namely the class *Splitter*. I will skip the directory tree explanation, because it works the same way as in VerbalExpression.

**Splitter.batakjava**
````
package com.google.common.base version 28;
import ru.lanwen.verbalregex requires version 1;

public class Splitter {
  public static Splitter on(VerbalExpression separator) {
    ... separator.testExact("") ...
    ... separator.getPattern() ...   
  }
}
````

**Splitter.overview**
````
package com.google.common.base;
import ru.lanwen.verbalregex;

overview class Splitter {
  version 1 requires ru.lanwen.verbalregex version 1;
  static Splitter on(VerbalExpression separator);
}
````

### Application Ver.1

Following that, we have the Application that uses both Guava and VerbalExpression. Let's suppose we have a class called *Test*.

````
package example version 1;
import ru.lanwen.verbalregex.* requires version 1;
import com.google.common.base.Splitter requires version 28;

public class Test {
  public static void main(String[] args) {
    VerbalExpression regex = new VerbalExpressionBuilder()....build();
    new Splitter(...).on(regex);
  }
}
````

### VerbalExpression Ver.2

Next the developer of VerbalExpression update the library. On their directory tree they add a new directory for the new version, so that it will look as follow:
````
main
|- batakjava-src
| |- ru.lanwen.verbalregex
|     |- 1
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- 2
|     |  |- VerbalExpression.batakjava
|     |  |- VerbalExpressionBuilder.batakjava
|     |- VerbalExpression.overview
|     |- VerbalExpressionBuilder.overview
|- java-src
   |- ...
````

**../2/VerbalExpression.batakjava**
````
package ru.lanwen.verbalregex version 2;
public class VerbalExpression {
  public boolean match(String pToTest) {...}    // new method
}
````

**../2/VerbalExpressionBuilder.batakjava**
````
package ru.lanwen.verbalregex version 2;
public class VerbalExpressionBuilder {
  public VerbalExpressionBuilder add(String pValue) {...}
  public VerbalExpressionBuilder append(String pValue) {...}    // new method
}
````
After both of these classes passed through type check, instead of creating two new overviews, because *VerbalExpression.overview* and *VerbalExpressionBuilder.overview* are already generated, those two overviews would be updated with the new functionalities that are introduced in the second version.

**VerbalExpression.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpression {
  boolean testExact(String pToTest) in version 1;
  boolean match(String pToTest) in version 2;
}
````
**VerbalExpressionBuilder.overview**
````
package ru.lanwen.verbalregex;
overview class VerbalExpressionBuilder {
  VerbalExpressionBuilder add(String pValue) in version 1, 2;
  VerbalExpressionBuilder append(String pValue) in version 2;
  VerbalExpression build() in version 1, 2;
}
````

### Application Ver. 2

Now that VerbalExpression is updated, we want to update our application to use the new VerbalExpression.

**../2/Test.batakjava**
````
package example version 2;
import ru.lanwen.verbalregex.* requires version 2;
import com.google.common.base.Splitter requires version 28;

public class Test {
  public static void main(String[] args) {
    VerbalExpression regexNG = new VerbalExpressionBuilder()... .append(...).build();    
    VerbalExpression regexOK = new VerbalExpressionBuilder()... .add(...).build();
    new Splitter(...).on(regexNG);        // result in type error
    new Splitter(...).on(regexOK);        // no problem 
  }
}
````

The first results in type error because when VersionExpressionBuilder invokes append, the context of the object is restricted to the version 2 of *ru.lanwen.verbalregex*, so the resulting object VerbalExpression would also be restricted to the version 2. When this is passed as an argument ot Splitter's method on invocation, this would be a problem because the class Splitter requires version 1 (as denoted by the priority declaration).
