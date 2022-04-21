# TimeOutMicroservicePattern
 What happens if the service you are calling does not respond timely? Are you going to wait forever and lead to bad customer experience, or manage intelligently? 

Note to myself : Sadly @TimeLimiter does not support Mono but dirty CompletableFuture objects of Java...

About what happens:

Product Service calls comment service and if comment service does not return a response in 2s, we show fake happy product comments...
