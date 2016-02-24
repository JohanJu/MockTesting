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

public class SheetTest {


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); // Flytta till @BeforeClass?
		when(cirSlot.value()).thenThrow(new XLException("circular dependency"));

	}
	
	Sheet sheet = new Sheet();

	@Mock	CircularSlot cirSlot;
	@Mock	ExprSlot exprSlot;
	@Mock	CommentSlot comSlot;
	@Mock 	HashMap<String, Slot> slotMap;
	
	
	Sheet sheetSpy = spy(sheet);
	@InjectMocks
	Sheet sheetSpyMockedMap = spy(sheet);
	

//	@Test
//	public void setTestComment() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
//		
//		//comSlot
//		when(comSlot.getLabelText()).thenReturn("Alice");
//		when(comSlot.getText()).thenReturn("#Alice");
//		
//		
//		//---------------------------- Test Setup ----------------------------
//		
//		//CommentSlot
//		sheetSpyMockedMap.set("#Alice", "A1");
//		
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("A1", comSlot);
//	}
	
	@Test
	public void setTestExprRegularSimple() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("Alice");
//		when(exprSlot.getText()).thenReturn("#Alice");
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

		
		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("Alice");
//		when(exprSlot.getText()).thenReturn("#Alice");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//---------------------------- Test Setup ----------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void setTestCircular() throws Exception{ //INTE SÄKER PÅ CIRCULAR!
		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("A3+B2",sheetSpyMockedMap);
//		
//		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("Alice");
//		when(exprSlot.getText()).thenReturn("#Alice");
//		
//		when(cirSlot.value()).thenThrow(new XLException("circular dependency"))
		
//		//---------------------------- Test Setup ----------------------------
//
//		//ExpressionSlot
//		try{
//			sheetSpyMockedMap.set("A2", "A2");	
//			Assert.fail("Returnerade inte exception");
//		} catch (XLException e){
//			//Testet skall passa när vi kommer hit, därför tomt block.
//		}
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("A1", comSlot);
//		verify(slotMap).put("A1", comSlot);
		
	}

}
