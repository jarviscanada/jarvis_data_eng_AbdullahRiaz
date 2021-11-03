# JAVA GREP APPLICATION

## Introduction
Designed a Java based application that allows the user to search for a specified string in a text file and returns instances of the string pattern as output onto a specified file.

### Technologies Used:
* Java
* Java Regex API
* SLF4J Logger
* Maven - Shade Plugin
* Libraries - Reader and Writer
* Maven 
* Docker
* Git
* IntelliJ IDEA

## Design
Designed a Java Grep interface that had essential grep app functionality which was then put into implementation via a Java Grep Implementation class. Key libraries were used to enable searching of a file, reading of a file and writing to a file. Coupled with these libraries, Java Regex API was used to search for string patterns in specified files. Lastly, SLF4J Logger was used to have clean, helpful logs that will help in understanding what errors are being triggered, and what information is being processed.

## Quick Start
**Running the grep app:**
```bash
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp StringPattern ./data ./out/grep.txt
```
****The following are arguments to the grep app:***
* `StringPattern`: The string pattern to be searched in file using Regex.
* `./data`: The directory containing file to be searched.
* `./out/grep.txt`: The specified file where the output of the results will be written to.

## Implementation
### Pseudocode:
```java
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
    if containsPattern(line)
      matchedLines.add(line)
writeToFile(matchedLines)
```

### Performance Issue:
 The application was limited by the size of the heap memory. The size of the heap restricted the size of the input file to be searched and therefore resulted in an error.

## Test
The application was tested manually by comparing the results of another string pattern with the results in the sample data file.

## Deployment
The application was first compiled and packaged into an uber-jar using the Maven build tool and subsequently dockerized by building an image through a dockerfile. The image was then uploaded to a docker registry (i.e. dockerhub) for distribution.

## Improvements
1. Have the app use any file size without manually modifying the heap memory size.
2. Be able to search multiple string patterns and output to a file.
3. Make the app more efficient in turns of memory usage and amount of time it takes to search for a string pattern.