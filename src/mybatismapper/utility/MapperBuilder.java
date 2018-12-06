package mybatismapper.utility;

import java.lang.reflect.Field;
import java.util.List;

public class MapperBuilder {
	
	private static final String FIXED_HEADER ="<resultMap id = %s type = %s> \n";
	private static final String FIXED_RESULT_PROPERTY="\t <result property = %s column = %s/> \n";
	private static final String FIXED_FOOTER = "</resultMap>";
	private static final String DELIMITER = "\n";
	private static final String TAB = "\t";
	private static final String FIXED_COLLECTION_PROP = "<collection  property=%s \t javaType=%s ofType=%s \t resultMap =%s/>";
	
	public static String getMapper(Class clazz) {
		StringBuilder mapperBuilder = new StringBuilder();
		if(clazz!=null) {
			String header = String.format(FIXED_HEADER,"\"" + clazz.getSimpleName() + "Mapper\" ","\""+ clazz.getCanonicalName() + "\"");
			mapperBuilder.append(header);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if(isCollection(field)) {
					mapperBuilder.append(String.format(
							FIXED_COLLECTION_PROP,"\"" + field.getName() + "\"",
							"\""+List.class.getName()+"\"",field.getType(),field.getType()));
				}else {
					mapperBuilder.append(String.format(
							FIXED_RESULT_PROPERTY,"\"" + field.getName() + "\"",
							"\""+field.getName().toUpperCase()+"\""));
				}
			}
			mapperBuilder.append(FIXED_FOOTER);
		}
		return mapperBuilder.toString();
		
	}
	
	private static boolean isCollection(Field field) {
		return field.getType().getCanonicalName().equals(List.class.getCanonicalName());
	}

}
