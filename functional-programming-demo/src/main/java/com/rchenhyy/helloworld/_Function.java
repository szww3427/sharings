package com.rchenhyy.helloworld;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/8
 */
public class _Function {

    // 函数合成
    public static void testCompose() {
        ArrayList<String> strings = Lists.newArrayList("a|b|c", "e|f|g", "i|j|k");

        Function<String, Iterable<String>> function = s -> Splitter.on("\\|").split(s);
        // concat, transform
        Iterable<String> concat = Iterables.concat(Iterables.transform(strings, function));
        // transformAndConcat
        FluentIterable.from(strings).transformAndConcat(function);
    }

    // 函数柯里化
    public static void testAddX() {
        // result = 6
        Integer result = AddX.of(2).apply(4);
    }

    static class AddX implements UnaryOperator<Integer> {
        private Integer x;

        AddX(Integer x) {
            this.x = x;
        }

        static AddX of(Integer x) {
            return new AddX(x);
        }

        @Override
        public Integer apply(Integer integer) {
            return x + integer;
        }
    }

    // ap函子
    static class AddXAP {
        private AddX function;

        AddXAP(AddX function) {
            this.function = function;
        }

        public static AddXAP of(AddX function) {
            return new AddXAP(function);
        }

        /*
         * public AddXAP one(Optional<Integer> optional) { return new AddXAP(AddX.of(optional.or(0))); } public AddXAP
         * two(Optional<Integer> optional) { return new AddXAP(AddX.of(optional.or(0))); }
         */
    }

    // ----------------------------------------------------------------------------------
    // 函子
    // @see FluentIterable

    // of 方法
    // FluentIterable.from

    // Maybe 函子 -- Optional
    // FluentIterable.first()

    // Either 函子 -- Optional.or(Optional secondChoice)

    // ap 函子
    // ?!

    // Monad 函子
    // FluentIterable.transformAndConcat

    // ----------------------------------------------------------------------------------

    // F -> T
    interface Transform<F, T> extends Function<F, Iterable<T>> {

    }

    // Iterable<? extends Iterable<? extends E> -> Iterable<E>
    interface Concat<E> extends Function<Iterable<? extends Iterable<? extends E>>, Iterable<E>> {

    }

    interface TransformAndConcat<F, T> extends Function<Iterable<F>, Iterable<T>> {

    }

    <E, F extends Iterable<E>, T> TransformAndConcat<F, T> transformAndConcat(Transform<F, T> toIterableFunction,
            Concat<T> concat) {
        return input -> {
            Iterable<Iterable<T>> iterables = Iterables.transform(input, toIterableFunction);
            return concat.apply(iterables);
        };
    }
}
