# Demo of automation-testing for AutoTrader site

The following test cases was realized during performing of the testing task:
1) Log in the application as user U1 http://www.autotrader.co.uk/
2) Open the search tab and add search criteria: Make - "Volkswagen", Model - "Golf"
3) Go to the third tab in search results and open the second from the top car, let's name it C1
4) Save the chosen car C1
5) Open section "My Auto Trader"->"Saved adverts" and delete the car C1
6) Open the form of car C1, verify the car is not saved

Implementation features:

Described test sometimes can be failed. It is related to the fact that sometimes in any place of the application can pop-up dialog "We'd welcome your feedback!".
Due to the chaotic behavior of this dialogue I didn’t have enough time to atomize it, but I can easily to automate on it if necessary.
If the test failed because of dialog it is needed to be restarted.
The test was written and tested under Windows 7 and Chrome browser.

Used technologies:
1) Selenium
2) PageObject pattern
3) Spring

Before starting make sure you have Java 8 and Maven 3.3.9 installed, variable environments JAVA_HOME, M2_HOME and PATH are written.
Checking of Java version is possible using the command: java –version.
Checking of Maven version is possible using the command: mvn –v.

Start:
1) Open the console in the root catalog of the project and enter the command "mvn install".