package io.coresdk;

public class CoreSDKException extends RuntimeException {
    private final ProblemDetail problem;

    public CoreSDKException(ProblemDetail problem) {
        super(problem.getTitle());
        this.problem = problem;
    }

    public CoreSDKException(ProblemDetail problem, Throwable cause) {
        super(problem.getTitle(), cause);
        this.problem = problem;
    }

    public ProblemDetail getProblem() { return problem; }
    public int getStatus() { return problem.getStatus(); }
}
