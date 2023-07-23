package rs.gov.mduls.einicijative.nipub.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import rs.gov.mduls.einicijative.nipub.batch.tasklets.*;
import rs.gov.mduls.einicijative.nipub.util.Consts;

@Configuration
public class BatchKonfiguracija {

    @Value("${nipub.dir.zajednicko}")
    private String dirZajednicko;

    @Value("${nipub.dir.inicijative}")
    private String dirInicijative;

    @Bean
    public Step listaAktivnihInicijativa(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaAktivnihInicijativa", jobRepository)
                .tasklet(listaAktivnihInicijativaTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaFazaObrade(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaFazaObrade", jobRepository)
                .tasklet(listaFazaObradeTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaNeaktivnihInicijativa(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaNeaktivnihInicijativa", jobRepository)
                .tasklet(listaNeaktivnihInicijativaTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaNivoaVlasti(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaNivoaVlasti", jobRepository)
                .tasklet(listaNivoaVlastiTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaOpstina(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaOpstina", jobRepository)
                .tasklet(listaOpstinaTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaTipovaInicijative(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaTipovaInicijative", jobRepository)
                .tasklet(listaTipovaInicijativeTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step listaUpravnihOkruga(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("listaUpravnihOkruga", jobRepository)
                .tasklet(listaUpravnihOkrugaTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Step skoroPromenjeneInicijative(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("skoroPromenjeneInicijative", jobRepository)
                .tasklet(skoroPromenjeneInicijativeTasklet(), transactionManager)
                .build();
    }

    @Bean
    public ListaAktivnihInicijativaTasklet listaAktivnihInicijativaTasklet() {
        ListaAktivnihInicijativaTasklet tasklet = new ListaAktivnihInicijativaTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_AKTIVNIH_INICIJATIVA);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_AKTIVNIH_INICIJATIVA);
        return tasklet;
    }

    @Bean
    public ListaFazaObradeTasklet listaFazaObradeTasklet() {
        ListaFazaObradeTasklet tasklet = new ListaFazaObradeTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_FAZE_OBRADE);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_FAZE_OBRADE);
        return tasklet;
    }

    @Bean
    public ListaNeaktivnihInicijativaTasklet listaNeaktivnihInicijativaTasklet() {
        ListaNeaktivnihInicijativaTasklet tasklet = new ListaNeaktivnihInicijativaTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_NEAKTIVNIH_INICIJATIVA);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_NEAKTIVNIH_INICIJATIVA);
        return tasklet;
    }

    @Bean
    public ListaNivoaVlastiTasklet listaNivoaVlastiTasklet() {
        ListaNivoaVlastiTasklet tasklet = new ListaNivoaVlastiTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_NIVOI_VLASTI);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_NIVOI_VLASTI);
        return tasklet;
    }

    @Bean
    public ListaOpstinaTasklet listaOpstinaTasklet() {
        ListaOpstinaTasklet tasklet = new ListaOpstinaTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_OPSTINE);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_OPSTINE);
        return tasklet;
    }

    @Bean
    public ListaTipovaInicijativeTasklet listaTipovaInicijativeTasklet() {
        ListaTipovaInicijativeTasklet tasklet = new ListaTipovaInicijativeTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_TIPOVI_INICIJATIVA);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_TIPOVI_INICIJATIVA);
        return tasklet;
    }

    @Bean
    public ListaUpravnihOkrugaTasklet listaUpravnihOkrugaTasklet() {
        ListaUpravnihOkrugaTasklet tasklet = new ListaUpravnihOkrugaTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_UPRAVNI_OKRUZI);
        tasklet.setDirDat(dirZajednicko);
        tasklet.setImeDat(Consts.IME_DATOTEKE_UPRAVNI_OKRUZI);
        return tasklet;
    }

    @Bean
    public SkoroPromenjeneInicijativeTasklet skoroPromenjeneInicijativeTasklet() {
        SkoroPromenjeneInicijativeTasklet tasklet = new SkoroPromenjeneInicijativeTasklet();
        tasklet.setSemaFunc(Consts.SEMA_SPROC);
        tasklet.setImeFunc(Consts.IME_SPROC_INICIJATIVE);
        tasklet.setDirDat(dirInicijative);
        tasklet.setModelImenaDat(Consts.MODEL_IMENA_DATOTEKE_INICIJATIVE);
        return tasklet;
    }

    @Bean
    public Job satnaObjavaJob(
            JobRepository jobRepository,
            Step listaNivoaVlasti,
            Step listaFazaObrade,
            Step listaTipovaInicijative,
            Step listaUpravnihOkruga,
            Step listaOpstina,
            Step listaNeaktivnihInicijativa
    ) {
        return new JobBuilder("satnaObjavaJob", jobRepository)
                .start(listaNivoaVlasti)
                .next(listaFazaObrade)
                .next(listaTipovaInicijative)
                .next(listaUpravnihOkruga)
                .next(listaOpstina)
                .next(listaNeaktivnihInicijativa)
                .build();
    }

    @Bean
    public Job minutnaObjavaJob(
            JobRepository jobRepository,
            Step listaAktivnihInicijativa,
            Step skoroPromenjeneInicijative
    ) {
        return new JobBuilder("minutnaObjavaJob", jobRepository)
                .start(listaAktivnihInicijativa)
                .next(skoroPromenjeneInicijative)
                .build();
    }

}
