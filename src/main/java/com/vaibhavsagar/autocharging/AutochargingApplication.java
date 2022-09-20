package com.vaibhavsagar.autocharging;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AutochargingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutochargingApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	/**
	 * Makes native calls to get the laptop's battery status.
	 * 
	 * @return Kernel32 object containing information about the battery status.
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Kernel32.SYSTEM_POWER_STATUS batteryStatus() {
		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
		return batteryStatus;
	}

}
