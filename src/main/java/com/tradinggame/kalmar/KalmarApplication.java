package com.tradinggame.kalmar;

import com.tradinggame.kalmar.game.model.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class KalmarApplication {

    //public static void main(String[] args) {
      //  SpringApplication.run(KalmarApplication.class, args);
    //}
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Map.class);
        Map map = context.getBean(Map.class);

        System.out.println(map.mapAsMatrix.toString());
    }
}
