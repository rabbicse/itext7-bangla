# itext7 community for Indic characters
Original sdk is iText: https://github.com/itext/itext7

Modified iText source code to make it work properly for indic characters like Bangla. During report generation/pdf generation with Bangla fonts faced problems. That's why this modified iText7 fixed issues. Also it should work for other indic characters. Please message me or comments here if you face any issue regarding create pdf with any indic fonts, also better solutions/suggestions are appritiated.

# Build instructions
Please follow the instructions below step by step:
- Go to itext7-bangla/itext7.1.8-bangla/itext7.1.8 with the following command:  
```cd /path/to/project/directory/itext7-bangla/itext7.1.8-bangla/itext7.1.8/```
- Write the following command to build modified itext7 jars.  
```
mvn clean

mvn compile
```

- If compilation success then write the following command:

```mvn package```

- It should create necessary jar files under /path/to/project/directory/itext7-bangla/itext7.1.8-bangla/itext7.1.8/target folder.
- Copy all jar files and put them under /path/to/project/directory/itext7-bangla/itext7.1.8-bangla/itext7-indic/src/lib/ directory
- Go to the project directory to test with the following command:

```cd /path/to/project/directory/itext7-bangla/itext7.1.8-bangla/itext7-indic/``` 
- Then write the following commands: 
```
mvn clean
mvn compile
mvn package
``` 
- Finally you should get the executable jar file named "itext7-indic-1.0.0-output.jar" under "/path/to/project/directory/itext7-bangla/itext7.1.8-bangla/itext7-indic/target" directory. write the following command to check output:  
```java -jar target/itext7-indic-1.0.0-output.jar```  
 

# Create maven project instructions
 ```mvn archetype:generate -DgroupId=work.rabbi.pdfgenerator -DartifactId=itext7-bangla -DinactiveMode=false```

Happy coding :)