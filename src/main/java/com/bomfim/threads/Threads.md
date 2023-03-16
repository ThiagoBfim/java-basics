## Concurrency

Java is a multi-thread platform, which means that you can create multiple threads and execute multiple tasks at the same time.

This is a useful thing, but you can have concurrency problem because of it. 


### Synchronized

In Java, you can create methods that will be executed by a single thread. You can do that using the special keyword "syncronized"


### Thread-safe

Thread safe means that it's prepared for multi-thread, and will prevent concurrency problemns.

Example: Hashmap is not thread-safe\
ConcurrencyHashmap is thread-safe

### Race condition

A race condition is a concurrency problem that may occur inside a critical section. A critical section is a section of code that is executed by multiple threads and where the sequence of execution for the threads makes a difference in the result of the concurrent execution of the critical section.


## References

* [Race Conditions and Critical Sections](https://jenkov.com/tutorials/java-concurrency/race-conditions-and-critical-sections.html)

