package learn.org.springframework.boot.test;


import learn.org.springframework.boot.test.bind.bean.SpringBootBindTestBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.ApplicationContext;

/**
 * 功能：
 *
 * @author zengyuan on 2019/1/14.
 * @see
 */
@SpringBootApplication
public class SimpleMainTests {


	@Test
	public void baseMainStartTest() {

		ApplicationContext applicationContext = SpringApplication.run(SimpleMainTests.class, new String[]{});

		SpringBootBindTestBean beforeBindBean = new SpringBootBindTestBean();
		beforeBindBean.setBindName("testBeanBindName");
		beforeBindBean.setBindType("testBeanBindType");

		BindResult<SpringBootBindTestBean> bind = Binder.get(applicationContext.getEnvironment())
				.bind(
						ConfigurationPropertyName.of("test.spring"),
						Bindable.of(SpringBootBindTestBean.class)
//								.withExistingValue(beforeBindBean)
//								.withSuppliedValue(() -> beforeBindBean)
//								.withAnnotations(annotations)
				);
		SpringBootBindTestBean bean = bind.orElse(null);
		Assert.assertNotNull(bean);
		System.out.println(bean.getBindType());
		System.out.println(bean.getBindName());
	}


}
