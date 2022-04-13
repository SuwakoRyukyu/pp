import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        Cat firstCat = applicationContext.getBean("cat", Cat.class);
        Cat secondCat = applicationContext.getBean("cat", Cat.class);
        if (firstCat == secondCat) {
            System.out.println("Objects have the same link");
        } else {
            System.out.println("Objects have different links");
        }
    }
}