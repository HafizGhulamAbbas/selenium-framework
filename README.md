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
1. After action, if reload new page, then return the object of that page --> **Fluent Interface**