package com.study.awswebstudy.domain.Pages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
public class Pages {

    @NotNull
    private Integer start;
    @NotNull
    private Integer limit;
    private Integer total;

    public int getOffset() {
        return start - 1;
    }
}
