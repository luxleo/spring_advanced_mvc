package com.typeconverter.converter;

import com.typeconverter.convertor.IntToStrConverter;
import com.typeconverter.convertor.IpPortToStrConverter;
import com.typeconverter.convertor.StringToIntConvertor;
import com.typeconverter.convertor.StringToIpPortConverter;
import com.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {
    @Test
    void StringToInt() {
        StringToIntConvertor convertor = new StringToIntConvertor();
        Integer intVal = convertor.convert("10");
        assertThat(intVal).isEqualTo(10);
    }

    @Test
    void IntToString() {
        IntToStrConverter converter = new IntToStrConverter();
        String strVal = converter.convert(10);
        assertThat(strVal).isEqualTo("10");
    }
    @Test
    void StringToPort(){
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8000";
        IpPort ipPort = converter.convert(source);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8000));
    }
    @Test
    void IpPortToString(){
        IpPortToStrConverter converter = new IpPortToStrConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8000);
        String convert = converter.convert(ipPort);
        assertThat(convert).isEqualTo("127.0.0.1:8000");
    }
}
