package ca.jrvs.apps.practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  @Override
  public boolean matchJpeg(String filename) {
    Pattern pattern = Pattern.compile("\\.jpeg$", Pattern.CASE_INSENSITIVE);
    Pattern pattern2 = Pattern.compile("\\.jpg$", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(filename);
    Matcher matcher2 = pattern2.matcher(filename);
    boolean matchFound = matcher.find();
    boolean matchFound2 = matcher2.find();

    if(matchFound || matchFound2) {
      System.out.println("Match found");
      return true;
    } else {
      System.out.println("Match not found");
    }

    return false;
  }

  @Override
  public boolean matchIp(String ip) {
    Pattern pattern = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\b");
    Matcher matcher = pattern.matcher(ip);
    boolean matchFound = matcher.find();

    if(matchFound) {
      System.out.println("Match found");
      return true;
    } else {
      System.out.println("Match not found");
      return false;
    }
  }

  @Override
  public boolean isEmptyLine(String line) {
    Pattern pattern = Pattern.compile("^\\s*$");
    Matcher matcher = pattern.matcher(line);
    boolean matchFound = matcher.find();

    if (matchFound) {
      System.out.println("Empty");
      return true;
    } else {
      System.out.println("Not empty");
      return false;
    }
  }
}
