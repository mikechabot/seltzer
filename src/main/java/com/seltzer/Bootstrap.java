package com.seltzer;

import com.seltzer.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Bootstrap {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Seltzer seltzer = Seltzer.getInstance();
        seltzer.start();

    }

}
