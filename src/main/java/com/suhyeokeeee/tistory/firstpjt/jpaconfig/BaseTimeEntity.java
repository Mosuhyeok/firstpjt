package com.suhyeokeeee.tistory.firstpjt.jpaconfig;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // Jpa의 entity가 이 클래스를 상속할경우 아래 필드들도 컬럼으로 인식하게 해줌
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity에 Auditing추가
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
