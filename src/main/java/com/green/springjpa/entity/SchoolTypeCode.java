package com.green.springjpa.entity;

import com.green.springjpa.config.relationenum.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //강제성이 있으므로 빼면 에러가난다.
@RequiredArgsConstructor
public enum SchoolTypeCode implements EnumMapperType {

    ELEMENTARY("00101", "초등학교")
    ,MIDDLE("00102", "중학교")
    ,HIGH("00103","고등학교")
    ;

    private final String code;
    private final String value;
}
