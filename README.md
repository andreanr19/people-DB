## About project

Efficient database management application of people with CRUD operations. Database of people from the Americas, generated with data such as names, identification code, age, and others. Programmed in Java.

## About solution

I will divide the explanation of the solution into several problems.

-Generation of the database: for the generation of data, since it can be up to one billion people, it was done by adding the information to a flat file and then if the user wants to add that information to the program it is added to our AVL tree , this process is done in order to be more efficient

-Autocomplete the search with suggestions: To carry out the search and that as the person was writing the application it will show suggestions we imported a Java library that made it easier for the FXML to do that

-Data generation progress bar: To make the progress bar that would be updated as the data was generated, we did it with its own thread that will perform the respective operations and constantly update the progress of the bar

## Technical specifications

We use structures such as AVL Trees, TIER, binary search tree, ArrayList and flat files for the solution. Each of these was selected thanks to certain criteria where we chose the best option that would allow us to create an efficient solution

## About how to download

To download the project, clone it or download the .Zip, in the src folder you will find the model, the interface and the threads used to reach the solution, to run the program enter the Main class in your IDE of preference. The data folder contains all the information so that the generation of the database is possible, do not modify or delete that folder for the correct operation of the application

## Documentation

Project engineering method, can be found [here](docs/MetodoIngenieria)

Functional requeriments and test design, can be found 

Class diagrams, can be found here 

## Project preview



## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).
