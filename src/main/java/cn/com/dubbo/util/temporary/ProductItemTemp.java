package cn.com.dubbo.util.temporary;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ProductItemTemp {
	private String str1;
	private static final String str2 = "str";

	public static void main(String[] args) {
		Field[] fields = ProductItemTemp.class.getDeclaredFields();
		for (Field f : fields) {
			System.out.println("字段" + f.getName() + "访问修饰符是否包括 private:" + Modifier.isPrivate(f.getModifiers()) + "   数字  " + f.getModifiers());
			System.out.println("字段" + f.getName() + "访问修饰符是否包括 static:" + Modifier.isStatic(f.getModifiers()));
			System.out.println("字段" + f.getName() + "访问修饰符是否包括 public:" + Modifier.isPublic(f.getModifiers()) + "   数字  " + f.getModifiers());
		}

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);

		System.out.println(list.contains(null));
	}

}