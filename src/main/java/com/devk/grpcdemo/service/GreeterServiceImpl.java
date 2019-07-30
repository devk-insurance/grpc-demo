package com.devk.grpcdemo.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService(interceptors = {LogInterceptor.class})
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase {
    @Override
    public void sayHello(GreeterServiceOuterClass.HelloRequest request, StreamObserver<GreeterServiceOuterClass.HelloReply> responseObserver) {
        String message = "Hello " + request.getName();
        GreeterServiceOuterClass.HelloReply helloReply = GreeterServiceOuterClass.HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
        log.info("Returning: " + message);
    }
}
