package oskar;

import java.io.IOException;

import expr.Environment;

public interface Slot {
	public void set(String string)throws IOException;
	public String getLabelText();
	public String getText();
	public Double value();
}
