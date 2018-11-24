package com.lib_java.compileAnnotation;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import static javax.lang.model.element.Modifier.PRIVATE;

/**
 * Created by XR_liu on 2018/11/24.
 */
@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor{

    private Elements elementUtils;
    private Map<String, CodeCreater> codeCreaterMap = new HashMap<String, CodeCreater>();


    @Override
    public SourceVersion getSupportedSourceVersion()
    {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv)
    {
        super.init(processingEnv);

        //Elements mElementUtils;跟元素相关的辅助类，帮助我们去获取一些元素相关的信息。
        elementUtils = processingEnv.getElementUtils();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(BindView.class.getCanonicalName());
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        codeCreaterMap.clear();

        //element所代表的元素只在编译期可见，用于保存元素在编译期的各种状态，而Type所代表的元素是运行期可见，用于保存元素在编译期的各种状态
        //通过roundEnv.getElementsAnnotatedWith拿到我们通过@BindView注解的元素，这里返回值，按照我们的预期应该是VariableElement集合，因为我们用于成员变量上。
        Set<? extends Element> elesWithBind = roundEnv.getElementsAnnotatedWith(BindView.class); //所有被Bind注解标识的元素,此时的Element是一个通用接口
        //一、收集信息

        //接下来for循环我们的元素，首先检查类型是否是VariableElement（代表变量、字段）
        for (Element element : elesWithBind)
        {
            //检查element类型
            checkAnnotationValid(element, BindView.class);
            //field type
            VariableElement variableElement = (VariableElement) element; //因为上面检查过了，所以这里强转
            //class type，拿到对应的类元素
            TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
            //full class name，类的全路径名
            String fqClassName = classElement.getQualifiedName().toString();

            //如果没有生成才会去生成一个新的，。
            CodeCreater codeCreater = codeCreaterMap.get(fqClassName);
            if (codeCreater == null)
            {
                //如果对应的类还没有生成代理类，才去创建一个并且保存
                codeCreater = new CodeCreater(elementUtils, classElement);
                codeCreaterMap.put(fqClassName, codeCreater);
            }
            //接下来，会将与该类对应的且被@BindView声明的VariableElement加入到codeCreater中去，key为我们声明时填写的id，即View的id
            BindView bindAnnotation = variableElement.getAnnotation(BindView.class); //获取对应的注解对象
            int id = bindAnnotation.value();
            //如果这个字段属于某个类，这里就会保存到同一个codeCreater中，因为不同类的时候，这里的codeCreater是不同的
            codeCreater.injectVariables.put(id , variableElement);
        }

        //二、生成代理类
        for (String key : codeCreaterMap.keySet())
        {
            CodeCreater codeCreater = codeCreaterMap.get(key);
            try
            {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                        codeCreater.getCreaterClassFullName(),
                        codeCreater.getTypeElement());
                Writer writer = jfo.openWriter();
                //写入自动生成的代码
                writer.write(codeCreater.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e)
            {
                error(codeCreater.getTypeElement(),
                        "Unable to write injector for type %s: %s",
                        codeCreater.getTypeElement(), e.getMessage());
            }

        }
        return true;
    }

    //要field字段和非private修饰才有效
    private boolean checkAnnotationValid(Element annotatedElement, Class clazz)
    {
        if (annotatedElement.getKind() != ElementKind.FIELD)
        {
            error(annotatedElement, "%s must be declared on field.", clazz.getSimpleName());
            return false;
        }
        //是否为私有的字段
        if (annotatedElement.getModifiers().contains(PRIVATE))
        {
            error(annotatedElement, "%s() must can not be private.", annotatedElement.getSimpleName());
            return false;
        }

        return true;
    }

    private void error(Element element, String message, Object... args)
    {
        if (args.length > 0)
        {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message, element);
    }
}
