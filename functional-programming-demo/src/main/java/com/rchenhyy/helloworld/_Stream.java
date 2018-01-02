package com.rchenhyy.helloworld;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/8/3
 */
public class _Stream {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public final String sampleFile = "/Users/rchenhyy/Documents/@Work/sharings/functional-programming-demo/src/main/java/com/rchenhyy/helloworld/_Stream.java";

    // -------------------------------------
    // intermediate op
    // -------------------------------------
    @Test
    public void map() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = ints.stream().map(n -> n * n).collect(Collectors.toList());
        squareNums.forEach(System.out::println);
    }

    @Test
    public void flatMap() {
        List<List<Integer>> lists = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<List<Integer>> listStream = lists.stream();
        Stream<Integer> integerStream = listStream.flatMap(Collection::stream); // concat
        integerStream.forEach(System.out::println);
    }

    @Test
    public void filter() {
        String path = sampleFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Stream<String> lines = reader.lines();
            Stream<String> words = lines.flatMap(line -> Stream.of(line.split("\\s"))) // transform and concat
                    .filter(word -> word.length() > 3);
            words.forEach(System.out::println);
        } catch (IOException e) {
            logger.error("io exception", e);
        }
    }

    // -------------------------------------
    // terminal op
    // -------------------------------------
    @Test
    public void forEach() {
        Stream.of("one", "two", "three", "four", "five").filter(e -> e.length() > 3)
                .peek(e -> System.out.printf("Filtered value: %s\n", e)).map(String::toUpperCase)
                .peek(e -> System.out.printf("Mapped value: %s\n", e)).collect(Collectors.toList());
    }

    @Test
    public void findFirst() {
        Optional<String> optional = Stream.of("a BBB c DDD".split("\\s")).findFirst();
        optional.ifPresent(System.out::println);
    }

    @Test
    public void reduce() {
        Optional<Integer> sum = Stream.of(1, 2, 3, 99, 100, 9999, 10000).filter(num -> num % 2 == 0)
                .reduce(Integer::sum);
        sum.ifPresent(System.out::println);
    }

    @Test
    public void limitSkip() {
        Stream<Integer> stream = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.limit(8).skip(2)/* .limit(3) */.forEach(System.out::println); // (limit + skip) vs (skip + limit)
    }

    @Test
    public void sorted() {
        Stream<Integer> stream = Stream.of(0, 0, 0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream.limit(8).distinct().sorted((a, b) -> b - a).forEach(System.out::println); // 逆序输出
    }

    @Test
    public void minMax() {
        String path = sampleFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            OptionalInt max = reader.lines().mapToInt(String::length).max();
            max.ifPresent(System.out::println);
        } catch (IOException e) {
            logger.error("io exception", e);
        }
    }

    @Test
    public void distinct() {
        String path = sampleFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Stream<String> sorted = reader.lines().flatMap(line -> Stream.of(line.split("\\s")))
                    .filter(word -> word.length() > 4).map(String::toLowerCase).distinct().sorted();
            sorted.forEach(System.out::println);
        } catch (IOException e) {
            logger.error("io exception", e);
        }
    }

    @Test
    public void match() {
        class Person {
            private String name;
            private int age;

            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("name" + 1, 10));
        persons.add(new Person("name" + 2, 21));
        persons.add(new Person("name" + 3, 34));
        persons.add(new Person("name" + 4, 6));
        persons.add(new Person("name" + 5, 55));

        boolean allMatch = persons.stream().allMatch(p -> p.getAge() >= 18);
        System.out.println("All are adult? " + allMatch);
        boolean anyMatch = persons.stream().anyMatch(p -> p.getAge() <= 12);
        System.out.println("Any child? " + anyMatch);
    }

    // -------------------------------------
    // advanced: generating streams
    // -------------------------------------
    @Test
    public void generate() {
        Supplier<Integer> randomInt = new Supplier<Integer>() {
            int index;
            Random seed = new Random();

            @Override
            public Integer get() {
                index++; // stateful
                return seed.nextInt();
            }
        };

        Stream.generate(randomInt).limit(100).forEach(System.out::println); // must use limit-ops to terminate the
                                                                            // stream
    }

    @Test
    public void iterate() {
        Stream.iterate(2, n -> n * 2).limit(10).forEach(System.out::println); // like reduce, but use a UnaryOperator to
                                                                              // calculate next value
    }

    // -------------------------------------
    // advanced: reduction with Collectors
    // java.util.stream.Collectors 类的主要作用就是辅助进行各类有用的 reduction 操作，例如转变输出为 Collection，把 Stream 元素进行归组。
    // -------------------------------------
    @Test
    public void Collectors_groupingBy() {
        ArrayList<BeanX> list = Lists.newArrayList(new BeanX(1, "abc"), new BeanX(2, "efg"), new BeanX(3, "ijk"),
                new BeanX(3, "xxx"), new BeanX(2, "2333"), new BeanX(2, "brilliant"));

        Map<Integer, List<BeanX>> map = list.stream().collect(Collectors.groupingBy(BeanX::getId));
        map.entrySet().forEach(System.out::println);
    }

    @Test
    public void Collectors_partitioningBy() throws InterruptedException {
        ArrayList<BeanX> list = Lists.newArrayList(new BeanX(1, "abc"), new BeanX(2, "efg"), new BeanX(3, "ijk"),
                new BeanX(3, "xxx"), new BeanX(2, "2333"), new BeanX(2, "brilliant"));

        Map<Boolean, List<BeanX>> xMap = list.stream().collect(Collectors.partitioningBy(x -> x.getId() % 2 == 0));
        xMap.get(true).forEach(System.out::println);
        Thread.sleep(3000);
        xMap.get(false).forEach(System.out::println);
    }

    static class BeanX {
        int id;
        String value;

        public BeanX(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public BeanX setId(int id) {
            this.id = id;
            return this;
        }

        public String getValue() {
            return value;
        }

        public BeanX setValue(String value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BeanX{");
            sb.append("id=").append(id);
            sb.append(", value='").append(value).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
