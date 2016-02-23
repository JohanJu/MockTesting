package mikael;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


public class MyDictionaryTest {
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Mock
	Map<String, String> wm;
//	@Mock
//	Poop p;
	 
//	@InjectMocks
	@Spy
	MyDictionary dic = new MyDictionary();
	
	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect() {
	    Mockito.when(wm.get("aWord")).thenReturn("aMeaning");
//	    Mockito.when(p.writeShit()).thenReturn("poop");
	 
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}
	@Test
	public void spyTest() {
	    Mockito.when(wm.get("aWord")).thenReturn("aMeaning");
	    Mockito.when(dic.newWordMap()).thenReturn(wm);
//	    Mockito.when(p.writeShit()).thenReturn("poop");
	 
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}

}

