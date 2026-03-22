package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class TenantServiceGrpc {

  private TenantServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.TenantService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Tenant.ResolveTenantRequest,
      coresdk.v1.Tenant.ResolveTenantResponse> getResolveTenantMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ResolveTenant",
      requestType = coresdk.v1.Tenant.ResolveTenantRequest.class,
      responseType = coresdk.v1.Tenant.ResolveTenantResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Tenant.ResolveTenantRequest,
      coresdk.v1.Tenant.ResolveTenantResponse> getResolveTenantMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Tenant.ResolveTenantRequest, coresdk.v1.Tenant.ResolveTenantResponse> getResolveTenantMethod;
    if ((getResolveTenantMethod = TenantServiceGrpc.getResolveTenantMethod) == null) {
      synchronized (TenantServiceGrpc.class) {
        if ((getResolveTenantMethod = TenantServiceGrpc.getResolveTenantMethod) == null) {
          TenantServiceGrpc.getResolveTenantMethod = getResolveTenantMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Tenant.ResolveTenantRequest, coresdk.v1.Tenant.ResolveTenantResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ResolveTenant"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Tenant.ResolveTenantRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Tenant.ResolveTenantResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TenantServiceMethodDescriptorSupplier("ResolveTenant"))
              .build();
        }
      }
    }
    return getResolveTenantMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Tenant.ValidateIsolationRequest,
      coresdk.v1.Tenant.ValidateIsolationResponse> getValidateIsolationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateIsolation",
      requestType = coresdk.v1.Tenant.ValidateIsolationRequest.class,
      responseType = coresdk.v1.Tenant.ValidateIsolationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Tenant.ValidateIsolationRequest,
      coresdk.v1.Tenant.ValidateIsolationResponse> getValidateIsolationMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Tenant.ValidateIsolationRequest, coresdk.v1.Tenant.ValidateIsolationResponse> getValidateIsolationMethod;
    if ((getValidateIsolationMethod = TenantServiceGrpc.getValidateIsolationMethod) == null) {
      synchronized (TenantServiceGrpc.class) {
        if ((getValidateIsolationMethod = TenantServiceGrpc.getValidateIsolationMethod) == null) {
          TenantServiceGrpc.getValidateIsolationMethod = getValidateIsolationMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Tenant.ValidateIsolationRequest, coresdk.v1.Tenant.ValidateIsolationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateIsolation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Tenant.ValidateIsolationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Tenant.ValidateIsolationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TenantServiceMethodDescriptorSupplier("ValidateIsolation"))
              .build();
        }
      }
    }
    return getValidateIsolationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TenantServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TenantServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TenantServiceStub>() {
        @java.lang.Override
        public TenantServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TenantServiceStub(channel, callOptions);
        }
      };
    return TenantServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static TenantServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TenantServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TenantServiceBlockingV2Stub>() {
        @java.lang.Override
        public TenantServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TenantServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return TenantServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TenantServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TenantServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TenantServiceBlockingStub>() {
        @java.lang.Override
        public TenantServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TenantServiceBlockingStub(channel, callOptions);
        }
      };
    return TenantServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TenantServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TenantServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TenantServiceFutureStub>() {
        @java.lang.Override
        public TenantServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TenantServiceFutureStub(channel, callOptions);
        }
      };
    return TenantServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void resolveTenant(coresdk.v1.Tenant.ResolveTenantRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ResolveTenantResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getResolveTenantMethod(), responseObserver);
    }

    /**
     */
    default void validateIsolation(coresdk.v1.Tenant.ValidateIsolationRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ValidateIsolationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateIsolationMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TenantService.
   */
  public static abstract class TenantServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TenantServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TenantService.
   */
  public static final class TenantServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TenantServiceStub> {
    private TenantServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TenantServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TenantServiceStub(channel, callOptions);
    }

    /**
     */
    public void resolveTenant(coresdk.v1.Tenant.ResolveTenantRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ResolveTenantResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getResolveTenantMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validateIsolation(coresdk.v1.Tenant.ValidateIsolationRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ValidateIsolationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateIsolationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TenantService.
   */
  public static final class TenantServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<TenantServiceBlockingV2Stub> {
    private TenantServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TenantServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TenantServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Tenant.ResolveTenantResponse resolveTenant(coresdk.v1.Tenant.ResolveTenantRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getResolveTenantMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Tenant.ValidateIsolationResponse validateIsolation(coresdk.v1.Tenant.ValidateIsolationRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getValidateIsolationMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service TenantService.
   */
  public static final class TenantServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TenantServiceBlockingStub> {
    private TenantServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TenantServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TenantServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Tenant.ResolveTenantResponse resolveTenant(coresdk.v1.Tenant.ResolveTenantRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getResolveTenantMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Tenant.ValidateIsolationResponse validateIsolation(coresdk.v1.Tenant.ValidateIsolationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateIsolationMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TenantService.
   */
  public static final class TenantServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TenantServiceFutureStub> {
    private TenantServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TenantServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TenantServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Tenant.ResolveTenantResponse> resolveTenant(
        coresdk.v1.Tenant.ResolveTenantRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getResolveTenantMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Tenant.ValidateIsolationResponse> validateIsolation(
        coresdk.v1.Tenant.ValidateIsolationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateIsolationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RESOLVE_TENANT = 0;
  private static final int METHODID_VALIDATE_ISOLATION = 1;

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
        case METHODID_RESOLVE_TENANT:
          serviceImpl.resolveTenant((coresdk.v1.Tenant.ResolveTenantRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ResolveTenantResponse>) responseObserver);
          break;
        case METHODID_VALIDATE_ISOLATION:
          serviceImpl.validateIsolation((coresdk.v1.Tenant.ValidateIsolationRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Tenant.ValidateIsolationResponse>) responseObserver);
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
          getResolveTenantMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Tenant.ResolveTenantRequest,
              coresdk.v1.Tenant.ResolveTenantResponse>(
                service, METHODID_RESOLVE_TENANT)))
        .addMethod(
          getValidateIsolationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Tenant.ValidateIsolationRequest,
              coresdk.v1.Tenant.ValidateIsolationResponse>(
                service, METHODID_VALIDATE_ISOLATION)))
        .build();
  }

  private static abstract class TenantServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TenantServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Tenant.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TenantService");
    }
  }

  private static final class TenantServiceFileDescriptorSupplier
      extends TenantServiceBaseDescriptorSupplier {
    TenantServiceFileDescriptorSupplier() {}
  }

  private static final class TenantServiceMethodDescriptorSupplier
      extends TenantServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TenantServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (TenantServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TenantServiceFileDescriptorSupplier())
              .addMethod(getResolveTenantMethod())
              .addMethod(getValidateIsolationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
