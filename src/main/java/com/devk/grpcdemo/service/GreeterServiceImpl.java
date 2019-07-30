package com.devk.grpcdemo.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService(interceptors = {LogInterceptor.class})
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String message = "Hello " + request.getName();
        HelloReply helloReply = HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
        log.info("Returning: " + message);
    }
}
