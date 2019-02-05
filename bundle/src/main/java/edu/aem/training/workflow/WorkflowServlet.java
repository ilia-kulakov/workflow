package edu.aem.training.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.Workflow;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.jcr.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SlingServlet (
    resourceTypes = {"sling/servlet/default"},
    methods = {"GET"},
    extensions = {"abc"}
)
public class WorkflowServlet extends SlingSafeMethodsServlet {

    @Reference
    WorkflowService workflowService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
        throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();

        Workflow wf =  null;
        try {
            Session session = request.getResourceResolver().adaptTo(Session.class);
            // Create a workflow session
            WorkflowSession wfSession = workflowService.getWorkflowSession(session);
            WorkflowModel wfModel = wfSession.getModel("/etc/workflow/models/aem-training-participation/jcr:content/model");

            String resourcePath = request.getRequestPathInfo().getResourcePath();
            // Get the workflow data
            // The first param in the newWorkflowData method is the payloadType
            WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", resourcePath);

            // Run the Workflow
            wf = wfSession.startWorkflow(wfModel, wfData);
        } catch (WorkflowException e) {
            throw new ServletException(e);
        }

        writer.write("<p>Workflow launched: " + wf.getId() + "</p>");
        writer.flush();
        writer.close();
    }
}
