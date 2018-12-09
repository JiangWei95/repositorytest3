package com.itheima.ssm.controller;


import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;


    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; // 开始时间
    private Class clazz;// 访问的类
    private Method method; // 访问的方法
    // 主要获取访问时间、访问的类、访问的方法

    //前置通知
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();//当前时间就是访问时间
        //这里直接用getClass会怎么样,为什么不能直接getClass
        clazz = jp.getTarget().getClass();
        //获取访问方法的名称
        String methodName = jp.getSignature().getName();
        //获取访问方法的参数
        Object[] args = jp.getArgs();


        //获取具体执行的Method对象

        Class[] classArgs = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classArgs[i] = args[i].getClass();
        }
        method = clazz.getMethod(methodName, classArgs);
    }

    //通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问路径
        long time = (new Date()).getTime() - visitTime.getTime();
        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {

            //获取类上的注解
            RequestMapping annotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String[] classvalue = annotation.value();

                //获取方法上的value值
                RequestMapping methodAnnotation = this.method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();

                    url = classvalue[0] + methodValue[0];
                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SyLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);//执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);


                    //调用Service完成日志操作

                    sysLogService.save(sysLog);
                }
            }
        }

    }
}
