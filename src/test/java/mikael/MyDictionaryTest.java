package mikael;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


public class MyDictionaryTest {
	MyDictionary dic;
	@Before
	public void setup() {
		dic = new MyDictionary();
		MockitoAnnotations.initMocks(this);
	}

	
	@Mock
	Map<String, String> wm;
//	@Mock
//	Poop p;
	 
//	@InjectMocks


	
//	@Test
//	public void whenUseInjectMocksAnnotation_thenCorrect() {
//		
//	    Mockito.when(wm.get("aWord")).thenReturn("aMeaning");
////	    Mockito.when(p.writeShit()).thenReturn("poop");
//	 
//	    assertEquals("aMeaning", dic.getMeaning("aWord"));
//	}
	@Test
	public void spyTest() {
		MyDictionary dicSpy = spy(dic);

	    when(wm.get("aWord")).thenReturn("aMeaning");
//	    when(dicSpy.newWordMap()).thenReturn(wm);
	    doReturn(wm).when(dicSpy).newWordMap();
//	    when(dicSpy.getMeaning(anyString())).thenReturn("SPIONERAD!");
//	    doReturn("SPIONERAD!").when(dicSpy).getMeaning(anyString());

//	    Mockito.when(p.writeShit()).thenReturn("poop");
	 
	    assertEquals("aMeaning", dicSpy.getMeaning("aWord"));
	}

}

