package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class RateLimitServiceGrpc {

  private RateLimitServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.RateLimitService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.CheckRateLimitRequest,
      coresdk.v1.Ratelimit.CheckRateLimitResponse> getCheckRateLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckRateLimit",
      requestType = coresdk.v1.Ratelimit.CheckRateLimitRequest.class,
      responseType = coresdk.v1.Ratelimit.CheckRateLimitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.CheckRateLimitRequest,
      coresdk.v1.Ratelimit.CheckRateLimitResponse> getCheckRateLimitMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.CheckRateLimitRequest, coresdk.v1.Ratelimit.CheckRateLimitResponse> getCheckRateLimitMethod;
    if ((getCheckRateLimitMethod = RateLimitServiceGrpc.getCheckRateLimitMethod) == null) {
      synchronized (RateLimitServiceGrpc.class) {
        if ((getCheckRateLimitMethod = RateLimitServiceGrpc.getCheckRateLimitMethod) == null) {
          RateLimitServiceGrpc.getCheckRateLimitMethod = getCheckRateLimitMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Ratelimit.CheckRateLimitRequest, coresdk.v1.Ratelimit.CheckRateLimitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckRateLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Ratelimit.CheckRateLimitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Ratelimit.CheckRateLimitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RateLimitServiceMethodDescriptorSupplier("CheckRateLimit"))
              .build();
        }
      }
    }
    return getCheckRateLimitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.ResetRateLimitRequest,
      coresdk.v1.Ratelimit.ResetRateLimitResponse> getResetRateLimitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ResetRateLimit",
      requestType = coresdk.v1.Ratelimit.ResetRateLimitRequest.class,
      responseType = coresdk.v1.Ratelimit.ResetRateLimitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.ResetRateLimitRequest,
      coresdk.v1.Ratelimit.ResetRateLimitResponse> getResetRateLimitMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Ratelimit.ResetRateLimitRequest, coresdk.v1.Ratelimit.ResetRateLimitResponse> getResetRateLimitMethod;
    if ((getResetRateLimitMethod = RateLimitServiceGrpc.getResetRateLimitMethod) == null) {
      synchronized (RateLimitServiceGrpc.class) {
        if ((getResetRateLimitMethod = RateLimitServiceGrpc.getResetRateLimitMethod) == null) {
          RateLimitServiceGrpc.getResetRateLimitMethod = getResetRateLimitMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Ratelimit.ResetRateLimitRequest, coresdk.v1.Ratelimit.ResetRateLimitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResetRateLimit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Ratelimit.ResetRateLimitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Ratelimit.ResetRateLimitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RateLimitServiceMethodDescriptorSupplier("ResetRateLimit"))
              .build();
        }
      }
    }
    return getResetRateLimitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RateLimitServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceStub>() {
        @java.lang.Override
        public RateLimitServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RateLimitServiceStub(channel, callOptions);
        }
      };
    return RateLimitServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static RateLimitServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceBlockingV2Stub>() {
        @java.lang.Override
        public RateLimitServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RateLimitServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return RateLimitServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RateLimitServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceBlockingStub>() {
        @java.lang.Override
        public RateLimitServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RateLimitServiceBlockingStub(channel, callOptions);
        }
      };
    return RateLimitServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RateLimitServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RateLimitServiceFutureStub>() {
        @java.lang.Override
        public RateLimitServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RateLimitServiceFutureStub(channel, callOptions);
        }
      };
    return RateLimitServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkRateLimit(coresdk.v1.Ratelimit.CheckRateLimitRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.CheckRateLimitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckRateLimitMethod(), responseObserver);
    }

    /**
     */
    default void resetRateLimit(coresdk.v1.Ratelimit.ResetRateLimitRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.ResetRateLimitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getResetRateLimitMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RateLimitService.
   */
  public static abstract class RateLimitServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RateLimitServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RateLimitService.
   */
  public static final class RateLimitServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RateLimitServiceStub> {
    private RateLimitServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateLimitServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RateLimitServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkRateLimit(coresdk.v1.Ratelimit.CheckRateLimitRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.CheckRateLimitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckRateLimitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void resetRateLimit(coresdk.v1.Ratelimit.ResetRateLimitRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.ResetRateLimitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getResetRateLimitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RateLimitService.
   */
  public static final class RateLimitServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<RateLimitServiceBlockingV2Stub> {
    private RateLimitServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateLimitServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RateLimitServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Ratelimit.CheckRateLimitResponse checkRateLimit(coresdk.v1.Ratelimit.CheckRateLimitRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckRateLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Ratelimit.ResetRateLimitResponse resetRateLimit(coresdk.v1.Ratelimit.ResetRateLimitRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getResetRateLimitMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service RateLimitService.
   */
  public static final class RateLimitServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RateLimitServiceBlockingStub> {
    private RateLimitServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateLimitServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RateLimitServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Ratelimit.CheckRateLimitResponse checkRateLimit(coresdk.v1.Ratelimit.CheckRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckRateLimitMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Ratelimit.ResetRateLimitResponse resetRateLimit(coresdk.v1.Ratelimit.ResetRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResetRateLimitMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RateLimitService.
   */
  public static final class RateLimitServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RateLimitServiceFutureStub> {
    private RateLimitServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RateLimitServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RateLimitServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Ratelimit.CheckRateLimitResponse> checkRateLimit(
        coresdk.v1.Ratelimit.CheckRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckRateLimitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Ratelimit.ResetRateLimitResponse> resetRateLimit(
        coresdk.v1.Ratelimit.ResetRateLimitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getResetRateLimitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_RATE_LIMIT = 0;
  private static final int METHODID_RESET_RATE_LIMIT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_RATE_LIMIT:
          serviceImpl.checkRateLimit((coresdk.v1.Ratelimit.CheckRateLimitRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.CheckRateLimitResponse>) responseObserver);
          break;
        case METHODID_RESET_RATE_LIMIT:
          serviceImpl.resetRateLimit((coresdk.v1.Ratelimit.ResetRateLimitRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Ratelimit.ResetRateLimitResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckRateLimitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Ratelimit.CheckRateLimitRequest,
              coresdk.v1.Ratelimit.CheckRateLimitResponse>(
                service, METHODID_CHECK_RATE_LIMIT)))
        .addMethod(
          getResetRateLimitMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Ratelimit.ResetRateLimitRequest,
              coresdk.v1.Ratelimit.ResetRateLimitResponse>(
                service, METHODID_RESET_RATE_LIMIT)))
        .build();
  }

  private static abstract class RateLimitServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RateLimitServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Ratelimit.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RateLimitService");
    }
  }

  private static final class RateLimitServiceFileDescriptorSupplier
      extends RateLimitServiceBaseDescriptorSupplier {
    RateLimitServiceFileDescriptorSupplier() {}
  }

  private static final class RateLimitServiceMethodDescriptorSupplier
      extends RateLimitServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RateLimitServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RateLimitServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RateLimitServiceFileDescriptorSupplier())
              .addMethod(getCheckRateLimitMethod())
              .addMethod(getResetRateLimitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
