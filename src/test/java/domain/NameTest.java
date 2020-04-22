package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {
     @Test
    public void testName(){
         Name name = new Name("Test");
         assertEquals(name.getName(), "Test");
     }
}
