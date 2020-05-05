package infrastructure.stereotypes;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Stereotype
@ApplicationScoped
@Retention(RUNTIME)
@Target(TYPE)
public @interface Repository {
}
