package com.dil.nit.servlets;

import java.io.IOException;
import java.rmi.ServerException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

@SlingServlet(paths = "/bin/mysearch.html", methods = "POST", metatype = true)
public class ExampleServlet extends SlingAllMethodsServlet {

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {

	}

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {
		
		response.setContentType("application/json");
		JSONObject jsonobject = new JSONObject();
		JSONObject jsonMain = new JSONObject();
		JSONArray arrayObj=new JSONArray();
		try {
			jsonobject.put("Name", "Nithya");
			jsonobject.put("Phone", "6148228287");
			arrayObj.put(jsonobject);
			jsonobject = new JSONObject();
			jsonobject.put("Name", "Dileep");
			jsonobject.put("Phone", "6362848459");
			arrayObj.put(jsonobject);
			jsonobject = new JSONObject();
			jsonobject.put("Name", "Dhanvi");
			jsonobject.put("Phone", "6362848459");
			arrayObj.put(jsonobject);
			jsonMain.put("Employee", arrayObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().write(jsonMain.toString());

		
		
		

	}

}
