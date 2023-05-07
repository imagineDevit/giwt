package io.github.imaginedevit.testIt;

import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.ExecutionRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;
import org.opentest4j.AssertionFailedError;

import java.util.logging.Logger;

public class TestItExecutor {

    private final Logger logger = Logger.getLogger(TestItExecutor.class.getName());

    public void execute(ExecutionRequest request, TestDescriptor root) {
        if (root instanceof EngineDescriptor) executeContainer(request, root);
        if (root instanceof TestItClassTestDescriptor) executeContainer(request, root);
        if (root instanceof TestItMethodTestDescriptor) executeTest(request, (TestItMethodTestDescriptor) root);
    }

    private void executeTest(ExecutionRequest request, TestItMethodTestDescriptor root) {

        EngineExecutionListener listener = request.getEngineExecutionListener();

        listener.executionStarted(root);

        try {

            TestCase<?, ?> testCase = root.getTestCase();

            Object instance = ReflectionUtils.newInstance(root.getTestClass());

            ReflectionUtils.invokeMethod(root.getTestMethod(), instance, testCase);

            testCase.run();

            System.out.println(TestCase.Result.SUCCESS.message(null));

            listener.executionFinished(root, TestExecutionResult.successful());

        } catch (Exception e) {

            if (e.getCause() instanceof AssertionFailedError afe) {
                System.out.println(TestCase.Result.FAILURE.message(afe.getMessage()));
            } else if (e.getCause() != null){
                System.out.println(TestCase.Result.FAILURE.message(e.getCause().getMessage()));
            } else {
                System.out.println(TestCase.Result.FAILURE.message(e.getMessage()));
            }

            listener.executionFinished(root, TestExecutionResult.failed(e));
        }

    }

    private void executeContainer(ExecutionRequest request, TestDescriptor root) {
        EngineExecutionListener listener = request.getEngineExecutionListener();

        listener.executionStarted(root);

        root.getChildren().forEach(child ->{
            execute(request, child);
        } );

        listener.executionFinished(root, TestExecutionResult.successful());
    }
}
