package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GettheWeather;
import model.weatherBean;

@WebServlet("/OWservlet")
public class OWservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cityStr = request.getParameter("city");
		String countryStr = request.getParameter("country");
		

		weatherBean wBean = new weatherBean(cityStr, countryStr);

		GettheWeather.getWeather(wBean);

		request.setAttribute("wBean", wBean);

		setCookieFromRequest(request, response, wBean);
		
		RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
		rd.forward(request, response);
	}

	private void setCookieFromRequest(HttpServletRequest request, HttpServletResponse response, weatherBean weatherBean) {
		ArrayList<String> existingCookies = getExistingCookies(request);

		removeExcessCookie(existingCookies);
		
		existingCookies.add(request.getRequestURI() + "?" + request.getQueryString() + "|" + weatherBean.getCityStr() + ":" + weatherBean.getCountryStr());

		saveCookies(response, existingCookies);
	}

	private void saveCookies(HttpServletResponse response, ArrayList<String> existingCookies) {
		for(int i = 0; i < existingCookies.size();i++) {
			String value = existingCookies.get(i);
			
			response.addCookie(new Cookie("Cookie" + i, value));
		}
	}

	private void removeExcessCookie(ArrayList<String> existingCookies) {
		if(existingCookies.size() == 5)
			existingCookies.remove(0);
	}

	private ArrayList<String> getExistingCookies(HttpServletRequest request) {
		ArrayList<String> existingCookies = new ArrayList<String>();
		
		for(int i = 0; i < 5;i++) {
			Cookie existing = GetCookie(request, "Cookie" + i);
			
			if(existing != null) 
				existingCookies.add(existing.getValue());
		}
		return existingCookies;
	}
	
	public static Cookie GetCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(name)) 
				return cookie;
		}
		
		return null;
	}
}
