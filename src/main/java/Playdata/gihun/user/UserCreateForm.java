package Playdata.gihun.user;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AssociationOverrides;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// lombok 라이브러리로 게터세터 어노테이션으로 활용
@Getter
@Setter
public class UserCreateForm {
    @Size(min=3, max=20)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.") // 비어있는 값을 넣을 때 메시지 츨력 가능
    private String username;
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;
    @NotEmpty(message = "비밀번호 재확인은 필수항목입니다.")
    private String password2;
    @Email // 이메일 형식
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;
}
