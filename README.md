# Mutation Test Kata
Code kata: using mutation testing to improve quality of unit tests.

### Summary
This is a set of exercises that will demonstrate 
* That having passing unit tests and high unit test coverage numbers may be giving a false sense of security due to low quality of the tests
* how to identify problem spots using mutation testing and common test smells
* how to fix these problems 

### What Is a Code Kata?
A code kata is an exercise in programming which helps programmers hone their skills through practice. A code kata is usually set up as a series of unit tests, which fail. Your task is to write code to make them pass. The idea is inspired by the Japanese concept of kata in the martial arts. Just like in the martial arts, you can repeat a kata multiple times to make improvements to you solutions. 
 
Please note that this kata is a little different - all the tests initially pass. Don't worry, all the same ideas mentioned above still apply here. We will improve the tests, in the process making them fail, and then we will fix the code to make the tests pass, but by then they will be good tests. 

### Running This Kata
To build this kata you will need
* Java 8 or newer
* Maven 3.6.1 or newer
* an IDE of your choice

The domain for the kata is made up of two classes: Company and Employee.

1.  **Run all the unit tests in the `mtk.domain.CompanyTest` class.** They should all pass. Check the test coverage metrics using either maven output or a coverage reporting function in your IDE test runner. The coverage should be close to 100%. Good news: there are tests, they all pass, and they cover all of our business logic. Looks like the software is ready to ship!

    Unfortunately that would be a terrible idea as the code is full of bugs. To see the effects of some of the bugs, just run the `mtk.CompanyRunner.main()` method and looks at the output. What is going on? How can we have all these bugs despite having all these test?

2.  **Run the `org.pitest:pitest-maven:mutationCoverage` maven task** in the module `kata`. You can find this task under the `pitest` plugin in the `Plugins` section of your maven build file. To run it from the command line, execute the `mvn pitest:mutationCoverage` command. This task will use the PIT framework to introduce changes in the application code and then execute tests. The results are written in HTML format into a file in the `target/pit-reports/YYYYMMDDHHMI` directory. Open this file in a browser - you should see plenty of red. This means that some of the code mutations managed to survive - were not caught by the unit tests. Which means that in fact the unit tests we have do not test what they are supposed to.

3.  **Fix the test smells.** Each test in the test class exhibits one or more test smells. Going through the tests one by one, fix the smell and make sure the test actually does what it is supposed to. To help you, the comments in some of the test methods explicitly say what smell is present there. Once you remove the smell, the test should start failing. This is a good thing, because now we have tests that actually validate the behavior of our software. 

4.  **Fix the business logic**, to make the tests pass. Look at the comments in the code, they may explain its intended behavior (does not mean the method as written behaves as intended). 

5.   **Kill all mutants!** The tests that have been fixed this way should catch mutation introduced by PITest. When all the tests (and the logic under test) are fixed, no mutations should be able to survive. So the end state should be passing tests and dead mutants (and no smells).

The rest of this documents offers some general pointers, which may come in handy if you are new to unit testing.

------------------------------------------------
### Unit Tests - Necessary, But Not Sufficient...
...to build confidence in the software system under test. While our focus here is on unit tests, it helps to put them in a broader context. The table below lists common types of tests. 

