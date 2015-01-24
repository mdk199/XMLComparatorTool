package xmlunit_comparator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SingletonWriterForMatchTracker {
	private static File file;
	private static FileWriter writer;
	private static SingletonWriterForMatchTracker instance;
	private SingletonWriterForMatchTracker(){
		if (file == null && writer==null){
			file = new File("src/MatchTracker.txt");
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static SingletonWriterForMatchTracker getSingletonFileandWriter(){
		if(instance == null){
			instance = new SingletonWriterForMatchTracker();
			return instance;
		}
		return instance;
	}
	
	public File getFile(){
		return file;
	}
	
	public FileWriter getFileWriter(){
		return writer;
	}
}
