package oskar;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class MyDictionaryTest {
	// Mocka både hasmapen och slotten, se om man kan injicera slotten i mappen...
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Mock
	Map<String, String> wordMap;
	 
	@InjectMocks
	MyDictionary dic = new MyDictionary();
	
	@Test
	public void testGet() {
	    Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");
	 
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}

}

