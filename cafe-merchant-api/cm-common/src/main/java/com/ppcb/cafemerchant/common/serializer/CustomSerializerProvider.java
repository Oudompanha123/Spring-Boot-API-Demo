package com.ppcb.cafemerchant.common.serializer;//package com.kosign.weinvoice.common.serializer;
//
//import com.fasterxml.jackson.databind.BeanProperty;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializationConfig;
//import com.fasterxml.jackson.databind.cfg.CacheProvider;
//import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
//import com.fasterxml.jackson.databind.ser.SerializerFactory;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//public class CustomSerializerProvider extends DefaultSerializerProvider {
//    public CustomSerializerProvider() {
//        super();
//    }
//
//    public CustomSerializerProvider(CustomSerializerProvider provider, SerializationConfig config,
//                                    SerializerFactory jsf) {
//        super(provider, config, jsf);
//    }
//
//    @Override
//    public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
//        return new CustomSerializerProvider(this, config, jsf);
//    }
//
//    @Override
//    public DefaultSerializerProvider withCaches(CacheProvider cacheProvider) {
//        return null;
//    }
//
//    @Override
//    public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
//        Class<?> propertyClass = property.getType().getRawClass();
//
//        if (isStringOrDate(propertyClass)) {
//            return Serializers.EMPTY_STRING_SERIALIZER_INSTANCE;
//        }
//
//        if (isBigDecimal(propertyClass)) {
//            return getBigDecimalSerializer();
//        }
//
//        return super.findNullValueSerializer(property);
//    }
//
//    private boolean isStringOrDate(Class<?> propertyClass) {
//        return propertyClass.equals(String.class)
//                || propertyClass.equals(LocalDate.class)
//                || propertyClass.equals(LocalDateTime.class);
//    }
//
//    private boolean isBigDecimal(Class<?> propertyClass) {
//        return propertyClass.equals(BigDecimal.class);
//    }
//
//    private JsonSerializer<Object> getBigDecimalSerializer() {
//        return Serializers.NULL_TO_ZERO_SERIALIZER_INSTANCE;
//    }
//
//}
