package mikael;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Spy;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class SheetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); // Flytta till @BeforeClass?
		
	}
	
//	@Mock	CircularSlot crSlot;
//	@Mock	ExprSlot eSlot;
	@Mock	CommentSlot comSlot;
	
	@Spy
	Sheet sheetSpy = new Sheet();
	
	@Test
	public void setTest() {
		//sheetSpy
		doReturn(comSlot).when(sheetSpy).createCommentSlot("#Alice");
		
		//comSlot
		when(comSlot.getLabelText()).thenReturn("Alice");
		when(comSlot.getText()).thenReturn("#Alice");
		
		try{
			sheetSpy.set("#Alice", "A1");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			assertEquals(sheetSpy.getLabelText("A1"),"Alice");
			assertEquals(sheetSpy.getText("A1"),"#Alice");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
