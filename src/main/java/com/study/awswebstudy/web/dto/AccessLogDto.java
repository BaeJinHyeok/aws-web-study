package com.study.awswebstudy.web.dto;

import com.study.awswebstudy.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class AccessLogDto extends BaseTimeEntity {

    @Id
    private Long sn;

    private String Method;

    private int postId; // PostDto -> ID

    private Long id; // User -> ID

    private String name;  // User Name

}
