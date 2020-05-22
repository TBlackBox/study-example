package gson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

public class TessEnum {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		/**
		 * 对name()处理
		 */
		//序列化
		String sexGirl = gson.toJson(Gender.GIRL);
		System.out.println(sexGirl); //"GIRL"
		
		String sexBoy = gson.toJson(Gender.BOY);
		System.out.println(sexBoy); //"男孩"
		
		//反序列化
		Gender gril = gson.fromJson(sexGirl, Gender.class); //GIRL
		Gender boy = gson.fromJson(sexBoy, Gender.class); //BOY
		
		//对ordinal()的，就要自定义序列化和反序列化器了
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new EnumSerializer());
		gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new EnumDeserialize());
		
//		gsonBuilder.registerTypeHierarchyAdapter(Enum.class, new JsonSerializer<Enum<?>>() {
//			@Override
//			public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
//				return new JsonPrimitive(src.ordinal());
//			}
//		});
		
		//序列化
		Gson gsonB = gsonBuilder.create();
		String boyB = gsonB.toJson(Gender.BOY);
		System.out.println("boyB:"+boyB); //boyB:0
		
		//反序列化
		Gender boyB1 = gsonB.fromJson(boyB, Gender.class);
		System.out.println(boyB1); //BOY
	}
	
	public static class EnumSerializer implements JsonSerializer<Enum<?>>{
		@Override
		public JsonElement serialize(Enum<?> src, Type typeOfSrc, JsonSerializationContext context) {
			//使用ordinal 来序列化成基本数据值
			return new JsonPrimitive(src.ordinal());
		}
	}
	
	public static class EnumDeserialize implements JsonDeserializer<Enum<?>>{

		@Override
		public Enum<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			//判断是否基本类型
			if(json.isJsonPrimitive()) {
				try {
					JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
					//获取枚举类对象
					Class classEnum = Class.forName(typeOfT.getTypeName());
					//获取枚举内容
					Enum<?>[] enums = (Enum<?>[]) classEnum.getEnumConstants();
					if(jsonPrimitive.isNumber()) {
						return enums[jsonPrimitive.getAsInt()];
					}else if(jsonPrimitive.isString()){
						for(Enum<?> constant : enums) {
							if(constant.name().equalsIgnoreCase(jsonPrimitive.getAsString())) {
								return constant;
							}
						}
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}
	
	public enum Gender{
		
		@SerializedName("男孩")
		BOY,
		
		GIRL,
		
		UNKNOWN
	}
}

