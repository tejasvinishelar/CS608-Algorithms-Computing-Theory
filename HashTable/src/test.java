import java.util.HashMap;
import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String , String> map = new HashMap<>();
		map.put("1", "AAA");
		map.put("2", "BBB");
		map.put("3", "CCC");
		map.put("4", "DDD");
		
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}

}
