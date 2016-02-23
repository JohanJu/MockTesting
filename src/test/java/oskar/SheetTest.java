package oskar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;



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
	
	Sheet sheet = new Sheet();
	
	@Mock CommentSlot comSlot;
//	@InjectMocks 
	Sheet spySheet = spy(sheet); //Spysheet injiceras med comSlot?
	
	//doReturn("foo").when(spy).get(0);
	

	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		doReturn(comSlot).when(spySheet).makeCommentSlot("#Alice");
		Mockito.when(comSlot.getLabelText()).thenReturn("Alice");
		try{
			spySheet.put("#Alice", "A1");
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			assertEquals(spySheet.getLabelText("A1"),"Alice");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
