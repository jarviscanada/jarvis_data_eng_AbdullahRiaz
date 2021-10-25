package ca.jrvs.apps.grep;

import java.io.File;
import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class JavaGrepImp implements JavaGrep{

  private String regex;
  private String rootPath;
  private String outFile;

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  @Override
  public void process() throws IOException {

  }

  @Override
  public List<File> listFiles(String rootDir) {

    return null;
  }

  @Override
  public List<String> readLines(File inputFile) {

    return null;
  }

  @Override
  public boolean containsPattern(String line) {

    return false;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {

  }

  @Override
  public String getRootPath() {

    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {

    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {

    return regex;
  }

  @Override
  public void setRegex(String regex) {

    this.regex = regex;
  }

  @Override
  public String getOutFile() {

    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {

    this.outFile = outFile;
  }

  public static void main(String args[]){
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config.
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRegex(args[1]);
    javaGrepImp.setRegex(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception ex){
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }

  }
}