The meaning of the columns:
* **Category** - A category of tests
* **Purpose** - Why is this kind of testing needed
* **Who** - Roles involved in creation of tests and validating test results  
* **Tools** - Example of tools supporting this type of testing
<table>
  <tr>
    <th>Category</th> <th>Purpose</th> <th>Who</th> <th>Tools</th>
  </tr>
  <tr>
    <td>Unit</td>
    <td>Validate a unit of behavior at the low (code) level, focusing on a small part of the system (e.g., a method)</td>
    <td>Dev</td>
    <td>JUnit</td>
  </tr>
  <tr>
    <td>Acceptance</td>
    <td>Validates that the business logic is implemented as specified for a given scenario</td>
    <td>Dev, User</td>
    <td>FitNesse</td>
  </tr>
  <tr>
    <td>Mutation</td>
    <td>Ensure quality of unit and acceptance tests</td>
    <td>Dev</td>
    <td>PITest</td>
  </tr>
  <tr>
    <td>Integration</td>
    <td>Detect issues in interactions between modules of the system</td>
    <td>Tech Ops, Dev</td>
    <td></td>
  </tr>
  <tr>
    <td>User Acceptance</td>
    <td>Certify by the users that the system as a whole is operating as expected</td>
    <td>Dev, User</td>
    <td></td>
  </tr>
  <tr>
    <td>Production Mirror</td>
    <td>Test system under load identical to production</td>
    <td>Tech Ops</td>
    <td></td>
  </tr>
  <tr>
    <td>Chaos Engineering</td>
    <td>Test system resiliency by failure injection into infrastructure (service processes, networks, clients, etc.)</td>
    <td>Tech Ops</td>
    <td>Chaos Monkey</td>
  </tr>
  <tr>
    <td>Breakpoint</td>
    <td>Determine the maximum amount of load that the system can support</td>
    <td>Tech Ops</td>
    <td>The Grinder</td>
  </tr>
</table>

### Unit Test Best Practices
These are some of the practices to follow to ensure that the unit tests are effective, easy to maintain, easy to execute:
* Automated - require no human involvement to determine the outcome
* Focused - each test method tests one scenario
* Complete - test the edge cases, try to cover all meaningfully different scenarios
* Well named - test method name describes the scenario being tested
* Fast - the relevant tests execute in a few seconds or faster  
* Independent - no external dependencies, no dependencies on other tests

### Unit Test Smells
These are the signs that there is possibly something wrong with the test - either because the test itself is not well written, or the code under test is not test friendly (which probably means that this code is not well factored):
* No assertions
* Irrelevant assertions 
* Use of Mocks
* Expected results are calculated rather than explicitly specified
* Test code reuse (that is test logic reuse, test utilities are good)
* Test data reuse
* "Flickering" tests(tests with nondeterministic behavior)
* Interdependencies between tests (e.g., execution order)
* Long running tests
* `@Ingore`'d or commented out tests

These smells often come together. For example, sharing test data can lead to tests' success depending on the execution order.

### Unit Test Quality
How can we measure the quality of the unit tests in a system? One metric, which is used broadly, is **test coverage**. Some things to keep in mind:
* Test coverage
    * %-ge of LOC, methods, classes covered by tests
    * Does not guarantee the covered code is actually tested
    * Does identify the code that is definitely not tested
* So having a coverage target in not entirely pointless, but...
* ...don't optimize just for coverage
* How do we make sure that the tests actually test? Mutation Testing is one way to ensure relevance of unit tests.

### Mutation Testing
Mutation testing is a way to validate the quality of unit tests. It means introducing changes in the code and observing the behavior of the unit tests. Assuming that all the tests were passing before the mutation, some of the unit tests will either start failing (good) or all the tests will keep on passing (bad). The latter scenario means that the unit tests do not really validate outcomes of the code under test: the results for all intents and purposes become random, yet all the tests pass.   

### Test Driven Development
Adopting Test Driven Development (TDD) will result in better tests, better interfaces, less unnecessary code, and more confident and steady development process. Just follow these steps:

* Write a test
    * Take the user's perspective: "What is the API that would make my job the easiest"
    * Think small increments
* Make the test pass
    * Do whatever it takes: Duplication? Fine! Hardcoding the expected result? Fine!
* Refactor
    * Remove duplication
* Repeat for all meaningfully different scenarios
* Reap the benefits
    * Almost all code is tested
    * You know when to stop coding
    * User friendly interfaces
    * Well factored, not overly abstracted code

Sounds too good to be true? The secret is that TDD does require a lot of discipline from its practitioners to work in tiny increments, diligently following the steps above, and not cutting corners. _Without the discipline_ it is possible to end up with tests (and code under tests) of the usual "quality". _With this discipline_, a good, clean, humane design and well enough factored code may just emerge. 

### Useful Links
* [JUnit](http://junit.org)
* [PITest](http://pitest.org)