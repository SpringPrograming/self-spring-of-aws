package com.jongyun.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Target 이 annotation 이 생성될 수 있는 위치를 지정합니다.
// PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용 가능
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
// 이 파일을 어노테이션 클래스로 지정합니다.
// LoginUser 라는 이름을 가진 어노테이션이 생성되었다고 보면 됨
public @interface LoginUser {
}
