package io.coresdk.proto;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.ClientCalls;

/**
 * Hand-written gRPC stub for coresdk.v1.AuthService.
 *
 * Uses a raw byte[] marshaller so no protoc / buf codegen is required at build time.
 * Replace with buf-generated code when protoc is available in CI (Phase 2 GA).
 *
 * Wire format: JSON-encoded request/response bytes framed over HTTP/2 by gRPC.
 * The sidecar's AuthService.ValidateToken method accepts and returns JSON payloads
 * matching the schemas documented in docs/ARCHITECTURE.md §gRPC API.
 */
public final class AuthServiceGrpc {

    private AuthServiceGrpc() {}

    public static final String SERVICE_NAME = "coresdk.v1.AuthService";

    private static final MethodDescriptor<byte[], byte[]> METHOD_VALIDATE_TOKEN =
        MethodDescriptor.<byte[], byte[]>newBuilder()
            .setType(MethodDescriptor.MethodType.UNARY)
            .setFullMethodName(SERVICE_NAME + "/ValidateToken")
            .setRequestMarshaller(bytesMarshaller())
            .setResponseMarshaller(bytesMarshaller())
            .build();

    private static final MethodDescriptor<byte[], byte[]> METHOD_AUTHORIZE =
        MethodDescriptor.<byte[], byte[]>newBuilder()
            .setType(MethodDescriptor.MethodType.UNARY)
            .setFullMethodName(SERVICE_NAME + "/Authorize")
            .setRequestMarshaller(bytesMarshaller())
            .setResponseMarshaller(bytesMarshaller())
            .build();

    public static final String POLICY_SERVICE_NAME = "coresdk.v1.PolicyService";

    private static final MethodDescriptor<byte[], byte[]> METHOD_EVALUATE_POLICY =
        MethodDescriptor.<byte[], byte[]>newBuilder()
            .setType(MethodDescriptor.MethodType.UNARY)
            .setFullMethodName(POLICY_SERVICE_NAME + "/Evaluate")
            .setRequestMarshaller(bytesMarshaller())
            .setResponseMarshaller(bytesMarshaller())
            .build();

    private static MethodDescriptor.Marshaller<byte[]> bytesMarshaller() {
        return new MethodDescriptor.Marshaller<byte[]>() {
            @Override
            public java.io.InputStream stream(byte[] value) {
                return new java.io.ByteArrayInputStream(value);
            }

            @Override
            public byte[] parse(java.io.InputStream stream) {
                try {
                    return stream.readAllBytes();
                } catch (java.io.IOException e) {
                    throw new RuntimeException("Failed to read gRPC response bytes", e);
                }
            }
        };
    }

    public static BlockingStub newBlockingStub(Channel channel) {
        return new BlockingStub(channel);
    }

    public static final class BlockingStub extends AbstractBlockingStub<BlockingStub> {

        private BlockingStub(Channel channel) {
            super(channel, CallOptions.DEFAULT);
        }

        @Override
        protected BlockingStub build(Channel channel, CallOptions callOptions) {
            return new BlockingStub(channel);
        }

        /**
         * Calls coresdk.v1.AuthService/ValidateToken with raw JSON bytes.
         *
         * @param request UTF-8 JSON bytes of a ValidateTokenRequest object
         * @return UTF-8 JSON bytes of a ValidateTokenResponse object
         */
        public byte[] validateToken(byte[] request) {
            return ClientCalls.blockingUnaryCall(
                getChannel(), METHOD_VALIDATE_TOKEN, getCallOptions(), request);
        }

        /**
         * Calls coresdk.v1.AuthService/Authorize with raw JSON bytes.
         *
         * @param request UTF-8 JSON bytes of an AuthorizeRequest object
         * @return UTF-8 JSON bytes of an AuthorizeResponse object
         */
        public byte[] authorize(byte[] request) {
            return ClientCalls.blockingUnaryCall(
                getChannel(), METHOD_AUTHORIZE, getCallOptions(), request);
        }

        /**
         * Calls coresdk.v1.PolicyService/Evaluate with raw JSON bytes.
         *
         * @param request UTF-8 JSON bytes of a PolicyEvaluateRequest object
         * @return UTF-8 JSON bytes of a PolicyEvaluateResponse object
         */
        public byte[] evaluatePolicy(byte[] request) {
            return ClientCalls.blockingUnaryCall(
                getChannel(), METHOD_EVALUATE_POLICY, getCallOptions(), request);
        }
    }
}
