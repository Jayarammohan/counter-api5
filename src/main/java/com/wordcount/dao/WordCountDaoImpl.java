package main.java.com.wordcount.dao;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
/**
 * Given a file in the resources folder, reads its lines into stream of strings.
 * @author Jaya
 */
@Component
public class WordCountDaoImpl implements WordCountDao {
	private final static String filename = "text.txt";
 	private Stream<String> lines;
	
 	public Stream<String> getLines() {
		return lines;
	}

	public void setLines(Stream<String> lines) {
		this.lines = lines;
	}

    @PostConstruct
  	public void init() throws IOException {  
  		loadFile();						//read the input file and populate the count words map
  	};

	
 	//load the input text file's lines into stream of strings
	public void loadFile()   {
		try {
			Path path = new File(getClass().getClassLoader().getResource(filename).getFile()).toPath();
			lines = Files.lines(path);			//read the lines from the file
		}
		catch(IOException ioe) {
			System.out.println("Error in opening file " + filename);
		}
	}    
 
  }