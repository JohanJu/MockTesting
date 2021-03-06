package mikael;

import java.util.HashMap;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import static org.easymock.EasyMock.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class SheetTestEasymock extends EasyMockSupport{


	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
//	Sheet sheet = new Sheet();

	@Mock	CircularSlot cirSlot;
	@Mock	ExprSlot exprSlot;
	@Mock	CommentSlot comSlot;
	@Mock(type = MockType.NICE)   HashMap<String, Slot> slotMap;
	
	
	@TestSubject
	Sheet sheet = partialMockBuilder(Sheet.class).addMockedMethod("update").addMockedMethod("test").addMockedMethod("newCommentSlot").addMockedMethod("newExprSlot").addMockedMethod("newCircularSlot").createNiceMock();
	
	
	@Test
	public void setCommentTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		//sheetSpy
//		doReturn(comSlot).when(sheetSpyMockedMap).newCommentSlot("#Alice");
		EasyMock.expect(sheet.newCommentSlot("#Alice")).andReturn(comSlot);
		//comSlot
//		when(comSlot.getLabelText()).thenReturn("Alice");
//		when(comSlot.getText()).thenReturn("#Alice");
//		not used
//		EasyMock.expect(comSlot.getLabelText()).andReturn("Alice");
//		EasyMock.expect(comSlot.getText()).andReturn("#Alice");
		
		
		
		//verify by .times(1)
		EasyMock.expect(slotMap.put("A1", comSlot)).andReturn(null).once();
		//-------------------------- Test Setup -------------------------
		//CommentSlot
//		sheetSpyMockedMap.set("#Alice", "A1");
		replay(sheet);
		replay(slotMap);
		sheet.set("#Alice", "A1");
		
		//---------------------------- Tests ----------------------------
		
//		verify(slotMap).put("A1", comSlot);
//		see row 50
	}
	
	@Test
	public void setExprSimpleTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
		EasyMock.expect(sheet.newExprSlot("6.0",sheet)).andReturn(exprSlot);
		
		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		EasyMock.expect(slotMap.put("A2", exprSlot)).andReturn(null).once();
		replay(sheet);
		replay(slotMap);
		sheet.set("6.0", "A2");

		//---------------------------- Tests ----------------------------
		
//		verify(slotMap).put("A2", exprSlot);
		
	}
	@Test
	public void setExprInvalidAddressTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
		EasyMock.expect(sheet.newExprSlot("6.0",sheet)).andReturn(exprSlot);
		
		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------

		//ExpressionSlot
		EasyMock.expect(sheet.newExprSlot("6.0",sheet)).andReturn(exprSlot);
		replay(sheet);
		replay(slotMap);
		sheet.set("6.0", "A2AA2");
//		sheetSpyMockedMap.set("6.0", "A2AA2");	


		//---------------------------- Tests ----------------------------
		
//		verify(slotMap).put("A2AA2", cirSlot);
		
	}
	@Test
	public void setExprInvalidValueTest() throws Exception{
		
		//---------------------------- Mocks ----------------------------
		
		
		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
		EasyMock.expect(sheet.newExprSlot("6.0",sheet)).andReturn(exprSlot);

		
		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
		
		
		//-------------------------- Test Setup -------------------------
		
		replay(sheet);
		replay(slotMap);

		//---------------------------- Tests ----------------------------
		
		try{
			sheet.set("nmdvnsl", "A2");
			Assert.fail("Expected XLException was not thrown");
		}catch(XLException e){
			//Test is correct
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail("Other exception than XLException thrown");
		}
		
	}
