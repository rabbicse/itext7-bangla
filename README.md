# itext7-bangla
Modified IText7 with Indic character problem. Specially the characters like Bangla.

# Create/Build instructions
 - mvn archetype:generate -DgroupId=work.rabbi.pdfgenerator -DartifactId=itext7-bangla -DinactiveMode=false

Go to itext7 folder and write the following commands:
mvn clean
mvn compile
mvn package

Then copy all jar files from target folder and add as external jar inside your pom.xml file of main project.