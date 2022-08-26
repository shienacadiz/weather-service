# weather-service
A simple API to check the weather based on a specific location.<br>
Integrated with [OpenWeatherMap API](https://openweathermap.org/current) to get real time weather forecast.<br><br>

---
## Demo
Access the live demo here: [https://weather-forecast-sqc-service.herokuapp.com/swagger-ui/index.html](https://weather-forecast-sqc-service.herokuapp.com/swagger-ui/index.html)<br>
*<b>Documentations included via OpenAPI</b><br><br>

## Development
### Prerequisite
- Make sure `Maven`, `JDK8` and `Git` are installed in your machine.

### Setup in local machine
- Clone the repository `git clone https://github.com/shienacadiz/weather-service.git`
- Go into the root directory `cd weather-service`
- Build the service using `mvn clean install`
- Run the service `mvn spring-boot:run`
- You may now access the service using the local URL `http://localhost:8080`<br>

### Integration with OpenWeatherMap third party API
- The service utilises [OpenWeatherMap](https://openweathermap.org/) endpoint `https://api.openweathermap.org/data/2.5/weather?q={city_name}&appid={api_key}&units={unit}` to get real time weather reports<br>
  <br>where ... <br>
<table>
  <tr><td>Parameters</td><td></td><td>Description</td></tr>
  <tr><td>q</td><td>required</td><td>City name, state code and country code divided by comma. Please note that searching by states available only for the USA locations.<br>
      This field can accept `Brisbane` or `Brisbane, Queensland, AU` and etc<br>We can specify state and country to avoid ambiguity</td></tr>
  <tr><td>appid</td><td>required</td><td>Unique API key configured in application.properties</td></tr>
  <tr><td>limit</td><td>optional</td><td>Units of measurement. Valid values are [celsius, fahrenheit, kelvin]</td></tr>
</table>

- [FeignClient](https://javadoc.io/doc/org.springframework.cloud/spring-cloud-netflix-core/1.2.1.RELEASE/org/springframework/cloud/netflix/feign/FeignClient.html) has been used to simplify the service to service API invocations. To enable it, dependency below have been imported
```bash
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>3.1.3</version>
  </dependency>
```
- Since Feign is an annotation-based, declarative way of writing a web service clients, we only needed to declare `@EnableFeignClients` in the main class and added `@FeignClient` to the interfaces per HTTP client
```bash
@FeignClient(name = "weather", url = "${weather.url}")
public interface WeatherClient {
    ...
```
- URLs are configurable and can be changed dynamically in the `application.properties`
- Lastly, we added the method mapping of the remote endpoint to be invoked
```bash
@GetMapping("weather")
Weather getWeather(@RequestParam(name = "q") String location,
                   @RequestParam(name = "units") String units,
                   @RequestParam(name = "appId") String apiKey);
}
```
- The interface can now be used as an HTTP client
```bash
@Autowired
private WeatherClient weatherClient;
...
weatherClient.getWeather(location, unit, apiKey);
```