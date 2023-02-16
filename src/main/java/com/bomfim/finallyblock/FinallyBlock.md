## Finally Block

The finally block is very important thing in Java, it ensures that this block will be even if an exception occurs on catch method.

The execution order is:

1. Try block
2. Catch block (in case of exception)
3. Finally Block (always)

> Note: The finally block may not execute if the JVM exits while the try or catch code is being executed.

### Try-with-resources

try-with-resources was introduced in Java 07 and brings the ability to close the object without the need for the final clause.

To use try-with-resources your class must implement AutoCloseable, we have some example like FileReader, BufferedReader.

1. Try-with-resources block
2. Close overridden method
3. Catch block (in case of exception)
4. Finally Block (always)

> Note: Using try-with-resource you should not use finally, because the resource will be closed automatically.

### References

* https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html
* https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html