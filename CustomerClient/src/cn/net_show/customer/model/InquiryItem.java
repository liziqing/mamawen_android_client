/**  
 * @Title: InquiryItem.java 
 * @Package cn.net_show.customer.model 
 * @author 王帅
 * @date 2015年3月13日 下午8:11:53  
 */ 
package cn.net_show.customer.model;

import java.util.ArrayList;

/** 
 * @ClassName: InquiryItem 
 * @author 王帅
 * @date 2015年3月13日 下午8:11:53  
 */
public class InquiryItem {
	/*"department": "xxxx",

	"keyWords": ["xxx", "xxx"], //目前是必须的，可以传个空的

	"questionTo": "xxx",

	"textContent": "xxxx",

	"drug": "xxx"*/
	
	public String department;	
	public ArrayList<String> keyWords;	
	public String questionTo;	
	public String textContent;	
	public String drug;	
	public String description;
}
