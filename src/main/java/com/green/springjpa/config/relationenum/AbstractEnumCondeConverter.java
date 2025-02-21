package com.green.springjpa.config.relationenum;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter(autoApply = true)
public abstract class AbstractEnumCondeConverter<E extends Enum<E> & EnumMapperType>
        implements AttributeConverter<E,String> {
    private final Class<E> targetEnumClass;
    private final boolean nullable; //null가능
    private final String enumName; // 문제가 생겼을 때 어떤 ENUM 때문인지 출력하기 위해 사용

    //DB에 값을 넣을 때 사용 (insert,update)
    @Override
    public String convertToDatabaseColumn(E enemItem) {
        if (!nullable && enemItem == null) { //null허용을 하지 않았는데 enemItem 값이 null이었다면
            throw new IllegalArgumentException(String.format("%s(는)은 NULL로 저장할 수 없습니다.", enumName));
        }
        return EnumConvertUtils.toCode(enemItem);
    }

    //DB에서 갑을 가져올 때 (select)
    @Override
    public E convertToEntityAttribute(String dbData) {
        if (!nullable && StringUtils.isBlank(dbData) || dbData == null) {
            throw new IllegalArgumentException(String.format("%s가 DB에 NULL 혹은 Empty로 저장되어 있습니다.", enumName));
        }
        return EnumConvertUtils.ofCode(targetEnumClass, dbData);
    }
}