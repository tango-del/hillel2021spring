package org.homework.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.homework")
@PropertySource("database.properties")
public class RootConfig {

}
