package com.muskan.shop.custom.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ProductDescTag extends TagSupport {
private String description;

	
public void setDescription(String description) {
	this.description = description;
}

	@Override
	public int doStartTag() throws JspException {
		 JspWriter out=pageContext.getOut(); 
		 String[] words=description.split(" ");
		 String newDesc="";
		    try{ 
		    	if(words.length>6)
		    	{
		    		for(int i=0;i<6;i++)
		    		{
		    			newDesc=newDesc+words[i]+" ";
		    		}
		    		newDesc=newDesc+"...";
		    		out.print(newDesc);
		    	}else {
		    		out.print(description);
		    	}
		    	
		          
		    }catch(Exception e){e.printStackTrace();}  
		      
		    return SKIP_BODY;  
	}
}
