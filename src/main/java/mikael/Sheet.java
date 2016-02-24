package mikael;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;

public class Sheet extends Observable implements Environment {

	private HashMap<String, Slot> slotMap;

	public Sheet() {
		slotMap = newHashMap();
	}

	public void set(String value, String slot) throws Exception {
		Slot old = slotMap.get(slot);
		slotMap.put(slot, newCircularSlot(null));
		if (!value.isEmpty()&&value.charAt(0) != '#') {
			Expr expr = new ExprParser().build(value);
			try {
				expr.value(this);
			} catch (XLException e) {
				slotMap.put(slot, newCircularSlot(null));
				throw e;
			}
		}
		slotMap.remove(slot);
		put(value, slot);
		test(old, slot);
		update();
	}
	public void save(String s){
		try {
			new XLPrintStream(s).save(slotMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void load(String s){
		try {
//			System.out.println("l1");
			new XLBufferedReader(s).load(this);
			update();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText(String slot) {
		Slot s = slotMap.get(slot);
		if (s != null) {
			return s.getText();
		} else
			return "";
	}

	public String getLabelText(String slot) throws Exception {
		Slot s = slotMap.get(slot);
		if (s != null) {
			return s.getLabelText();
		} else
			return "";
	}

	public double value(String name) {
		Slot s = slotMap.get(name);
		if (s == null) {
			throw new XLException("reference to empty slot");
		}
		try {
			return slotMap.get(name).value();
//			return 0;
		} catch (XLException e) {
			throw e;
		}
	}
	
	public void test(Slot old, String slot) throws Exception {
		Iterator<Map.Entry<String, Slot>> it = slotMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Slot> pair = it.next();
			try {
				pair.getValue().value();
			} catch (XLException e) {
				slotMap.put(slot, old);
				throw e;
			}
		}
	}
	
	public void update() {
		setChanged();
		notifyObservers();
	}

	void put(String value, String slot) throws Exception {
		if (!value.isEmpty()) {
			if (value.charAt(0) == '#') {
//				slotMap.put(slot, new CommentSlot(value));
				slotMap.put(slot, newCommentSlot(value));
			} else {
//				slotMap.put(slot, new ExprSlot(value,this));
				slotMap.put(slot, newExprSlot(value,this));
			}
		}
	}
	
	
	protected CommentSlot newCommentSlot(String value){
		return new CommentSlot(value);
	}
	protected ExprSlot newExprSlot(String value, Sheet sheet){
		return new ExprSlot(value, sheet);
	}
	protected CircularSlot newCircularSlot(Slot slot){
		return new CircularSlot(slot);
	}
	protected HashMap<String, Slot> newHashMap(){
		return new HashMap<String, Slot>();
	}

	
}
