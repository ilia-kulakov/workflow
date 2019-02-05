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
@Property(name="process.label", value="EPAM Training Workflow Step 2")
public class TrainingWorkflowProcessStep2 implements WorkflowProcess {

    private static Logger log =
            LoggerFactory.getLogger(TrainingWorkflowProcessStep2.class);

    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        String message = String.valueOf(workItem.getWorkflowData().getMetaDataMap().get("interstep.message"));
        log.info("Training process executed: step 2");
        log.info("Message: " + message);
    }
}
