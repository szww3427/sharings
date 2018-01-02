# Functional Programming #


### Functional Programming

Functional programming is a practical implementation of Alonzo Church's ideas. Not all lambda calculus ideas transform to practice because lambda calculus was not designed to work under physical limitations. Therefore, like object oriented	programming, functional programming is a set of ideas, not a set of strict guidelines.

Lambda calculus was designed to investigate problems related to calculation. Functional programming, therefore, primarily deals with calculation, and, surprisingly, uses functions to do so. A function is a very basic unit in functional programming. Functions are used for almost everything, even the simplest of calculations. Even variables are replaced with functions.

It turns out that functional programs can keep state, except they don't use variables to do it. They use functions instead. The state is kept in function parameters, on the stack.

When Alonzo was working on lambda calculus he wasn't interested in maintaining state over periods of time in order to modify it later. He was interested in performing operations on data (also commonly referred to as "calculating stuff"). However, it was proved that lambda calculus is equivalent to a Turing machine. It can do all the same things an imperative programming language can.

    String reverse(String arg) {
        if(arg.length == 0) {
            return arg;
        }
        else {
            return reverse(arg.substring(1, arg.length)) + arg.substring(0, 1);
        }
    }

### Benefits of FP

#### Unit Testing

Since every symbol in FP is final, no function can ever cause side effects. You can never modify things in place, nor can one function modify a value outside of its scope for another function to use (like a class member or a global variable). That means that the only effect of evaluating a function is its return value and the only thing that affects the return value of a function is its arguments.

#### Debugging

You will always be able to reproduce your problem because a bug in a functional program doesn't depend on unrelated code paths that were executed before it.

Once you reproduce the problem, getting to the bottom of it is trivial. It is almost pleasant. You break the execution of your program and examine the stack.
Walking through the stack you look at arguments passed to functions and their return values. The minute a return value doesn't make sense you step into the offending function and walk through it. You repeat this recursively until the process leads you to the source of the bug!

#### Concurrency

A functional program is ready for concurrency without any further modifications. You never have to worry about deadlocks and race conditions because you don't need to use locks! No piece of data in a functional program is modified twice by the same thread, let alone by two different threads.

    String s1 = somewhatLongOperation1();
    String s2 = somewhatLongOperation2();
    String s3 = concatenate(s1, s2);

#### Hot Code Deployment

In a functional program all state is stored on the stack in the arguments passed to functions. This makes hot deployment significantly easier!

#### Machine Assisted Proofs and Optimizations

...

### Higher Order Functions

Functions that operate on other functions (accept them as arguments) are called higher order functions. Don't let this term intimidate you, it's no different from Java classes that operate on each other (we can pass class instances to other classes).
We don't restrict ourselves to class hierarchies: we can pass new functions at runtime and change them at any time with a much higher degree of granularity with less code.

### Currying

Currying is used very often to adapt functions to an interface that someone else expects.

    # in pure FP
    square = int pow(int i, 2);
    # in Java
    class square_function_t {
        int square(int i) {
            return pow(i, 2);
        }
    }
    square_function_t square = new square_function_t();

### Lazy Evaluation

Lazy (or delayed) evaluation is an interesting technique that becomes possible once we adopt a functional philosophy. 

    String s1 = somewhatLongOperation1();
    String s2 = somewhatLongOperation2();
    String s3 = concatenate(s1, s2);

In an imperative language the order of evaluation would be clear. Because each function may affect or depend on an external state it would be necessary to execute them in order: first somewhatLongOperation1, then somewhatLongOperation2, followed by concatenate. Not so in functional languages.

Lazy evaluation has numerous advantages as well as disadvantages.

Advantages: 

* Optimization
* Abstracting Control Structures
* Infinite Data Structures (Because the language is lazy, only the necessary parts of the list that are actually used by the program are ever evaluated.) 

Disadvantages: 

In a lazy language you have no guarantee that the first line will be executed before the second! This means we can't do IO, can't use native functions in any meaningful way (because they need to be called in order since they depend on side effects), and can't interact with the outside world!

## Continuations

...

## Closures

While an implementation of an axiomatic system is useful because it allows thinking about programs in terms of mathematical expressions, it may or may not always be practical. Many languages choose to incorporate functional elements without strictly adhering to functional doctrine.

Passing functions around in impure languages is a little bit different than doing it in the confines of lambda calculus and requires support for an interesting feature often referred to as lexical closure.
A closure, then, is implemented just like a function we discussed earlier, except that it has an additional reference to the surrounding variables.
Closures bring functional and OO worlds closer together. Every time you create a class that holds some state and pass it to somewhere else, think of closures. A closure is just an object that creates "member variables" on the fly by grabbing them from the scope, so you don't have to!

## What's more

In the future I plan to write about category theory, monads, functional data structures, type systems in functional languages, functional concurrency, functional databases and much more.

--- 

Stream API (c.f. concurrency in jdk8)

GroupingUtil