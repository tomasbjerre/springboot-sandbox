package se.bjurr.springboot.properties.beans;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import se.bjurr.springboot.properties.annotations.ExampleProperty;

@Component
public class ExamplePropertyBeanPostProcessor implements BeanPostProcessor {
  @Autowired private Environment env;

  @Override
  public Object postProcessBeforeInitialization(final Object bean, final String beanName)
      throws BeansException {
    if (bean == null) {
      return bean;
    }
    final Field[] declaredFields = bean.getClass().getDeclaredFields();
    for (final Field declaredField : declaredFields) {
      final ExampleProperty annotation = declaredField.getAnnotation(ExampleProperty.class);
      if (annotation == null) {
        continue;
      }
      ReflectionUtils.makeAccessible(declaredField);
      try {
        final String value = this.env.resolvePlaceholders(annotation.value());
        if (declaredField.getType() == String.class) {
          declaredField.set(bean, value);
        } else if (declaredField.getType() == List.class) {
          declaredField.set(bean, this.toList(value));
        }
      } catch (final Exception e) {
        throw new RuntimeException(e);
      }
    }
    return bean;
  }

  private List<String> toList(final String value) {
    return Arrays.asList(value.split("[,\\r?\\n]")).stream()
        .map(it -> it.trim())
        .filter(it -> !it.startsWith("#"))
        .filter(it -> !it.isEmpty())
        .collect(Collectors.toList());
  }
}