//	
//	@Test
//	public void setEmptyValueTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpyMockedMap.set("", "A2");	
//
//
//		//---------------------------- Tests ----------------------------
//		
////		verify(slotMap, never()).put(anyString(), any(Slot.class));
//		verify(slotMap).put("A2", cirSlot);
//
//	}
//	@Test
//	public void setEmptySlotKeyTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpyMockedMap.set("6.0", "");	
//
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("", cirSlot);
//	}
//	
//	/*
//	 * Tests for getText()
//	 */
//
//	@Test
//	public void getTextTest(){
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(slotMap.get("A2")).thenReturn(comSlot);
//		
//		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
//		when(comSlot.getLabelText()).thenReturn("Alice");
//		when(comSlot.getText()).thenReturn("#Alice");
//		
//		
//		//-------------------------- Test Setup -------------------------
//
//
//		//---------------------------- Tests ----------------------------
//		
//		assertEquals("#Alice",sheetSpyMockedMap.getText("A2"));
//		
//	}
//	
//	@Test
//	public void getTextNoSlotTest(){
//		//Kolla vilken return som förväntas här.
//		
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(slotMap.get("A2")).thenReturn(null);
//		assertEquals("",sheetSpyMockedMap.getText("A2"));
//		
//	}
//	
//	
//	/*
//	 * Tests for getLabelText()
//	 */
//	
//	@Test
//	public void getLabelTextTest() throws Exception {
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(slotMap.get("A2")).thenReturn(comSlot);
//		
//		//exprSlot - Finns med ifall den felaktigt skulle skapa ExprSlot
//		when(comSlot.getLabelText()).thenReturn("Alice");
//		when(comSlot.getText()).thenReturn("#Alice");
//		
//		
//		//-------------------------- Test Setup -------------------------
//
//
//		//---------------------------- Tests ----------------------------
//		
//		assertEquals("Alice",sheetSpyMockedMap.getLabelText("A2"));
//		
//	}
//	
//	@Test
//	public void getLabelTextNoSlotTest(){
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(slotMap.get("A2")).thenReturn(null);
//		
//		//-------------------------- Test Setup -------------------------
//
//
//		//---------------------------- Tests ----------------------------
//		
//		assertEquals("",sheetSpyMockedMap.getText("A2"));
//
//	}
//
//	/*
//	 * Tests for value(String)
//	 */
//	
//	@Test
//	public void valueTest(){
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		when(slotMap.get("A2")).thenReturn(exprSlot);
//		
//		//-------------------------- Test Setup -------------------------
//
//
//		//---------------------------- Tests ----------------------------
//		
//		assertTrue(6.0 == sheetSpyMockedMap.value("A2"));
//
//	}
//	
//	@Test
//	public void valueNoSlotTest(){
//		
//		//---------------------------- Mocks ----------------------------
//		
//		when(slotMap.get("A2")).thenReturn(null);
//		
//		//-------------------------- Test Setup -------------------------
//		
//		
//		//---------------------------- Tests ----------------------------
//		try{
//			sheetSpyMockedMap.value("A2");
//			Assert.fail("Expected XLException was not thrown");
//		}catch(XLException e){
//			//We are looking for XLException, test should pass here.
//		}catch(Exception e){
//			Assert.fail("Other exception than XLException thrown");
//		}
//	}
//	
//	
//	
//	/*
//	 * Tests for put
//	 */
//	
//	@Test
//	public void putCommentTest() throws Exception{
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
//		//-------------------------- Test Setup -------------------------
//		
//		//CommentSlot
//		sheetSpyMockedMap.put("#Alice", "A1");
//		
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("A1", comSlot);
//	}
//	
//	@Test
//	public void putExprSimpleTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//
//		
//		sheetSpyMockedMap.put("6.0", "A2");	
//
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("A2", exprSlot);
//		
//	}
//	@Test
//	public void putExprInvalidAddressTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//
//		
//		sheetSpyMockedMap.put("6.0", "A2A22");	
//
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap).put("A2AA2", cirSlot);
//		
//	}
//	
//	@Test
//	public void putExprInvalidValueTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//		
//
//
//		//---------------------------- Tests ----------------------------
//		
//		try{
//			sheetSpyMockedMap.put("bvjksbvdjks", "A2");
//			Assert.fail("Expected XLException was not thrown");
//		}catch(XLException e){
//			//Test is correct
//		}catch(Exception e){
//			Assert.fail("Other exception than XLException thrown");
//		}
//	}
//	@Test
//	public void putEmptyValueTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpyMockedMap.put("", "A2A22");	
//
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap, never()).put(anyString(), any(Slot.class));
//	}
//	@Test
//	public void putEmptySlotKeyTest() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		
//		//sheetSpy
//		doReturn(exprSlot).when(sheetSpyMockedMap).newExprSlot("6.0",sheetSpyMockedMap);
//
//		
//		//exprSlot - These mocks exist in the case it would incorrectly create a ExprSlot
//		when(exprSlot.getLabelText()).thenReturn("6.0");
//		when(exprSlot.getText()).thenReturn("6.0");
//		when(exprSlot.value()).thenReturn(6.0);
//		
//		
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpyMockedMap.put("6.0", "");	
//
//
//		//---------------------------- Tests ----------------------------
//		
//		verify(slotMap,never()).put("",cirSlot);
//		verify(slotMap,never()).put("",exprSlot);
//		verify(slotMap,never()).put("",comSlot);
//	}
//	
//	/*
//	 * Tests for test(Slot old, String slot)
//	 */
//	@Mock	ExprSlot exprSlot1;
//	@Mock	ExprSlot exprSlot2;
//	@Mock	ExprSlot exprSlot3;
//	@Test
//	public void testOneValueChain() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		//exprSlot1
//		when(exprSlot1.getLabelText()).thenReturn("1.0");
//		when(exprSlot1.getText()).thenReturn("1.0");
//		when(exprSlot1.value()).thenReturn(1.0);
//		//exprSlot2
//		when(exprSlot2.getLabelText()).thenReturn("2.0");
//		when(exprSlot2.getText()).thenReturn("2.0");
//		when(exprSlot2.value()).thenReturn(2.0);
//		//exprSlot3
//		when(exprSlot3.getLabelText()).thenReturn("A1+A2");
//		when(exprSlot3.getText()).thenReturn("3.0");
//		when(exprSlot3.value()).thenReturn(3.0);
//		
//		
//		//Prepare a HashMap with some mocked elements
//		HashMap<String,Slot> hashMap = new HashMap<String,Slot>();
//		HashMap<String,Slot> hmSpy = spy(hashMap);
//		hashMap.put("A1", exprSlot1);
//		hashMap.put("A2", exprSlot2);
//		hashMap.put("A3", exprSlot3);
//		
//		
//		//sheetSpy
//		doReturn(hmSpy).when(sheetSpy).newHashMap();
//
//
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpy.test(exprSlot,"A3");
//
//		
//		//---------------------------- Tests ----------------------------
//		
//		verify(hmSpy,never()).put("A3",exprSlot);
//
//	}
//	
//	@Test
//	public void testOneValueCircular() throws Exception{
//		
//		//---------------------------- Mocks ----------------------------
//		
//		//exprSlot1
//		when(exprSlot1.getLabelText()).thenReturn("1.0");
//		when(exprSlot1.getText()).thenReturn("1.0");
//		when(exprSlot1.value()).thenReturn(1.0);
//		//exprSlot2
//		when(exprSlot2.getLabelText()).thenReturn("2.0");
//		when(exprSlot2.getText()).thenReturn("2.0");
//		when(exprSlot2.value()).thenReturn(2.0);
//
//		
//		
//		//Prepare a HashMap with some mocked elements
//		HashMap<String,Slot> hashMap = new HashMap<String,Slot>();
//		HashMap<String,Slot> hmSpy = spy(hashMap);
//		hashMap.put("A1", exprSlot1);
//		hashMap.put("A2", exprSlot2);
//		hashMap.put("A3", cirSlot);
//		
//		
//		//sheetSpy
//		doReturn(hashMap).when(sheetSpy).newHashMap();
//
//
//		//-------------------------- Test Setup -------------------------
//		
//		sheetSpy.test(exprSlot,"A3");
//		
//		//---------------------------- Tests ----------------------------
////		try{
////			sheetSpyMockedMap.test(exprSlot,"A3");
////			Assert.fail("Expected XLException was not thrown");
////		}catch(XLException e){
////			//Test passed
////		}catch(Exception e){
////			Assert.fail("Exception other that XLException was thrown");
////		}
//		verify(hmSpy).put("A3",exprSlot);
//
//	}


}
