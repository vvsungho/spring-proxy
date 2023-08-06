package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

  @Test
  void reflection0() {
    Hello target = new Hello();

    // 공통 로직 1 시작
    log.info("start");
    String result1 = target.callA(); // 호출하는 메서드가 다름, 전체 코드 흐름은 같다.
    log.info("result={}",result1);
    // 공통 로직 1 종료

    // 공통 로직 2 시작
    log.info("start");
    String result2 = target.callB(); // 호출하는 메서드가 다름, 전체 코드 흐름은 같다.
    log.info("result={}",result2);
    // 공통 로직 2 종료
  }

  @Test
  void reflection1()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // 클래스 정보
    Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    // callA 메서드 정보
    Method methodCallA = classHello.getMethod("callA");
    Object result1 = methodCallA.invoke(target);
    log.info("result={}",result1);

    // callB 메서드 정보
    Method methodCallB = classHello.getMethod("callB");
    Object result2 = methodCallB.invoke(target);
    log.info("result={}",result2);
  }

  /**
   * reflection은 컴파일 단계에서 확인이 불가하다. 런타임 오류의 가능성을 내제하고 있음. e.g) 메서드 이름 틀렸을 때..
   * 반드시 공통적으로 처리해야할 부분이 있는 것이 아니면 지양하는 것이 좋다.
   */
  @Test
  void reflection2()
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // 클래스 정보
    Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

    Hello target = new Hello();
    // callA 메서드 정보
    Method methodCallA = classHello.getMethod("callA");
    dynamicCall(methodCallA, target);

    // callB 메서드 정보
    Method methodCallB = classHello.getMethod("callB");
    dynamicCall(methodCallB, target);
  }

  private void dynamicCall(Method method, Object target)
      throws InvocationTargetException, IllegalAccessException {
    // 공통 로직 시작
    log.info("start");
    Object result = method.invoke(target);
    log.info("result={}",result);
  }

  @Slf4j
  static class Hello {
    public String callA() {
      log.info("callA");
      return "A";
    }

    public String callB() {
      log.info("callB");
      return "B";
    }
  }
}
