# cse237-project25

Team Members:

* Jason Li  
* Refik Celik
* Ethan Matsubayashi
* James Chen

For each iteration you should answer the following:

What user stories were completed this iteration?

What user stories do you intend to complete next iteration?

Is there anything that you implemented but doesn't currently work?

What commands are needed to compile and run your code from the command line (please provide a script that users can run to launch your program)?



#### Iteration 1:

###### Team work:

In this iteration, our team delivered a set of key features that form the foundation of our complete banking system. Each team member focused on specific components, ensuring a well-rounded and robust implementation.

Refik worked on enhancing the system’s account management by developing the savings account functionality and handling customer personal information. His contributions ensure that users can create and manage their accounts smoothly.

Ethan focused on implementing the core deposit and withdrawal operations. In addition to these transaction functionalities, he integrated a file-based persistence mechanism that logs all transactions to a simple database file. This approach guarantees that every operation is recorded and can be retrieved later.

James concentrated on the overall bank account management and the processing of transactions between accounts. His work provided the necessary validations and secure handling of fund transfers, which are critical for maintaining account integrity.

Jason was responsible for developing the credit management features, including borrowing and repaying credit. He also designed and implemented the financial statement generation, allowing users to review a detailed history of their transactions.

Together, our collaborative efforts in this iteration have established a solid groundwork for the banking system, paving the way for further enhancements and refinements in future iterations.

​	

##### Completed User Stories:

- As a bank customer, I want to deposit money into my account so that I can save for future needs. This story was completed by implementing the deposit method in the BankAccount class, which adds the deposit amount to the balance and logs the transaction using the FinancialStatement class and file-based logging.
- As a bank customer, I want to withdraw funds from my account so that I can access my money when necessary. This story was completed by implementing the withdraw method (along with the ensureValid helper) in the BankAccount class. The method validates the withdrawal amount against the current balance and records the transaction in the financial statement.
- As a bank customer, I want to borrow credit so that I can cover unexpected expenses. This story was completed by adding the borrowCredit method in the BankAccount class, which updates the credit balance after ensuring the input amount is positive.
- As a bank customer, I want to repay borrowed credit so that I can reduce my liabilities. This story was completed by adding the repayCredit method in the BankAccount class. It ensures that the repayment amount is valid and does not exceed the current credit balance before making the adjustment.
- As a bank customer, I want to view a detailed financial statement so that I can track all my transactions. This story was completed by creating the FinancialStatement class. This class maintains a record of each transaction (deposit or withdrawal) and formats the output in a tab-separated list. The financial statement can be accessed through the BankAccount class and is also logged to a file for persistence.
- As a bank customer, I want to see my current balance and credit balance so that I always know my financial standing. This story was completed by implementing the getCurrentBalance and getCreditBalance methods in the BankAccount class, and displaying these values in the console-based menu interface.



##### Upcoming User Stories:

- As a bank customer, I want to create a secure registration and login process so that only authorized users can access their accounts. This story is planned for future iterations and will include user authentication, password encryption, and session management. 
- As a bank customer, I want to transfer funds between my accounts so that I can manage my finances more flexibly. This story is planned to be implemented with thorough validations, real-time balance updates, and comprehensive transaction logging to ensure secure transfers.
- As a bank customer, I want to receive notifications for large transactions or unusual activities so that I can monitor my account’s security in real time. This story is planned for future development and will involve integrating an alert system that notifies users via email or in-app messages when significant or suspicious transactions occur.
- As a bank customer, I want to schedule recurring transactions (such as automatic bill payments) so that I can automate routine financial activities. This story is planned to be developed with options for setting up and managing recurring payments, along with reminders and customizable scheduling features.
- As an administrator, I want to access detailed transaction logs and user activity reports so that I can perform audits and ensure system integrity. This story is planned to extend our file-based logging mechanism by providing enhanced reporting tools and an intuitive interface for reviewing historical data.



##### Not currently working function:

N/A





#### Iteration 2:

###### Team work:

In this iteration, our team further enhanced and extended the foundation of our banking system by refining several key components and beginning integration between modules. Each team member continued to focus on specific areas, ensuring progress toward a more comprehensive system.

Jason Li and Refik Celik collaboratively advanced the integration between customer information and account management. Their efforts in iteration 1 demonstrate improvements in how customer profiles interact with account functionalities, setting the stage for smoother operations across various account types.

Refik Celik further contributed in iteration 2 by focusing on improving the savings account functionality and reinforcing the personal information handling process. His work continues to refine the methods by which users can open and manage savings accounts, even though this module is not entirely complete yet.

Ethan Matsubayashi built upon his previous work by enhancing the core transaction functionalities. In iteration 3, he refined the deposit and withdrawal operations and ensured that each transaction is logged accurately using our file-based persistence mechanism. This improvement lays the groundwork for more robust transaction tracking.

James Chen continued his efforts on overall bank account management and secure fund transfers. His work further improved the validations and consistency of transaction processing, which is critical for maintaining account integrity and reliability.

