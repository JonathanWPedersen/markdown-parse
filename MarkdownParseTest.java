import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest 
{
    @Test
    public void addition() 
    {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks1()
    {  
        String inputFile = "test-file.md";
        List <String> expected = List.of("some-page.html");
        try
        {            assertEquals(expected, MarkdownParse.getLinks(Files.readString(Path.of(inputFile))));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLinks2()
    {
        String inputFile = "test-file2.md";
        List <String> expected = List.of("some-page.html");
        try
        {            assertEquals(expected, MarkdownParse.getLinks(Files.readString(Path.of(inputFile))));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLinks3()
    {
        String inputFile = "test-file3.md";
        List <String> expected = List.of("a link on the first line");
        try
        {            assertEquals(expected, MarkdownParse.getLinks(Files.readString(Path.of(inputFile))));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}