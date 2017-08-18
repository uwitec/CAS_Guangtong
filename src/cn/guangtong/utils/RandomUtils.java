package cn.guangtong.utils;

public class RandomUtils {
	
	/**
	 * 随机码生成 
	 * @author Sutong 
	 * 
	 * @return
	 */
	public static String random(){  
	    String random = "";  
	    /*随机数函数*/  
	    java.util.Random r=new java.util.Random();  
	    for(int i = 0;i<6;i++){  
	        /*生成36以内的随机数，不含36，并转化为36位*/  
	        random += Integer.toString(r.nextInt(36), 36);  
	    }  
	    return random;  
	}  
	
}
