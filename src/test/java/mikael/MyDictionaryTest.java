package mikael;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class MyDictionaryTest {
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Mock
	Map<String, String> wordMap;
	 
	@InjectMocks
	MyDictionary dic = new MyDictionary();
	
	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect() {
	    Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");
	 
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}

}

