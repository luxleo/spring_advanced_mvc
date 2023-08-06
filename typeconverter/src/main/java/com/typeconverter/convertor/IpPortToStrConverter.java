package com.typeconverter.convertor;

import com.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStrConverter implements Converter<IpPort, String> {
    @Override
    public String convert(IpPort source) {
        log.info("convert source = {}",source);
        // IpPort객체 -> 127.0.0.1:8000
        return source.getIp()+":" +source.getPort();
    }
}
