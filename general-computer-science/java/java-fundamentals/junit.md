**Module Objectives**

- List the core Assert methods available to use and explain their purpose
- Write unit tests using JUnit 4
- Use the various Assert statements to write tests
- List the 5 key annotations and explain what they do

**Unit Testing Overview**

- Why test?
  - To determine if the code works correctly under ideal conditions
  - To determine if the code fails correctly under error conditions

- What is a “unit”?
  - The smallest testable piece of code
  - Unit testing is the process of ensuring that the smallest testable parts of an application (“units”) work as expected.

- Tests are methods within a class dedicated to tests
  - Each tests one unit
  - Each works by asserting certain states

- Tests should be:
  - Simple
  - Focused
  - Independent
  - Flexible
  - Easy to read

- Tests should follow good naming conventions

**JUnit**

- xUnit is a common framework that exists for most languages
  - JUnit is the testing framework for Java

- To use JUnit:
  - Retrieve the JUnit JAR file
  - Use the following to compile and run the tests on the command line:

**Annotations**

- @Test
  - Indicates that a method is a test
- @Before
  - The annotated method should be run before each test
- @BeforeClass
  - The annotated method should be run once before any tests
- @After
  - The annotated method should be run after each test
- @AfterClass
  - The annotated method should be run once after all tests

**Assertions**

- Assertions are methods which verify whether a test should pass or fail:
  - assertEquals(expectedObject, actualObject)
  - assertTrue(boolean)
  - assertNull(object)
  - assertFalse(boolean)
  - assertNotNull(object)
  - @Test(expected=CustomException.class)

- There are many others – check the JUnit documentation.

**Emergent Design**

- Waterfall development
  - Design is done up front
  - Functionality is implemented after all design is done
- Agile
  - Design occurs at the same time as testing
  - All aspects are done concurrently 

- TDD is Agile:
  - Tests are written first
  - Focuses and directs effort to produce value
  - Encourages emergent design

**TDD**

- Five steps:
  - Write the test
  - Make the test compile
  - Watch the test fail
  - Do just enough to make all tests pass
  - Refactor and generalize
- Arrange-Act-Assert is a pattern for writing unit tests:
  - Arrange – Set up preconditions
  - Act – Call the code under test
  - Assert –  Check that expected results have occurred

**Module Review**

- What is JUnit?
- What are the signs of a good test?
- What are the different Annotations and what do they do?
- What are the different Assertions and their behaviour?
- What are the 5 steps of TDD?
- What is the most important part of a test method?













