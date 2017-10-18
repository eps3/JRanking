package cn.sheep3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sheep3 on 2017/10/17.
 */
@Data
@NoArgsConstructor
public class TopicForm {

    @NotNull
    @Size(min=1, max=20)
    private String name;

    @NotNull
    @Size(max=140)
    private String desc;

}
