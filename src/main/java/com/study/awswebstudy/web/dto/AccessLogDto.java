package com.study.awswebstudy.web.dto;

import com.study.awswebstudy.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AccessLogDto extends BaseTimeEntity {

    @Id
    private Long sn;

    private int postId; // PostDto -> ID

    private Long id; // User -> ID

    private String name;  // User Name

    private String requestUri;

    private String className;

    private String classSimpleName;

    private String methodName;

    private String remoteAddr;

    private LocalDateTime regDate;
    private LocalDateTime updDate;

}
