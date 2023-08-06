package com.typeconverter;

import com.typeconverter.convertor.IntToStrConverter;
import com.typeconverter.convertor.IpPortToStrConverter;
import com.typeconverter.convertor.StringToIntConvertor;
import com.typeconverter.convertor.StringToIpPortConverter;
import com.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Integer -> String, String -> INteger 우선순위 때문에 주석 처리 한다.
        //registry.addConverter(new IntToStrConverter());
        //registry.addConverter(new StringToIntConvertor());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStrConverter());

        // formatter추가 String <-> Integer
        registry.addFormatter(new MyNumberFormatter());

    }
}
