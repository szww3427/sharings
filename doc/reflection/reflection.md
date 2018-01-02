[TODO] Class.getName
[TODO] strictfp
[TODO] java.lang.reflect.TypeVariable
[TODO] Field.isEnumConstant()
[TODO] toGenericString()

## Classes

### Retrieving Class Objects

* **Object.getClass()**
    This only works for reference types which all inherit from Object.

* **The .class Syntax**

* **Class.forName()**
    If the fully-qualified name of a class is available, it is possible to get the corresponding Class using the static method `Class.forName()`.
    The syntax for names of array classes is described by `Class.getName()`. This syntax is applicable to references and primitive types.
    
    ![Class_getName](./Class_getName.png)

* **TYPE Field for Primitive Type Wrappers**
    Each of the primitive types and void has a wrapper class in java.lang that is used for boxing of primitive types to reference types.
    Each wrapper class contains a field named TYPE which is equal to the Class for the primitive type being wrapped.

* **Methods that Return Classes**
    There are several Reflection APIs which return classes but these may only be accessed if a Class has already been obtained either directly or indirectly.
    
    `Class.getSuperclass()`
    `Class.getClasses()`
    `Class.getDeclaredClasses()`
    `Class.getDeclaringClass()`, `Member.getDeclaringClass()` (Member: Field, Method, Constructor)

    Returns the Class in which these members were declared. Anonymous Class Declarations will not have a declaring class but will have an enclosing class.
    
    `Class.getEnclosingClass()`
    
    Returns the immediately enclosing class of the class.
    
### Examining Class Modifiers and Types
A class may be declared with one or more modifiers which affect its runtime behavior:

* Access modifiers: public, protected, and private
* Modifier requiring override: abstract
* Modifier restricting to one instance: static
* Modifier prohibiting value modification: final
* Modifier forcing strict floating point behavior: strictfp
* Annotations

Not all modifiers are allowed on all classes, for example an interface cannot be final and an enum cannot be abstract. java.lang.reflect.Modifier contains declarations for all possible modifiers. It also contains methods which may be used to decode the set of modifiers returned by Class.getModifiers().

**@see ClassDeclarationSpy**

### Discovering Class Members
There are two categories of methods provided in Class for accessing fields, methods, and constructors: methods which _enumerate these members_ and methods which _search for particular members_.
Also there are distinct methods for _accessing members declared directly on the class_ versus methods which _search the superinterfaces and superclasses for inherited members_.

|Field|Method|Constructor|
|---|---|---|
|getDeclaredField()|getDeclaredMethod()|getDeclaredConstructor()|
|getField()|getMethod()|getConstructor()|
|getDeclaredFields()|getDeclaredMethods()|getDeclaredConstructors()|
|getFields()|getMethods()|getConstructors()|

Tips: 
getDeclaredXXX - access members declared directly on the class (including `private` members)
getXXX - search the superinterfaces and superclasses for inherited members (no `private` members)

**@see ClassSpy**

### Troubleshooting
[TODO]
http://docs.oracle.com/javase/tutorial/reflect/class/classTrouble.html


## Members

### Fields

#### Obtaining Field Types

A field may be either of primitive or reference type.

> getType() vs getGenericType(): 
> `Field.getGenericType()` will consult the Signature Attribute in the class file if it's present. 
> If the attribute isn't available, it falls back on Field.getType() which was not changed by the introduction of generics.
> The other methods in reflection with name **getGenericFoo** for some value of Foo are implemented similarly.

**@see FieldSpy**

#### Retrieving and Parsing Field Modifiers

There are several modifiers that may be part of a field declaration:

* Access modifiers: public, protected, and private
* Field-specific modifiers governing runtime behavior: transient and volatile
* Modifier restricting to one instance: static
* Modifier prohibiting value modification: final
* Annotations

The method Field.getModifiers() can be used to return the integer representing the set of declared modifiers for the field. 
The bits representing the modifiers in this integer are defined in java.lang.reflect.Modifier.
_`Field.isSynthetic()`_: tells whether the located field is synthetic (compiler-generated)
_`Field.isEnumConstant()`_: tells whether the located field is an enum constant

**@see FieldModifierSpy**

