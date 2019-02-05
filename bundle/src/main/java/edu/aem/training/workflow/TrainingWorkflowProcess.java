package edu.aem.training.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
@Service(WorkflowProcess.class)
@Property(name="process.label", value="EPAM Training Workflow")
public class TrainingWorkflowProcess implements WorkflowProcess {

    private static Logger log =
            LoggerFactory.getLogger(TrainingWorkflowProcess.class);

    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        log.info("Training process executed: called from the Java code step 1");
        log.info("Payload: " + workItem.getWorkflowData().getPayload());
        workItem.getWorkflowData().getMetaDataMap().put("interstep.message", "Hello from step 1");
    }
}
