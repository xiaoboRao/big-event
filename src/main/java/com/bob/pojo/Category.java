package com.bob.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {

    @NotNull(groups = { update.class })
    private Integer id;//主键ID
    @NotNull(groups = { update.class, add.class })

    private String categoryName;//分类名称
    @NotNull(groups = { update.class, add.class })
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    public interface add {}
    public interface update {}
}


