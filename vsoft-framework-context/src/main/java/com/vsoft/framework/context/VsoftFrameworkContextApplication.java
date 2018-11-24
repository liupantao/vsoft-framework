package com.vsoft.framework.context;

import com.vsoft.framework.context.annotation.ModuleScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ModuleScan
@SpringBootApplication
public class VsoftFrameworkContextApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsoftFrameworkContextApplication.class, args);
    }
}
