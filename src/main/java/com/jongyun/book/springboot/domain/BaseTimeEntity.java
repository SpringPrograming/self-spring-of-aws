package com.jongyun.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
* 모든 Entity 의 상위 클래스가 되어 Entity 들의
* createdDate,m modifiedDate 를 자동으로 관리하는 역활 입니다.
 */
@Getter
/*
* MappedSuperclass
* JPA Entity 클래스 들이 BaseTimeEntity 를 상속할 경우 필드들
* createdDate, modifiedDate 도 column 으로 인식하도록 합니다.
 */
@MappedSuperclass
/*
* EntityListeners BaseTimeEntity class 에 Auditing 기능을 포함시킵니다.
 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
