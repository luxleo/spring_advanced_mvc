package com.typeconverter.converter;

import com.typeconverter.convertor.IntToStrConverter;
import com.typeconverter.convertor.IpPortToStrConverter;
import com.typeconverter.convertor.StringToIntConvertor;
import com.typeconverter.convertor.StringToIpPortConverter;
import com.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {
    @Test
    void conversionServiceTest(){
        DefaultConversionService conversion = new DefaultConversionService();
        conversion.addConverter(new IntToStrConverter());
        conversion.addConverter(new StringToIntConvertor());
        conversion.addConverter(new StringToIpPortConverter());
        conversion.addConverter(new IpPortToStrConverter());

        assertThat(conversion.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversion.convert(10, String.class)).isEqualTo("10");
        assertThat(conversion.convert("127.0.0.1:8000", IpPort.class))
                .isEqualTo(new IpPort("127.0.0.1", 8000));
        assertThat(conversion.convert(new IpPort("127.0.0.1", 8000), String.class))
                .isEqualTo("127.0.0.1:8000");
    }
}
