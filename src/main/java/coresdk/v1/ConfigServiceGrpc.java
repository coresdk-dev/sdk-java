package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ConfigServiceGrpc {

  private ConfigServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.ConfigService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Config.GetConfigRequest,
      coresdk.v1.Config.GetConfigResponse> getGetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConfig",
      requestType = coresdk.v1.Config.GetConfigRequest.class,
      responseType = coresdk.v1.Config.GetConfigResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Config.GetConfigRequest,
      coresdk.v1.Config.GetConfigResponse> getGetConfigMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Config.GetConfigRequest, coresdk.v1.Config.GetConfigResponse> getGetConfigMethod;
    if ((getGetConfigMethod = ConfigServiceGrpc.getGetConfigMethod) == null) {
      synchronized (ConfigServiceGrpc.class) {
        if ((getGetConfigMethod = ConfigServiceGrpc.getGetConfigMethod) == null) {
          ConfigServiceGrpc.getGetConfigMethod = getGetConfigMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Config.GetConfigRequest, coresdk.v1.Config.GetConfigResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Config.GetConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Config.GetConfigResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ConfigServiceMethodDescriptorSupplier("GetConfig"))
              .build();
        }
      }
    }
    return getGetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Config.WatchConfigRequest,
      coresdk.v1.Config.ConfigSnapshot> getWatchConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WatchConfig",
      requestType = coresdk.v1.Config.WatchConfigRequest.class,
      responseType = coresdk.v1.Config.ConfigSnapshot.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<coresdk.v1.Config.WatchConfigRequest,
      coresdk.v1.Config.ConfigSnapshot> getWatchConfigMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Config.WatchConfigRequest, coresdk.v1.Config.ConfigSnapshot> getWatchConfigMethod;
    if ((getWatchConfigMethod = ConfigServiceGrpc.getWatchConfigMethod) == null) {
      synchronized (ConfigServiceGrpc.class) {
        if ((getWatchConfigMethod = ConfigServiceGrpc.getWatchConfigMethod) == null) {
          ConfigServiceGrpc.getWatchConfigMethod = getWatchConfigMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Config.WatchConfigRequest, coresdk.v1.Config.ConfigSnapshot>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WatchConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Config.WatchConfigRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Config.ConfigSnapshot.getDefaultInstance()))
              .setSchemaDescriptor(new ConfigServiceMethodDescriptorSupplier("WatchConfig"))
              .build();
        }
      }
    }
    return getWatchConfigMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConfigServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigServiceStub>() {
        @java.lang.Override
        public ConfigServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigServiceStub(channel, callOptions);
        }
      };
    return ConfigServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ConfigServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigServiceBlockingV2Stub>() {
        @java.lang.Override
        public ConfigServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ConfigServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConfigServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigServiceBlockingStub>() {
        @java.lang.Override
        public ConfigServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigServiceBlockingStub(channel, callOptions);
        }
      };
    return ConfigServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConfigServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConfigServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConfigServiceFutureStub>() {
        @java.lang.Override
        public ConfigServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConfigServiceFutureStub(channel, callOptions);
        }
      };
    return ConfigServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getConfig(coresdk.v1.Config.GetConfigRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Config.GetConfigResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigMethod(), responseObserver);
    }

    /**
     */
    default void watchConfig(coresdk.v1.Config.WatchConfigRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Config.ConfigSnapshot> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWatchConfigMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ConfigService.
   */
  public static abstract class ConfigServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ConfigServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ConfigService.
   */
  public static final class ConfigServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ConfigServiceStub> {
    private ConfigServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigServiceStub(channel, callOptions);
    }

    /**
     */
    public void getConfig(coresdk.v1.Config.GetConfigRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Config.GetConfigResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void watchConfig(coresdk.v1.Config.WatchConfigRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Config.ConfigSnapshot> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getWatchConfigMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ConfigService.
   */
  public static final class ConfigServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ConfigServiceBlockingV2Stub> {
    private ConfigServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Config.GetConfigResponse getConfig(coresdk.v1.Config.GetConfigRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, coresdk.v1.Config.ConfigSnapshot>
        watchConfig(coresdk.v1.Config.WatchConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getWatchConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ConfigService.
   */
  public static final class ConfigServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ConfigServiceBlockingStub> {
    private ConfigServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Config.GetConfigResponse getConfig(coresdk.v1.Config.GetConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<coresdk.v1.Config.ConfigSnapshot> watchConfig(
        coresdk.v1.Config.WatchConfigRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getWatchConfigMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ConfigService.
   */
  public static final class ConfigServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ConfigServiceFutureStub> {
    private ConfigServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConfigServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConfigServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Config.GetConfigResponse> getConfig(
        coresdk.v1.Config.GetConfigRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CONFIG = 0;
  private static final int METHODID_WATCH_CONFIG = 1;

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
        case METHODID_GET_CONFIG:
          serviceImpl.getConfig((coresdk.v1.Config.GetConfigRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Config.GetConfigResponse>) responseObserver);
          break;
        case METHODID_WATCH_CONFIG:
          serviceImpl.watchConfig((coresdk.v1.Config.WatchConfigRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Config.ConfigSnapshot>) responseObserver);
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
          getGetConfigMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Config.GetConfigRequest,
              coresdk.v1.Config.GetConfigResponse>(
                service, METHODID_GET_CONFIG)))
        .addMethod(
          getWatchConfigMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              coresdk.v1.Config.WatchConfigRequest,
              coresdk.v1.Config.ConfigSnapshot>(
                service, METHODID_WATCH_CONFIG)))
        .build();
  }

  private static abstract class ConfigServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConfigServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Config.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConfigService");
    }
  }

  private static final class ConfigServiceFileDescriptorSupplier
      extends ConfigServiceBaseDescriptorSupplier {
    ConfigServiceFileDescriptorSupplier() {}
  }

  private static final class ConfigServiceMethodDescriptorSupplier
      extends ConfigServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ConfigServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ConfigServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConfigServiceFileDescriptorSupplier())
              .addMethod(getGetConfigMethod())
              .addMethod(getWatchConfigMethod())
              .build();
        }
      }
    }
    return result;
  }
}
