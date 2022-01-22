package HELLOz.hello.springz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
        /*resource:static에 만들어 둔 기본 url로 접속 시 나오는
        index.html이 안나오고 이 home.html이 실행되는 이유?

        우선순위가 있음. 요청받으면 톰캣이 우선 컨트롤러부터 찾음. 컨트롤러에서 해당 소스 있으면 그거 반환
        없으면 static으로 넘어가서 static에 있는 걸 반환.
        여기선 homecontroller에 기본 url인 localhost:8080에 해당하는 home.html이 있으니까
        이게 호출되어 반환되는는 것!
        */
    }
}
