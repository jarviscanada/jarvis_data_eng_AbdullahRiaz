package ca.jrvs.apps.grep;


import java.io.File;
import java.io.IOException;
import java.util.*;

public interface JavaGrep {

  /**
   * Top level search workflow.
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files.
   * @param rootDir input directory
   * @return files under the root directory
   */
   List<File> listFiles(String rootDir);

  /**
   * Read a file and return all the lines.
   *
   * BufferedReader accepts any type of Reader(StringReader, FileReader, etc.)
   * and hence capable of reading from any character input stream.
   * Whereas, FileReader is capable of reading characters from files only.
   * Usually, we wrap a FileReader with BufferedReader to read characters from files.
   *
   * @param inputFile File to be read
   * @return lines
   * @throws IllegalArgumentException if a given input file is not a file
   */
  List<String> readLines(File inputFile);

  /**
   * Check if a line contains the regex pattern (passed by user).
   *
   * @param line input string
   * @return true if there is a match
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file.
   *
   * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
   *
   * @param lines matched lines
   * @throws IOException removes write failed
   */
  void writeToFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);
}
