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
	public void getTextTest() {
		Slot slot = EasyMock.createMock(Slot.class);
		EasyMock.expect(slotMap.get("a1")).andReturn(slot);
		EasyMock.expect(slotMap.get("a2")).andReturn(null);
		EasyMock.expect(slot.getText()).andReturn("test");
		replay(slotMap);
		replay(slot);
		assertEquals("test", sh.getText("a1"));
		assertEquals("", sh.getText("a2"));
	}

	@Test
	public void getLabelTextTest() {
		Slot slot = EasyMock.createMock(Slot.class);
		EasyMock.expect(slotMap.get("a1")).andReturn(slot);
		EasyMock.expect(slotMap.get("a2")).andReturn(null);
		EasyMock.expect(slot.getLabelText()).andReturn("lab");
		replay(slotMap);
		replay(slot);
		try {
			assertEquals("lab", sh.getLabelText("a1"));
			assertEquals("", sh.getLabelText("a2"));
		} catch (Exception e) {
		}

	}

	@Test
	public void valueTest() {
		Slot slot = EasyMock.createMock(Slot.class);
		EasyMock.expect(slotMap.get("a1")).andReturn(slot).anyTimes();
		EasyMock.expect(slotMap.get("a2")).andReturn(null);
		EasyMock.expect(slot.value()).andReturn(1.0);
		replay(slotMap);
		replay(slot);
		assertEquals(1.0, sh.value("a1"), 0.001);
		try {
			sh.value("a2");
			fail();
		} catch (XLException e) {
		}
	}

}
