package com.dreammore.framework.codegeneration.service;

import java.lang.reflect.Field;

import com.dreammore.framework.codegeneration.model.Comment;

public class HtmlListGeneration extends AbstractGeneration {

	public HtmlListGeneration(String filePath) {
		super(filePath);
	}

	@Override
	public StringBuffer generate(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		
		sb.append(getBlanks(0)).append("<!DOCTYPE html>").append(BR);
		sb.append(getBlanks(0)).append("<html lang=\"en\">").append(BR);
		sb.append(getBlanks(1)).append("<head>").append(BR);
		sb.append(getBlanks(2)).append("#set($baseurl=$request.getContextPath())").append(BR);
		sb.append(getBlanks(2)).append("<title>XXXX—YYYYYYYY</title>").append(BR);
		sb.append(getBlanks(2)).append("<meta charset=\"utf-8\">").append(BR);
		sb.append(getBlanks(2)).append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">").append(BR);
		sb.append(getBlanks(2)).append("<meta name=\"description\" content=\"\">").append(BR);
		sb.append(getBlanks(2)).append("<meta name=\"author\" content=\"\">").append(BR);
		sb.append(BR);
		sb.append(getBlanks(2)).append("<!-- Le styles -->").append(BR);	
		sb.append(getBlanks(2)).append("<link rel=\"stylesheet\" type=\"text/css\" href=\"$baseurl/bootstrap/css/bootstrap.css\">").append(BR);
		sb.append(getBlanks(2)).append("<!--[if lte IE 6]>").append(BR);
	    sb.append(getBlanks(2)).append("<link rel=\"stylesheet\" type=\"text/css\" href=\"$baseurl/bootstrap/css/bootstrap-ie6.css\">").append(BR);
	    sb.append(getBlanks(2)).append("<![endif]-->").append(BR);
	    sb.append(getBlanks(2)).append("<!--[if lte IE 7]>").append(BR);
	    sb.append(getBlanks(2)).append("<link rel=\"stylesheet\" type=\"text/css\" href=\"$baseurl/bootstrap/css/ie.css\">").append(BR);
	    sb.append(getBlanks(2)).append("<![endif]-->").append(BR);
	    sb.append(getBlanks(2)).append("<link rel=\"stylesheet\" type=\"text/css\" href=\"$baseurl/css/style.css\">").append(BR);
	    sb.append(getBlanks(2)).append("<style type=\"text/css\">").append(BR);
	    sb.append(getBlanks(3)).append("body {").append(BR);
	    sb.append(getBlanks(4)).append("padding-top: 60px;").append(BR);
	    sb.append(getBlanks(4)).append("padding-bottom: 40px;").append(BR);
	    sb.append(getBlanks(3)).append("}").append(BR);
	    sb.append(getBlanks(2)).append("</style>").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(2)).append("<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->").append(BR);
	    sb.append(getBlanks(2)).append("<!--[if lt IE 9]>").append(BR);
	    sb.append(getBlanks(2)).append("<script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>").append(BR);
	    sb.append(getBlanks(2)).append("<![endif]-->").append(BR);
	    sb.append(getBlanks(2)).append("<script type=\"text/javascript\">").append(BR);
	    sb.append(getBlanks(3)).append("var baseurl = \"$baseurl\";").append(BR);
	    sb.append(getBlanks(3)).append("var searchUrl = baseurl + \"/").append(clazz.getSimpleName().toLowerCase());
      	sb.append(getBlanks(0)).append("/to").append(clazz.getSimpleName()).append("List.do\";").append(BR);
	    sb.append(getBlanks(3)).append("var searchParameters = $searchParams;").append(BR);
	    sb.append(getBlanks(2)).append("</script>").append(BR);
	    sb.append(getBlanks(1)).append("</head>").append(BR);  	
	    sb.append(BR);    	
		sb.append(getBlanks(1)).append("<body>").append(BR);
        sb.append(getBlanks(2)).append("#parse(\"common/header.vm\")").append(BR);
        sb.append(BR);
		sb.append(getBlanks(2)).append("<div class=\"container-fluid\">").append(BR);
		sb.append(getBlanks(3)).append("<div class=\"row-fluid\">").append(BR);
		sb.append(getBlanks(4)).append("<div class=\"span2\">").append(BR);
	    sb.append(getBlanks(5)).append("#parse(\"common/menu.vm\")").append(BR);
	    sb.append(getBlanks(4)).append("</div><!--/span-->").append(BR);
	    sb.append(getBlanks(4)).append("<div class=\"span10\">").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<!-- 面包屑开始 -->").append(BR);    
	    sb.append(getBlanks(5)).append("<div>").append(BR);
	    sb.append(getBlanks(6)).append("<ul class=\"breadcrumb\">").append(BR);
	    sb.append(getBlanks(7)).append("<li>XXX<span class=\"divider\">/</span></li>").append(BR);
	    sb.append(getBlanks(7)).append("<li class=\"active\">YYY</li>").append(BR);
	    sb.append(getBlanks(6)).append("</ul>").append(BR);
	    sb.append(getBlanks(5)).append("</div>").append(BR);
	    sb.append(getBlanks(5)).append("<!-- 面包屑结束 -->").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<hr>").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<!-- 信息搜索开始 -->").append(BR);
	    sb.append(getBlanks(5)).append("<div class=\"hero-unit\">").append(BR);
      	sb.append(getBlanks(6)).append("<form class=\"form-search\" method=\"post\" action=\"$baseurl/").append(clazz.getSimpleName().toLowerCase());
      	sb.append("/to").append(clazz.getSimpleName()).append("List.do\">").append(BR);

      	sb.append(getBlanks(7)).append("<div class=\"row\">").append(BR);
      	int counter = 0;
      	for(int i = 0, length = fields.length; i < length; i++){
      		Field field = fields[i];
      		Comment comment = field.getAnnotation(Comment.class);
      		if (comment != null && comment.searchable()) {
      			counter++;
      			sb.append(getBlanks(8)).append("<div class=\"span2 right\">").append(BR);
      			sb.append(getBlanks(9)).append("<label class=\"control-label\" for=\"").append(field.getName()).append("\">").append(comment.value()).append("</label>").append(BR);
      			sb.append(getBlanks(8)).append("</div>").append(BR);
      			sb.append(getBlanks(8)).append("<div class=\"span2 left\">").append(BR);
      			sb.append(getBlanks(9)).append("<input type=\"text\" id=\"").append(field.getName()).append("\" name=\"").append(field.getName()).append("\" placeholder=\"").append(comment.value()).append("\">").append(BR);
      			sb.append(getBlanks(8)).append("</div>").append(BR);      			

      			if (counter % 2 == 0) {
      				sb.append(getBlanks(7)).append("</div>").append(BR);
      				sb.append(getBlanks(7)).append("<div class=\"row\">").append(BR);
      			}
			}//end if searchable
      	}//end for
      	sb.append(getBlanks(7)).append("</div>").append(BR);					
		
		
		sb.append(getBlanks(7)).append("<div class=\"row\">").append(BR);
      	sb.append(getBlanks(8)).append("<div class=\"span2 right\">").append(BR);
      	sb.append(getBlanks(9)).append("<label class=\"control-label\" for=\" \"> </label>").append(BR);
  	   	sb.append(getBlanks(8)).append("</div>").append(BR);
  	   	sb.append(getBlanks(8)).append("<div class=\"span2 left\">").append(BR);
