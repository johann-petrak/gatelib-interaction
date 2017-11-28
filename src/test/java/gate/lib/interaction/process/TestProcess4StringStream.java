/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gate.lib.interaction.process;

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Johann Petrak
 */
public class TestProcess4StringStream {
  
  @Test
  public void testAll() {
    System.err.println("Test Process4StringStream: before creation");
    Process4StringStream process = 
            new Process4StringStream(new File("."), 
            "java -cp target/*:target/dependency/*  gate.lib.interaction.process.EchoStream".split("\\s+",-1));
    System.err.println("Test Process4StringStream: after creation");
    assertTrue(process.isAlive());
    System.err.println("Test Process4StringStream: is alive");
    // send something to the echo process
    System.err.println("Test Process4StringStream: before write");
    process.writeObject("This is the first line");
    System.err.println("Test Process4StringStream: written, before read");
    String ret = (String)process.readObject();
    System.err.println("Test Process4StringStream: after read, got "+ret);
    // make sure we got it back properly
    assertEquals("This is the first line", ret);
    
    // test another one
    System.err.println("Before sending the second line");
    process.writeObject("1234");
    System.err.println("Before retrieving the second line");
    ret = (String)process.readObject();
    System.err.println("Got this: "+ret);
    assertEquals("1234",ret);
    // stop process
    System.err.println("Sending the STOP command");
    process.writeObject("STOP");
    process.stop();
    // make sure it is stopped
    // NOTE: this does not seem to work?
    // TODO
    // assertFalse(process.isAlive());
            
  }
  
  
}
