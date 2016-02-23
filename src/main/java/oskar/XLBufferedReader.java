package oskar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XLBufferedReader extends BufferedReader {

    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

	public void load(Sheet sheet) {
		// TODO Auto-generated method stub
		
	}

}
