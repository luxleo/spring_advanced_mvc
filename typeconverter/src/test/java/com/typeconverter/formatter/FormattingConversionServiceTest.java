package com.typeconverter.formatter;

import com.typeconverter.convertor.IpPortToStrConverter;
import com.typeconverter.convertor.StringToIpPortConverter;
import com.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        // 컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStrConverter());
        // 포매터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        // 서비스 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        Integer convertInt = conversionService.convert("1000", Integer.class);
        assertThat(convertInt).isEqualTo(1000L);

        String convertStr = conversionService.convert(1000, String.class);
        assertThat(convertStr).isEqualTo("1,000");
    }


}
