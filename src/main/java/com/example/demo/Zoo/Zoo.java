package com.example.demo.Zoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Y7993
 */
@Component
public class Zoo {
    private final Dog dog;
    private final Cat cat;

    // 构造函数注入
    @Autowired
    public Zoo(Dog dog, Cat cat) {
        this.dog = dog;
        this.cat = cat;
    }

    public void animalSounds() {
        System.out.println("Dog says: " + dog.bark());
        System.out.println("Cat says: " + cat.meow());
    }
}
