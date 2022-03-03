import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextOpenBracket == -1 || nextCloseBracket == -1 || openParen == -1 || closeParen == -1) 
            {
		        break;
	        }
	        if((nextOpenBracket != 0 && markdown.charAt(nextOpenBracket - 1) == '!') ||
             markdown.substring(openParen + 1, closeParen).indexOf(" ") != -1) 
            {
		        currentIndex = closeParen + 1;
		        continue;
	        }
            if ((nextOpenBracket + 1 == nextCloseBracket || openParen + 1 == closeParen))
            {
                break;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        int one = 1;
        return toReturn;
    }

    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        Map<String, List <String>> files = getLinks(new File(args[0]));
        System.out.println(files);
    }
}