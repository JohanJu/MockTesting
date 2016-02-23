package johan;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import static org.easymock.EasyMock.*;
import org.junit.Rule;
import org.junit.Test;



public class SheetEasy {
	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
	@Mock
	HashMap<String, Slot> slotMap;
	
	
	@TestSubject
	Sheet sh = new Sheet();
	
	@Test
	public void tget() {
		Slot slot =  EasyMock.createMock(Slot.class);
	    EasyMock.expect(slotMap.get("a1")).andReturn(slot);
		EasyMock.expect(slot.getText()).andReturn("test"); 
	    replay(slotMap);
	    replay(slot);
	    assertEquals("test", sh.getText("a1"));
	}

}

