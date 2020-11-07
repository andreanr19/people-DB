## About project

Efficient database management application of people with CRUD operations. Database of people from the Americas, generated with data such as names, identification code, age, and others. Programmed in Java.

## About solution

I will divide the explanation of the solution into several problems.

-Generation of the database: for the generation of data, since it can be up to one billion people, it was done by adding the information to a flat file and then if the user wants to add that information to the program it is added to our AVL tree , this process is done in order to be more efficient

-Autocomplete the search with suggestions: To carry out the search and that as the person was writing the application it will show suggestions we imported a Java library that made it easier for the FXML to do that

-Data generation progress bar: To make the progress bar that would be updated as the data was generated, we did it with its own thread that will perform the respective operations and constantly update the progress of the bar

## About how to download

## Documentation

Project engineering method


## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).
