package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class MaskingServiceGrpc {

  private MaskingServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.MaskingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskRequest,
      coresdk.v1.Masking.MaskResponse> getMaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Mask",
      requestType = coresdk.v1.Masking.MaskRequest.class,
      responseType = coresdk.v1.Masking.MaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskRequest,
      coresdk.v1.Masking.MaskResponse> getMaskMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskRequest, coresdk.v1.Masking.MaskResponse> getMaskMethod;
    if ((getMaskMethod = MaskingServiceGrpc.getMaskMethod) == null) {
      synchronized (MaskingServiceGrpc.class) {
        if ((getMaskMethod = MaskingServiceGrpc.getMaskMethod) == null) {
          MaskingServiceGrpc.getMaskMethod = getMaskMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Masking.MaskRequest, coresdk.v1.Masking.MaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Mask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Masking.MaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Masking.MaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MaskingServiceMethodDescriptorSupplier("Mask"))
              .build();
        }
      }
    }
    return getMaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskStringRequest,
      coresdk.v1.Masking.MaskStringResponse> getMaskStringMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MaskString",
      requestType = coresdk.v1.Masking.MaskStringRequest.class,
      responseType = coresdk.v1.Masking.MaskStringResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskStringRequest,
      coresdk.v1.Masking.MaskStringResponse> getMaskStringMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Masking.MaskStringRequest, coresdk.v1.Masking.MaskStringResponse> getMaskStringMethod;
    if ((getMaskStringMethod = MaskingServiceGrpc.getMaskStringMethod) == null) {
      synchronized (MaskingServiceGrpc.class) {
        if ((getMaskStringMethod = MaskingServiceGrpc.getMaskStringMethod) == null) {
          MaskingServiceGrpc.getMaskStringMethod = getMaskStringMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Masking.MaskStringRequest, coresdk.v1.Masking.MaskStringResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MaskString"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Masking.MaskStringRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Masking.MaskStringResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MaskingServiceMethodDescriptorSupplier("MaskString"))
              .build();
        }
      }
    }
    return getMaskStringMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MaskingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MaskingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MaskingServiceStub>() {
        @java.lang.Override
        public MaskingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MaskingServiceStub(channel, callOptions);
        }
      };
    return MaskingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static MaskingServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MaskingServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MaskingServiceBlockingV2Stub>() {
        @java.lang.Override
        public MaskingServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MaskingServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return MaskingServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MaskingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MaskingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MaskingServiceBlockingStub>() {
        @java.lang.Override
        public MaskingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MaskingServiceBlockingStub(channel, callOptions);
        }
      };
    return MaskingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MaskingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MaskingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MaskingServiceFutureStub>() {
        @java.lang.Override
        public MaskingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MaskingServiceFutureStub(channel, callOptions);
        }
      };
    return MaskingServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void mask(coresdk.v1.Masking.MaskRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMaskMethod(), responseObserver);
    }

    /**
     */
    default void maskString(coresdk.v1.Masking.MaskStringRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskStringResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMaskStringMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MaskingService.
   */
  public static abstract class MaskingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MaskingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MaskingService.
   */
  public static final class MaskingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MaskingServiceStub> {
    private MaskingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MaskingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MaskingServiceStub(channel, callOptions);
    }

    /**
     */
    public void mask(coresdk.v1.Masking.MaskRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void maskString(coresdk.v1.Masking.MaskStringRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskStringResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMaskStringMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MaskingService.
   */
  public static final class MaskingServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<MaskingServiceBlockingV2Stub> {
    private MaskingServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MaskingServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MaskingServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Masking.MaskResponse mask(coresdk.v1.Masking.MaskRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getMaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Masking.MaskStringResponse maskString(coresdk.v1.Masking.MaskStringRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getMaskStringMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service MaskingService.
   */
  public static final class MaskingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MaskingServiceBlockingStub> {
    private MaskingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MaskingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MaskingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Masking.MaskResponse mask(coresdk.v1.Masking.MaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Masking.MaskStringResponse maskString(coresdk.v1.Masking.MaskStringRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMaskStringMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MaskingService.
   */
  public static final class MaskingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MaskingServiceFutureStub> {
    private MaskingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MaskingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MaskingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Masking.MaskResponse> mask(
        coresdk.v1.Masking.MaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Masking.MaskStringResponse> maskString(
        coresdk.v1.Masking.MaskStringRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMaskStringMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MASK = 0;
  private static final int METHODID_MASK_STRING = 1;

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
        case METHODID_MASK:
          serviceImpl.mask((coresdk.v1.Masking.MaskRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskResponse>) responseObserver);
          break;
        case METHODID_MASK_STRING:
          serviceImpl.maskString((coresdk.v1.Masking.MaskStringRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Masking.MaskStringResponse>) responseObserver);
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
          getMaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Masking.MaskRequest,
              coresdk.v1.Masking.MaskResponse>(
                service, METHODID_MASK)))
        .addMethod(
          getMaskStringMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Masking.MaskStringRequest,
              coresdk.v1.Masking.MaskStringResponse>(
                service, METHODID_MASK_STRING)))
        .build();
  }

  private static abstract class MaskingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MaskingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Masking.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MaskingService");
    }
  }

  private static final class MaskingServiceFileDescriptorSupplier
      extends MaskingServiceBaseDescriptorSupplier {
    MaskingServiceFileDescriptorSupplier() {}
  }

  private static final class MaskingServiceMethodDescriptorSupplier
      extends MaskingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MaskingServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MaskingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MaskingServiceFileDescriptorSupplier())
              .addMethod(getMaskMethod())
              .addMethod(getMaskStringMethod())
              .build();
        }
      }
    }
    return result;
  }
}
