package com.sparta.board.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass//해당 추상 클래스 상속할 경우 밑에 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)//Auditing 기능 포함
public abstract class Timestamped {

    @CreatedDate//생성 시간
    @Column(updatable = false)//최초 시간만 저장
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate//변경 시간
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}