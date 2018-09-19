package com.example.wz1.ec.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

import static javax.lang.model.element.Modifier.FINAL;

/**
 * Created by wz on 2018/9/8.
 */

public final class AppRegisterVisitor extends SimpleAnnotationValueVisitor8<Void,Void> {
    //遍历对象
    private Filer mFiler=null;
    private String packageName =null;

    public void setFiler(Filer mFiler)
    {
        this.mFiler=mFiler;
    }

    @Override
    public Void visitString(String s, Void p) {
        this.packageName=s;
        return p;
    }

    //找出注解的类，标注的元信息  生成需要的代码

    @Override
    public Void visitType(TypeMirror t, Void p) {
        generateJavaCode(t);
        return p;
    }

    public void generateJavaCode(TypeMirror typeMirror){
        final TypeSpec targetActivity =TypeSpec.classBuilder("AppRegister")
                                        .addModifiers(Modifier.PUBLIC)
                                        .addModifiers(FINAL)
                                        .superclass(TypeName.get(typeMirror))
                                        .build();

        JavaFile javaFile = JavaFile.builder(packageName + "wxapi", targetActivity)
                .addFileComment("微信广播接收器")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
