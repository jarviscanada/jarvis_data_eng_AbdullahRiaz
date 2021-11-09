package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompareTwoMapsTest {
  Map<Integer,String> m1 = new HashMap<>();
  Map<Integer,String> m2 = new HashMap<>();

  @Before
  public void setUp() throws Exception {
    m1.put(1,"Abdullah");
    m1.put(2,"Muhammad");
    m2.put(1,"Abdullah");
    m2.put(2,"Aymen");
  }

  @Test
  public void compareMaps() {
    Assert.assertEquals(false, CompareTwoMaps.compareMaps(m1,m2));
  }
}