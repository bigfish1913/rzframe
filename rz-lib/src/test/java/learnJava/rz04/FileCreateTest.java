package learnJava.rz04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreateTest {
 public static void main(String[] args) {
  File file = new File("D:\\1.txt");
//  System.out.println(Long.parseLong(file.getName()));
  try {
   Path directories = Files.createDirectories(file.toPath());
   System.out.println(directories);
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}
