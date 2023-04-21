# ATM Machine

## What Will The Application Do?
This application will closely resemble an **ATM Machine**. The user will
be able to
- Create an **account**
- **Suspend** an account
- Have multiple **account types** such as chequing and savings
- **Deposit** money into an account they choose 
- **Withdraw** money from an account

## Who Will Use It?
Anybody will be able to use this application as long as they wish to 
create a ***banking*** account or already have one. Someone that wishes
to have an easy access and user-friendly ATM at their fingertips will
use this banking application. Also, it would a good introduction to 
banking for those who are new to the era of bank accounts.

## Why Is This Project Of Interest To You?
This project intrigues me because I am quite *lazy* when it comes to
going to the bank. Especially, when the only reason I am going is to use
the ATM. I find that it would be useful to have access to an ATM whenever
because there are still many uses for physical cash. Also, I believe that
the typical person would not want to wander around with $200 or more in
cash. Also, I am interested to create a new and improved ATM that has 
more features and is easier to use than normal ATMs you see at everyday
banks.

## User Stories
- As a user, I want to be able to create a bank account and add it to a 
list with my other bank accounts
- As a user, I want to be able to deposit money into a bank account
- As a user, I want to be able to withdraw money into a bank account
- As a user, I want to be able to suspend/remove a bank account
- As a user, I want to be able to save my bank accounts to file
- As a user, I want to be able to be able to load my bank accounts from file

## Instructions for Grader
**Adding an account to the list**
- You can generate the first required action related to adding Xs to a Y by:
- Typing a name into the name field
- Typing a balance into the balance field
- Press the "Add Account" button

**Removing an account from the list**
- You can generate the second required action by:
- Select an account from the list
- Then press the "Remove Account" button

**Visual Component**
- When the "Add Account" button is pressed, a smile image appears
- When the "Remove Account" button is pressed, a frown image appears

**Saving the Accounts**
- Press the "Save Accounts" button to save the accounts in the list

**Loading the Accounts**
- Press the "Load Accounts" button to load the accounts you saved

## Sample of the Events that Occur when Run
Added account: Jayden  
Added account: Joe  
Removed account: Jayden  
Added account: Evan  
Added account: Olive  
Removed account: Joe  
Removed account: Olive

## Refactoring
The design of my UML class diagram is definitely not ideal and not very organized. I 
will be honest, my design was not very good. There are a lot of different ways that I 
could refactor this project to improve the design. One very obvious thing is that the 
ChequingAccount and SavingsAccount class are very similar. There are basically identical 
apart from names. I would imagine I could have created an abstract class that both 
ChequingAccount and SavingsAccount could extend since most methods are similar. Also, 
that abstract class could even implement an interface that holds those methods that are 
implemented differently in each class. Similarly, for the ListOfChequingAccount and 
ListOfSavingsAccount classes could also extend a single abstract class. This abstract 
class would hold everything that is similar in the two classes because there is a 
substantial amount of similarity and duplication between the two classes.

Another form of refactoring that I could have done would be with the AtmMachine class 
in the ui package. Currently, the class has an association with every class in my model 
and my persistence package. This could be prone to many errors and bugs. If I were to change 
one thing anywhere, there is a possibility of bugs popping up in many other classes. As a 
result, this would not be a very systematic approach. Also, it doesn't look very nice inside 
AtmMachine because there are so many different fields and many are duplicated. I could have 
created another concrete class or classes that AtmMachine can inherit. For example, I could 
have left the associations to ChequingAccount and SavingsAccount to be in a different class 
that AtmMachine could extend. With that, I would only need one association to 
ListOfChequingAccount and ListOfSavingsAccount that would with the extended class. Also, 
with how it is currently, there is a lot of duplication in the code that could be reduced 
quite easily. Same thing with the two associations to JsonReader and JsonWriter. There is 
a lot of duplication relating to the methods that use those private fields. I could have 
created a concrete or abstract class that AtmMachine extends to reduce a lot of this 
duplication. With all of this refactoring, this project would be easier to work on in the 
future and is prone to less bugs with future changes. Also, the readability of the code 
would be much simpler. 