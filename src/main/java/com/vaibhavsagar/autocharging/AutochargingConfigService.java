package com.vaibhavsagar.autocharging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * @author Vaibhav Sagar
 */
@Configuration
@Service
@EnableScheduling
public class AutochargingConfigService {

	private String webhookKey;

	private int minBatteryPercentage;

	private int maxBatteryPercentage;

	private String eventNameToTurnSwitchOn;

	private String eventNameToTurnSwitchOff;

	private final Logger logger = LoggerFactory.getLogger(AutochargingConfigService.class);

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@Lookup
	public Kernel32.SYSTEM_POWER_STATUS batteryStatus() {
		return null;
	}

	private WebClient.Builder webClientBuilder;

	@Autowired
	public AutochargingConfigService(@Value("${webhook.key}") final String webhookKey,
			@Value("${battery.percentage.min}") final int minBatteryPercentage,
			@Value("${battery.percentage.max}") final int maxBatteryPercentage,
			@Value("${event.switch.on_trigger_name}") final String eventNameToTurnSwitchOn,
			@Value("${event.switch.off_trigger_name}") final String eventNameToTurnSwitchOff,
			final WebClient.Builder webClientBuilder) {
		this.webhookKey = webhookKey;
		this.minBatteryPercentage = minBatteryPercentage;
		this.maxBatteryPercentage = maxBatteryPercentage;
		this.eventNameToTurnSwitchOn = eventNameToTurnSwitchOn;
		this.eventNameToTurnSwitchOff = eventNameToTurnSwitchOff;
		this.webClientBuilder = webClientBuilder;
	}

	/**
	 * Calls the web hook service based on battery percentage.
	 */
	@Scheduled(fixedDelayString = "${delay.in.millis}")
	public void scheduleFixedDelayTask() {
		logger.info("Battery: {}, Status: {}", batteryStatus().getBatteryLifePercent(),
				batteryStatus().getACLineStatusString());

		String now = getCurrentDateTime();
		if (isFullyCharged()) {
			logger.info("Charging Turned Off At : {}", now);
			callIFTTTWebHook(eventNameToTurnSwitchOff);
		} else if (isBatteryLow()) {
			logger.info("Charging Turned On At : {}", now);
			callIFTTTWebHook(eventNameToTurnSwitchOn);
		} else {
			logger.info("No Action Taken At : {}", now);
		}
	}

	private String getCurrentDateTime() {
		return LocalDateTime.now().format(formatter);
	}

	/**
	 * Checks whether battery is equal to or greater than the full percentage.
	 * 
	 * @return boolean result of the check
	 */
	private boolean isFullyCharged() {
		return Integer
				.parseInt(batteryStatus().getBatteryLifePercent().substring(0,
						batteryStatus().getBatteryLifePercent().length() - 1)) >= maxBatteryPercentage
				&& batteryStatus().getACLineStatusString().equals("Charging");
	}

	/**
	 * Checks whether battery is equal to or less than the low percentage.
	 * 
	 * @return boolean result of the check
	 */
	private boolean isBatteryLow() {
		return Integer
				.parseInt(batteryStatus().getBatteryLifePercent().substring(0,
						batteryStatus().getBatteryLifePercent().length() - 1)) <= minBatteryPercentage
				&& batteryStatus().getACLineStatusString().equals("Not Charging");
	}

	/**
	 * Calls the IFTTT web hook with the passed event name. The key is picked from
	 * the application.yaml file.
	 * 
	 * @param eventName The event to be triggered.
	 */
	private void callIFTTTWebHook(String eventName) {
		String uri = "https://maker.ifttt.com/trigger/" + eventName + "/with/key/" + webhookKey;
		try {
			ResponseEntity<String> response = webClientBuilder.build().get().uri(uri).retrieve().toEntity(String.class)
					.block();
			if (response != null) {
				logger.info("Web hook response status : {} for web request: {}", response.getStatusCode(), uri);
			} else {
				throw new IllegalStateException("No response from the IFTTT service");
			}
		} catch (WebClientResponseException ex) {
			logger.error("Web hook error status : {} for web request: {} with message: {}", ex.getStatusCode(), uri,
					ex.getMessage());
		} catch (IllegalStateException ex) {
			logger.error(ex.getMessage());
		}
	}

}
