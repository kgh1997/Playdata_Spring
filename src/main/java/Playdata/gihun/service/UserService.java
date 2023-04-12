package Playdata.gihun.service;

import Playdata.gihun.user.SignUp;
import Playdata.gihun.user.SignUpRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 레퍼지토리와 연결이 됨
public class UserService {
    private final SignUpRepository signUpRepository;
    private final PasswordEncoder passwordEncoder;
    // 생성자 자동 생성
    public UserService(SignUpRepository signUpRepository, PasswordEncoder passwordEncoder) {
        this.signUpRepository = signUpRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // 컨트롤러에서 끌어다 쓰면 저장은 할 수 있음
    public SignUp create(String username, String password, String email) {
        SignUp user = new SignUp();
        user.setUsername(username);

        // Bean으로 등록을 해놓으면 메모리면에서 효과를 볼 수 있음(암호화 생성자 지정)
        user.setPassword(passwordEncoder.encode(password)); // 암호는 암호화가 되어서 저장이 되어야 함
        user.setEmail(email);
        this.signUpRepository.save(user);
        return user;
    }

}
