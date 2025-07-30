package com.lease.common.exception;

//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Result<Void> handle(Exception e){
//        e.printStackTrace();
//        return Result.fail();
//    }
//
//    @ExceptionHandler(LeaseException.class)
//    @ResponseBody
//    public Result<Void> handle(LeaseException e){
//        e.printStackTrace();
//        return Result.fail(e.getCode(), e.getMessage());
//    }
//}