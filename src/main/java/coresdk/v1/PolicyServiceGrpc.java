package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class PolicyServiceGrpc {

  private PolicyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.PolicyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest,
      coresdk.v1.Policy.PolicyEvaluateResponse> getEvaluateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Evaluate",
      requestType = coresdk.v1.Policy.PolicyEvaluateRequest.class,
      responseType = coresdk.v1.Policy.PolicyEvaluateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest,
      coresdk.v1.Policy.PolicyEvaluateResponse> getEvaluateMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest, coresdk.v1.Policy.PolicyEvaluateResponse> getEvaluateMethod;
    if ((getEvaluateMethod = PolicyServiceGrpc.getEvaluateMethod) == null) {
      synchronized (PolicyServiceGrpc.class) {
        if ((getEvaluateMethod = PolicyServiceGrpc.getEvaluateMethod) == null) {
          PolicyServiceGrpc.getEvaluateMethod = getEvaluateMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Policy.PolicyEvaluateRequest, coresdk.v1.Policy.PolicyEvaluateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Evaluate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.PolicyEvaluateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.PolicyEvaluateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PolicyServiceMethodDescriptorSupplier("Evaluate"))
              .build();
        }
      }
    }
    return getEvaluateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest,
      coresdk.v1.Policy.PolicyEvaluateResponse> getDryRunMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DryRun",
      requestType = coresdk.v1.Policy.PolicyEvaluateRequest.class,
      responseType = coresdk.v1.Policy.PolicyEvaluateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest,
      coresdk.v1.Policy.PolicyEvaluateResponse> getDryRunMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Policy.PolicyEvaluateRequest, coresdk.v1.Policy.PolicyEvaluateResponse> getDryRunMethod;
    if ((getDryRunMethod = PolicyServiceGrpc.getDryRunMethod) == null) {
      synchronized (PolicyServiceGrpc.class) {
        if ((getDryRunMethod = PolicyServiceGrpc.getDryRunMethod) == null) {
          PolicyServiceGrpc.getDryRunMethod = getDryRunMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Policy.PolicyEvaluateRequest, coresdk.v1.Policy.PolicyEvaluateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DryRun"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.PolicyEvaluateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.PolicyEvaluateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PolicyServiceMethodDescriptorSupplier("DryRun"))
              .build();
        }
      }
    }
    return getDryRunMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Policy.WatchPolicyUpdatesRequest,
      coresdk.v1.Policy.PolicyBundleUpdate> getWatchPolicyUpdatesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WatchPolicyUpdates",
      requestType = coresdk.v1.Policy.WatchPolicyUpdatesRequest.class,
      responseType = coresdk.v1.Policy.PolicyBundleUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<coresdk.v1.Policy.WatchPolicyUpdatesRequest,
      coresdk.v1.Policy.PolicyBundleUpdate> getWatchPolicyUpdatesMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Policy.WatchPolicyUpdatesRequest, coresdk.v1.Policy.PolicyBundleUpdate> getWatchPolicyUpdatesMethod;
    if ((getWatchPolicyUpdatesMethod = PolicyServiceGrpc.getWatchPolicyUpdatesMethod) == null) {
      synchronized (PolicyServiceGrpc.class) {
        if ((getWatchPolicyUpdatesMethod = PolicyServiceGrpc.getWatchPolicyUpdatesMethod) == null) {
          PolicyServiceGrpc.getWatchPolicyUpdatesMethod = getWatchPolicyUpdatesMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Policy.WatchPolicyUpdatesRequest, coresdk.v1.Policy.PolicyBundleUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WatchPolicyUpdates"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.WatchPolicyUpdatesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Policy.PolicyBundleUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new PolicyServiceMethodDescriptorSupplier("WatchPolicyUpdates"))
              .build();
        }
      }
    }
    return getWatchPolicyUpdatesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PolicyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PolicyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PolicyServiceStub>() {
        @java.lang.Override
        public PolicyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PolicyServiceStub(channel, callOptions);
        }
      };
    return PolicyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static PolicyServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PolicyServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PolicyServiceBlockingV2Stub>() {
        @java.lang.Override
        public PolicyServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PolicyServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return PolicyServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PolicyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PolicyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PolicyServiceBlockingStub>() {
        @java.lang.Override
        public PolicyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PolicyServiceBlockingStub(channel, callOptions);
        }
      };
    return PolicyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PolicyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PolicyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PolicyServiceFutureStub>() {
        @java.lang.Override
        public PolicyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PolicyServiceFutureStub(channel, callOptions);
        }
      };
    return PolicyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void evaluate(coresdk.v1.Policy.PolicyEvaluateRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEvaluateMethod(), responseObserver);
    }

    /**
     */
    default void dryRun(coresdk.v1.Policy.PolicyEvaluateRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDryRunMethod(), responseObserver);
    }

    /**
     */
    default void watchPolicyUpdates(coresdk.v1.Policy.WatchPolicyUpdatesRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyBundleUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWatchPolicyUpdatesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PolicyService.
   */
  public static abstract class PolicyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PolicyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PolicyService.
   */
  public static final class PolicyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PolicyServiceStub> {
    private PolicyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PolicyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PolicyServiceStub(channel, callOptions);
    }

    /**
     */
    public void evaluate(coresdk.v1.Policy.PolicyEvaluateRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEvaluateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void dryRun(coresdk.v1.Policy.PolicyEvaluateRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDryRunMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void watchPolicyUpdates(coresdk.v1.Policy.WatchPolicyUpdatesRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyBundleUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getWatchPolicyUpdatesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PolicyService.
   */
  public static final class PolicyServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<PolicyServiceBlockingV2Stub> {
    private PolicyServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PolicyServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PolicyServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Policy.PolicyEvaluateResponse evaluate(coresdk.v1.Policy.PolicyEvaluateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getEvaluateMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Policy.PolicyEvaluateResponse dryRun(coresdk.v1.Policy.PolicyEvaluateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDryRunMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, coresdk.v1.Policy.PolicyBundleUpdate>
        watchPolicyUpdates(coresdk.v1.Policy.WatchPolicyUpdatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getWatchPolicyUpdatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service PolicyService.
   */
  public static final class PolicyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PolicyServiceBlockingStub> {
    private PolicyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PolicyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PolicyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Policy.PolicyEvaluateResponse evaluate(coresdk.v1.Policy.PolicyEvaluateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEvaluateMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Policy.PolicyEvaluateResponse dryRun(coresdk.v1.Policy.PolicyEvaluateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDryRunMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<coresdk.v1.Policy.PolicyBundleUpdate> watchPolicyUpdates(
        coresdk.v1.Policy.WatchPolicyUpdatesRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getWatchPolicyUpdatesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PolicyService.
   */
  public static final class PolicyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PolicyServiceFutureStub> {
    private PolicyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PolicyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PolicyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Policy.PolicyEvaluateResponse> evaluate(
        coresdk.v1.Policy.PolicyEvaluateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEvaluateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Policy.PolicyEvaluateResponse> dryRun(
        coresdk.v1.Policy.PolicyEvaluateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDryRunMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVALUATE = 0;
  private static final int METHODID_DRY_RUN = 1;
  private static final int METHODID_WATCH_POLICY_UPDATES = 2;

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
        case METHODID_EVALUATE:
          serviceImpl.evaluate((coresdk.v1.Policy.PolicyEvaluateRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse>) responseObserver);
          break;
        case METHODID_DRY_RUN:
          serviceImpl.dryRun((coresdk.v1.Policy.PolicyEvaluateRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyEvaluateResponse>) responseObserver);
          break;
        case METHODID_WATCH_POLICY_UPDATES:
          serviceImpl.watchPolicyUpdates((coresdk.v1.Policy.WatchPolicyUpdatesRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Policy.PolicyBundleUpdate>) responseObserver);
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
          getEvaluateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Policy.PolicyEvaluateRequest,
              coresdk.v1.Policy.PolicyEvaluateResponse>(
                service, METHODID_EVALUATE)))
        .addMethod(
          getDryRunMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Policy.PolicyEvaluateRequest,
              coresdk.v1.Policy.PolicyEvaluateResponse>(
                service, METHODID_DRY_RUN)))
        .addMethod(
          getWatchPolicyUpdatesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              coresdk.v1.Policy.WatchPolicyUpdatesRequest,
              coresdk.v1.Policy.PolicyBundleUpdate>(
                service, METHODID_WATCH_POLICY_UPDATES)))
        .build();
  }

  private static abstract class PolicyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PolicyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Policy.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PolicyService");
    }
  }

  private static final class PolicyServiceFileDescriptorSupplier
      extends PolicyServiceBaseDescriptorSupplier {
    PolicyServiceFileDescriptorSupplier() {}
  }

  private static final class PolicyServiceMethodDescriptorSupplier
      extends PolicyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PolicyServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (PolicyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PolicyServiceFileDescriptorSupplier())
              .addMethod(getEvaluateMethod())
              .addMethod(getDryRunMethod())
              .addMethod(getWatchPolicyUpdatesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
