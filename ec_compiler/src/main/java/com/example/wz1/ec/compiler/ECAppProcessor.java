package com.example.wz1.ec.compiler;

import com.example.wz1.ec.annotation.AppRegisterGenerator;
import com.example.wz1.ec.annotation.EntryGenerator;
import com.example.wz1.ec.annotation.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by wz on 2018/9/8.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class ECAppProcessor extends AbstractProcessor {


    //整个程序里已经注解的类型 传进来了。
    @Override
    public Set<String> getSupportedAnnotationTypes(){
        final Set<String> types=new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportannotations =getSupportAnnotations();
        for (Class<? extends Annotation> annotation:supportannotations)
        {
            types.add(annotation.getCanonicalName());
        }

        return types;
    }

    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<? extends Annotation>> annotations=new LinkedHashSet<>();
        //传了我们自己三个注解的类型
        annotations.add(EntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        annotations.add(PayEntryGenerator.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generatorEntryCode(roundEnv);
        generatorPayEntryCode(roundEnv);
        generatorAppRegisterCode(roundEnv);
        return true;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor)
    {
         for (Element typeElement :env.getElementsAnnotatedWith(annotation))
         {
             final List<? extends AnnotationMirror> annotationMirrors =typeElement.getAnnotationMirrors();
             for (AnnotationMirror mirror:annotationMirrors)
             {
                 final Map<? extends ExecutableElement ,? extends AnnotationValue> elementValues=mirror.getElementValues();
                 for (Map.Entry<? extends ExecutableElement ,? extends AnnotationValue> entry:elementValues.entrySet())
                 {
                      entry.getValue().accept(visitor,null);
                 }
             }
         }
    }

    private void generatorEntryCode(RoundEnvironment env)
    {
        EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,EntryGenerator.class,entryVisitor);
    }

    private void generatorPayEntryCode(RoundEnvironment env)
    {
        PayEntryVisitor entryVisitor = new PayEntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,entryVisitor);
    }

    private void generatorAppRegisterCode(RoundEnvironment env)
    {
        AppRegisterVisitor entryVisitor = new AppRegisterVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,entryVisitor);
    }
}
