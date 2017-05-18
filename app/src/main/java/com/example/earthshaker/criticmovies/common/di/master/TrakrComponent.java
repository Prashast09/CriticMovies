package com.example.earthshaker.criticmovies.common.di.master;

import com.example.earthshaker.criticmovies.common.di.subdi.HttpComponent;
import com.example.earthshaker.criticmovies.common.di.subdi.HttpModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by earthshaker on 5/18/17.
 */

@Singleton @Component(modules = AppModule.class) public interface TrakrComponent {
  HttpComponent plus(HttpModule httpModule);
}
