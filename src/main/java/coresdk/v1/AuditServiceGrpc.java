package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class AuditServiceGrpc {

  private AuditServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.AuditService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Audit.EmitAuditEventRequest,
      coresdk.v1.Audit.EmitAuditEventResponse> getEmitAuditEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EmitAuditEvent",
      requestType = coresdk.v1.Audit.EmitAuditEventRequest.class,
      responseType = coresdk.v1.Audit.EmitAuditEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Audit.EmitAuditEventRequest,
      coresdk.v1.Audit.EmitAuditEventResponse> getEmitAuditEventMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Audit.EmitAuditEventRequest, coresdk.v1.Audit.EmitAuditEventResponse> getEmitAuditEventMethod;
    if ((getEmitAuditEventMethod = AuditServiceGrpc.getEmitAuditEventMethod) == null) {
      synchronized (AuditServiceGrpc.class) {
        if ((getEmitAuditEventMethod = AuditServiceGrpc.getEmitAuditEventMethod) == null) {
          AuditServiceGrpc.getEmitAuditEventMethod = getEmitAuditEventMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Audit.EmitAuditEventRequest, coresdk.v1.Audit.EmitAuditEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EmitAuditEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Audit.EmitAuditEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Audit.EmitAuditEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuditServiceMethodDescriptorSupplier("EmitAuditEvent"))
              .build();
        }
      }
    }
    return getEmitAuditEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Audit.QueryAuditRequest,
      coresdk.v1.Audit.QueryAuditResponse> getQueryAuditMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryAudit",
      requestType = coresdk.v1.Audit.QueryAuditRequest.class,
      responseType = coresdk.v1.Audit.QueryAuditResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Audit.QueryAuditRequest,
      coresdk.v1.Audit.QueryAuditResponse> getQueryAuditMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Audit.QueryAuditRequest, coresdk.v1.Audit.QueryAuditResponse> getQueryAuditMethod;
    if ((getQueryAuditMethod = AuditServiceGrpc.getQueryAuditMethod) == null) {
      synchronized (AuditServiceGrpc.class) {
        if ((getQueryAuditMethod = AuditServiceGrpc.getQueryAuditMethod) == null) {
          AuditServiceGrpc.getQueryAuditMethod = getQueryAuditMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Audit.QueryAuditRequest, coresdk.v1.Audit.QueryAuditResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryAudit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Audit.QueryAuditRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Audit.QueryAuditResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuditServiceMethodDescriptorSupplier("QueryAudit"))
              .build();
        }
      }
    }
    return getQueryAuditMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuditServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuditServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuditServiceStub>() {
        @java.lang.Override
        public AuditServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuditServiceStub(channel, callOptions);
        }
      };
    return AuditServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static AuditServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuditServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuditServiceBlockingV2Stub>() {
        @java.lang.Override
        public AuditServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuditServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return AuditServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuditServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuditServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuditServiceBlockingStub>() {
        @java.lang.Override
        public AuditServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuditServiceBlockingStub(channel, callOptions);
        }
      };
    return AuditServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuditServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuditServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuditServiceFutureStub>() {
        @java.lang.Override
        public AuditServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuditServiceFutureStub(channel, callOptions);
        }
      };
    return AuditServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void emitAuditEvent(coresdk.v1.Audit.EmitAuditEventRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Audit.EmitAuditEventResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEmitAuditEventMethod(), responseObserver);
    }

    /**
     */
    default void queryAudit(coresdk.v1.Audit.QueryAuditRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Audit.QueryAuditResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryAuditMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuditService.
   */
  public static abstract class AuditServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuditServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuditService.
   */
  public static final class AuditServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuditServiceStub> {
    private AuditServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuditServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuditServiceStub(channel, callOptions);
    }

    /**
     */
    public void emitAuditEvent(coresdk.v1.Audit.EmitAuditEventRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Audit.EmitAuditEventResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEmitAuditEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void queryAudit(coresdk.v1.Audit.QueryAuditRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Audit.QueryAuditResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryAuditMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuditService.
   */
  public static final class AuditServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<AuditServiceBlockingV2Stub> {
    private AuditServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuditServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuditServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Audit.EmitAuditEventResponse emitAuditEvent(coresdk.v1.Audit.EmitAuditEventRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getEmitAuditEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Audit.QueryAuditResponse queryAudit(coresdk.v1.Audit.QueryAuditRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getQueryAuditMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service AuditService.
   */
  public static final class AuditServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuditServiceBlockingStub> {
    private AuditServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuditServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuditServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Audit.EmitAuditEventResponse emitAuditEvent(coresdk.v1.Audit.EmitAuditEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEmitAuditEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Audit.QueryAuditResponse queryAudit(coresdk.v1.Audit.QueryAuditRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryAuditMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuditService.
   */
  public static final class AuditServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuditServiceFutureStub> {
    private AuditServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuditServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuditServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Audit.EmitAuditEventResponse> emitAuditEvent(
        coresdk.v1.Audit.EmitAuditEventRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEmitAuditEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Audit.QueryAuditResponse> queryAudit(
        coresdk.v1.Audit.QueryAuditRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryAuditMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EMIT_AUDIT_EVENT = 0;
  private static final int METHODID_QUERY_AUDIT = 1;

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
        case METHODID_EMIT_AUDIT_EVENT:
          serviceImpl.emitAuditEvent((coresdk.v1.Audit.EmitAuditEventRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Audit.EmitAuditEventResponse>) responseObserver);
          break;
        case METHODID_QUERY_AUDIT:
          serviceImpl.queryAudit((coresdk.v1.Audit.QueryAuditRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Audit.QueryAuditResponse>) responseObserver);
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
          getEmitAuditEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Audit.EmitAuditEventRequest,
              coresdk.v1.Audit.EmitAuditEventResponse>(
                service, METHODID_EMIT_AUDIT_EVENT)))
        .addMethod(
          getQueryAuditMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Audit.QueryAuditRequest,
              coresdk.v1.Audit.QueryAuditResponse>(
                service, METHODID_QUERY_AUDIT)))
        .build();
  }

  private static abstract class AuditServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuditServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Audit.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuditService");
    }
  }

  private static final class AuditServiceFileDescriptorSupplier
      extends AuditServiceBaseDescriptorSupplier {
    AuditServiceFileDescriptorSupplier() {}
  }

  private static final class AuditServiceMethodDescriptorSupplier
      extends AuditServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuditServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuditServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuditServiceFileDescriptorSupplier())
              .addMethod(getEmitAuditEventMethod())
              .addMethod(getQueryAuditMethod())
              .build();
        }
      }
    }
    return result;
  }
}
