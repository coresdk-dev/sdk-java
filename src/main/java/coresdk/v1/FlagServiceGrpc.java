package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class FlagServiceGrpc {

  private FlagServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.FlagService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Flags.EvaluateFlagRequest,
      coresdk.v1.Flags.EvaluateFlagResponse> getEvaluateFlagMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EvaluateFlag",
      requestType = coresdk.v1.Flags.EvaluateFlagRequest.class,
      responseType = coresdk.v1.Flags.EvaluateFlagResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Flags.EvaluateFlagRequest,
      coresdk.v1.Flags.EvaluateFlagResponse> getEvaluateFlagMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Flags.EvaluateFlagRequest, coresdk.v1.Flags.EvaluateFlagResponse> getEvaluateFlagMethod;
    if ((getEvaluateFlagMethod = FlagServiceGrpc.getEvaluateFlagMethod) == null) {
      synchronized (FlagServiceGrpc.class) {
        if ((getEvaluateFlagMethod = FlagServiceGrpc.getEvaluateFlagMethod) == null) {
          FlagServiceGrpc.getEvaluateFlagMethod = getEvaluateFlagMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Flags.EvaluateFlagRequest, coresdk.v1.Flags.EvaluateFlagResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EvaluateFlag"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Flags.EvaluateFlagRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Flags.EvaluateFlagResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FlagServiceMethodDescriptorSupplier("EvaluateFlag"))
              .build();
        }
      }
    }
    return getEvaluateFlagMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FlagServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlagServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlagServiceStub>() {
        @java.lang.Override
        public FlagServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlagServiceStub(channel, callOptions);
        }
      };
    return FlagServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static FlagServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlagServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlagServiceBlockingV2Stub>() {
        @java.lang.Override
        public FlagServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlagServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return FlagServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FlagServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlagServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlagServiceBlockingStub>() {
        @java.lang.Override
        public FlagServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlagServiceBlockingStub(channel, callOptions);
        }
      };
    return FlagServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FlagServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlagServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlagServiceFutureStub>() {
        @java.lang.Override
        public FlagServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlagServiceFutureStub(channel, callOptions);
        }
      };
    return FlagServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void evaluateFlag(coresdk.v1.Flags.EvaluateFlagRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Flags.EvaluateFlagResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEvaluateFlagMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FlagService.
   */
  public static abstract class FlagServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FlagServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FlagService.
   */
  public static final class FlagServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FlagServiceStub> {
    private FlagServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlagServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlagServiceStub(channel, callOptions);
    }

    /**
     */
    public void evaluateFlag(coresdk.v1.Flags.EvaluateFlagRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Flags.EvaluateFlagResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEvaluateFlagMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FlagService.
   */
  public static final class FlagServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<FlagServiceBlockingV2Stub> {
    private FlagServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlagServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlagServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Flags.EvaluateFlagResponse evaluateFlag(coresdk.v1.Flags.EvaluateFlagRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getEvaluateFlagMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service FlagService.
   */
  public static final class FlagServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FlagServiceBlockingStub> {
    private FlagServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlagServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlagServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Flags.EvaluateFlagResponse evaluateFlag(coresdk.v1.Flags.EvaluateFlagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEvaluateFlagMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FlagService.
   */
  public static final class FlagServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FlagServiceFutureStub> {
    private FlagServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlagServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlagServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Flags.EvaluateFlagResponse> evaluateFlag(
        coresdk.v1.Flags.EvaluateFlagRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEvaluateFlagMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVALUATE_FLAG = 0;

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
        case METHODID_EVALUATE_FLAG:
          serviceImpl.evaluateFlag((coresdk.v1.Flags.EvaluateFlagRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Flags.EvaluateFlagResponse>) responseObserver);
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
          getEvaluateFlagMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Flags.EvaluateFlagRequest,
              coresdk.v1.Flags.EvaluateFlagResponse>(
                service, METHODID_EVALUATE_FLAG)))
        .build();
  }

  private static abstract class FlagServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FlagServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Flags.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FlagService");
    }
  }

  private static final class FlagServiceFileDescriptorSupplier
      extends FlagServiceBaseDescriptorSupplier {
    FlagServiceFileDescriptorSupplier() {}
  }

  private static final class FlagServiceMethodDescriptorSupplier
      extends FlagServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FlagServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (FlagServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FlagServiceFileDescriptorSupplier())
              .addMethod(getEvaluateFlagMethod())
              .build();
        }
      }
    }
    return result;
  }
}
