package hello.proxy;

import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 프록시 : 클라이언트와 서버 사이에서 부가기능 및 접근제어를 담당하는 것
 * 웹서버, 객체 프록시는 규모의 차이일 뿐 기능은 같다.
 */
//@Import(AppV1Config.class)
//@Import(AppV2Config.class)
//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
@Import(DynamicProxyFilterConfig.class)
//@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의 -> hello.proxy.app 하위의 컴퍼넌트만 스캔한다.
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의 -> hello.proxy.app 하위의 컴퍼넌트만 스캔한다.
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
