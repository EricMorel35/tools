package com.orange.batchsi;

import java.io.File;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;

/**
 * Hello world!
 * 
 */
public class AppTag {

    public static void main(String[] args) {
        SVNClientManager manager = SVNClientManager.newInstance();
        SVNURL url = null;
        final SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
        try {
            url = SVNURL
                    .parseURIEncoded("https://www.forge.orange-labs.fr/svnroot/IS-Batch-ESC/ABDNTV/trunk");
            svnOperationFactory.setAuthenticationManager(new BasicAuthenticationManager("blns9494",
                    "p19WI125!"));

            // final SVNLogClient log =
            // SVNRepository repo = manager.createRepository(url, false);
            /*
             * log.setSingleTarget(SvnTarget.fromURL(url)); log.setReceiver(new
             * ISvnObjectReceiver<SVNLogEntry>() {
             * 
             * public void receive(SvnTarget target, SVNLogEntry object) throws
             * SVNException { // TODO Auto-generated method stub
             * System.out.println(object.getRevision()); } });
             */
            // log.do
            // SVNCopySource copySource = new SVNCopySource(SVNRevision.HEAD,
            // SVNRevision.HEAD, path);
            // manager.getCopyClient().doCopy(sources, dst, isMove, makeParents,
            // failWhenDstExists);
            AppTag app = new AppTag();
            app.getLog(manager);
        } catch (SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            svnOperationFactory.dispose();
        }
    }

    private void getLog(SVNClientManager manager) {
        SVNLogClient client = manager.getLogClient();
        String path = "C:\\Users\\blns9494\\Documents\\projets\\livraison_SIM_cft";
        SVNRevision pegRevision = SVNRevision.create(0l);
        SVNRevision startRevision = SVNRevision.create(0l);
        SVNRevision endRevision = SVNRevision.HEAD;

        ISVNLogEntryHandler handler = new ISVNLogEntryHandler() {
            public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
                System.out.print("Author: " + logEntry.getAuthor());
                System.out.print(" Date: " + logEntry.getDate());
                System.out.print(" Message: " + logEntry.getMessage());
                System.out.println(" Revision: " + logEntry.getRevision());
            }
        };

        try {
            client.doLog(new File[] { new File(path) }, startRevision, endRevision, pegRevision,
                    false, true, true, 0, null, handler);
        } catch (SVNException e) {
            e.printStackTrace();
        }
    }
}
