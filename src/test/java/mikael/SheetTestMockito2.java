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
import org.mockito.Spy;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import java.util.HashMap;

public class SheetTestMockito2 {
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); // Flytta till @BeforeClass?
		
		


	}
	
	/*
	 * Tests for test(Slot old, String slot)
	 */
	@Mock	ExprSlot exprSlot1;
	@Mock	ExprSlot exprSlot2;
	@Mock	ExprSlot exprSlot3;
	@Mock	ExprSlot oldSlot;
	
	@Spy	HashMap<String,Slot> hmSpy = new HashMap<String,Slot>();
	@Spy 
	@InjectMocks Sheet sheetSpy = new Sheet();
	
	@Test
	public void testOneValueChain() throws Exception{
		
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
		
		
		//oldSlot
		when(oldSlot.getLabelText()).thenReturn("4.0");
		when(oldSlot.getText()).thenReturn("4.0");
		when(oldSlot.value()).thenReturn(4.0);
		
		
		//Prepare a HashMap with some mocked elements

		hmSpy.put("A1", exprSlot1);
		hmSpy.put("A2", exprSlot2);
		hmSpy.put("A3", exprSlot3);
		
		


		//-------------------------- Test Setup -------------------------
		
		sheetSpy.test(oldSlot,"A3");

		
		//---------------------------- Tests ----------------------------
		
		verify(hmSpy,never()).put("A3",oldSlot);

	}
	
	
	
	@Test
	public void testOneDivideZero() throws Exception{
		
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
		when(exprSlot3.value()).thenThrow(new XLException("Divide by zero"));
		
		
		//oldSlot
		when(oldSlot.getLabelText()).thenReturn("4.0");
		when(oldSlot.getText()).thenReturn("4.0");
		when(oldSlot.value()).thenReturn(4.0);
		
		
		//Prepare a HashMap with some mocked elements

		hmSpy.put("A1", exprSlot1);
		hmSpy.put("A2", exprSlot2);
		hmSpy.put("A3", exprSlot3);
		
		


		//-------------------------- Test Setup -------------------------
		
		
		try{
			sheetSpy.test(oldSlot,"A3");
			Assert.fail("Expected exception not thrown");
		}catch(XLException e){
			//Expected exception
		}catch(Exception e){
			Assert.fail("Non expected exception");
		}
		
		//---------------------------- Tests ----------------------------
		
		verify(hmSpy).put("A3",oldSlot);

	}
}
