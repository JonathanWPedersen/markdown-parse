import static org.junit.Assert.*;  //import necessary classes for testing
import org.junit.*; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class MarkdownParseTest {  // class header
    @Test  // telling us that function on next line is a test
    public void addition() {  // method header
        assertEquals(2, 1 + 1);  // checks if 2 is equal to 1+1
    }

    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    // test-file test
    @Test
    public void testGetLinks() throws IOException {
        //right click on test-file.md in left sidebar to copy path
        // change \ to /
        Path fileName = Path.of("C:/Users/jessw/Documents/GitHub/markdown-parse/test-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }

    // test2-file test
    @Test
    public void testFile2() throws IOException {
        Path fileName = Path.of("C:/Users/jessw/Documents/GitHub/markdown-parse/test2-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of("https://duckduckgo.com", "https://kde.org"), links);
    }
    
    @Test
    public void testFile3() throws IOException {
        Path fileName = Path.of("C:/Users/jessw/Documents/GitHub/markdown-parse/test3-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of(), links);
    }

    @Test
    public void testFile4() throws IOException {
        Path fileName = Path.of("C:/Users/jessw/Documents/GitHub/markdown-parse/test4-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(List.of(), links);
    }
}