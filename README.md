# BDD_Framework Execution Guide

Prerequisite
1. Install Java 8 or higher
2. Install eclipse
3. Install Testng in eclipse
4. Install maven



**1. Get clone URL from git**
![image](https://github.com/user-attachments/assets/f9597afd-5255-4259-9c38-5272f508b4c3)


**2.Import to eclipse as Maven project**

![image](https://github.com/user-attachments/assets/00b3f6e5-d651-4658-8931-60079b3bcd0c)


**3.Test Runner File**

![image](https://github.com/user-attachments/assets/d2c577c6-14e7-4167-8ac9-a826569b0a54)

**4.Test Steps**

![image](https://github.com/user-attachments/assets/6be1ef60-c4b1-4e47-9507-da2b00405101)


**5. Feature Files**

![image](https://github.com/user-attachments/assets/15cfd833-ca6b-406d-89a6-2eb60455786e)

![image](https://github.com/user-attachments/assets/12a23c25-5170-46c9-8bb8-c45f3f89c97a)


**6. Execution**

![image](https://github.com/user-attachments/assets/785e010b-72e0-4a0b-97aa-3a4b4d37a4d2)

Right click feature file and run as cucumber feature



**Summary of Implementation Technique**

**1. Behavior-Driven Development (BDD) with Cucumber**

Implementation Technique:

•	Uses .feature files written in Gherkin syntax to describe test scenarios in plain English.

•	Each scenario is mapped to a step definition method in Java.

•	Follows the format:

Given I have todos "Task A" and "Task B" marked as active

Why it’s used:

•	Promotes collaboration between developers, testers, and non-technical stakeholders.

•	Clear traceability from business requirements to automation.

________________________________________
**2. Test Automation with Selenium WebDriver**

Implementation Technique:

•	Automates UI interactions like typing into the input box, marking todos completed, filtering views, and validating UI elements.

•	Uses standard Selenium locators (By.className, By.cssSelector) to interact with the DOM.

Example in Java:

WebElement input = driver.findElement(By.className("new-todo"));

input.sendKeys("Task A");

input.sendKeys(Keys.ENTER);

Why it’s used:

•	Enables end-to-end validation of the web UI.

•	Mimics real user behavior in the browser.


 **3. Build & Dependency Management with Maven**

Implementation Technique:

•	All dependencies (cucumber-java, selenium-java, testng, etc.) are declared in pom.xml.

•	Ensures consistent builds and manages libraries centrally.

Why it’s used:

•	Streamlines dependency management.

•	Enables running tests using Maven commands (mvn test).

________________________________________
**4. Project Structure**

Folders:
/Feature         → contains feature files

/src             → contains step definitions

/RunnerTest.java → contains the Cucumber TestNG runner










