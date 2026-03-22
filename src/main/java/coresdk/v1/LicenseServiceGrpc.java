package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class LicenseServiceGrpc {

  private LicenseServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.LicenseService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.License.CheckEntitlementRequest,
      coresdk.v1.License.CheckEntitlementResponse> getCheckEntitlementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckEntitlement",
      requestType = coresdk.v1.License.CheckEntitlementRequest.class,
      responseType = coresdk.v1.License.CheckEntitlementResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.License.CheckEntitlementRequest,
      coresdk.v1.License.CheckEntitlementResponse> getCheckEntitlementMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.License.CheckEntitlementRequest, coresdk.v1.License.CheckEntitlementResponse> getCheckEntitlementMethod;
    if ((getCheckEntitlementMethod = LicenseServiceGrpc.getCheckEntitlementMethod) == null) {
      synchronized (LicenseServiceGrpc.class) {
        if ((getCheckEntitlementMethod = LicenseServiceGrpc.getCheckEntitlementMethod) == null) {
          LicenseServiceGrpc.getCheckEntitlementMethod = getCheckEntitlementMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.License.CheckEntitlementRequest, coresdk.v1.License.CheckEntitlementResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckEntitlement"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.License.CheckEntitlementRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.License.CheckEntitlementResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LicenseServiceMethodDescriptorSupplier("CheckEntitlement"))
              .build();
        }
      }
    }
    return getCheckEntitlementMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LicenseServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LicenseServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LicenseServiceStub>() {
        @java.lang.Override
        public LicenseServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LicenseServiceStub(channel, callOptions);
        }
      };
    return LicenseServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static LicenseServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LicenseServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LicenseServiceBlockingV2Stub>() {
        @java.lang.Override
        public LicenseServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LicenseServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return LicenseServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LicenseServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LicenseServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LicenseServiceBlockingStub>() {
        @java.lang.Override
        public LicenseServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LicenseServiceBlockingStub(channel, callOptions);
        }
      };
    return LicenseServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LicenseServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LicenseServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LicenseServiceFutureStub>() {
        @java.lang.Override
        public LicenseServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LicenseServiceFutureStub(channel, callOptions);
        }
      };
    return LicenseServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkEntitlement(coresdk.v1.License.CheckEntitlementRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.License.CheckEntitlementResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckEntitlementMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LicenseService.
   */
  public static abstract class LicenseServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LicenseServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LicenseService.
   */
  public static final class LicenseServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LicenseServiceStub> {
    private LicenseServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LicenseServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LicenseServiceStub(channel, callOptions);
    }

    /**
     */
    public void checkEntitlement(coresdk.v1.License.CheckEntitlementRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.License.CheckEntitlementResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckEntitlementMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LicenseService.
   */
  public static final class LicenseServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<LicenseServiceBlockingV2Stub> {
    private LicenseServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LicenseServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LicenseServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.License.CheckEntitlementResponse checkEntitlement(coresdk.v1.License.CheckEntitlementRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckEntitlementMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service LicenseService.
   */
  public static final class LicenseServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LicenseServiceBlockingStub> {
    private LicenseServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LicenseServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LicenseServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.License.CheckEntitlementResponse checkEntitlement(coresdk.v1.License.CheckEntitlementRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckEntitlementMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LicenseService.
   */
  public static final class LicenseServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LicenseServiceFutureStub> {
    private LicenseServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LicenseServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LicenseServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.License.CheckEntitlementResponse> checkEntitlement(
        coresdk.v1.License.CheckEntitlementRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckEntitlementMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ENTITLEMENT = 0;

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
        case METHODID_CHECK_ENTITLEMENT:
          serviceImpl.checkEntitlement((coresdk.v1.License.CheckEntitlementRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.License.CheckEntitlementResponse>) responseObserver);
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
          getCheckEntitlementMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.License.CheckEntitlementRequest,
              coresdk.v1.License.CheckEntitlementResponse>(
                service, METHODID_CHECK_ENTITLEMENT)))
        .build();
  }

  private static abstract class LicenseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LicenseServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.License.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LicenseService");
    }
  }

  private static final class LicenseServiceFileDescriptorSupplier
      extends LicenseServiceBaseDescriptorSupplier {
    LicenseServiceFileDescriptorSupplier() {}
  }

  private static final class LicenseServiceMethodDescriptorSupplier
      extends LicenseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LicenseServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (LicenseServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LicenseServiceFileDescriptorSupplier())
              .addMethod(getCheckEntitlementMethod())
              .build();
        }
      }
    }
    return result;
  }
}
