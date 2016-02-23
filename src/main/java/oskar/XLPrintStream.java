package oskar;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;

public class XLPrintStream extends PrintStream{

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

	public void save(HashMap<String, Slot> slotMap) {
		// TODO Auto-generated method stub
		
	}

}
