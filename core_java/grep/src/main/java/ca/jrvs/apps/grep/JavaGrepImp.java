package ca.jrvs.apps.grep;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
    ArrayList<String> matchedLines = new ArrayList<>();

    for (File file : listFiles(rootPath)){
      for (String line : readLines(file)){
        if (containsPattern(line)){
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<>();
    File file = new File(rootDir);
    File[] listOfFiles = file.listFiles();
    assert listOfFiles != null;
    files = Arrays.asList(listOfFiles);
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> lines = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String line;
      while ((line = br.readLine()) != null) {
        if (containsPattern(line)){
          lines.add(line);
        }
      }
      br.close();
    } catch (IOException ex) {
      logger.error("Input file is not a file", ex);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(outFile)));
      for (String line : lines){
        bw.write(line);
        bw.newLine();
      }
      bw.close();
    } catch (IOException ex) {
      logger.error("Error: Did not write to file", ex);
    }
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

  public static void main(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config.
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception ex){
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }
}
