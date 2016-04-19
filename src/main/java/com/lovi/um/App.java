package com.lovi.um;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.lovi.puppy.PuppyApp;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        PuppyApp.create(App.class, "user-mgr", args).run(81);
    }
}
