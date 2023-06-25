package dev.dmgiangi.solar;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolarApplication.class, args);
	}

	@Scheduled(initialDelay = 1000, fixedDelay = 500)
	public void task(){
		W1Master w1Master = new W1Master();
		for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
			  log.info(String.format("%-20s %3.1f°C %3.1f°F\n", device.getName(), device.getTemperature(),
			 device.getTemperature(TemperatureScale.CELSIUS)));
		}
	}
}
