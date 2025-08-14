package org.delivery.storeadmin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 실행시 적용
@Service // Bean으로 등록하고 이 Business 어노테이션을 인식 후 Bean 등록
public @interface Converter {

  @AliasFor(annotation = Service.class)
  String value() default "";
}
