package com.tyrael.laundry.reports.extraction;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * The sole purpose of this class is to call the main kettle job. All
 * actual extraction is handled in kettle jobs & transformations.
 * @author mbmartinez
 */
public class ExtractionService {

    private static final Logger LOG = LoggerFactory.getLogger(ExtractionService.class);

    @Autowired
    private Environment env;

    public void runKettle() {
        try {
            System.setProperty("KETTLE_JNDI_ROOT", "./reports/extraction/jndi");
            System.setProperty("erx.reports.kettle.root", "./reports/extraction");
            System.setProperty("default.effectiveDateTime.MMddyyyyHHmmss", "01011900000000");
            System.setProperty("default.effectiveDateTime.dbmask", "MMddyyyyHH24MIss");

            KettleEnvironment.init();

            Repository repository = new KettleFileRepository();
            KettleFileRepositoryMeta repositoryMeta = new KettleFileRepositoryMeta("id", "name", "desc", "./reports/extraction");
            repository.init(repositoryMeta);
            JobMeta jobMeta = null;

            jobMeta = new JobMeta("reports/extraction/main.kjb", repository);

            org.pentaho.di.job.Job job = new org.pentaho.di.job.Job(repository, jobMeta);
            String[] arg = new String[2];
            arg[0] = "kahit ano";
            arg[1] = "kahit ano";
            job.setArguments(arg);
            job.start();
            job.waitUntilFinished();
            org.pentaho.di.core.Result result = job.getResult();
        } catch (KettleException e) {
            LOG.error("Error in running kettle", e);
        }
    }

}
