package com.glancebar.mybatisdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.Null;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Mapper
interface CarMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into car(make, model, year) values(#{make}, #{model}, #{year})")
    int insert(Car car);

    @Select("select * from CAR")
    Collection<Car> selectAll();

    Collection<Car> search(@Param("make") String make, @Param("model") String model, @Param("id") Long id);

}

@SpringBootApplication
public class MybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    CommandLineRunner demo(CarMapper carMapper) {
        return args -> {
            List<Car> cars = Arrays.asList(
                    new Car("Honda", "Civic", 1984, null),
                    new Car("BMW", "330i", 2012, null),
                    new Car("Infiniti", "Q50", 2014, null)
            );

            cars.forEach(car -> {
                carMapper.insert(car);
                System.out.println(car.toString());
            });

            carMapper.selectAll().forEach(System.out::println);

            carMapper.search("BMW", null, 2L).forEach(System.out::println);

        };
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {
    private String make, model;
    private int year;
    private Long id;
}