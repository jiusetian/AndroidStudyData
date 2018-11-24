package com.lib_java.compileAnnotation;

import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by XR_liu on 2018/11/23.
 */
@AutoService(Process.class)
public class CreateSqlProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes()
    {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(RAnnotation.Entity.class.getCanonicalName());
        return supportTypes;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        return false;
    }
}
