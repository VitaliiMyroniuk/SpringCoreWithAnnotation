package com.epam.myroniuk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vitalii Myroniuk
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.myroniuk.service.impl"})
public class LoggersConfig {

}
