package mikael;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Spy;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.HashMap;

public class SheetTestMockito {


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); // Flytta till @BeforeClass?
		
		
		// Circular dependency skall finnas för alla olika därför ligger den här
		when(cirSlot.value()).thenThrow(new XLException("circular dependency"));
		doReturn(cirSlot).when(sheetSpyMockedMap).newCircularSlot(any(Slot.class));

	}
	
//	Sheet sheet = new Sheet();

	@Mock	CircularSlot cirSlot;
	@Mock	ExprSlot exprSlot;
	@Mock	CommentSlot comSlot;
	@Mock 	HashMap<String, Slot> slotMap;
	
	
	@InjectMocks
	Sheet sheetSpyMockedMap = spy(new Sheet());
	

	@Test
	public void setTestComment() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
		
		//comSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		
		//---------------------------- Test Setup ----------------------------
		
		//CommentSlot
		sheetSpyMockedMap.set("#Alice", "A1");
		
		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A1", comSlot);
	}
	
	@Test
	public void setTestExprRegularSimple() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//---------------------------- Test Setup ----------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void setTestExprInvalid() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//---------------------------- Test Setup ----------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2AA2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2AA2", cirSlot);
		
	}



}
