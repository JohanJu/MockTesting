package johan;

import static org.junit.Assert.*;

import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import static org.easymock.EasyMock.*;
import org.junit.Rule;
import org.junit.Test;

import mikael.MyDictionary;


public class MyDictionaryTest {
	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
	@Mock
	Map<String, String> wordMap;
	 
	@TestSubject
	MyDictionary dic = new MyDictionary();
	
	@Test
	public void whenUseInjectMocksAnnotation_thenCorrect() {
	    EasyMock.expect(wordMap.get("aWord")).andReturn("aMeaning");
	    replay(wordMap);
	    assertEquals("aMeaning", dic.getMeaning("aWord"));
	}

}

