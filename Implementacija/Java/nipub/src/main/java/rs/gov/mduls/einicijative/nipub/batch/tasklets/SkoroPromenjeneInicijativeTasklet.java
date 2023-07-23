package rs.gov.mduls.einicijative.nipub.batch.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import rs.gov.mduls.einicijative.nipub.database.ObjaviSkoroPromenjeneInicijative;

public class SkoroPromenjeneInicijativeTasklet implements Tasklet, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SkoroPromenjeneInicijativeTasklet.class);

    @Autowired
    ObjaviSkoroPromenjeneInicijative procesor;
    private String semaFunc;
    private String imeFunc;
    private String dirDat;
    private String modelImenaDat;

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        logger.info("Објава скоро промењене иницијативе...");
        procesor.obrada(semaFunc, imeFunc, dirDat, modelImenaDat);
        return RepeatStatus.FINISHED;
    }

    public void setSemaFunc(String semaFunc) {
        this.semaFunc = semaFunc;
    }

    public void setImeFunc(String imeFunc) {
        this.imeFunc = imeFunc;
    }

    public void setDirDat(String dirDat) { this.dirDat = dirDat; }

    public void setModelImenaDat(String modelImenaDat) { this.modelImenaDat = modelImenaDat; }

    public void afterPropertiesSet() throws Exception {
        Assert.state(semaFunc != null, "Шема података недостаје");
        Assert.state(imeFunc != null, "Име функције недостаје");
        Assert.state(dirDat != null, "Каталог датотеке недостаје");
        Assert.state(modelImenaDat != null, "Модел имена датотеке недостаје");
    }
}
