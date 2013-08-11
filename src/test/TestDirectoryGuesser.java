package test;

import org.af.jhlir.tools.DirectoryGuesser;

public class TestDirectoryGuesser {
    public static void main(String[] args) {
        try {
            System.out.println("JAVA_HOME");
            System.out.println(DirectoryGuesser.guessJavaHome());
            System.out.println("R_HOME");
            System.out.println(DirectoryGuesser.guessRHome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
