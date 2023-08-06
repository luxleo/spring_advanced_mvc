package com.typeconverter.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntConvertor implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        log.info("convert source = {}",source);
        return Integer.valueOf(source);
    }
}
