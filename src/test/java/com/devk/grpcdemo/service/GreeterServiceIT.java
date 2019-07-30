package com.devk.grpcdemo.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GreeterServiceIT {
    @Test
    public void callGreeterService() {

        ManagedChannel channel = ManagedChannelBuilder.
                forAddress("localhost", 12345).
                usePlaintext().
                build();

        GreeterServiceGrpc.GreeterServiceBlockingStub client = GreeterServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName("Foo").build();
        try {
            HelloReply helloReply = client.sayHello(request);
            Assert.assertEquals("Hello Foo", helloReply.getMessage());
        } catch (StatusRuntimeException ex) {
            System.out.println("Server nicht erreichbar..");
        } finally {
            channel.shutdown();
        }

    }
}
