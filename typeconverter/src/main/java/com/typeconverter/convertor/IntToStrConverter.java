package com.typeconverter.convertor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IntToStrConverter implements Converter<Integer,String> {
    @Override
    public String convert(Integer source) {
        log.info("convert source = {}", source);
        return String.valueOf(source);
    }

}