//  	   	sb.append(getBlanks(9)).append("<input type=\"text\" id=\" \" name=\"\" placeholder=\" \">").append(BR);
  	   	sb.append(getBlanks(8)).append("</div>").append(BR);
  	   	sb.append(getBlanks(8)).append("<div class=\"span2 right\">").append(BR);
    	sb.append(getBlanks(9)).append("<label class=\"control-label\" for=\" \"> </label>").append(BR);
	   	sb.append(getBlanks(8)).append("</div>").append(BR);
	   	sb.append(getBlanks(8)).append("<div class=\"span2 left\">").append(BR);
	   	sb.append(getBlanks(9)).append("<input type=\"hidden\" id=\"pageNo\" name=\"pageNo\" value=\"1\">").append(BR);
	   	sb.append(getBlanks(9)).append("<input class=\"btn btn-primary\" type=\"submit\" id=\"searchButton\" value=\"查询\">").append(BR);
	   	sb.append(getBlanks(9)).append("<a href=\"$baseurl/\" class=\"btn btn-inverse\">新增</a>").append(BR);
	   	sb.append(getBlanks(8)).append("</div>").append(BR);
		sb.append(getBlanks(7)).append("</div>").append(BR);	
		sb.append(getBlanks(6)).append("</form>").append(BR);
	    sb.append(getBlanks(5)).append("</div>").append(BR);     
	    sb.append(getBlanks(5)).append("<!-- 信息搜索结束 -->").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<hr>").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<div class=\"row-fluid\">").append(BR);
		sb.append(getBlanks(6)).append("<div class=\"span12\">").append(BR);
		sb.append(BR);
		sb.append(getBlanks(7)).append("<!--通知消息開始-->").append(BR);
	    sb.append(getBlanks(7)).append("<div id=\"message\" class=\"alert #if($success) alert-success #else alert-warning #end\"").append(BR);
        sb.append(getBlanks(8)).append("#if($show) style=\"display:block\" #else style=\"display:none\"  #end>").append(BR);
        sb.append(getBlanks(8)).append("<button data-dismiss=\"alert\" class=\"close\" type=\"button\">×</button>").append(BR);
		sb.append(getBlanks(8)).append("<div id=\"messageContent\">").append(BR);
		sb.append(getBlanks(9)).append("<ul>").append(BR);
		sb.append(getBlanks(10)).append("#foreach($message in $messages)").append(BR);
		sb.append(getBlanks(11)).append("<li>$message</li>").append(BR);
		sb.append(getBlanks(10)).append("#end").append(BR);
    	sb.append(getBlanks(9)).append("</ul>").append(BR);
    	sb.append(getBlanks(8)).append("</div>").append(BR);
		sb.append(getBlanks(7)).append("</div>").append(BR);	
		sb.append(getBlanks(7)).append("<!--通知消息結束-->").append(BR);
		sb.append(BR);
		sb.append(getBlanks(7)).append("<table class=\"table table-bordered table-hover table-condensed\" >").append(BR);
		sb.append(getBlanks(8)).append("<thead>").append(BR);
		sb.append(getBlanks(9)).append("<tr >").append(BR);
		sb.append(getBlanks(10)).append("<th style=\"width:3%; text-align:center\" >#</th>").append(BR);
		for(Field field : fields){
			Comment comment = field.getAnnotation(Comment.class);
			if (comment != null) {
				sb.append(getBlanks(10)).append("<th style=\"width:10%; text-align:center\" >").append(comment.value()).append("</th>").append(BR);
			}
		}
        sb.append(getBlanks(9)).append("</tr>").append(BR);
	    sb.append(getBlanks(8)).append("</thead>").append(BR);
		sb.append(getBlanks(8)).append("<tbody>  ").append(BR); 
        sb.append(getBlanks(9)).append(" <tr>").append(BR);
        sb.append(getBlanks(10)).append("<td>").append(BR);
        sb.append(getBlanks(10)).append("</td>").append(BR);
        sb.append(getBlanks(9)).append("</tr>").append(BR);
        sb.append(getBlanks(8)).append("</tbody>").append(BR);
        sb.append(getBlanks(7)).append("</table>").append(BR);
        sb.append(BR);
        sb.append(getBlanks(6)).append("</div>").append(BR);
        sb.append(getBlanks(5)).append("</div><!--/row-->").append(BR);
        sb.append(BR);
        sb.append(getBlanks(5)).append("<!-- 分页开始 -->").append(BR);
        sb.append(getBlanks(5)).append("#parse(\"common/page.vm\")").append(BR);
	    sb.append(getBlanks(5)).append(" <!-- 分页结束 -->").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(5)).append("<!-- 信息列表结束 -->").append(BR);
	    sb.append(BR);
	    sb.append(getBlanks(4)).append("</div><!--/span-->").append(BR);
        sb.append(getBlanks(3)).append("</div><!--/row-->").append(BR);
        sb.append(BR);
        sb.append(getBlanks(3)).append("<hr>").append(BR);
        sb.append(BR);
        sb.append(getBlanks(3)).append("<footer>").append(BR);
        sb.append(getBlanks(4)).append("<p>© Company 2012</p>").append(BR);
        sb.append(getBlanks(3)).append("</footer>").append(BR);
        sb.append(getBlanks(2)).append("</div><!--/.fluid-container-->").append(BR);
        sb.append(BR);      
        sb.append(getBlanks(2)).append("<!-- Le javascript   ================================================== -->").append(BR);
        sb.append(getBlanks(2)).append("<!-- Placed at the end of the document so the pages load faster -->").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\" src=\"$baseurl/jquery/jquery-1.9.1.js\"></script>").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\" src=\"$baseurl/bootstrap/js/bootstrap.js\"></script>").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\" src=\"$baseurl/bootstrap/js/bootstrap-ie.js\"></script>").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\" src=\"$baseurl/my97/WdatePicker.js\" ></script>").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\" src=\"$baseurl/js/page.js\" ></script>").append(BR);
        sb.append(getBlanks(2)).append("<script type=\"text/javascript\">").append(BR);
        sb.append(BR);
        sb.append(getBlanks(2)).append("</script>").append(BR);
        sb.append(BR);
        sb.append(getBlanks(1)).append("</body>").append(BR);
        sb.append(getBlanks(0)).append("</html>").append(BR);

		return sb;
	}

	@Override
	protected String getOutputFileName(Class<?> clazz) {
		return getFilePath().concat(firstLetterLower(clazz.getSimpleName())).concat("List.vm");
	}

}