Together, our team’s efforts in this iteration have extended the groundwork from Iteration 1, moving us closer to a fully integrated and secure banking system.



##### Completed User Stories:

- **Enhanced Customer and Account Integration:**
  Jason Li and Refik Celik jointly improved the connection between customer profiles and account data, enabling more seamless management of personal information and account functionalities.
- **Improved Transaction Logging:**
  Ethan Matsubayashi refined the core deposit and withdrawal processes, ensuring that every transaction is logged persistently and accurately for later retrieval.
- **Advanced Credit Operations:**
  Credit management functions—including borrowing and repaying credit—have been further stabilized, ensuring that users can manage unexpected expenses with improved error handling.
- **Secure Fund Transfer Enhancements:**
  James Chen contributed crucial validations and processing improvements, ensuring that fund transfers and transaction operations maintain robust security standards.



##### Upcoming User Stories:

- **Secure Registration and Login:**
  Future iterations will concentrate on implementing a secure registration and login process that incorporates advanced authentication, password encryption, and session management.
- **Fund Transfers Between Accounts:**
  Plans are in place to allow users to transfer funds between different accounts. This feature will incorporate real-time balance updates, additional validations, and comprehensive transaction logging.
- **Scheduled Recurring Transactions:**
  The next phase will introduce features for setting up recurring transactions, such as automatic bill payments. Users will have options for scheduling, reminders, and customizable setups.
- **Administrative Reporting:**
  Future work will extend our file-based logging to provide enhanced reporting tools, allowing administrators to access detailed transaction logs and user activity reports.



##### Not Currently Working Functions:

- **Savings Account Functionality:**
  While users can trigger the opening of a savings account, the complete integration and full feature set for savings accounts are not yet fully operational.
- **Bank Account and Checking Account Menu Integration:**
  Basic operations such as deposits, withdrawals, and balance displays are functional. However, the seamless synchronization between these account modules and the menu system requires further refinement to ensure that all updates are accurately reflected in the user interface.
- **Database Connection to Account Modules:**
  The system’s database integration—which should connect and synchronize data for the various account types—is not yet working. Further development is required to establish a reliable connection between our data repository and the account modules, ensuring that account data (including transactions and balances) is properly maintained across the system.





#### Iteration 3

Team work:
In this final iteration, our team brought all modules—checking, savings, credit, mortgage and transfers—into a unified, production-ready banking system. We harmonized menu flows, tightened input validation, and guaranteed end-to-end persistence of every transaction. Each member concentrated on polishing their area while ensuring seamless integration:

Refik finalized the savings-account enhancements by building a complete statement generator and enforcing persistent logging for every deposit and withdrawal. His work ensures that users can request and review a formatted savings statement at any time.

Ethan strengthened both security and robustness in transaction handling. He introduced a password-confirmation step for withdrawals over the high-value threshold, as well as a 2-step confirmation for all transfers.

Jason delivered the remaining credit and mortgage features. In the credit service menu, he integrated a comprehensive CreditStatement view showing credit limit, available credit, credit score, and full borrow/repay history. He also refined the HouseLoan module to calculate principal with interest, validate payment input, process repayments, and update the outstanding balance interactively.

James completed the cross-user fund-transfer capability. His implementation in the Menu class performs balance checks, prompts for password confirmation on large transfers, confirms recipient identity, updates both sender and receiver accounts, and appends each side’s transaction to its statement file.

##### Completed User Stories:

As a bank customer, I want to view a formatted savings account statement so that I can easily review my deposit and withdrawal history. This was delivered by implementing SavingsStatement, which reads the SavingsAccount log, formats each entry with date, description, amount and type, and displays it in a clear table.

As a bank customer, I want withdrawals over a high-value limit to require password confirmation so that I can prevent unauthorized or accidental large transactions. This was delivered by adding a “large-sum withdrawal” step in Menu, which prompts for and verifies the user’s password before proceeding.

As a bank customer, I want the system to load all existing transaction statements reliably on startup, handling missing or corrupted files without errors. This was delivered by enhancing Statement.loadStatement to auto-create absent files, skip invalid lines, and initialize each user’s activity list safely.

As a bank customer, I want to view a complete credit-account statement—including limit, available credit, score, and detailed borrow/repay history—so that I can track my credit usage. This was delivered by integrating CreditStatement.generateStatement into the credit menu and formatting all account metrics and transactions for display.

As a bank customer, I want to apply for a house loan and make repayments within the system so that I can manage my mortgage. This was delivered by refining HouseLoan to calculate principal with interest based on home price and down payment, validate payment amounts, process repayments, and update the remaining balance through the menu.

As a bank customer, I want to transfer funds securely to another user so that I can send payments within the bank network. This was delivered by implementing transferFunds in Menu, which verifies sender balance, requires password confirmation on large amounts, confirms the recipient, updates both accounts, and logs both sides’ transactions.
