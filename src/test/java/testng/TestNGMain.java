package testng;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestNGMain {

    public  static void main(String[] args){
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("/Users/ankuragrawal/IdeaProjects/TRChrome/src/test/testng.xml");
        testng.setTestSuites(suites);
        testng.run();
    }
}