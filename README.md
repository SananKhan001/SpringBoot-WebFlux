# Reactor Core Examples

## Mono and Flux Overview
- Mono: Represents a single value or an empty value (0 or 1 item).
- Flux: Represents a stream of values (0 to N items).

## Mono Operators

### Mono.just()

- Creates a Mono that emits the specified item.

```
Mono<String> mono = Mono.just("data");
mono.subscribe(System.out::println); // Output: data
```

### Mono.error()

- Creates a Mono that terminates with the specified error.

```
Mono.just("data")
.then(Mono.error(new RuntimeException("ERROR")))
.subscribe(System.out::println, System.err::println); // Output: java.lang.RuntimeException: ERROR
```

### zipWith()

- Combines the result from this Mono and another Mono into a Tuple2.
```
var m1 = Mono.just("First");
var m2 = Mono.just("Second");
Mono<Tuple2<String, String>> tuple2Mono = m1.zipWith(m2);

tuple2Mono.subscribe(values -> {
System.out.println(values.getT1()); // Output: First
System.out.println(values.getT2()); // Output: Second
});
```

### map()

- Transforms the value emitted by the current Mono using a synchronous function.

```
var m1 = Mono.just("First Project working");
Mono<String> changeMono = m1.map(item -> item.toUpperCase());
changeMono.subscribe(System.out::println); // Output: FIRST PROJECT WORKING
```

### flatMap()

- Transforms the value emitted by the current Mono asynchronously, returning the value emitted by another Mono.

```
var m1 = Mono.just("First");
var m2 = Mono.just(124);
Mono<String> integerMono = m1.flatMap(item1 -> m2.map(item2 -> item2.toString().concat(item1)));
integerMono.subscribe(System.out::println); // Output: 124First
```

### flatMapMany()

- Transforms this Mono into a Publisher, then returns a Flux.

```
var m1 = Mono.just("First Project working");
Flux<String> flux = m1.flatMapMany(item -> Flux.just(item, "second string"));
flux.subscribe(System.out::println); // Output: First Project working, second string
```

### concatWith()

- Joins two Mono instances and provides a Flux.

```
var m1 = Mono.just("First Project working");
Flux<String> flux = m1.concatWith(Mono.just("second string"));
flux.subscribe(System.out::println); // Output: First Project working, second string
```

## Flux Operators

### map()

- Transforms each element of the Flux.

```
Flux<Integer> flux = Flux.just(1, 2, 3).map(i -> i * 2);
flux.subscribe(System.out::println); // Output: 2, 4, 6
```

### filter()

- Filters elements based on a predicate.

```
Flux<Integer> flux = Flux.just(1, 2, 3).filter(i -> i % 2 == 0);
flux.subscribe(System.out::println); // Output: 2
```

### flatMap()

- Transforms each element into a Publisher and merges them.

```
Flux<String> flux = Flux.just("a", "b").flatMap(s -> Flux.just(s.toUpperCase(), s + s));
flux.subscribe(System.out::println); // Output: A, aa, B, bb
```

### concatMap()

- Transforms each element into a Publisher and concatenates them, preserving the order.

```
Flux<String> flux = Flux.just("a", "b").concatMap(s -> Flux.just(s.toUpperCase(), s + s));
flux.subscribe(System.out::println); // Output: A, aa, B, bb
```

### delayElements()

- Delays each element.

```
Flux<Integer> flux = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));
flux.subscribe(System.out::println); // Delays the output: 1, 2, 3
```

### transform()

- Applies a transformation function to the Flux.

```
Flux<Integer> flux = Flux.just(1, 2, 3).transform(f -> f.map(i -> i * 2));
flux.subscribe(System.out::println); // Output: 2, 4, 6
```

### defaultIfEmpty()

- Provides a default value if the Flux is empty.

```
Flux<Integer> flux = Flux.<Integer>empty().defaultIfEmpty(10);
flux.subscribe(System.out::println); // Output: 10
```

### switchIfEmpty()

- Switches to a new Flux if the original is empty.

```
Flux<Integer> flux = Flux.<Integer>empty().switchIfEmpty(Flux.just(4, 5, 6));
flux.subscribe(System.out::println); // Output: 4, 5, 6
```

### concat()

- Concatenates multiple Flux instances.

```
Flux<Integer> flux1 = Flux.just(1, 2);
Flux<Integer> flux2 = Flux.just(3, 4);
Flux<Integer> flux = Flux.concat(flux1, flux2);
flux.subscribe(System.out::println); // Output: 1, 2, 3, 4
```

### merge()

- Merges multiple Flux instances, interleaving their values.

```
Flux<Integer> flux1 = Flux.just(1, 2);
Flux<Integer> flux2 = Flux.just(3, 4);
Flux<Integer> flux = Flux.merge(flux1, flux2);
flux.subscribe(System.out::println); // Output: 1, 3, 2, 4
```

### zip()

- Combines elements from multiple Flux instances into a Tuple.

```
Flux<String> flux1 = Flux.just("a", "b");
Flux<Integer> flux2 = Flux.just(1, 2);
Flux<String> result = Flux.zip(flux1, flux2, (s, i) -> s + i);
result.subscribe(System.out::println); // Output: a1, b2
```

### Side-Effect Operators

#### doOnNext()

- Performs a side-effect when the Flux emits an item.

```
Flux<Integer> flux = Flux.just(1, 2, 3).doOnNext(i -> System.out.println("Next: " + i));
flux.subscribe(System.out::println); // Output: Next: 1, 1, Next: 2, 2, Next: 3, 3
```

#### doOnSubscribe()

- Performs a side-effect when the Flux is subscribed to.

```
Flux<Integer> flux = Flux.just(1, 2, 3).doOnSubscribe(s -> System.out.println("Subscribed"));
flux.subscribe(System.out::println); // Output: Subscribed, 1, 2, 3
```