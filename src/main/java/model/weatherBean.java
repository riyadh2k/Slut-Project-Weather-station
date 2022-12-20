package model;

public class weatherBean {

	private String cityStr;

	private String countryStr;

	private String cloudsStr;

	private String temperatureStr;

	public weatherBean(String cityStr, String countryStr) {

		this.cityStr = cityStr;
		this.countryStr = countryStr;

	}

	public String getCityStr() {
		return cityStr;
	}

	public String getCountryStr() {
		return countryStr;
	}

	public String getCloudsStr() {
		return cloudsStr;
	}

	public void setCloudsStr(String cloudsStr) {
		this.cloudsStr = cloudsStr;
	}

	public String getTemperatureStr() {
		return temperatureStr;
	}

	public void setTemperatureStr(String temperatureStr) {
		this.temperatureStr = temperatureStr;
	}

}
