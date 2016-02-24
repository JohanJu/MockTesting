package johan;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import static org.easymock.EasyMock.*;
import org.junit.Rule;
import org.junit.Test;

public class SheetTestEasymock {


	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
//	Sheet sheet = new Sheet();

//	@Mock	CircularSlot cirSlot;
//	@Mock	ExprSlot exprSlot;
	@Mock	CommentSlot comSlot;
	@Mock 	HashMap<String, Slot> slotMap;
	
	
	@TestSubject
	Sheet sheet = new Sheet(); //gets injected
	

	@Test
	public void setTestComment() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
		EasyMock.expect(sheet.newCommentSlot("#Alice")).andReturn(comSlot).anyTimes();
		
		
		//comSlot
//		when(comSlot.getLabelText()).thenReturn("Alice");
//		when(comSlot.getText()).thenReturn("#Alice");
		EasyMock.expect(comSlot.getLabelText()).andReturn("Alice").anyTimes();
		EasyMock.expect(comSlot.getText()).andReturn("#Alice").anyTimes();
		
		//---------------------------- Test Setup ----------------------------
		
		//CommentSlot
//		sheetSpyMockedMap.set("#Alice", "A1");
		sheet.set("#Alice", "A1");
		
		//---------------------------- Tests ----------------------------
		
		replay(sheet);
		replay(comSlot);
		
		
//		verify(slotMap).put("A1", comSlot);
		slotMap.put("A1", comSlot);
	}
	
	@Test
	public void setTestExprRegularSimple() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
		
		
		//---------------------------- Test Setup ----------------------------

		//ExpressionSlot
		
//		sheetSpyMockedMap.set("6.0", "A2");	


		//---------------------------- Tests ----------------------------
		
//		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void setTestExprInvalid() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
		
		
		//---------------------------- Test Setup ----------------------------

		//ExpressionSlot
		
//		sheetSpyMockedMap.set("6.0", "A2AA2");	


		//---------------------------- Tests ----------------------------
		
//		verify(slotMap).put("A2AA2", cirSlot);
		
	}



}
