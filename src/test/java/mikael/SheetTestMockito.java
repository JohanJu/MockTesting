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
	public void setExprInvalidAddressTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		//ExpressionSlot
		
		sheetSpyMockedMap.set("6.0", "A2AA2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2AA2", cirSlot);
		
	}
	@Test
	public void setExprInvalidValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		


		//---------------------------- Tests ----------------------------
		
		try{
			sheetSpyMockedMap.set("nmdvnsl", "A2");
			Assert.fail("Expected XLException was not thrown");
		}catch(XLException e){
			//Test is correct
		}catch(Exception e){
			Assert.fail("Other exception than XLException thrown");
		}
		
	}
	
	@Test
	public void setEmptyValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		
		sheetSpyMockedMap.set("", "A2");	


		//---------------------------- Tests ----------------------------
		
//		verify(slotMap, never()).put(anyString(), any(Slot.class));
		verify(slotMap).put("A2", cirSlot);

	}
	@Test
	public void setEmptySlotKeyTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		
		sheetSpyMockedMap.set("6.0", "");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("", cirSlot);
	}
	
	/*
	 * Tests for getText()
	 */

	@Test
	public void getTextTest(){
		
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
	public void getTextNoSlotTest(){
		//Kolla vilken return som förväntas här.
		
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(null);
		assertEquals("",sheetSpyMockedMap.getText("A2"));
		
	}
	
	
	/*
	 * Tests for getLabelText()
	 */
	
	@Test
	public void getLabelTextTest() throws Exception {
		
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
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(null);
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertEquals("",sheetSpyMockedMap.getText("A2"));

	}

	/*
	 * Tests for value(String)
	 */
	
	@Test
	public void valueTest(){
		
		//---------------------------- Mocks ----------------------------
		
		when(exprSlot.value()).thenReturn(6.0);
		
		when(slotMap.get("A2")).thenReturn(exprSlot);
		
		//-------------------------- Test Setup -------------------------


		//---------------------------- Tests ----------------------------
		
		assertTrue(6.0 == sheetSpyMockedMap.value("A2"));

	}
	
	@Test
	public void valueNoSlotTest(){
		
		//---------------------------- Mocks ----------------------------
		
		when(slotMap.get("A2")).thenReturn(null);
		
		//-------------------------- Test Setup -------------------------
		
		
		//---------------------------- Tests ----------------------------
		try{
			sheetSpyMockedMap.value("A2");
			Assert.fail("Expected XLException was not thrown");
		}catch(XLException e){
			//We are looking for XLException, test should pass here.
		}catch(Exception e){
			Assert.fail("Other exception than XLException thrown");
		}
	}
	
	
	
	/*
	 * Tests for put
	 */
	
	@Test
	public void putCommentTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
		
		//comSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		
		//-------------------------- Test Setup -------------------------
		
		//CommentSlot
		sheetSpyMockedMap.put("#Alice", "A1");
		
		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A1", comSlot);
	}
	
	@Test
	public void putExprSimpleTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		
		sheetSpyMockedMap.put("6.0", "A2");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void putExprInvalidAddressTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		
		sheetSpyMockedMap.put("6.0", "A2A22");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap).put("A2AA2", cirSlot);
		
	}
	
	@Test
	public void putExprInvalidValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		


		//---------------------------- Tests ----------------------------
		
		try{
			sheetSpyMockedMap.put("bvjksbvdjks", "A2");
			Assert.fail("Expected XLException was not thrown");
		}catch(XLException e){
			//Test is correct
		}catch(Exception e){
			Assert.fail("Other exception than XLException thrown");
		}
	}
	@Test
	public void putEmptyValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		
		sheetSpyMockedMap.put("", "A2A22");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap, never()).put(anyString(), any(Slot.class));
	}
	@Test
	public void putEmptySlotKeyTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
		when(exprSlot.getLabelText()).thenReturn("6.0");
		when(exprSlot.getText()).thenReturn("6.0");
		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		
		sheetSpyMockedMap.put("6.0", "");	


		//---------------------------- Tests ----------------------------
		
		verify(slotMap,never()).put("",cirSlot);
		verify(slotMap,never()).put("",exprSlot);
		verify(slotMap,never()).put("",comSlot);
	}
	
	/*
	 * Tests for test(Slot old, String slot)
	 */
	@Mock	ExprSlot exprSlot1;
	@Mock	ExprSlot exprSlot2;
	@Mock	ExprSlot exprSlot3;
	@Test
	public void testOneValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		//exprSlot1
		when(exprSlot1.getLabelText()).thenReturn("1.0");
		when(exprSlot1.getText()).thenReturn("1.0");
		when(exprSlot1.value()).thenReturn(1.0);
		//exprSlot2
		when(exprSlot2.getLabelText()).thenReturn("2.0");
		when(exprSlot2.getText()).thenReturn("2.0");
		when(exprSlot2.value()).thenReturn(2.0);
		//exprSlot3
		when(exprSlot3.getLabelText()).thenReturn("A1+A2");
		when(exprSlot3.getText()).thenReturn("3.0");
		when(exprSlot3.value()).thenReturn(3.0);
		
		
		//Prepare a HashMap with some mocked elements
		HashMap<String,Slot> hashMap = new HashMap<String,Slot>();
		HashMap<String,Slot> hmSpy = spy(hashMap);
		hashMap.put("A1", exprSlot1);
		hashMap.put("A2", exprSlot2);
		hashMap.put("A3", exprSlot3);
		
		
		//sheetSpy
		doReturn(hashMap).when(sheetSpyMockedMap).newHashMap();

		

		
		
		//-------------------------- Test Setup -------------------------
		
		sheetSpyMockedMap.test(exprSlot,"A3");

		
		//---------------------------- Tests ----------------------------
		
		verify(hmSpy,never()).put("A3",exprSlot);

	}
	//testOneChain
	//testCircular - exception
	
}
