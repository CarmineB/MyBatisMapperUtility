package mybatismapper;

import mybatismapper.models.TestTO;
import mybatismapper.utility.MapperBuilder;

public class MyBatisMapperMain {

	public static void main(String[] args) {
		
		TestTO test = new TestTO("test", 10);
		
		String result = MapperBuilder.getMapper(test.getClass());
		
		System.out.println(result);

	}

}
