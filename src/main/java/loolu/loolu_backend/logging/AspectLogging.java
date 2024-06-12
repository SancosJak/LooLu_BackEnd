package loolu.loolu_backend.logging;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class AspectLogging {
    private final Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    //******************************************************** Vyacheslav **************************
    //Pointcut for Domain.User
    @Pointcut("execution(* loolu.loolu_backend.domain.User.User(..))")
    public void DomainUser() {
    }

    @AfterReturning(value = "DomainUser()", returning = "result")
    public void afterReturningCartControllerUser(Object result) {
        logger.info("=== Vyacheslav === Method User of the class User successfully returned product {}", result);
    }

    @AfterThrowing(value = "DomainUser()", throwing = "e")
    public void afterThrowingExceptionWhileGettingCartControllerUser(Exception e) {
        logger.info("=== Vyacheslav === Method User of the class User threw an exception while getting product: message - {}", e.getMessage());
    }


    //Pointcut for CartController.AaddItemToCart
    @Pointcut("execution(* loolu.loolu_backend.controllers.CartController.addItemToCart(..))")
    public void CartControllerAaddItemToCart() {
    }

    @AfterReturning(value = "CartControllerAaddItemToCart()", returning = "result")
    public void afterReturningCartControllerAaddItemToCart(Object result) {
        logger.info("=== Vyacheslav === Method getCartItems of the class ProductServiceImpl successfully returned product {}", result);
    }

    @AfterThrowing(value = "CartControllerAaddItemToCart()", throwing = "e")
    public void afterThrowingExceptionWhileGettingCartControllerAaddItemToCart(Exception e) {
        logger.info("=== Vyacheslav === Method getCartItems of the class ProductServiceImpl threw an exception while getting product: message - {}", e.getMessage());
    }


    //Pointcut for getCartItems
    @Pointcut("execution(* loolu.loolu_backend.services.impl.CartServiceImpl.getCartItems(..))")
    public void getCartItems() {
    }

    @AfterReturning(value = "getCartItems()", returning = "result")
    public void afterReturninggetCartItems(Object result) {
        logger.info("=== Vyacheslav === Method getCartItems of the class ProductServiceImpl successfully returned product {}", result);
    }

    @AfterThrowing(value = "getCartItems()", throwing = "e")
    public void afterThrowingExceptionWhileGettinggetCartItems(Exception e) {
        logger.info("=== Vyacheslav === Method getCartItems of the class ProductServiceImpl threw an exception while getting product: message - {}", e.getMessage());
    }

    // Pointcut for addItemToCart
    @Pointcut("execution(* loolu.loolu_backend.services.impl.CartServiceImpl.addItemToCart(..))")
    public void addItemToCart() {
    }

    @AfterReturning(value = "addItemToCart()", returning = "result")
    public void afterReturningAddItemToCart(Object result) {
        logger.info("=== Vyacheslav === Method AddItemToCart of the class ProductServiceImpl successfully returned product {}", result);
    }

    @AfterThrowing(value = "addItemToCart()", throwing = "e")
    public void afterThrowingExceptionWhileGettingAddItemToCart(Exception e) {
        logger.info("=== Vyacheslav === Method AddItemToCart of the class ProductServiceImpl threw an exception while getting product: message - {}", e.getMessage());
    }


    //******************************************************** Sandor **************************

    // Pointcut for getting a product by ID
    @Pointcut("execution(* loolu.loolu_backend.services.impl.ProductServiceImpl.getProductById(..))")
    public void getProductById() {
    }

    @AfterReturning(value = "getProductById()", returning = "result")
    public void afterReturningProductById(Object result) {
        logger.info("Method getProductById of the class ProductServiceImpl successfully returned product {}", result);
    }

    @AfterThrowing(value = "getProductById()", throwing = "e")
    public void afterThrowingExceptionWhileGettingProductById(Exception e) {
        logger.info("Method getProductById of the class ProductServiceImpl threw an exception while getting product: message - {}", e.getMessage());
    }

    @SneakyThrows
    @Around("getProductById()")
    public Object profiler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info("Method {} of the class {} is called", methodName, proceedingJoinPoint.getTarget().getClass().getName());
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("Method {} of the class {} finished its work in {} ms", methodName, proceedingJoinPoint.getTarget().getClass().getName(), end - start);
        return result;
    }

    // Pointcut and Advice for getting all products
    @Pointcut("execution(* loolu.loolu_backend.services.impl.ProductServiceImpl.getAllProducts(..))")
    public void getAllProducts() {
    }

    @Before("getAllProducts()")
    public void beforeGettingAllProducts() {
        logger.info("Method getAllProducts of the class ProductServiceImpl is called");
    }

    @After("getAllProducts()")
    public void afterGettingAllProducts() {
        logger.info("Method getAllProducts of the class ProductServiceImpl finished its work");
    }

    @AfterReturning(value = "getAllProducts()", returning = "result")
    public void afterReturningAllProducts(Object result) {
        logger.info("Method getAllProducts of the class ProductServiceImpl successfully returned products. Number of products: {}", ((List<?>) result).size());
    }

    @AfterThrowing(value = "getAllProducts()", throwing = "e")
    public void afterThrowingExceptionWhileGettingAllProducts(Exception e) {
        logger.info("Method getAllProducts of the class ProductServiceImpl threw an exception while getting products: message - {}", e.getMessage());
    }

}
