package rs.gov.mduls.einicijative.nipub.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PlanerPoslova
{
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job satnaObjavaJob;
    @Autowired
    Job minutnaObjavaJob;

    @Scheduled(cron = "${nipub.minutna-paketna.cron}")
    public void radiMinutniCiklus() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(minutnaObjavaJob, params);
    }

    @Scheduled(cron = "${nipub.satna-paketna.cron}")
    public void radiSatniCiklus() throws Exception
    {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(satnaObjavaJob, params);
    }


}