> Notice that some fields are reported even though they are not declared in the original code. This is because the compiler will generate some synthetic fields which are needed during runtime. To test whether a field is synthetic, the example invokes `Field.isSynthetic()`. 
> The set of synthetic fields is compiler-dependent; however commonly used fields include `this$0` for inner classes (i.e. nested classes that are not static member classes) to reference the outermost enclosing class and `$VALUES` used by enums to implement the implicitly defined static method values().
> The names of synthetic class members are not specified and may not be the same in all compiler implementations or releases. These and other synthetic fields will be included in the array returned by `Class.getDeclaredFields()` but not identified by `Class.getField()` since synthetic members are not typically public.
> Because Field implements the interface java.lang.reflect.AnnotatedElement, it is possible to retrieve any runtime annotation with java.lang.annotation.RetentionPolicy.RUNTIME. For an example of obtaining annotations see the section Examining Class Modifiers and Types.

#### Getting and Setting Field Values

Given an instance of a class, it is possible to use reflection to set the values of fields in that class. This is typically done only in special circumstances when setting the values in the usual way is not possible. 
Because such access usually violates the design intentions of the class, it should be used with the utmost discretion.

> Note: 
> Setting a field's value via reflection has a certain amount of performance overhead because various operations must occur such as validating access permissions. From the runtime's point of view, the effects are the same, and the operation is as atomic as if the value was changed in the class code directly.

#### Troubleshooting
[TODO]
http://docs.oracle.com/javase/tutorial/reflect/member/fieldTrouble.html

### Methods

#### Obtaining Method Type Information

A method declaration includes the name, modifiers, parameters, return type, and list of throwable exceptions. 
The java.lang.reflect.Method class provides a way to obtain this information.

**@see MethodSpy**

> Note: 
> Method.getGenericExceptionTypes() exists because it is actually possible to declare a method with a generic exception type. However this is rarely used since it is not possible to catch a generic exception type.

#### Obtaining Names of Method Parameters
     
You can obtain the names of the formal parameters of any method or constructor with the method java.lang.reflect.Executable.getParameters. (The classes Method and Constructor extend the class Executable and therefore inherit the method Executable.getParameters.)
However, .class files do not store formal parameter names by default. This is because many tools that produce and consume class files may not expect the larger static and dynamic footprint of .class files that contain parameter names. In particular, these tools would have to handle larger .class files, and the Java Virtual Machine (JVM) would use more memory. 
In addition, some parameter names, such as secret or password, may expose information about security-sensitive methods.

To store formal parameter names in a particular .class file, and thus enable the Reflection API to retrieve formal parameter names, compile the source file with the **_-parameters_** option to the javac compiler.

**@see MethodParameterSpy**

`getType`: Returns a Class object that identifies the declared type for the parameter.
`getName`: Returns the name of the parameter. If the parameter's name is present, then this method returns the name provided by the .class file. Otherwise, this method synthesizes a name of the form **_argN_**, where N is the index of the parameter in the descriptor of the method that declares the parameter.
`getModifiers`: Returns an integer that represents various characteristics that the formal parameter possesses.

|Value (in decimal)|Value (in hexadecimal)|Description|
|---|---|---|
|16	|0x0010	|The formal parameter is declared final|
|4096	|0x1000	|The formal parameter is synthetic. Alternatively, you can invoke the method isSynthetic.|
|32768	|0x8000	|The parameter is implicitly declared in source code. Alternatively, you can invoke the method isImplicit.|

`isImplicit`: Returns true if this parameter is implicitly declared in source code. See the section Implicit and Synthetic Parameters for more information.
`isNamePresent`: Returns true if the parameter has a name according to the .class file.
`isSynthetic`: Returns true if this parameter is neither implicitly nor explicitly declared in source code.

