package com.example.ledexample.parser;

import com.example.ledexample.config.ParserConfig;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@EnableScheduling
@Component
public class Parser {
    private final ParserConfig config;
    private final Map<String, GpioPinDigitalOutput> ledMap;

    @Autowired
    public Parser(ParserConfig config, @Qualifier("ledMap") Map<String, GpioPinDigitalOutput> ledMap) {
        this.config = config;
        this.ledMap = ledMap;
    }

    @Scheduled(fixedRate = 1800000)
    public void start() {

        int trafficJamScore = 0;

        try {
            Document document = Jsoup.connect(config.getLink()).get();
            Elements elements = document.select(".traffic__rate-text");
            trafficJamScore = Integer.parseInt(elements.first().text());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (GpioPinDigitalOutput pin : ledMap.values()) {
            pin.low();
        }

        if (trafficJamScore > 0 && trafficJamScore <= 3) {
            ledMap.get("GPIO 0").high();
        } else if (trafficJamScore > 3 && trafficJamScore <= 6) {
            ledMap.get("GPIO 2").high();
        } else if (trafficJamScore > 6 && trafficJamScore <= 10) {
            ledMap.get("GPIO 3").high();
        }
    }
}