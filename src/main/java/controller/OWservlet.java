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

		saveChoiceCookie(request, response);

		if (cityStr == null) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			weatherBean wBean = new weatherBean(cityStr, countryStr);

			request.setAttribute("wBean", wBean);
			GettheWeather.getWeather(wBean);
			setCookieFromRequest(request, response, wBean);

			RequestDispatcher rd = request.getRequestDispatcher("showWeather.jsp");
			rd.forward(request, response);

		}
	}

	private void setCookieFromRequest(HttpServletRequest request, HttpServletResponse response,
			weatherBean weatherBean) {
		if (request.getAttribute("useCookie") == null) {
			return;
		}
		if ((boolean) request.getAttribute("useCookie") == false) {
			return;
		}
		ArrayList<String> existingCookies = getExistingCookies(request);

		removeExcessCookie(existingCookies);

		existingCookies.add(request.getRequestURI() + "?" + request.getQueryString() + "|" + weatherBean.getCityStr()
				+ ":" + weatherBean.getCountryStr());

		saveCityCookies(response, existingCookies);
	}

	private void saveCityCookies(HttpServletResponse response, ArrayList<String> existingCookies) {
		for (int i = 0; i < existingCookies.size(); i++) {
			String value = existingCookies.get(i);

			response.addCookie(new Cookie("Cookie" + i, value));
		}
	}

	private void saveChoiceCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		// Searches for if a cookie called ckChoice exists or if a buttonResult has been
		// clicked in order to create
		// create a cookie. In both ifs checker is set to true
		boolean checker = false;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("ckChoice")) {
					checker = true;
					request.setAttribute("useCookie", cookies[i].getValue().equals("Accept"));
				} else if (request.getParameter("buttonResult") != null) {
					Cookie ckButtonResult = new Cookie("ckChoice", request.getParameter("buttonResult"));
					ckButtonResult.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(ckButtonResult);
					checker = true;
					request.setAttribute("useCookie", request.getParameter("buttonResult").equals("Accept"));
				}
			}
		}

	}

	private void removeExcessCookie(ArrayList<String> existingCookies) {
		if (existingCookies.size() == 5)
			existingCookies.remove(0);
	}

	private ArrayList<String> getExistingCookies(HttpServletRequest request) {
		ArrayList<String> existingCookies = new ArrayList<String>();

		for (int i = 0; i < 5; i++) {
			Cookie existing = GetCookie(request, "Cookie" + i);

			if (existing != null)
				existingCookies.add(existing.getValue());
		}
		return existingCookies;
	}

	public static Cookie GetCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if(cookies == null) return null;
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name))
				return cookie;
		}

		return null;
	}
}
