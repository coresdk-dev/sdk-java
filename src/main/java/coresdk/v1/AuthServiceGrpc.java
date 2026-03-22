package coresdk.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "coresdk.v1.AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateTokenRequest,
      coresdk.v1.Auth.ValidateTokenResponse> getValidateTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateToken",
      requestType = coresdk.v1.Auth.ValidateTokenRequest.class,
      responseType = coresdk.v1.Auth.ValidateTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateTokenRequest,
      coresdk.v1.Auth.ValidateTokenResponse> getValidateTokenMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateTokenRequest, coresdk.v1.Auth.ValidateTokenResponse> getValidateTokenMethod;
    if ((getValidateTokenMethod = AuthServiceGrpc.getValidateTokenMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getValidateTokenMethod = AuthServiceGrpc.getValidateTokenMethod) == null) {
          AuthServiceGrpc.getValidateTokenMethod = getValidateTokenMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.ValidateTokenRequest, coresdk.v1.Auth.ValidateTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.ValidateTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.ValidateTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("ValidateToken"))
              .build();
        }
      }
    }
    return getValidateTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.AuthorizeRequest,
      coresdk.v1.Auth.AuthorizeResponse> getAuthorizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Authorize",
      requestType = coresdk.v1.Auth.AuthorizeRequest.class,
      responseType = coresdk.v1.Auth.AuthorizeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.AuthorizeRequest,
      coresdk.v1.Auth.AuthorizeResponse> getAuthorizeMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.AuthorizeRequest, coresdk.v1.Auth.AuthorizeResponse> getAuthorizeMethod;
    if ((getAuthorizeMethod = AuthServiceGrpc.getAuthorizeMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getAuthorizeMethod = AuthServiceGrpc.getAuthorizeMethod) == null) {
          AuthServiceGrpc.getAuthorizeMethod = getAuthorizeMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.AuthorizeRequest, coresdk.v1.Auth.AuthorizeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Authorize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.AuthorizeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.AuthorizeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("Authorize"))
              .build();
        }
      }
    }
    return getAuthorizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.GetJwksRequest,
      coresdk.v1.Auth.GetJwksResponse> getGetJwksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetJwks",
      requestType = coresdk.v1.Auth.GetJwksRequest.class,
      responseType = coresdk.v1.Auth.GetJwksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.GetJwksRequest,
      coresdk.v1.Auth.GetJwksResponse> getGetJwksMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.GetJwksRequest, coresdk.v1.Auth.GetJwksResponse> getGetJwksMethod;
    if ((getGetJwksMethod = AuthServiceGrpc.getGetJwksMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getGetJwksMethod = AuthServiceGrpc.getGetJwksMethod) == null) {
          AuthServiceGrpc.getGetJwksMethod = getGetJwksMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.GetJwksRequest, coresdk.v1.Auth.GetJwksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetJwks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.GetJwksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.GetJwksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("GetJwks"))
              .build();
        }
      }
    }
    return getGetJwksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.RevokeTokenRequest,
      coresdk.v1.Auth.RevokeTokenResponse> getRevokeTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeToken",
      requestType = coresdk.v1.Auth.RevokeTokenRequest.class,
      responseType = coresdk.v1.Auth.RevokeTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.RevokeTokenRequest,
      coresdk.v1.Auth.RevokeTokenResponse> getRevokeTokenMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.RevokeTokenRequest, coresdk.v1.Auth.RevokeTokenResponse> getRevokeTokenMethod;
    if ((getRevokeTokenMethod = AuthServiceGrpc.getRevokeTokenMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getRevokeTokenMethod = AuthServiceGrpc.getRevokeTokenMethod) == null) {
          AuthServiceGrpc.getRevokeTokenMethod = getRevokeTokenMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.RevokeTokenRequest, coresdk.v1.Auth.RevokeTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.RevokeTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.RevokeTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("RevokeToken"))
              .build();
        }
      }
    }
    return getRevokeTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.IsRevokedRequest,
      coresdk.v1.Auth.IsRevokedResponse> getIsRevokedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IsRevoked",
      requestType = coresdk.v1.Auth.IsRevokedRequest.class,
      responseType = coresdk.v1.Auth.IsRevokedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.IsRevokedRequest,
      coresdk.v1.Auth.IsRevokedResponse> getIsRevokedMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.IsRevokedRequest, coresdk.v1.Auth.IsRevokedResponse> getIsRevokedMethod;
    if ((getIsRevokedMethod = AuthServiceGrpc.getIsRevokedMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getIsRevokedMethod = AuthServiceGrpc.getIsRevokedMethod) == null) {
          AuthServiceGrpc.getIsRevokedMethod = getIsRevokedMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.IsRevokedRequest, coresdk.v1.Auth.IsRevokedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IsRevoked"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.IsRevokedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.IsRevokedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("IsRevoked"))
              .build();
        }
      }
    }
    return getIsRevokedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateSamlAssertionRequest,
      coresdk.v1.Auth.ValidateSamlAssertionResponse> getValidateSAMLAssertionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ValidateSAMLAssertion",
      requestType = coresdk.v1.Auth.ValidateSamlAssertionRequest.class,
      responseType = coresdk.v1.Auth.ValidateSamlAssertionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateSamlAssertionRequest,
      coresdk.v1.Auth.ValidateSamlAssertionResponse> getValidateSAMLAssertionMethod() {
    io.grpc.MethodDescriptor<coresdk.v1.Auth.ValidateSamlAssertionRequest, coresdk.v1.Auth.ValidateSamlAssertionResponse> getValidateSAMLAssertionMethod;
    if ((getValidateSAMLAssertionMethod = AuthServiceGrpc.getValidateSAMLAssertionMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getValidateSAMLAssertionMethod = AuthServiceGrpc.getValidateSAMLAssertionMethod) == null) {
          AuthServiceGrpc.getValidateSAMLAssertionMethod = getValidateSAMLAssertionMethod =
              io.grpc.MethodDescriptor.<coresdk.v1.Auth.ValidateSamlAssertionRequest, coresdk.v1.Auth.ValidateSamlAssertionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateSAMLAssertion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.ValidateSamlAssertionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  coresdk.v1.Auth.ValidateSamlAssertionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthServiceMethodDescriptorSupplier("ValidateSAMLAssertion"))
              .build();
        }
      }
    }
    return getValidateSAMLAssertionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @java.lang.Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static AuthServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingV2Stub>() {
        @java.lang.Override
        public AuthServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return AuthServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @java.lang.Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @java.lang.Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void validateToken(coresdk.v1.Auth.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateTokenMethod(), responseObserver);
    }

    /**
     */
    default void authorize(coresdk.v1.Auth.AuthorizeRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.AuthorizeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthorizeMethod(), responseObserver);
    }

    /**
     */
    default void getJwks(coresdk.v1.Auth.GetJwksRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.GetJwksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetJwksMethod(), responseObserver);
    }

    /**
     */
    default void revokeToken(coresdk.v1.Auth.RevokeTokenRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.RevokeTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRevokeTokenMethod(), responseObserver);
    }

    /**
     */
    default void isRevoked(coresdk.v1.Auth.IsRevokedRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.IsRevokedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIsRevokedMethod(), responseObserver);
    }

    /**
     */
    default void validateSAMLAssertion(coresdk.v1.Auth.ValidateSamlAssertionRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateSamlAssertionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateSAMLAssertionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthService.
   */
  public static abstract class AuthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void validateToken(coresdk.v1.Auth.ValidateTokenRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void authorize(coresdk.v1.Auth.AuthorizeRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.AuthorizeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuthorizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJwks(coresdk.v1.Auth.GetJwksRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.GetJwksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetJwksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void revokeToken(coresdk.v1.Auth.RevokeTokenRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.RevokeTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRevokeTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void isRevoked(coresdk.v1.Auth.IsRevokedRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.IsRevokedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIsRevokedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void validateSAMLAssertion(coresdk.v1.Auth.ValidateSamlAssertionRequest request,
        io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateSamlAssertionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getValidateSAMLAssertionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingV2Stub> {
    private AuthServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Auth.ValidateTokenResponse validateToken(coresdk.v1.Auth.ValidateTokenRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getValidateTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.AuthorizeResponse authorize(coresdk.v1.Auth.AuthorizeRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getAuthorizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.GetJwksResponse getJwks(coresdk.v1.Auth.GetJwksRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetJwksMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.RevokeTokenResponse revokeToken(coresdk.v1.Auth.RevokeTokenRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getRevokeTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.IsRevokedResponse isRevoked(coresdk.v1.Auth.IsRevokedRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getIsRevokedMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.ValidateSamlAssertionResponse validateSAMLAssertion(coresdk.v1.Auth.ValidateSamlAssertionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getValidateSAMLAssertionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service AuthService.
   */
  public static final class AuthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public coresdk.v1.Auth.ValidateTokenResponse validateToken(coresdk.v1.Auth.ValidateTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.AuthorizeResponse authorize(coresdk.v1.Auth.AuthorizeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuthorizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.GetJwksResponse getJwks(coresdk.v1.Auth.GetJwksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetJwksMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.RevokeTokenResponse revokeToken(coresdk.v1.Auth.RevokeTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRevokeTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.IsRevokedResponse isRevoked(coresdk.v1.Auth.IsRevokedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIsRevokedMethod(), getCallOptions(), request);
    }

    /**
     */
    public coresdk.v1.Auth.ValidateSamlAssertionResponse validateSAMLAssertion(coresdk.v1.Auth.ValidateSamlAssertionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getValidateSAMLAssertionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthService.
   */
  public static final class AuthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.ValidateTokenResponse> validateToken(
        coresdk.v1.Auth.ValidateTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.AuthorizeResponse> authorize(
        coresdk.v1.Auth.AuthorizeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuthorizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.GetJwksResponse> getJwks(
        coresdk.v1.Auth.GetJwksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetJwksMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.RevokeTokenResponse> revokeToken(
        coresdk.v1.Auth.RevokeTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRevokeTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.IsRevokedResponse> isRevoked(
        coresdk.v1.Auth.IsRevokedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIsRevokedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<coresdk.v1.Auth.ValidateSamlAssertionResponse> validateSAMLAssertion(
        coresdk.v1.Auth.ValidateSamlAssertionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getValidateSAMLAssertionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VALIDATE_TOKEN = 0;
  private static final int METHODID_AUTHORIZE = 1;
  private static final int METHODID_GET_JWKS = 2;
  private static final int METHODID_REVOKE_TOKEN = 3;
  private static final int METHODID_IS_REVOKED = 4;
  private static final int METHODID_VALIDATE_SAMLASSERTION = 5;

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
        case METHODID_VALIDATE_TOKEN:
          serviceImpl.validateToken((coresdk.v1.Auth.ValidateTokenRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateTokenResponse>) responseObserver);
          break;
        case METHODID_AUTHORIZE:
          serviceImpl.authorize((coresdk.v1.Auth.AuthorizeRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.AuthorizeResponse>) responseObserver);
          break;
        case METHODID_GET_JWKS:
          serviceImpl.getJwks((coresdk.v1.Auth.GetJwksRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.GetJwksResponse>) responseObserver);
          break;
        case METHODID_REVOKE_TOKEN:
          serviceImpl.revokeToken((coresdk.v1.Auth.RevokeTokenRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.RevokeTokenResponse>) responseObserver);
          break;
        case METHODID_IS_REVOKED:
          serviceImpl.isRevoked((coresdk.v1.Auth.IsRevokedRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.IsRevokedResponse>) responseObserver);
          break;
        case METHODID_VALIDATE_SAMLASSERTION:
          serviceImpl.validateSAMLAssertion((coresdk.v1.Auth.ValidateSamlAssertionRequest) request,
              (io.grpc.stub.StreamObserver<coresdk.v1.Auth.ValidateSamlAssertionResponse>) responseObserver);
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
          getValidateTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.ValidateTokenRequest,
              coresdk.v1.Auth.ValidateTokenResponse>(
                service, METHODID_VALIDATE_TOKEN)))
        .addMethod(
          getAuthorizeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.AuthorizeRequest,
              coresdk.v1.Auth.AuthorizeResponse>(
                service, METHODID_AUTHORIZE)))
        .addMethod(
          getGetJwksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.GetJwksRequest,
              coresdk.v1.Auth.GetJwksResponse>(
                service, METHODID_GET_JWKS)))
        .addMethod(
          getRevokeTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.RevokeTokenRequest,
              coresdk.v1.Auth.RevokeTokenResponse>(
                service, METHODID_REVOKE_TOKEN)))
        .addMethod(
          getIsRevokedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.IsRevokedRequest,
              coresdk.v1.Auth.IsRevokedResponse>(
                service, METHODID_IS_REVOKED)))
        .addMethod(
          getValidateSAMLAssertionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              coresdk.v1.Auth.ValidateSamlAssertionRequest,
              coresdk.v1.Auth.ValidateSamlAssertionResponse>(
                service, METHODID_VALIDATE_SAMLASSERTION)))
        .build();
  }

  private static abstract class AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return coresdk.v1.Auth.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthService");
    }
  }

  private static final class AuthServiceFileDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier {
    AuthServiceFileDescriptorSupplier() {}
  }

  private static final class AuthServiceMethodDescriptorSupplier
      extends AuthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthServiceFileDescriptorSupplier())
              .addMethod(getValidateTokenMethod())
              .addMethod(getAuthorizeMethod())
              .addMethod(getGetJwksMethod())
              .addMethod(getRevokeTokenMethod())
              .addMethod(getIsRevokedMethod())
              .addMethod(getValidateSAMLAssertionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
