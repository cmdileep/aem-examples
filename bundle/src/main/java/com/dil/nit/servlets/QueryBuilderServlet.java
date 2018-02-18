package com.dil.nit.servlets;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;

@SlingServlet(paths = "/bin/queryexample.html", methods = "GET", metatype = true)
public class QueryBuilderServlet extends SlingAllMethodsServlet {

	
	@Reference
    private QueryBuilder queryBuilder;
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {

	}	

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {
		
		final Map<String, String> map = new HashMap<String, String>();
		map.put("type", "cq:PageContent");
			map.put("property", "cq:template");
		map.put("property.value", "/apps/geometrixx/templates/homepage");
		map.put("p.limit", "20");
		 
		 Query query = queryBuilder.createQuery(PredicateGroup.create(map), request.getResourceResolver().adaptTo(Session.class));
		
	        SearchResult result = query.getResult();
	        
	        int count=query.getResult().getHits().size();
	       response.getWriter().write("The final result-->"+count);
		
	}

	
}
