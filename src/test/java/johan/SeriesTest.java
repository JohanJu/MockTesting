package johan;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
public class SeriesTest extends EasyMockSupport{
	Series series;

	@Before
	public void setUp() {
		series = new Series();
	}

	@Test
	public void Normal() {
		CalcInt ci = new All();
		assertEquals("Last: 3\n All: 1 2 3", series.calc(ci, 1, 2));
	}

	@Test
	public void MockingClass() {
		CalcInt mock =  EasyMock.createMock(All.class);
		EasyMock.expect(mock.last(1,2)).andReturn(4);
		EasyMock.expect(mock.all(1,2)).andReturn(" 1 2 3");
		EasyMock.replay(mock);
		assertEquals("Last: 4\n All: 1 2 3", series.calc(mock, 1, 2));
	}
	@Test
	public void MockingInterface() {
		CalcInt mock =  EasyMock.createMock(CalcInt.class);
		EasyMock.expect(mock.last(1,2)).andReturn(4);
		EasyMock.expect(mock.all(1,2)).andReturn(" 1 2 3");
		EasyMock.replay(mock);
		assertEquals("Last: 4\n All: 1 2 3", series.calc(mock, 1, 2));
	}
	@Test
	public void MockingClassPart() {
		CalcInt mock = partialMockBuilder(All.class).addMockedMethod("last").createMock();
		EasyMock.expect(mock.last(1,2)).andReturn(5);
		EasyMock.replay(mock);
		assertEquals("Last: 5\n All: 1 2 3", series.calc(mock, 1, 2));
	}
}
