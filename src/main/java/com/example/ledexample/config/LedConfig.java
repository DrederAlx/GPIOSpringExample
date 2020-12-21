package com.example.ledexample.config;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "led.config")
public class LedConfig {

    private List<String> pinNames;

    public void setPinNames(List<String> pinNames) {
        this.pinNames = pinNames;
    }

    @Bean
    @Qualifier("ledMap")
    public Map<String, GpioPinDigitalOutput> pinDigitalOutput(){
        Map<String, GpioPinDigitalOutput> ledMap = new HashMap<>();
        for (String pinName : pinNames) {
            ledMap.put(pinName, GpioFactory.getInstance().provisionDigitalOutputPin(
                    RaspiPin.getPinByName(pinName), // Номер пина по WiringPi
                    pinName, // Имя пина (необязательный)
                    PinState.LOW) // Состояние пина при запуске (необязательный)
            );
        }
        return ledMap;
    }
}