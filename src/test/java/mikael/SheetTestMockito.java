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
		
		
		// Circular dependency skall kunna kollas för alla olika därför ligger den här
		when(cirSlot.value()).thenThrow(new XLException("circular dependency"));
		doReturn(cirSlot).when(sheetSpyMockedMap).newCircularSlot(any(Slot.class));

	}
	
	@Mock	CircularSlot cirSlot;
	@Mock	ExprSlot exprSlot;
	@Mock	CommentSlot comSlot;
	@Mock 	HashMap<String, Slot> slotMap;
	
	
	@InjectMocks
	Sheet sheetSpyMockedMap = spy(new Sheet());
	
	/*
	 * Tests for set(String, String) 
	 */
	
	@Test
	public void setCommentTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
		
		//comSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		
		//-------------------------- Test Setup -------------------------
		
		//CommentSlot
		sheetSpyMockedMap.set("#Alice", "A1");
		
		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A1", comSlot);
	}
	
	@Test
	public void setExprSimpleTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void setExprInvalidTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2AA2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2AA2", cirSlot);
		
	}
	
	/*
	 * Tests for getText()
	 */
	
	@Test
	public void getTextExprTest(){
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(exprSlot);
		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("6.0",sheetSpyMockedMap.getText("A2"));
	}
	@Test
	public void getTextCommentTest(){
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(comSlot);
		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("#Alice",sheetSpyMockedMap.getText("A2"));
		
	}
	
	@Test
	public void getTextCircularTest(){
		//Behövs denna?
	}
	
	@Test
	public void getTextNoSlotTest(){
		//Kolla vilken return som förväntas här.
		
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(null);
//		try{
		assertEquals("",sheetSpyMockedMap.getText("A2"));
//			Assert.fail("Expected XLException not thrown");
//		}catch(XLException e){
//			//This is the exception we want. Test will pass if it enters here
//		}
//		catch(Exception e){
//			Assert.fail("Other exception than XLException thrown.");
//		}
	}
	
	
	/*
	 * Tests for getLabelText()
	 */
	
	@Test
	public void getLabelTextExprTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(exprSlot);
		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("6.0",sheetSpyMockedMap.getLabelText("A2"));
	}
	@Test
	public void getLabelTextCommentTest() throws Exception {
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(comSlot);
		
		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("Alice",sheetSpyMockedMap.getLabelText("A2"));
		
	}
	
	@Test
	public void getLabelTextNoSlotTest(){
		//Kolla vilken return som förväntas här.
		
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(null);
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("",sheetSpyMockedMap.getText("A2"));

	}
	
	@Test
	public void getLabelTextCircularTest(){
		//Behövs denna?
	}
	
	/*
	 * Tests for value(String)
	 */
	
}
