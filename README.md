EndtoEnd-ECommerce

📝 Project Description

This project provides an automated end-to-end testing suite for an e-commerce website. The tests are designed to simulate real user journeys, such as browsing products, adding items to the cart, and completing the checkout process. We use Selenium WebDriver to interact with the web elements and ensure that critical functionalities work as expected from a user's perspective.

🚀 Getting Started

These instructions will get a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
You'll need to have the following installed on your system:

Java Development Kit (JDK): Version 1.8 or higher.

Maven: To manage project dependencies.

A web browser for testing (e.g., Chrome, Firefox, Edge).

The corresponding Selenium WebDriver for your chosen browser.

Installation

1- Clone the repository: 
git clone https://github.com/your-username/EndtoEnd-ECommerce.git
cd EndtoEnd-ECommerce


2- Install dependencies: 
    mvn clean install

    
⚙️ Running Test Profiles
This project uses Maven profiles to run different test suites. This allows you to selectively execute tests based on your needs, such as a full regression suite or a quick validation run.

Regression Profile: Use this profile to run the complete suite of regression tests.

Bash

mvn test -PRegression
Validation Profile: This profile is for a specific set of validation tests. Note that these tests are designed to deliberately fail to showcase certain scenarios.

Bash

mvn test -PValidation

🚀 What's Next
Here's a look at the future plans for this project:

Expand Negative Test Scenarios: We will add more negative tests and create dedicated profiles to validate a wider range of failure conditions.

Integrate API Testing: We plan to add API tests to verify that orders and other critical data are correctly registered in the backend database.


📊 Allure Reporting
This project uses Allure Report to generate comprehensive, interactive test reports. Allure provides a clear overview of test execution, including pass/fail rates, detailed steps for each test, and screenshots on failure.

Generating the Report
After running the tests, an allure-results directory will be generated.

To generate and open the report, use the Allure command-line tool:

Bash

allure serve allure-results
This command will generate the report and automatically open it in your default browser.

<img width="2530" height="1070" alt="image" src="https://github.com/user-attachments/assets/544de91e-d82e-47e2-946e-438080398a25" />