* **Implicit and Synthetic Parameters**
    
    1) Certain constructs are implicitly declared in the source code if they have not been written explicitly (i.e. the default constructor).
    Consider the following excerpt from `MethodParameterExamples`:

        public class MethodParameterExamples {
            public class InnerClass { }
        }
    
    The class `InnerClass` is a non-static nested class or inner class. A constructor for inner classes is also implicitly declared. However, this constructor will contain a parameter. 
    When the Java compiler compiles InnerClass, it creates a .class file that represents code similar to the following:

        public class MethodParameterExamples {
            public class InnerClass {
                final MethodParameterExamples parent;
                InnerClass(final MethodParameterExamples this$0) {
                    parent = this$0; 
                }
            }
        }
        
    The `InnerClass` constructor contains a parameter whose type is the class that encloses `InnerClass`, which is `MethodParameterExamples`.

    2) Constructs emitted by a Java compiler are marked as synthetic if they do not correspond to a construct declared explicitly or implicitly in source code, unless they are class initialization methods.
    Consider the following excerpt from `MethodParameterExamples`:
    
        public class MethodParameterExamples {
            enum Colors {
                RED, WHITE;
            }
        }

    When the Java compiler encounters an enum construct, it creates several methods that are compatible with the .class file structure and provide the expected functionality of the enum construct. For example, the Java compiler would create a .class file for the enum construct Colors that represents code similar to the following:
    
        final class Colors extends java.lang.Enum<Colors> {
            public final static Colors RED = new Colors("RED", 0);
            public final static Colors BLUE = new Colors("WHITE", 1);
         
            private final static values = new Colors[]{ RED, BLUE };
         
            private Colors(String name, int ordinal) {
                super(name, ordinal);
            }
         
            public static Colors[] values(){
                return values;
            }
         
            public static Colors valueOf(String name){
                return (Colors)java.lang.Enum.valueOf(Colors.class, name);
            }
        }
    
    The Java compiler creates three constructors and methods for this enum construct: Colors(String name, int ordinal), Colors[] values(), and Colors valueOf(String name). The methods values and valueOf are implicitly declared. Consequently, their formal parameter names are implicitly declared as well.
    The enum constructor Colors(String name, int ordinal) is a default constructor and it is implicitly declared. However, the formal parameters of this constructor (name and ordinal) are not implicitly declared. Because these formal parameters are neither explicitly or implicitly declared, they are synthetic. (The formal parameters for the default constructor of an enum construct are not implicitly declared because different compilers need not agree on the form of this constructor; another Java compiler might specify different formal parameters for it. When compilers compile expressions that use enum constants, they rely only on the public static fields of the enum construct, which are implicitly declared, and not on their constructors or how these constants are initialized.)
    
**@see MethodParameterExamples**

#### Retrieving and Parsing Method Modifiers 
There a several modifiers that may be part of a method declaration:

* Access modifiers: public, protected, and private
* Modifier restricting to one instance: static
* Modifier prohibiting value modification: final
* Modifier requiring override: abstract
* Modifier preventing reentrancy: synchronized
* Modifier indicating implementation in another programming language: native
* Modifier forcing strict floating point behavior: strictfp
* Annotations

Following methods display whether a method is synthetic (compiler-generated), of variable arity, or a bridge method (compiler-generated to support generic interfaces).
`Method.isSynthetic()`
`Method.isVarArgs()`
`Method.isBridge()`

For example:

    public int compareTo(String anotherString);

During type erasure, the argument type of the inherited method Comparable.compareTo() is changed from java.lang.Object to java.lang.String. 
Since the parameter types for the compareTo methods in Comparable and String no longer match after erasure, overriding can not occur. In all other circumstances, this would produce a compile-time error because the interface is not implemented. 
The addition of the **bridge** method avoids this problem.

**@see MethodModifierSpy**

> Note: 
> Method implements java.lang.reflect.AnnotatedElement. Thus any runtime annotations with java.lang.annotation.RetentionPolicy.RUNTIME may be retrieved. For an example of obtaining annotations see the section Examining Class Modifiers and Types.

#### Invoking Methods

Methods are invoked with java.lang.reflect.Method.invoke(). 
The first argument is the object instance on which this particular method is to be invoked. (If the method is static, the first argument should be null.) Subsequent arguments are the method's parameters. 
If the underlying method throws an exception, it will be wrapped by an `java.lang.reflect.InvocationTargetException`. The method's original exception may be retrieved using the exception chaining mechanism's `InvocationTargetException.getCause()` method.

* **Finding and Invoking a Method with a Specific Declaration**

* **Invoking Methods with a Variable Number of Arguments**
    

### Constructors



