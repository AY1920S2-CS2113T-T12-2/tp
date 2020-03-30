# Developer Guide

By: `CS2113T-T12-2` Since: `2020`


## Table of Contents


1. [Introduction](#introduction)
2. [Setting up](#setting-up)
3. [Design](#design)

    3.1. Architecture
    
    
    
4. [Implementation](#implementation)

    4.1. Add Patient's Information
          
      - 4.1.1. Description
      
      - 4.1.2. Implementation
      
      - 4.1.3. Design Considerations
    
    4.2. Appointment Scheduling
    
      - 4.2.1. Description
      
      - 4.2.2. Implementation 
      
      - 4.2.3. Design Consideration 
    
    4.3. 
5. [Documentation](#documentation)
6. [Testing](#testing)
7. [DevOps](#devops)

[Appendix A: Product Scope](#)

[Appendix B: User Stories](#)

[Appendix C: Non Functional Requirements](#)

[Appendix E: Glossary](#)

[Appendix F: Instructions for Manual Testing](#)


## 1. Introduction


## 2. Setting Up

## 3. Design 

## 4. Implementation

### 4.1. Add Patient's Information

#### 4.1.1 Description

Users are able to store patients’ information on the program, 
ensuring that the patients’ information can be accessed easily with the NRIC as a unique identifier.

#### 4.1.2 Implementation

This feature was implemented to allow users to add patient’s information when using HappyPills.

The commands introduced in this feature include : `add`, `edit`, `list`, `delete`, `get`. 
The commands are implemented with HashMap and use NRIC as key and the Patient class as value.
The patient list feature is facilitated by PatientMap class which implements the following operations: 

    - PatientMap #add(Patient patient) — This command adds the patient object into the patient list using the patient’s nric as key.

    - PatientMap #remove(String nric) — This command removes the patient object from the existing patient list. 

    - PatientMap #hasKey(String nric) — This command checks whether the patient object resides in the existing patient list. 

The activity diagram below summarises the process of executing an `add` command.
![Add Activity Diagram](https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/docs/images/AddCommandActivityDiagram.png)

#### 4.2.3 Design Consideration

##### Aspect: Data Structure of the Patient List

        Alternative 1 (current choice): Hash Map
          Pros: Allow faster lookup of patients’ information using the unique identifier (nric)
          Cons: Implementation is harder and may result in bugs if not implemented accurately.
            
        Alternative 2: Array List
          Pros: This would be easier to implement and retrieve the information.
          Cons: When a patient is deleted, all the patients in the patient list need to be checked. 
                This would cause the deletion to be very slow when there is a large number of patients in the list.


### 4.2. Appointment Scheduling 
    
#### 4.2.1 Description

Users are able to schedule appointments made by their patients on the program, ensuring that the 
appointments do not clash and are within the opening hours of the clinic. 

#### 4.2.2 Implementation 
   
Users can manage their appointment schedule using these commands:
   
   - `add appt ic/[NRIC] d/[DATE] t/[TIME] r/[REASON]`
   
     - The appointment object takes in the date and time of the appointment as well as the patient appointment is 
     scheduled for and the reason for the appointment. This appointment must be tied to a patient that exists 
     within the program. The program will display the appointment ID that is assigned to the appointment should 
     the adding of appointment be successful.
     
   - `find appt [NRIC]`
   
     - Users can easily find all the appointments tied to a certain patient within the program. This command will 
     list down all the details of the appointment, along with the appointment ID that was assigned to the appointment 
     when it was created. 
     
   - `edit [APPT ID] d/[NEW DATE] t/[NEW TIME] r/[NEW REASON]`
   
     - If the user made a mistake while adding an appointment, it is possible to edit the appointment details via 
     the ID of the appointment.
     
     - Users are able to update the date, time and reason of the existing appointment but the parameters must be 
     used in the given order. 
     
   - `done [APPT ID]`
   
     - An appointment can be marked as done after the patient has showed up for the appointment.
     
   - `list appt` 
   
     - Users can list all the appointments that has been scheduled within the program.

#### 4.2.3 Design Consideration
   
*Aspect: Storing of Appointment Schedule*
   
   - **Alternative 1: Use of ArrayList of Appointments**
   
Pros: 

Cons: 
   
   - **Alternative 2: Use of ArrayList of Appointment Date/Time and Hashmap of Appointments**  

Pros:

Cons:

### 4.3. User Prompting 
    
#### 4.2.1 Description

When the user adds a patient’s details, the input could be missing a few compulsory fields. Instead of prompting the user to re-enter the entire input, HappyPills will only ask the user for the missing details.

The user may choose to abort the command because of a lack of knowledge of the compulsory field or provide the requested details. The add command will only be executed when all the compulsory fields are provided. 

#### 4.2.2 Implementation 

Representing a prompt

The prompting mechanism uses prefix to represent individual 

#### 4.2.3 Design Consideration

##### Representing a prompt

The prompting mechanism uses tag such as `/ic[NRIC]` to represent individual field in patient's information. A list of tags is use to pass to the `Parser` which contains:

        - Parser #addCommandParser(String input) — This method break down user input base on tags such as (/ic, /p)

##### Passing the prompts

Given below is an example scenario where the user command has missing compulsory fields

Step 1: The `HappyPills` pass the user's command to `Parser`, which finds one or more missing compulsory fields.

Step 2: The `Parser` call `Parser#parseAddCommand`, which prompt the corresponding missing field back to the user. And wait for user response

Step 3: The new user input was than check again by `Parser#parseAddCommand` and repeat the process until all the compulsory fields is added correctly.

Step 4: `Parser#parseAddCommand` will ask for conformation before passing the correct input into `AddCommand`.

Step 5: `HappyPills` will execute the command.

##### Aspect: Prompt handling method

        Alternative 1 (current choice): The `HappyPill` functions is unaware of prompting. The `Parser` keeps track of the incomplete command and sends back as `addCommand` objects.
          Pros: Decrease coupling between `HappyPill` and `Parser` components
          Cons: `HappyPill` has no way to know if it is currently handling prompting, so it cannot abort prompts, `Parser` return IncorrectCommand to act as abort.
            
        Alternative 2: The `Parser` componetnt keeps track of the incomplete command and throws an exception containing promts to the `HappyPills`.
          Pros: Greater flexibility for `HappyPill` to handle prompt, e.g. aborting
          Cons: A new class is required to keep track of the command entered, rather than simply acting as a bridge between the `Command` and `Parser` sub-component. Increase number of pontential points of failure and decrease maintainability.
          
        Alternative 1 was chosen as it decrease coupling between components. And reduces major failure during v1.
        P.S subject to change in v2.

### 4.3 Storage

#### 4.3.1 Description

This is an internal feature of the program, implemented to allow users to recover information even after HappyPills is closed in the terminal. This is achieved by storing all important information in a text file using a structured format.

#### 4.3.2 Implementation

 The current methods implemented in this class include: ‘addStringToFile’, ‘loadFromFile’ and ‘parseFileContent’. Each of these methods will carry out the following operations:
- `addStringToFile` — allows the program to add a new class object to the specified text file. This requires the program to structure the content of the class object as a single-line string, which can be done within the various classes. This saves on execution time as the saving time for all information could increase extensively with the increase of patient database. 
- `loadFromFile` — allows the program to access the specific folder and retrieve all information in the file as strings and passes it to parseFileContent line by line.
- `parseFileContent` — processes the string of information passed to it, by instantiating a relevant class object and adding it into the list containing all the objects of the same class.

#### 4.3.3 Design Considerations

##### Aspect: Saving method

        Alternative 1 (current choice): Single object stored into the same file
          Pros: Parsing a single file would be easier as they are structured in a similar fashion. Fewer checks are needed to identify which class does the object belongs to and which list it should be added to.
          Cons: Deleting a single patient could be tedious as it involves saving the entire list of the various class object back into their respective text file. If there was a large amount of the class object, then it would take an even longer time.
          Cons: It can also appear messy and relationship of prescriptions and appointments of individual users may not be clear without the use of the program. This is not friendly for backup recovery or data transfer in the future.

        Alternative 2: Store each patient as an individual text file, along with all its relevant class objects. A list with all the patient’s NRIC will also be stored to be used as a reference.
          Pros: Each patient and their relevant details are well-associated. Deleting a single user will only involve the deletion of the associated text file and overwriting the text file containing all the NRIC of the patients, instead of overwriting all the textfiles that could possibly be relevant to the patient.
          Cons: The user could end up with a lot of text files and more checks/rules are needed to find out which is the class that each string in the individual text files belongs to. Adding to the wrong class could trigger exceptions and errors that requires manual intervention.

##### Aspect: Updating deletion

        Alternative 1 (current choice): Upon every deletion, update the relevant text file
          Pros: All deletions are updated in the relevant text files immediately and will not be affected by any unexpected termination of the program.
          Cons: In the event that there is a large amount of deletion, it could be time-consuming for the user and memory-intensive on the machine.

        Alternative 2: Saving the patients for deletion to a list then processed before the exit of the program
          Pros: This will push the deletion time cost towards the end of the code so that the use of the program is faster and smoother even with a large amount of deletion.
          Cons: If the program was to terminate unexpectedly, the deletion may not be reflected in the respective text files and would be recovered in the next run of the program.



## Appendix A: Product Scope

Our product is targeted at users who:
  
   - has a need to record significant number of patients' information
   
   - want to keep patients' information organised
   
   - prefer desktop apps over other types
   
   - can type fast
   
   - prefer typing over mouse input
   
   - prefer CLI apps over GUI apps
  
Value proposition: Note taking application built for doctors to manage notes faster than a typical mouse/GUI driven app

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Doctor|add Patient's details|view their information in their subsequent visits.|
|v1.0|Doctor|have a quick overview of a list of all patients|check for their detailed information.|
|v1.0|Doctor|retrieve my patient's detailed information|check for his/her allergies and provide a more accurate diagnosis|
|v1.0|Doctor|edit a particular patient's information|the most up-to-date details in our patient records|
|v2.0|Doctor|ensure that Patient's details are accurate before I add into the patient list|so that I can retify mistakes earlier|
|v2.0|Doctor|?|?|
|v2.0|Doctor|?|?|
|v2.0|Doctor|?|?|

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.

2. A user with above average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.

3. Should be able to hold up to 1000 patients' information without a noticeable sluggishness in performance for typical usage.

4. Should be able to display large amount of inforamtion quickly. 


## Glossary

*Mainstream OS*

Windows, Linux, Unix, OS-X

## Instructions for Manual Testing

## Useful links:
* [User Guide](UserGuide.md)
* [About Us](AboutUs.md)


