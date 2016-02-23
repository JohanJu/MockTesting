package mikael;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Spy;
import static org.mockito.Matchers.anyString;

//@RunWith(MockitoJUnitRunner.class)
public class SheetTest {

	Sheet sheet;
	
	@Before
	public void setup() {
		sheet = new Sheet();
		MockitoAnnotations.initMocks(this);
	}
	@After
	public void tearDown(){

	}
	
//	@Mock	CircularSlot cislot;
//	@Mock	ExprSlot eslot;
	@Mock	CommentSlot cmslot;
	
//	@InjectMocks 
//	@Spy
	
	
	@Test
	public void setTestCommentSlot(){
		// TODO funkar inte.
		Sheet sheetSpy = Mockito.spy(sheet);
		try{
			sheetSpy.set("#hej", "A3");
		}   catch (Exception e) {
		     Assert.fail("Exception " + e);
		}
		
		
		//Sheet skall returnera mocks för olika Slots 
		Mockito.when(sheetSpy.createCommentSlot(anyString())).thenReturn(cmslot);
		
		
		//cmslot beteende
		Mockito.when(cmslot.getText()).thenReturn("#hej");
		sheetSpy.getText("A3");
//		assertEquals("#hej",sheet.getText("A3"));
		Mockito.verify(cmslot).getText();
		
		
	}
	
	
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
