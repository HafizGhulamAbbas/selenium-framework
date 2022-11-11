# selenium-framework

### Core Principles
1. Less is more
2. Clear intention
3. Independent
4. Good vs bad rather than Right vs wrong or Best vs worse
5. The code will tell


###### Let's have a look on the existing code, find bad practices and check how can we remove the those bad practices. 

### Remove Bad Practices Checklist
- [ ] Non-atomic test (Add to cart, login and checkout should not interdependent) —> Discrete tests
- [ ] Code duplication
- [ ] Duplicate element definitions —> POM
- [ ] User & Application state dependencies (login & checkout | only checkout) —> Create state
- [ ] Non-readable tests —> POM & Good naming convention
- [ ] Duplicate driver initialisation code —> TestNG Annotations
- [ ] Static sleeps —> Explicit waits
- [ ] Hardcoded test data —> Constants / Enums
- [ ] Hardcoded static text —> XMLs
- [ ] Lacking multiple browser support —> System parameters
- [ ] Lacking multiple environment support
- [ ] Hardcoded driver executable path and manual driver management —> Auto discovery
- [ ] Stale browser instances (driver not quit if test case fail)

###### Last, but not the least:

1. The selenium imports should not be in test cases
2. Test cases should describe what it is testing, not how it is testing
3. Test cases should be changed only when requirement is change, not the implementation

### Framework Objectives
- Readable, Maintainable, and Scalable
- DRY - Don’t Repeat Yourself
- SRP - Single Responsibility Principle
- OOP - Encapsulation, Inheritance, Composition and Interface and polymorphism
- POM - Page Object Model

### POM (Page Object Model) Design
- Each page is represented by a class
- Contains the UI element definitions and the methods for user actions
- Fluent Interface and builder pattern

**Advantages:**
- Encapsulation
- Some level of SRP
- Reusability
- Readability
- Low maintenance

**Thumb rules:**
- Good names
- Private variables
- No assertion (expect for asserting the page title)
- No other task other than the user action
- Avoid bulky page objects



### Instructions
1. Keep the BasePage and BaseTest as simple as can

### Concepts
###### Fluent Interface and Builder Pattern

On HomePage, when click storeMenuLink, it loads the store page. We’re no more on the HomePage. And for the store page, we can create the object explicitly in our test class. But, if you know the behaviour of application. And if it is going to remain same every time, that is, every time the store page is going to load, then instead of explicitly creating the store page object, what we can do it after clicking the store menu link, we pass the handle to store page. Basically, we can return StorePage object through clickStoreMenuLink function. This is called Fluent Interface. This makes the code more readable.

##### Conclusion:
- If after action (like clicking a link), reload a new page —> return that page in the function —> Fluent Interface (Tight coupling among pages. Some people doesn’t like)
- If after action (like clicking a show button), stay on the same page —> don’t do anything the function —> Builder method

### Execution via Maven Command
`mvn clean test -Dbrowser=Chrome`

### Adding TestNG XML file
https://testng.org/doc/documentation-main.html#testng-xml
