package com.vaibhavsagar.autocharging;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.vaibhavsagar.autocharging.Kernel32.SYSTEM_POWER_STATUS;

@ExtendWith(MockitoExtension.class)
class AutochargingConfigServiceTest {

	private AutochargingConfigService autochargingConfigService;

	@Mock
	private Logger logger;

	@Mock
	private WebClient.Builder webClientBuilder;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@BeforeEach
	private void setUp() {
		autochargingConfigService = Mockito.mock(AutochargingConfigService.class,
				Mockito.withSettings().useConstructor("Key", 40, 100, "low_battery", "full_battery", webClientBuilder));
		Mockito.doCallRealMethod().when(autochargingConfigService).scheduleFixedDelayTask();
		ReflectionTestUtils.setField(autochargingConfigService, "logger", logger);
		ReflectionTestUtils.setField(autochargingConfigService, "formatter", formatter);
	}

	private SYSTEM_POWER_STATUS createBatteryStatus(int batteryPercentage, boolean isCharging) {
		SYSTEM_POWER_STATUS batteryStatus = new SYSTEM_POWER_STATUS();
		batteryStatus.BatteryLifePercent = (byte) batteryPercentage;
		batteryStatus.ACLineStatus = (byte) (isCharging ? 1 : 0);
		return batteryStatus;
	}

	@Test
	void testScheduleFixedDelayTaskWhenBattery50AndNotCharging() {
		SYSTEM_POWER_STATUS batteryStatus = createBatteryStatus(50, false);
		Mockito.when(autochargingConfigService.batteryStatus()).thenReturn(batteryStatus);
		autochargingConfigService.scheduleFixedDelayTask();
		verify(logger).info("Battery: {}, Status: {}", "50%", "Not Charging");
		verify(logger).info(eq("No Action Taken At : {}"), anyString());
	}

	@Test
	void testScheduleFixedDelayTaskWhenBattery50AndCharging() {
		SYSTEM_POWER_STATUS batteryStatus = createBatteryStatus(50, true);
		Mockito.when(autochargingConfigService.batteryStatus()).thenReturn(batteryStatus);
		autochargingConfigService.scheduleFixedDelayTask();
		verify(logger).info("Battery: {}, Status: {}", "50%", "Charging");
		verify(logger).info(eq("No Action Taken At : {}"), anyString());
	}

//	@Test
//	void testScheduleFixedDelayTaskWhenBattery100AndNotCharging() {
//		SYSTEM_POWER_STATUS batteryStatus = createBatteryStatus(100, false);
//		Mockito.when(autochargingConfigService.batteryStatus()).thenReturn(batteryStatus);
//		autochargingConfigService.scheduleFixedDelayTask();
//		verify(logger).info("Battery: {}, Status: {}", "100%", "Not Charging");
//		verify(logger).info(eq("No Action Taken At : {}"), anyString());
//	}
//
//	@Test
//	void testScheduleFixedDelayTaskWhenBattery100AndCharging() {
//		SYSTEM_POWER_STATUS batteryStatus = createBatteryStatus(100, true);
//		Mockito.when(autochargingConfigService.batteryStatus()).thenReturn(batteryStatus);
//		autochargingConfigService.scheduleFixedDelayTask();
//		verify(logger).info("Battery: {}, Status: {}", "100%", "Charging");
//		verify(logger).info(eq("No Action Taken At : {}"), anyString());
//	}

}
