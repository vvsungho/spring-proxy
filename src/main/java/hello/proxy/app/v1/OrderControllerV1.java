package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // 스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식한다. 차이점은 스프링이 Component scan 하는가 여부, bean 수동등록을 위해 @RequestMapping 사용한다.
@ResponseBody
public interface OrderControllerV1 {
  @GetMapping("/v1/request")
  // @RequestParam을 명시하지 않으면 interface 레벨에선 자바버전에 따라서 실행시점에 에러가 발생한다.
  String request(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/no-log")
  String noLog();
}
